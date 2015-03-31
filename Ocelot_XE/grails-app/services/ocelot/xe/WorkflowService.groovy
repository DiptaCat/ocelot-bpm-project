package ocelot.xe

import grails.util.GrailsNameUtils
import org.camunda.bpm.engine.*
import org.camunda.bpm.engine.impl.persistence.entity.DeploymentEntity
import org.camunda.bpm.engine.impl.persistence.entity.ProcessDefinitionEntity
import org.camunda.bpm.engine.repository.ProcessDefinition
import org.camunda.bpm.model.bpmn.BpmnModelInstance
import org.camunda.bpm.model.bpmn.Bpmn
import org.camunda.bpm.model.bpmn.instance.ExtensionElements
import org.camunda.bpm.model.bpmn.instance.camunda.CamundaFormData
import org.camunda.bpm.model.bpmn.instance.camunda.CamundaFormField

class WorkflowService {

    RepositoryService repositoryService
    RuntimeService runtimeService
    TaskService taskService
    FormService formService
    HistoryService historyService
    IdentityService identityService

    //repository service

    def getDeployments() {
        repositoryService.createProcessDefinitionQuery().active().latestVersion().list()
    }

    def getDeployment(String id) {
        repositoryService.createDeploymentQuery().deploymentId(id).singleResult()
    }

    def getProcessDefinition(String deploymentId) {
        repositoryService.createProcessDefinitionQuery()
                .deploymentId(deploymentId)
                .singleResult();
    }

    def getProcessDefinitionById(String processDefinitionId) {
        repositoryService.createProcessDefinitionQuery().processDefinitionId(processDefinitionId).singleResult()

    }

    def deployProcess(InputStream modelStream, String processName) throws Exception {

        String xmlString

        try {
            xmlString = extractBpmn(modelStream) //task interception mechanism
        }catch(Exception e){
            throw e
        }

        DeploymentEntity d = (DeploymentEntity) repositoryService.createDeployment()
                .name(fileName.toString())
                .addString(processName + ".bpmn20.xml", xmlString)
                .deploy()

        int version = 0
        int revision = 0

        if (d) {
            ProcessDefinitionEntity pde = d.getDeployedArtifacts(ProcessDefinitionEntity).first()
            //println "PDE : ${pde.dump()}"
            if (pde) {
                processName = pde.name
                version = pde.version
                revision = pde.revision
            }
        }

        [object:d, name:processName, version:version, revision:revision]

    }

    def extractBpmn(modelStream) throws Exception{

        BpmnModelInstance modelInstance = Bpmn.readModelFromStream(modelStream)
        handleBpmnExtension(modelInstance)
        Bpmn.convertToString(modelInstance)
    }

    def handleBpmnExtension(modelInstance) {

        Collection<ExtensionElements> extensionElements = modelInstance.getModelElementsByType(ExtensionElements.class);
        println "Size => " + extensionElements.size()

        CamundaFormData camundaFormData

        extensionElements.each {ExtensionElements ee ->
            println "Parent Element: " + ee.getParentElement() + "\n"


            if(ee.getParentElement().toString().substring(0, ee.getParentElement().toString().indexOf('@')).equals("org.camunda.bpm.model.bpmn.impl.instance.UserTaskImpl")) {
                camundaFormData = ee.getElementsQuery().filterByType(CamundaFormData.class).singleResult();
                camundaFormData.camundaFormFields.each { CamundaFormField ff ->
                    println "Id = " + ff.camundaId
                    println "Label = " + ff.camundaLabel
                    println "Value = " + ff.camundaDefaultValue
                    println "\n"
                }
            }
        }
    }

    //runtime service

    def startProcess(processDefinitionId, user, vars = [:]) {

        vars.user = user
        runtimeService.startProcessInstanceById(processDefinitionId, vars)
    }

    def startProcessById(processDefinitionId, user = null) {

        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().processDefinitionId(processDefinitionId).singleResult()
        def vars = user ? [user:user] : [:]
        runtimeService.startProcessInstanceById(processDefinition.id, vars)
    }

    //task service

    def getUnassignedTasks() {
        taskService.createTaskQuery().taskUnassigned().list()
    }

    def getAssignedTasks(user) {
        taskService.createTaskQuery().taskAssignee(user).list()
    }

    def getCandidateTasks(groups) {
        taskService.createTaskQuery().taskCandidateGroupIn(groups).list()
    }

    def getUserTaskById(id) {
        taskService.createTaskQuery().taskId(id)
    }

    def getProcessInstanceOpenTasks(processInstanceId) {
        taskService.createTaskQuery().processInstanceId(processInstanceId).orderByTaskCreateTime().asc().list()

    }

    def getPIOpenTasks(List processInstanceIds) {
        processInstanceIds.collectEntries({ [it.id, getPIOpenTasks(it.id)] })
    }


    def getTask(String processInstanceId, String methodName = null, String user = '') {
        def q = taskService.createTaskQuery().processInstanceId(processInstanceId)
        if (methodName)
            q = q."${methodName}"(user)
        q.singleResult()
    }

    def getTaskById(String taskId) {
        taskService.createTaskQuery().taskId(taskId).singleResult()
    }

    def claimTask(String taskId, String username) {
        taskService.claim(taskId, username)
    }

    def unclaimTask(String taskId) {
        taskService.claim(taskId, null)
    }

    def getTaskVars(String taskId) {
        taskService.getVariables(taskId)
    }

    def saveTask(String taskId, Map vars, String user) {
        vars.user = user
        taskService.setVariables(taskId, vars)
    }

    def completeTask(String taskId, Map vars, String user) {
        vars.user = user
        taskService.setVariablesLocal(taskId, vars)
        //println "complete $taskId -> $vars"
        taskService.complete(taskId, vars)
    }

    def setAssignee(String taskId, String username) {
        taskService.setAssignee(taskId, username)
    }

    def setPriority(String taskId, int priority) {
        taskService.setPriority(taskId, priority)
    }

    def getIdentityLinksForTask(String taskId) {
        taskService.getIdentityLinksForTask(taskId)
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

    //form service

    String getStartFormKey(String processDefinitionId) {
        formService.getStartFormKey(processDefinitionId)
    }

    def getTaskFormKey(String taskId) {

        def t = getTaskById(taskId)
        formService.getTaskFormKey(t.processDefinitionId, t.taskDefinitionKey)
    }

    def getStartFormData(String processDefinitionId) {
        def startFormData  = formService.getStartFormData(processDefinitionId)
        startFormData?.formFields
    }

    def getFormData(String taskId) {
        def formData = formService.getTaskFormData(taskId)
        formData?.formFields
    }

    //history service

    def getProcessInstance(id) {
        def q = runtimeService.createProcessInstanceQuery().processInstanceId(id)
        def pi = q.singleResult()
        if (!pi) {
            q = historyService.createHistoricProcessInstanceQuery().processInstanceId(id)
            pi = q.singleResult()
        }
        pi
    }

    def getNumInstances(ProcessDefinition processDefinition) {
        historyService.createHistoricProcessInstanceQuery().processDefinitionId(processDefinition.getId()).count();
    }

    def getProcessInstanceTasks(processInstanceId) {
        historyService.createHistoricTaskInstanceQuery().processInstanceId(processInstanceId).orderByHistoricActivityInstanceStartTime().asc().list()
    }

    //identity service

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
        userIds.flatten().unique()
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