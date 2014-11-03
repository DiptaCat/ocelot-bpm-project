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

    def deploy(){
        workflowService.deployProcess()
    }


    def upload() {
        def f = request.getFile('myFile')

        if (f.empty) {
            flash.error = 'ERROR: File cannot be empty'
            redirect(action:'index')
            return
        }
        print f.originalFilename
        print f.name
        print f.size
        print f.contentType
        print f.getClass()
        def fileContent = f.getInputStream()
        workflowService.deployProcess(fileContent, f.originalFilename)
        flash.message = 'The file was uploaded'
        redirect(action:'index')
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
