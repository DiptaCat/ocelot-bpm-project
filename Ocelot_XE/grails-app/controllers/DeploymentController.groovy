
class DeploymentController {

    def modelerService
    def workflowService

    def index() {

        def result = modelerService.obtainAll()

        [processes: result.models, numModels: result.numModels]
    }

    def deploy(){

        def result = modelerService.obtain(params.id)

        def xml = result.bpmn

        if (!xml || xml == "") {
            flash.error = 'ERROR: File cannot be empty'
            redirect controller:'process',action:'index'
            return
        }

        try {
            workflowService.deployProcess(new ByteArrayInputStream(xml.getBytes("UTF-8")), result.name)
            flash.message = 'The file was uploaded'

        }catch(Exception e){
            flash.error = 'ERROR: File cannot be read'
        }

        redirect controller:'process', action:'index'
    }
}
