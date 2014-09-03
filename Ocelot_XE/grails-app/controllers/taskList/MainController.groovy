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
    def newInstance(String id){
        workflowService.startProcess(id)
        def deployment = workflowService.getDeploymentById(id)
        def processDefinition = workflowService.getProcessDefinition(id)
        def numInstnaces = workflowService.getNumInstances(processDefinition)

        //
        render (view:'show.gsp', model: [deployment:deployment, numInstnaces:numInstnaces])

    }
    def newInstanceView(String id){
        def processDefinition = workflowService.getProcessDefinition(id)
        def startFormData = workflowService.getStartFormData(processDefinition.id)
        render (view:'startProcess.gsp', model: [startFormData:startFormData])
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

    //
}
