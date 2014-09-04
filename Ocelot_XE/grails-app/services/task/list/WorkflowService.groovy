package task.list

import grails.transaction.Transactional

import grails.util.GrailsNameUtils
import org.camunda.bpm.engine.form.FormField
import org.camunda.bpm.engine.form.StartFormData
import org.camunda.bpm.engine.runtime.ProcessInstance
import org.camunda.bpm.engine.task.Task
import org.camunda.bpm.engine.repository.ProcessDefinition

class WorkflowService {

    def grailsApplication

    def runtimeService
    def taskService
    def identityService
    def formService
    def repositoryService
    def historyService


    static getAllProtectedNames() {
        [
                'ownerUnit',
                'user',
                '_tasks'
        ]
    }

    def getProcessDefinition(String deploymentId) {
        repositoryService.createProcessDefinitionQuery()
                .deploymentId(deploymentId)
                .singleResult();
    }

    def deployProcess(fileContent, fileName) {
/*        def r = new File("grails-app/processes/my/test/SimpleProcess2.xml")
        String xml = "";
        r.eachLine {
            xml += it
        }*/

        def d = repositoryService.createDeployment()
                .name(fileName.toString())
                .addString(fileName + ".bpmn20.xml", fileContent)
                .deploy()

    }

    def startProcess(String taskId, String taskName, Map vars = [:]) {
        //log.info "STARTING PROCESS $p: ${vars.grep({it.key!='__files__'})}"

        ProcessInstance pi = runtimeService.startProcessInstanceById(taskId, taskName, vars)
        //println "STARTED PROCESS $p: ${vars.grep({it.key!='__files__'})}: $pi"
        pi
    }

    def startProcess(String deploymentId) {
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().deploymentId(deploymentId).singleResult()
        println processDefinition
        ProcessInstance pi = runtimeService.startProcessInstanceById(processDefinition.getId())
        pi
    }

    def deploymentList() {
        def processDefinitions = repositoryService.createProcessDefinitionQuery().active().list()
        processDefinitions
//        def data = [:]
//        processDefinitions.each { ProcessDefinition p ->
//            data."$p.id" = [id: p.deploymentId, name: p.name, time: p.version]}
//        def deployments = repositoryService.createDeploymentQuery().listPage(1, 10)
//
//        if (deployments.size() > 0) {
//            for (Deployment d : deployments) {
//                data[ d.getId()] = ['id': d.getId(), 'name':d.getName(), 'deployment time': d.getDeploymentTime().toString()]
//
//            }
//        }
         //   data

        }
        def getDeploymentById(String id) {
            def deployment = repositoryService.createDeploymentQuery().deploymentId(id).singleResult()
            deployment

        }

        /**
         * Every time you want to save variables should run this method, making maintenance to the variables so that everything is consistent
         *
         * @param taskid
         * @param vars
         * @return
         * */

        def saveTask(taskid, vars, complete = false) {

            def existingVars = taskService.getVariables(taskid)

            println "SAVE TASK $taskid: complete=$complete: $vars"
            println "SAVE TASK $taskid: complete=$complete: $vars"

            removeFromArrays(taskid, existingVars, vars)
            if (complete)
                taskService.complete(taskid, vars)
            else
                taskService.setVariables(taskid, vars)
        }

        /**
         * Multivalue variables are stored as single variables with names: varid, varid-1, varid-2, ..., varid-n
         * When the newly assigned multivalue variable is assigned, some existing indices must be removed when the new size is smaller than the existing one.
         * Every existing var has to be checked. If the var base name is being assigned but the existing index is not in the assigned one, must be removed.
         *
         * @param taskid ID of task
         * @param existingVars Existing vars in process
         * @params assignedVars    Vars that have recently been assigned
         * @return Nothing
         */
        def removeFromArrays(taskid, Map existingVars = [:], Map assignedVars = [:]) {
            def toremove = []
            existingVars.each { k, v ->
                def kbase = k.replaceAll(/\-\d+$/, "")
                if (k.contains('-') &&
                        assignedVars.containsKey(kbase) &&
                        !assignedVars.containsKey(k)) {
                    // array component in assigned vars. check if array is reassigned and if index exists
                    toremove.add(k)
                }
            }
            taskService.removeVariables(taskid, toremove)
        }


        def getVars(taskId, user = null, vars = [:], formdata = [:], taskdata = [:]) {

            def tasksdata = [:]

            if (!taskId) { // start task
                taskId = '_start'
            } else {
                tasksdata = taskService.getVariable(taskId, '_tasks')
                //println tasksdata
            }

            if (!formdata) {
                formdata = getFormData(taskId)
            } else if (formdata instanceof Task) {
                formdata = getFormData(formdata)
            }
            if (!formdata)
                throw new Exception("No form data found.")

            def fd = [:]
            //println vars
            formdata.formProperties.each {
                fd[it.id] = ['name': it.id, 'type': it?.type?.name ?: 'string']
                if (!it.writable) { // assign default value
                    fd[it.id] = it?.value
                }
                if (it.name) {
                    fd[it.id]['name'] = it.name
                }
                def values = it?.type?.getInformation('values') ?: [:]
                def value = vars?."$it.id" ?: null
                if (values && value != null && values.containsKey(value)) {
                    fd[it.id]['label'] = values[value]
                }
                if (vars.__extra__?."$it.id"?.label) {
                    fd[it.id]['label'] = vars.__extra__?."$it.id"?.label
                }
                if (fd[it.id]?.type == 'date' && it?.type?.datePattern) {
                    fd[it.id]['pattern'] = it.type.datePattern
                }
            }
            def r = ['_formdata': fd]
            // it contains the configuration of form fields of the tasks that have already been visited
            if (user)
                r['_user'] = user
            if (taskdata)
                r['_taskdata'] = taskdata
            //		def aux = ['_tasks':[:]]
            //		aux['_tasks'][taskId] = r
            vars['_tasks'] = tasksdata
            vars['_tasks'][taskId] = r
            vars.remove('__extra__') // extra information of values provided. filled at getFormData
            vars
        }

        /**
         * Get data to show.
         *
         * @param processInstance The processInstance to get data from
         * @param hist Historic info
         * @return All values ​​of the variables in the different tasks
         */
        def getData(processInstance, hist = [:]) {

            if (!hist)
                hist = getActivities(processInstance.id).collectEntries({ [it.id, it] })

            //		println "HISTORIC: ${hist*.value.activityName}"
            def data = [:]

            def histvars = historyService.createHistoricDetailQuery().variableUpdates().processInstanceId(processInstance.id).list()
            //		println "HISTORICS VARIABLES: $histvars"
            histvars = histvars.groupBy({ it.activityInstanceId })

            //		println hist
            //		println "HISTORICS VARIABLES: $histvars"
            def pv = processInstance.processVariables

            hist.each { acid, h ->
                def did = h.activityType == 'startEvent' ? '_start' : h.taskId
                data[did] = [:]
                try {
                    def groupedhv = histvars[h.id]?.groupBy({ it.name }) ?: [:] // full hist
                    def hv = groupedhv?.collectEntries({ k, v -> [k, v[-1]] }) ?: [:]
// only one value per variable and task
                    //				println "HISTORICS (TOTS): $h.activityName --> " + hv?._tasks?.value
                    //				println "HISTORICS: $h.activityName --> " + hv?._tasks?.value?."$did"
                    data[did]['_values'] = getTaskData(hv, hv?._tasks?.value?."$did"?.'_formdata')
                    data[did]['_user'] = hv?._tasks?.value?."$did"?._user
                    data[did]['_historic'] = groupedhv
                } catch (e) {
                    log.error e.message, e
                }
            }
            println "DATA: $data"
            data
        }

        /**
         * Get data corresponding to a task
         *
         * @param values Raw values
         * @param definition Values types and definitions
         * @return Map with values and metadata
         */
        def getTaskData(values, definition) {
            def data = [:]
            println "VALUES: $values"
            //		println "DEFINITION: $definition"
            definition.each { k, v ->
                if (values?.containsKey(k)) {
                    data[v.name] = [value: values[k].value, time: values[k].time]
                } else {
                    data[v.name] = [value: null]
                }
                if (v.label) {
                    data[v.name].label = v.label
                }
                if (v.type == 'file') { // special type
                    data[v.name] = [type: 'file', id: data[v.name].value, label: v.label, time: data[v.name].time]
                } else if (v.label && v.label == data[v.name]) {
                    // enum post process and other types that do have a label
                    data[v.name].label = v.label
                }
                if (v.type == 'date' && v.pattern) {
                    data[v.name].pattern = v.pattern
                }
            }
            data
        }

        def assignCurrentTask(String taskId, String user) {
            // assign task to current user
            def task = getTask(taskId, "taskCandidateUser", user)
            claimTask(task.id, user)
        }

        def getProcessDefinitionByProcessDefinitionId(String processDefinitionId) {
            println processDefinitionId
            ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().processDefinitionId(processDefinitionId).singleResult()
            println processDefinition
            processDefinition
        }

        def getStartFormData(String processDefinitionId) {
            def startFormData  = formService.getStartFormData(processDefinitionId)
            /*  Updated with Camunda 7.1.0-Final
                FormProperty class is deprecated -> Updated to Form Field */
            List<FormField> formFields = startFormData.getFormFields()
            formFields // codi HTML a sac (injectar-ho a una vista! )
        }

        def getFormData(Task task) {
            getFormData(task.id)
        }

        def getFormData(String taskId) {
            formService.getTaskFormData(taskId)
        }

        // Values from the process variables
        def getTaskLocalValues(taskId, values) {
            return values?._tasks?."$taskId"?._taskdata ?: [:]
        }

        // Values from the process variables
        def getFormValuesExtra(taskId, values) {
            def extra = [:]
            values?._tasks?.each { tid, task ->
                task._formdata.each { fid, field ->
                    extra[fid] = field
                }
            }
            return extra
        }

        // Values via getFormData
        def getFormValuesExtra(values) {
            println "EXTRA VALUES: $values"
            return values?.__extra__ ?: [:]
        }

        private findTasks(String methodName, String username, int firstResult, int maxResults, Map orderBy) {
            def taskQuery = taskService.createTaskQuery()
            if (methodName) {
                taskQuery."${methodName}"(username)
            }

            if (orderBy) {
                orderBy.each { k, v ->
                    taskQuery."orderByTask${GrailsNameUtils.getClassNameRepresentation(k)}"()."${v}"()
                }
            }
            taskQuery.listPage(firstResult, maxResults)
        }

        def Long getTasksCount(String methodName, String username) {
            def taskQuery = taskService.createTaskQuery()
            if (methodName) {
                taskQuery."${methodName}"(username)
            }
            taskQuery.count()
        }

        def getTasks(String method, Map params) {
            def orderBy = grailsApplication.config.activiti.assignedTasksOrderBy ?: [:]
            if (params.sort) {
                orderBy << ["${params.sort}": params.order]
            }
            orderBy['priority'] = 'desc'
            //println "$method $params"
            findTasks(method, params.user, getOffset(params.offset), params.max, orderBy)
        }

        private getOffset(def offset) {
            return offset ? Integer.parseInt(offset) : 0
        }

        def getProcessInstances(filters, params) {
            def q = historyService.createHistoricProcessInstanceQuery()
            q.listPage(params.offset ?: 0, params.max)
        }

        def getProcessInstance(id) {
            def q = runtimeService.createProcessInstanceQuery().processInstanceId(id)
            def pi = q.singleResult()
            if (!pi) {
                q = historyService.createHistoricProcessInstanceQuery().processInstanceId(id)
                pi = q.singleResult()
            }
            //println pi.processProperties
            pi

        }
        def getNumInstances(ProcessDefinition processDefinition) {
            historyService.createHistoricProcessInstanceQuery().processDefinitionId(processDefinition.getId()).count();
        }
        def getNumInstancesList(List<ProcessDefinition> processDefinitionList){

        }


        def getPITasks(processInstanceId) {
            historyService.createHistoricTaskInstanceQuery().processInstanceId(processInstanceId).orderByHistoricActivityInstanceStartTime().asc().list()
        }

        def getPIOpenTasks(processInstanceId) {
            taskService.createTaskQuery().processInstanceId(processInstanceId).orderByTaskCreateTime().asc().list()

        }

        def getPIOpenTasks(List processInstanceIds) {
            processInstanceIds.collectEntries({ [it.id, getPIOpenTasks(it.id)] })
        }

        def getActiveActivities(List processInstanceIds) {
            processInstanceIds.collectEntries({
                def exs = runtimeService.createExecutionQuery().processInstanceId(it.activitiId).list()
                def acts = exs.collect({
                    historyService.createHistoricActivityInstanceQuery().executionId(it.id).unfinished().list()
                }).flatten().unique({ it.id })
                [it.id, acts]
            })
        }

        def getActivities(String processInstanceId, params = [:]) {
            def q = historyService.createHistoricActivityInstanceQuery().processInstanceId(processInstanceId)
            if (params.finished != null) {
                if (params.finished)
                    q = q.finished()
                else
                    q = q.unfinished()
            }
            if (params.order) {
                q = q.orderBy "$params.sort.capitalize()"()
                q = params.order ? q."$params.order"() : q.asc() // it's a must when using orderBy
            }
            //		else {
            //			q = q.orderByHistoricActivityInstanceStartTime()
            //			q = params.order ? q."$params.order"() : q.asc() // it's a must when using orderBy
            //		}
            def l = q.list()

            if (!params.sort)
                l = l.sort { a, b ->
                    a.startTime != b.startTime && (a.activityType != 'gateway' || b.activityType != 'gateway') ?
                            a.startTime <=> b.startTime :
                            a.activityType == 'gateway' ? -1 : 1
                }
            l
        }

        def getTask(String processInstanceId, String methodName = null, String username = '') {
            def q = taskService.createTaskQuery().processInstanceId(processInstanceId)
            if (methodName)
                q = q."${methodName}"(username)
            q.singleResult()
        }

        def getTaskById(String taskId) {
            taskService.createTaskQuery().taskId(taskId).singleResult()
        }

        def claimTask(String taskId, String username) {
            taskService.claim(taskId, username)
        }

        def completeTask(String taskId, Map params) {
            taskService.setVariablesLocal(taskId, params)
            taskService.complete(taskId, params)
        }


        def getTotalProcessInfo(processInstanceId) {

            def tasks = getPITasks(processInstanceId).collectEntries({ [it.id, it] })

            def activities = getActivities(processInstanceId).collectEntries({ [it.id, it] })

            def fullHist = (activities*.value.collectEntries({ [it.taskId ?: it.id, it] }) + tasks).values().sort {
                it.startTime
            }

            def fullHistNoSubProcesses = fullHist.grep {
                it.hasProperty('taskDefinitionKey') || it.activityType != 'subProcess'
                // is task or not is subprocess === is not subprocess
            }

            def processInstance = getProcessInstance(processInstanceId)
            println "PROCESS INSTANCE: $processInstance"
            println "HISTORIC ACTIVITIES: ${activities*.value*.activityId}"
            println "HISTORIC TASKS: ${tasks*.value*.name}"

            def totals = [
                    startTime                 : fullHist ? fullHist[0].startTime : null,
                    endTime                   : fullHist ? fullHist[-1].endTime : null,
                    cumulativeDurationInMillis: fullHistNoSubProcesses*.durationInMillis?.grep({ it }).sum() ?: 0,
                    durationInMillis          : fullHist ? (fullHist[-1].endTime ?: new Date()).time - fullHist[0].startTime.time : null
            ]

            [historic: fullHist, totals: totals, activities: activities, tasks: tasks]
        }

        def setAssignee(String taskId, String username) {
            taskService.setAssignee(taskId, username)
        }

        def setPriority(String taskId, int priority) {
            taskService.setPriority(taskId, priority)
        }

        def getCandidateUserIds(String taskId) {
            def identityLinks = getIdentityLinksForTask(taskId)
            def userIds = []
            def users
            identityLinks.each { identityLink ->
                if (identityLink.groupId) {
                    users = identityService.createUserQuery()
                            .memberOfGroup(identityLink.groupId)
                            .orderByUserId().asc().list()

                    userIds << users?.collect { it.id }
                } else {
                    userIds << identityLink.userId
                }
            }
            return userIds.flatten().unique()
        }

        def getIdentityLinksForTask(String taskId) {
            taskService.getIdentityLinksForTask(taskId)
        }

        def syncUser(userId, groupIds) {

            def user = identityService.createUserQuery().userId(userId).list()
            if (!user) identityService.saveUser(identityService.newUser(userId))

            groupIds.each { gid ->
                def group = identityService.createGroupQuery().groupId(gid).list()
                if (!group) identityService.saveGroup(identityService.newGroup(gid))
            }

            def userGroups = identityService.createGroupQuery().groupMember(userId).list()

            //new membership?
            def newGroups = groupIds - userGroups*.id
            newGroups.each { group -> identityService.createMembership(userId, group) }

            //leave membership?
            def oldGroups = userGroups*.id - groupIds
            oldGroups.each { group -> identityService.deleteMembership(userId, group) }

        }


 }