package taskList

import org.camunda.bpm.engine.form.FormField
import org.camunda.bpm.engine.form.FormProperty
import org.camunda.bpm.engine.repository.ProcessDefinition
import org.camunda.bpm.engine.form.StartFormData

class MainController {
    def workflowService

    def index() {
       //def resp = rest.get("http://localhost:8080/BlankApp/display")
       //workflowService.deployProcess(resp.xml)
        def processDefinitions = workflowService.deploymentList()
        def data = [:]
        processDefinitions.each { ProcessDefinition p ->
            def numInstance = workflowService.getNumInstances(p)
            data."$p.id" = [id: p.deploymentId, name: p.name, time: numInstance]}
        [deployments: data]
    }
    def show(){
        def deployment = workflowService.getDeploymentById(params["id"])
        ProcessDefinition processDefinition = workflowService.getProcessDefinition(params["id"])

        def numInstnaces = workflowService.getNumInstances(processDefinition)
        [deployment: deployment, numInstnaces: numInstnaces]
    }
    def deploy(){
        workflowService.deployProcess()
    }
    def newInstance(){
        //vars and processDefinition
        workflowService.startProcess(id)
        def deployment = workflowService.getDeploymentById(id)
        def processDefinition = workflowService.getProcessDefinition(id)
        def numInstnaces = workflowService.getNumInstances(processDefinition)

        workflowService.submitStartForm(processDefinitionId, properties)
        //
        render (view:'index.gsp')

    }
    def newInstanceView(String id){
        ProcessDefinition processDefinition = workflowService.getProcessDefinition(id)
        def startFormData = workflowService.getStartFormData(processDefinition.id)
        startFormData.each {FormField f ->
            println f.id
            println f.properties
            println f.defaultValue
            println f.label
            println f.type
        }
        render (view:'startProcess.gsp', model: [startFormData:startFormData, processDefinition:processDefinition])

    }
//    def newInstance(id, taskName=null, vars=[:]){
//        workflowService.startProcess(id, taskName, vars)
//        render (view:'index.gsp')
//    } //ask to ruben

    def upload() {
        def f = request.getFile('myFile')

        if (f.empty) {
            flash.message = 'file cannot be empty'
            render(view: 'uploadForm')
            return
        }
        print f.originalFilename
        print f.name
        print f.size
        print f.contentType
        print f.getClass()
        String fileContent = f.getInputStream().getText()
        workflowService.deployProcess(fileContent, f.originalFilename)
        response.sendError(200, 'Done')
    }
    def saveFile() {
        def webRootDir = servletContext.getRealPath("/")
        def uploadedFile = request.getFile('payload')
        /*You can get some important file information on grail like file name, file size etc*?
        /*println "Class: ${uploadedFile.class}"
         println "Name: ${uploadedFile.name}"
         println "OriginalFileName:          ${uploadedFile.originalFilename}"
         println "Size: ${uploadedFile.size}"
         println "ContentType: ${uploadedFile.contentType}"*/
    }

    def emptyView() {
        render(view: 'emptyView')
    }

    //
}
