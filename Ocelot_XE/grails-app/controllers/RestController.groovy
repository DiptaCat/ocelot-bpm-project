class RestController {

    def workflowService

    def completeTask(taskId, vars, user) {
        workflowService.completeTask(taskId, vars, user)
    }





}
