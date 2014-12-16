import grails.plugins.rest.client.RestBuilder

class DeploymentController {

    def camundaService

    def index() {
    //TODO: REST CLIENT CALL OCELOT_MT AND OBTAIN A LIST /api/model/list

        //def list = [['id':2, 'name':'pepito', 'description':'sasasas'], ['id':3, 'name':'pepito_2', 'description':'asddsadsadsa']]
        def resp = new RestBuilder().get("http://localhost:9090/ocelot/api/model/list")
        def numModels = resp.json.numModels
        def list = resp.json.models
        [processes: list, numModels: numModels]
    }
    def show(){
        println 'id -> '+params.id
        def resp = new RestBuilder().get("http://localhost:9090/ocelot/api/model/show/$params.id")
        def xml = resp.json.bpmn
        if (xml.empty) {
            flash.error = 'ERROR: File cannot be empty'
            redirect(controller:'process',action:'index')
            return
        }
        println xml
        def merda = new ByteArrayInputStream(xml.getBytes("UTF-8"))
        println merda
        def name = resp.json.name
        println name
            camundaService.deployProcess(merda, resp.json.name)
            flash.message = 'The file was uploaded'



        redirect(controller:'process', action:'index')
    }
}
