package ocelot.xe

class RestController {

    def workflowService

    //repository service

    def getDeployments() {
        workflowService.getDeployments()
    }

    def getDeployment() {
        workflowService.getDeployment(params.id)
    }

    def getProcessDefinition() {
        workflowService.getProcessDefinition(params.id) //deployment id
    }

    def getProcessDefinitionById() {
        workflowService.getProcessDefinitionById(params.id) //procDef id
    }

    def deployProcess() {
        workflowService.deployProcess(params.modelStream, params.processName)
    }

    //runtime service

    def startProcess() {
        def vars = params.vars?:[:]
        workflowService.startProcess(params.id, params.user, vars)
    }

    //task service

    def getUnassignedTasks() {
        workflowService.getUnassignedTasks()
    }

    def getAssignedTasks() {
        workflowService.getAssignedTasks(params.user)
    }

    def getCandidateTasks() {
        workflowService.getCandidateTasks(params.groups)
    }

    def getTasksByUser() {
        workflowService.getTasksByUser(params.user)
    }

    def getTaskById() {
        workflowService.getTaskById(params.id)
    }

    def claimTask() {
        workflowService.claimTask(params.id, params.user)
    }

    def unclaimTask() {
        workflowService.unclaimTask(params.id)
    }

    def getTaskVars() {
        workflowService.getTaskVars(params.id)
    }

    def saveTask() {
        workflowService.saveTask(params.id, params.vars, params.user)
    }

    def completeTask() {
        workflowService.completeTask(params.id, params.vars, params.user)
    }

    def setAssignee() {
        workflowService.setAssignee(params.id, params.user)
    }

    def setPriority() {
        workflowService.setPriority(params.id, params.priority)
    }

    //form service

    def getStartFormKey() {
        workflowService.getStartFormKey(params.id) //procDef id
    }

    def getTaskFormKey() {
        workflowService.getTaskFormKey(params.id) //task id
    }

    def getStartFormData() {
        workflowService.getStartFormKey(params.id) //procDef id
    }

    def getTaskFormData() {
        workflowService.getTaskFormKey(params.id) //task id
    }

    //history service

    def countProcessInstances() {
        workflowService.countProcessInstances(params.id) //procDef id
    }

    def getProcessInstanceTasks() {
        workflowService.getProcessInstanceTasks(params.id) //procDef id
    }

    //identity service

    def getCandidatesByTask() {
        workflowService.getCandidatesByTask(params.id) //task id
    }

    def syncUser() {
        workflowService.syncUser(params.user, params.groups)
    }




}
