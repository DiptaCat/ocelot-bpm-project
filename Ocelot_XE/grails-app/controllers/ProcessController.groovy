
import org.camunda.bpm.engine.form.FormField
import org.camunda.bpm.engine.repository.ProcessDefinition

class ProcessController {

    def camundaService

    def index() {

        def processDefinitions = camundaService.deploymentList()
        def data = []
        processDefinitions.each { ProcessDefinition p ->

            def numInstances = camundaService.getNumInstances(p)
            Expando exp = new Expando(id:p.id, name:p.name, version:p.version, numInstances:numInstances)
            data << exp
        }

        [processes: data]
    }

    def show() {
        def processDefinition = camundaService.getProcessDefinitionById(params.id)

        def numInstances = camundaService.getNumInstances(processDefinition)
        [process: processDefinition, numInstances: numInstances]
    }

    def create(String id){

        def processDefinition = camundaService.getProcessDefinitionById(id)

        //external form ?
        def formKey = camundaService.getStartFormKey(id)

        if (formKey) {
            redirect uri:"$formKey/$id"
        }
        else { //or generate form
            def startFormData = camundaService.getStartFormData(id)
            render(view: 'create.gsp', model: [startFormData: startFormData, processDefinitionId: processDefinition.id])
        }
    }

    def save(){

//        def processDefinition = camundaService.getProcessDefinitionById(params.id)
        def startFormData = camundaService.getStartFormData(params.id)

        def vars = [:]
        startFormData.each { FormField d ->
            d.defaultValue = params[d.id]
            vars.put(d.id, d.defaultValue)
        }

        camundaService.startProcess(params.id, vars, session.user)

        redirect(action:'index')

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



}
