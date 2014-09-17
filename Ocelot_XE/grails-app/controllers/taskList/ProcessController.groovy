package taskList

import org.camunda.bpm.engine.form.FormField
import org.camunda.bpm.engine.repository.ProcessDefinition

class ProcessController {

    def workflowService

    def show(){
        def deployment = workflowService.getDeploymentById(params["id"])
        ProcessDefinition processDefinition = workflowService.getProcessDefinition(params["id"])

        def numInstnaces = workflowService.getNumInstances(processDefinition)
        [deployment: deployment, numInstnaces: numInstnaces]
    }

    def newInstanceView(String id){
        ProcessDefinition processDefinition = workflowService.getProcessDefinition(id)
        def startFormData = workflowService.getStartFormData(processDefinition.id)
        startFormData.each {FormField d ->
            println d.id
            println d.label
            println d
        }
        render (view:'startProcess.gsp', model: [startFormData:startFormData, processDefinitionId:processDefinition.deploymentId])

    }

    def newInstance(){
            println 'params' + params

            ProcessDefinition processDefinition = workflowService.getProcessDefinition(params['id'])
            def startFormData = workflowService.getStartFormData(processDefinition.id)
            HashMap<String, Object> vars = new HashMap<String, Object>()
            startFormData.each {FormField d ->
                d.defaultValue = params[d.id]
                vars.put(d.id, d.defaultValue)
            }

            def response = workflowService.startProcess(processDefinition.id, vars)

            println response

            //println processDefinition.properties
            //vars and processDefinition
            /*workflowService.startProcess(id)
            def deployment = workflowService.getDeploymentById(id)
            def processDefinition = workflowService.getProcessDefinition(id)
            def numInstnaces = workflowService.getNumInstances(processDefinition)

            workflowService.submitStartForm(processDefinitionId, properties)*/
            //
            redirect(controller:'Main',action:'index')

        }


}
