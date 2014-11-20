import org.camunda.bpm.engine.repository.ProcessDefinition

class MainController {
    def camundaService

    def index() {
       //def resp = rest.get("http://localhost:8080/BlankApp/display")
       //camundaService.deployProcess(resp.xml)
        def processDefinitions = camundaService.deploymentList()
        def data = [:]
        processDefinitions.each { ProcessDefinition p ->
            def numInstance = camundaService.getNumInstances(p)
            data."$p.id" = [id: p.deploymentId, name: p.name, time: numInstance]}
        [deployments: data]
    }

    def deploy(){
        camundaService.deployProcess()
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
        camundaService.deployProcess(fileContent, f.originalFilename)
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
