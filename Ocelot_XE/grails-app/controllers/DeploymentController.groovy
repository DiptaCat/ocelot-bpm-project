

class DeploymentController {

    def index() {
    //TODO: REST CLIENT CALL OCELOT_MT AND OBTAIN A LIST
        def list = [['id':2, 'name':'pepito'], ['id':3, 'name':'pepito_2']]
        [processes: list]
    }
    def show(){
        def response = 'response' //REST CALL TO OCELOT MT
        def f = 'xmlFile'//f = a XML FILE
        if (f.empty) {
            flash.error = 'ERROR: File cannot be empty'
            redirect(action:'index')
            return
        }
        def fileContent = f.getInputStream()
        camundaService.deployProcess(fileContent, f.originalFilename)
        flash.message = 'The file was uploaded'
        redirect(action:'index')
    }
}
