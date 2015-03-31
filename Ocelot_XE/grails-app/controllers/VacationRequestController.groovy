import grails.transaction.Transactional

import static org.springframework.http.HttpStatus.CREATED
import static org.springframework.http.HttpStatus.NOT_FOUND

@Transactional(readOnly = true)
class VacationRequestController {

    static allowedMethods = [save: "POST", performApproval: "POST", update: "POST"]

    def workflowService

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond VacationRequest.list(params), model: [vacationRequestInstanceCount: VacationRequest.count()]
    }

    def create() {

        def processDefinition = workflowService.getProcessDefinitionById(params.id)
        session.currentProcDefId = processDefinition.id

        respond new VacationRequest(params)
    }

    @Transactional
    def save(VacationRequest vacationRequestInstance) {
        if (vacationRequestInstance == null) {
            notFound()
            return
        }

        if (vacationRequestInstance.hasErrors()) {
            respond vacationRequestInstance.errors, view: 'create'
            return
        }

        def pi = workflowService.startProcess(session.currentProcDefId, session.user, vacationRequestInstance.properties)
        vacationRequestInstance.processInstanceId = pi.processInstanceId

        vacationRequestInstance.save flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'default.vacationRequest.label', default: 'VacationRequest'), vacationRequestInstance.id])
                redirect vacationRequestInstance
            }
            '*' { respond vacationRequestInstance, [status: CREATED] }
        }
    }

    def show(VacationRequest vacationRequestInstance) {
        respond vacationRequestInstance
    }

    def approval() {

        def t = workflowService.getTaskById(params.id)
        VacationRequest vacationRequestInstance = VacationRequest.findByProcessInstanceId(t.processInstanceId)
        def vars = workflowService.getTaskVars(t.id)
        vars.each { k, v ->
            vacationRequestInstance.properties."$k" = v
        }
        render(view: "approval", model: [vacationRequestInstance: vacationRequestInstance, taskId: t.id])
    }

    def performApproval() {

        VacationRequest vacationRequestInstance = VacationRequest.get(params.id)
        vacationRequestInstance.properties = params
        def t = workflowService.getTaskById(params.taskId)

        if (!vacationRequestInstance.hasErrors() && vacationRequestInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.updated.message', args: [message(code: 'vacationRequest.label', default: 'VacationRequest'), vacationRequestInstance.id])}"

            if (params.save) {
                workflowService.saveTask(t.id, params, session.user)
                //redirect(action:'show', params:[id:"${params.id}"])
            } else {
                params.id = vacationRequestInstance.id
                params.vacationApproved = vacationRequestInstance.approvalStatus.toUpperCase() == "APPROVED"
                params.approvalRemark = params.approvalRemark && params.approvalRemark != "" ? params.approvalRemark : "No Approval Remark."
                workflowService.completeTask(t.id, params, session.user)

                params.isApproval = true
            }
            redirect(action: "show", id: vacationRequestInstance.id, params: params)
        } else {
            render(view: "approval", model: [vacationRequestInstance: vacationRequestInstance, taskId: t.id])
        }

    }

    def edit() {

        def t = workflowService.getTaskById(params.id)
        VacationRequest vacationRequestInstance = VacationRequest.findByProcessInstanceId(t.processInstanceId)
        def vars = workflowService.getTaskVars(t.id)
        vars.each { k, v ->
            vacationRequestInstance.properties."$k" = v
        }
        render(view: "edit", model: [vacationRequestInstance: vacationRequestInstance, taskId: t.id])
    }

    def update() {

        VacationRequest vacationRequestInstance = VacationRequest.get(params.id)
        vacationRequestInstance.properties = params
        def t = workflowService.getTaskById(params.taskId)

        if (!vacationRequestInstance.hasErrors() && vacationRequestInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.updated.message', args: [message(code: 'vacationRequest.label', default: 'VacationRequest'), vacationRequestInstance.id])}"

            if (params.save) {
                workflowService.saveTask(t.id, params, session.user)
            } else {
                params.resendRequest = vacationRequestInstance.resendRequest
                workflowService.completeTask(t.id, params, session.user)
            }
            redirect(action: "show", id: vacationRequestInstance.id, params: [taskId: params.taskId, complete: true])
        } else {
            render(view: "edit", model: [vacationRequestInstance: vacationRequestInstance, myTasksCount: assignedTasksCount])
        }

    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'vacationRequest.label', default: 'VacationRequest'), params.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NOT_FOUND }
        }
    }
}
