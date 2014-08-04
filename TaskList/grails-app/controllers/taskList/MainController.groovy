package taskList

class MainController {
    def workflowService

    def index() {
       //def resp = rest.get("http://localhost:8080/BlankApp/display")
       //workflowService.deployProcess(resp.xml)
        def data = workflowService.deploymentList()
        println data
        [deployments: data]
    }
    def deploy(){
        workflowService.deployProcess()
    }
    def newInstance(id){} //ask to ruben

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
