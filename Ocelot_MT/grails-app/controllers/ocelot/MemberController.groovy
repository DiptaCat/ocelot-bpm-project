package ocelot

import grails.transaction.Transactional

import static org.springframework.http.HttpStatus.*

@Transactional(readOnly = true)
class MemberController {

	//static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]
	def index(Integer max) {
		params.max = Math.min(max ?: 10, 100)
		[memberInstanceList: Member.list(params), memberInstanceCount: Member.count]
	}

	def show(Member memberInstance) {
		respond memberInstance
	}

	def create() {
		respond new Member(params)
	}

	def edit(Member memberInstance) {
		respond memberInstance
	}

	@Transactional
	def save(Member memberInstance) {

		if (memberInstance == null) {
			notFound()
			return
		}

		if (memberInstance.hasErrors()) {
			respond memberInstance.errors, view: 'create'
			return
		}

		memberInstance.save flush: true

		request.withFormat {
			form multipartForm {
				flash.message = message(code: 'default.created.message', args: [message(code: 'user.label', default: 'Member'), memberInstance.name])
				redirect memberInstance
			}
			'*' { respond memberInstance, [status: CREATED] }
		}
	}

	@Transactional
	def update(Member memberInstance) {
		if (memberInstance == null) {
			notFound()
			return
		}

		if (memberInstance.hasErrors()) {
			respond memberInstance.errors, view: 'edit'
			return
		}

		memberInstance.save flush: true

		request.withFormat {
			form multipartForm {
				flash.message = message(code: 'default.updated.message', args: [message(code: 'User.label', default: 'Member'), memberInstance.name])
				redirect memberInstance
			}
			'*' { respond memberInstance, [status: OK] }
		}
	}

	@Transactional
	def delete(Member memberInstance) {

		if (memberInstance == null) {
			notFound()
			return
		}

		memberInstance.delete flush: true

		request.withFormat {
			form multipartForm {
				flash.message = message(code: 'default.deleted.message', args: [message(code: 'User.label', default: 'Member'), memberInstance.name])
				redirect action: "index", method: "GET"
			}
			'*' { respond memberInstance, [status: OK] }
		}
	}

	protected void notFound() {
		request.withFormat {
			form multipartForm {
				flash.message = message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'Member'), params.id])
				redirect action: "index", method: "GET"
			}
			'*' { render status: NOT_FOUND }
		}
	}

	def markFavourite() {

		Member memberInstance = Member.get(params.userId)
		Model model = Model.get(params.modelId)
		model.favourite = true
		model.save(flush: true)

		respond memberInstance, view: 'getModelTabs'
	}

	def unmarkFavourite() {

		Member memberInstance = Member.get(params.userId)
		Model model = Model.get(params.modelId)
		model.favourite = false
		model.save(flush: true)

		respond memberInstance, view: 'getModelTabs'
	}

	def getModels(Member memberInstance) {
		if (memberInstance)
			respond memberInstance
		else
			redirect action: "index", method: "GET"
	}

	def getModelTabs(Member memberInstance) {
		respond memberInstance
	}
}