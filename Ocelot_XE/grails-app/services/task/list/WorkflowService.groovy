package task.list

import grails.transaction.Transactional

import grails.util.GrailsNameUtils
import org.apache.commons.validator.Form
import org.camunda.bpm.engine.form.FormField
import org.camunda.bpm.engine.form.StartFormData
import org.camunda.bpm.engine.impl.persistence.entity.DeploymentEntity
import org.camunda.bpm.engine.impl.persistence.entity.ProcessDefinitionEntity
import org.camunda.bpm.engine.runtime.ProcessInstance
import org.camunda.bpm.engine.task.Task
import org.camunda.bpm.engine.repository.ProcessDefinition
import org.camunda.bpm.model.bpmn.Bpmn
import org.camunda.bpm.model.bpmn.BpmnModelInstance;
import org.camunda.bpm.model.bpmn.instance.UserTask
import org.camunda.bpm.model.bpmn.impl.instance.camunda.CamundaFormDataImpl as CamundaForm
import org.camunda.bpm.model.bpmn.instance.camunda.CamundaFormField
import org.camunda.bpm.model.xml.instance.DomElement
import org.camunda.bpm.model.xml.instance.ModelElementInstance
import org.camunda.bpm.model.bpmn.instance.ExtensionElements;

import org.camunda.bpm.model.bpmn.instance.camunda.CamundaFormData;

import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

import static groovy.util.XmlSlurper.*

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

    def getProcessDefinitionByProcessId(String processDefinitionId) {
        repositoryService.createProcessDefinitionQuery()
                .processDefinitionId(processDefinitionId)
                .singleResult();
    }

    def deployProcess(fileStream, fileName) {

        String processName = fileName
        int version = 0
        int revision = 0

        String xmlString = readBpmnFile(fileStream) //task interception mechanism
        //println xmlString

        DeploymentEntity d = (DeploymentEntity) repositoryService.createDeployment()
                .name(fileName.toString())
                .addString(processName + ".bpmn20.xml", xmlString)
                .deploy()


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

    def readBpmnFile(fileStream) {

        BpmnModelInstance modelInstance = Bpmn.readModelFromStream(fileStream)


/**
 * Method to read extensions elements. TODO: Implement it into a separate method to handle the extensions elements.
 *
//        BpmnModelInstance modelInstance = Bpmn.readModelFromStream(fileStream)
//        Collection<ExtensionElements> extensionElements = modelInstance.getModelElementsByType(ExtensionElements.class);
//        println "Size => " + extensionElements.size()
//
//        CamundaFormData camundaFormData
//
//        extensionElements.each {ExtensionElements ee ->
//            println "Parent Element: " + ee.getParentElement() + "\n"
//
//
//            if(ee.getParentElement().toString().substring(0, ee.getParentElement().toString().indexOf('@')).equals("org.camunda.bpm.model.bpmn.impl.instance.UserTaskImpl")) {
//                camundaFormData = ee.getElementsQuery().filterByType(CamundaFormData.class).singleResult();
//                camundaFormData.camundaFormFields.each { CamundaFormField ff ->
//                    println "Id = " + ff.camundaId
//                    println "Label = " + ff.camundaLabel
//                    println "Value = " + ff.camundaDefaultValue
//                    println "\n"
//                }
//            }
//        }
 **/

        Bpmn.convertToString(modelInstance)
    }

//    def deployProcess(fileContent, fileName) {
///*        def r = new File("grails-app/processes/my/test/SimpleProcess2.xml")
//        String xml = "";
//        r.eachLine {
//            xml += it
//        }*/
//
//        def d = repositoryService.createDeployment()
//                .name(fileName.toString())
//                .addString(fileName + ".bpmn20.xml", fileContent)
//                .deploy()
//
//    }

    def startProcess(String taskId, Map vars) {
        //log.info "STARTING PROCESS $p: ${vars.grep({it.key!='__files__'})}"
        println vars
        ProcessInstance pi = runtimeService.startProcessInstanceById(taskId, vars)
        //println "STARTED PROCESS $p: ${vars.grep({it.key!='__files__'})}: $pi"
        pi
    }

    def startProcess(String deploymentId) {
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().deploymentId(deploymentId).singleResult()
        println processDefinition
        ProcessInstance pi = runtimeService.startProcessInstanceById(processDefinition.getId())
        pi
    }

    def getUnassignedTasks() {
        def unassignedTasks = taskService.createTaskQuery().taskUnassigned().list()
        unassignedTasks
    }

    def getAllUserTasks(String user) {
        def userTasks = taskService.createTaskQuery().taskAssignee(user).list()
        userTasks
    }

    def getUserTaskById(String id) {
        def task = taskService.createTaskQuery().taskId(id)
        task
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

        def getProcessDefinitionByProcessDefinitionId(String processDefinitionId) {
            println processDefinitionId
            ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().processDefinitionId(processDefinitionId).singleResult()
            println processDefinition
            processDefinition
        }

        def getStartFormData(String processDefinitionId) {
            def startFormData  = formService.getStartFormData(processDefinitionId)
            /*  Updated with Camunda 7  .1.0-Final
                FormProperty class is deprecated -> Updated to Form Field */
            List<FormField> formFields = startFormData.getFormFields()
            formFields // codi HTML a sac (injectar-ho a una vista! )
        }

        def submitStartForm(String processDefinitionId, Map<String, Object> properties){
            formService.submitStartForm(processDefinitionId, properties)
        }

        def getFormData(Task task) {
            getFormData(task.id)
        }

        def getFormData(String taskId) {
            def formFields = taskService.getVariablesLocal(taskId)
            println formFields
            formFields
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


 }