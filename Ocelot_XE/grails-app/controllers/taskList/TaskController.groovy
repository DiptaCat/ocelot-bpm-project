package taskList

import org.camunda.bpm.engine.form.FormField
import org.camunda.bpm.engine.form.TaskFormData
import org.camunda.bpm.engine.repository.ProcessDefinition
import org.camunda.bpm.engine.task.Task

class TaskController {

    def workflowService

    def index() {
        //TODO: Get only tasks for user session
        def tasks = workflowService.getUnassignedTasks()
        def processDefinitions = []


        tasks.each {Task t ->
//            println t.getName()
//            println 'Task ID->\t' + t.id
//            println 'task definition key  '+t.taskDefinitionKey
//            println 'Process ID->\t' + t.getProcessDefinitionId()
            ProcessDefinition processDefinition = workflowService.getProcessDefinitionByProcessDefinitionId(t.getProcessDefinitionId())
//            println processDefinition.getName()
            processDefinitions.add(processDefinition)
//            println t.getOwner()
//            println '--------------\n'
        }
        [tasks: tasks, processDefinitions: processDefinitions]
    }

    def show() {
        println "Task id  "+params['id']
        def task = workflowService.getFormData(params['id'])
        println "task" + task.dump()
        println params['processDefinitionId']
        [startFormData:task, taskId:params['id']]
    }

    def completeTask() {
        println 'params' + params
        def FormData = workflowService.getFormData(params['id'])
        HashMap<String, Object> vars = new HashMap<String, Object>()
        FormData.each {FormField d ->
            vars.put(d.id, params[d.id])
        }


        workflowService.saveTask(params['id'], vars, true)

        redirect(controller:'Task',action:'index')

    }
    /*
    * Unused method at moment
    * */
    def saveTask() {
        println 'params savebghjghjvghj' + params

        def FormData = workflowService.getFormData(params['id'])
        HashMap<String, Object> vars = new HashMap<String, Object>()
        FormData.each {FormField d ->
            vars.put(d.id, params[d.id])
        }


        workflowService.saveTask(params['id'], vars, false)

        redirect(controller:'Task', action:'index')

    }
}
