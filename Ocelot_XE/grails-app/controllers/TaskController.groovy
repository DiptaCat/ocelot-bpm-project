import org.camunda.bpm.engine.task.Task

class TaskController {

    static allowedMethods = [update: "POST"]

    def workflowService

    def index() {

        def assignedTasks = []
        def at = workflowService.getAssignedTasks(session.user)
        at.each { Task t ->
            def pd = workflowService.getProcessDefinitionById(t.processDefinitionId)
            def exp = new Expando(id:t.id, name:t.name, processId: t.processDefinitionId, processName:pd.name, date:t.createTime, assignee:t.assignee)
            assignedTasks << exp
        }

        def unassignedTaks =[]
        def ut = workflowService.getUnassignedTasks()
        ut.each { Task t ->
            def pd = workflowService.getProcessDefinitionById(t.processDefinitionId)
            def exp = new Expando(id:t.id, name:t.name, processId: t.processDefinitionId, processName:pd.name, date:t.createTime)
            unassignedTaks << exp
        }
        [assigned: assignedTasks, unassigned: unassignedTaks]
    }

    def claim() {
        workflowService.claimTask(params.id, session.user)
        redirect(action:'index')
    }

    def unclaim() {
        workflowService.unclaimTask(params.id)
        redirect(action:'index')
    }

    def show(String id){

        //external form ?
        def formKey = workflowService.getTaskFormKey(params.id)

        if (formKey) {
            redirect uri:"$formKey/$id"
        }
        else { //or generate form

            def vars = workflowService.getTaskVars(params.id)

            def formData = workflowService.getFormData(params.id)
            formData.each { property ->
                property.defaultValue = vars."${property.id}"
            }

            [formData:formData, taskId:params.id]
        }
    }

    def update(){

        def formData = workflowService.getFormData(params.taskId)

        def vars = [:]
        formData.each { property ->

            if (property.type.toString().contains('BooleanFormType')) {
                vars."${property.id}" = (params."${property.id}"?.toLowerCase() == 'on')
            }
            else vars."${property.id}" = params."${property.id}"

        }

        if (params.save) {
            workflowService.saveTask(params.taskId, vars, session.user)
        }
        else {
            workflowService.completeTask(params.taskId, vars, session.user)
        }
        redirect(action:'index')

    }




}
