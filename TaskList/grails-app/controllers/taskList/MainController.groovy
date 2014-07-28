package taskList

class MainController {
    def workflowService

    def index() {
        //def resp = rest.get("http://grails.org/api/v1.0/plugin/acegi/")
        //workflowService.deployProcess()
    }
    def deploy(){
        workflowService.deployProcess()
    }
    def newInstance(){} //ask to ruben

}
