package taskList

import org.camunda.bpm.engine.repository.ProcessDefinition
import org.camunda.bpm.engine.task.Task

class TaskController {

    def workflowService

    def emptyView() {
        def tasks = workflowService.getUnassignedTasks()

        tasks.each {Task t ->
            println t.getName()
            ProcessDefinition processDefinition = workflowService.getProcessInstance(t.getProcessInstanceId())
            println processDefinition.getName()
            println t.getOwner()
        }

        render(view: 'emptyView')
    }
}
