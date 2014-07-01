package modeller

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class BpmnController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Bpmn.list(params), model:[bpmnInstanceCount: Bpmn.count()]
    }

    def show(Bpmn bpmnInstance) {
        respond bpmnInstance
    }

    def create() {
        respond new Bpmn(params)
    }

    @Transactional
    def save(Bpmn bpmnInstance) {
        if (bpmnInstance == null) {
            notFound()
            return
        }

        if (bpmnInstance.hasErrors()) {
            respond bpmnInstance.errors, view:'create'
            return
        }

        bpmnInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'bpmn.label', default: 'Bpmn'), bpmnInstance.id])
                redirect bpmnInstance
            }
            '*' { respond bpmnInstance, [status: CREATED] }
        }
    }

    def edit(Bpmn bpmnInstance) {
        respond bpmnInstance
    }

    @Transactional
    def update(Bpmn bpmnInstance) {
        if (bpmnInstance == null) {
            notFound()
            return
        }

        if (bpmnInstance.hasErrors()) {
            respond bpmnInstance.errors, view:'edit'
            return
        }

        bpmnInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Bpmn.label', default: 'Bpmn'), bpmnInstance.id])
                redirect bpmnInstance
            }
            '*'{ respond bpmnInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Bpmn bpmnInstance) {

        if (bpmnInstance == null) {
            notFound()
            return
        }

        bpmnInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Bpmn.label', default: 'Bpmn'), bpmnInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'bpmn.label', default: 'Bpmn'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
