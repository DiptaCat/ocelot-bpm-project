package blank

import org.camunda.bpm.engine.RuntimeService
import org.camunda.bpm.engine.TaskService

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class TaskController {
    RuntimeService runtimeService
    def
    TaskService taskService
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Task.list(params), model: [taskInstanceCount: Task.count()]
        respond taskService.g
    }

    def show(Task taskInstance) {
        respond taskInstance
    }

    def create() {
        respond new Task(params)
    }

    @Transactional
    def save(Task taskInstance) {
        if (taskInstance == null) {
            notFound()
            return
        }

        if (taskInstance.hasErrors()) {
            respond taskInstance.errors, view: 'create'
            return
        }

        taskInstance.save flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'task.label', default: 'Task'), taskInstance.id])
                redirect taskInstance
            }
            '*' { respond taskInstance, [status: CREATED] }
        }
    }

    def edit(Task taskInstance) {
        respond taskInstance
    }

    @Transactional
    def update(Task taskInstance) {
        if (taskInstance == null) {
            notFound()
            return
        }

        if (taskInstance.hasErrors()) {
            respond taskInstance.errors, view: 'edit'
            return
        }

        taskInstance.save flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Task.label', default: 'Task'), taskInstance.id])
                redirect taskInstance
            }
            '*' { respond taskInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Task taskInstance) {

        if (taskInstance == null) {
            notFound()
            return
        }

        taskInstance.delete flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Task.label', default: 'Task'), taskInstance.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NO_CONTENT }
        }
    }

    def getTask(task, p, user) {

        def map = [:]
        map['process'] = p.flux.descripcio //grailsApplication.metadata['app.name']
        map['processVersion'] = task.processDefinitionId
        map['description'] = p?.descripcio
        map['taskName'] = task.name
        map['taskId'] = task.id
        map['url'] = g.createLink (controller: 'task', action: 'edit', id: task.id, absolute:true)
        map['processStartDate'] = RFC_822_DATE_TIME.format(p?.dataAlta)
        map['taskReadyDate'] = RFC_822_DATE_TIME.format(task.createTime)
        if (task.dueDate)
            map['taskExpirationDate'] = RFC_822_DATE_TIME.format(task.dueDate)
//			map['roles'] = "" --> TODO: calcular a quins grups candidats pertany l'usuari. És necessari?
//			map['documentNumber'] = it.id --> TODO expedient
        map['initiatorUser'] = p?.usuari
        map['assignedToMe'] = task.assignee && (user == task.assignee)
        return map
    }
    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'task.label', default: 'Task'), params.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NOT_FOUND }
        }
    }
}
