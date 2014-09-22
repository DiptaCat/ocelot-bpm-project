package taskList

import org.camunda.bpm.engine.form.TaskFormData
import org.camunda.bpm.engine.repository.ProcessDefinition
import org.camunda.bpm.engine.task.Task

class TaskController {

    def workflowService

    def index() {
        def tasks = workflowService.getUnassignedTasks()
        def processDefinitions = []


        tasks.each {Task t ->
            println t.getName()
            ProcessDefinition processDefinition = workflowService.getProcessDefinitionByProcessDefinitionId(t.getProcessDefinitionId())
            println processDefinition.getName()
            processDefinitions.add(processDefinition)
            println t.getOwner()
        }

        [tasks: tasks, processDefinitions: processDefinitions]
    }

    def show() {
        println "Task id  "+params['id']
        TaskFormData task = workflowService.getFormData(params['id'])
        println params['processDefinitionId']
        [startFormData:task, processDefinitionId:params['processDefinitionId']]
    }

    def completeTask() {


    }
}
