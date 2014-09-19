package taskList

import org.camunda.bpm.engine.repository.ProcessDefinition
import org.camunda.bpm.engine.task.Task

class TaskController {

    def workflowService

    def index() {
        def tasks = workflowService.getUnassignedTasks()
        def processNames = []


        tasks.each {Task t ->
            println t.getName()
            ProcessDefinition processDefinition = workflowService.getProcessDefinitionByProcessDefinitionId(t.getProcessDefinitionId())
            println processDefinition.getName()
            processNames.add(processDefinition.getName())
            println t.getOwner()
        }

        [tasks: tasks, processNames: processNames]
    }

    def show() {
        def task = workflowService.getUserTaskById(params['id'])
        [task: task, procName: params['procName']]
    }

    def action() {}
}
