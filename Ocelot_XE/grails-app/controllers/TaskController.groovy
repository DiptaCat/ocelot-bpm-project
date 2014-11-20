
import org.camunda.bpm.engine.form.FormField
import org.camunda.bpm.engine.form.TaskFormData
import org.camunda.bpm.engine.task.Task

class TaskController {

    static allowedMethods = [update: "POST"]

    def camundaService

    def index() {

        def assignedTasks = []
        def at = camundaService.getAssignedTasks(session.user)
        at.each { Task t ->
            def pd = camundaService.getProcessDefinitionById(t.processDefinitionId)
            def exp = new Expando(id:t.id, name:t.name, processId: t.processDefinitionId, processName:pd.name, date:t.createTime, assignee:t.assignee)
            assignedTasks << exp
        }

        def unassignedTaks =[]
        def ut = camundaService.getUnassignedTasks()
        ut.each { Task t ->
            def pd = camundaService.getProcessDefinitionById(t.processDefinitionId)
            def exp = new Expando(id:t.id, name:t.name, processId: t.processDefinitionId, processName:pd.name, date:t.createTime)
            unassignedTaks << exp
        }
        [assigned: assignedTasks, unassigned: unassignedTaks]
    }

    def claim() {
        camundaService.claimTask(params.id, session.user)
        redirect(action:'index')
    }

    def unclaim() {
        camundaService.unclaimTask(params.id, session.user)
        redirect(action:'index')
    }

    def show(String id){

        //external form ?
        def formKey = camundaService.getTaskFormKey(params.id)

        if (formKey) {
            redirect uri:"$formKey/$id"
        }
        else { //or generate form

            def vars = camundaService.getTaskVars(params.id)

            def formData = camundaService.getFormData(params.id)
            formData.each { property ->
                property.defaultValue = vars."${property.id}"
            }

            [formData:formData, taskId:params.id]
        }
    }

    def update(){

        def formData = camundaService.getFormData(params.taskId)

        def vars = [:]
        formData.each { property ->

            if (property.type.toString().contains('BooleanFormType')) {
                vars."${property.id}" = (params."${property.id}"?.toLowerCase() == 'on')
            }
            else vars."${property.id}" = params."${property.id}"

        }

        if (params.save) {
            camundaService.saveTask(params.taskId, vars, session.user)
        }
        else {
            camundaService.completeTask(params.taskId, vars, session.user)
        }
        redirect(action:'index')

    }




}
