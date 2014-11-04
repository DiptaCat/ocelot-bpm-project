package ocelot

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import grails.converters.JSON

@Transactional(readOnly = true)
class ModelerController {

	//static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

	def index() {

	}

	def _properties(PaletteItem element){
		[props: JSON.parse(element.props.toString())]
	}

	def show(Modeler modelerInstance) {
		respond modelerInstance
	}

	def create() {
		respond new Modeler(params)
	}

	@Transactional
	def save(Modeler modelerInstance) {
		if (modelerInstance == null) {
			notFound()
			return
		}

		if (modelerInstance.hasErrors()) {
			respond modelerInstance.errors, view: 'create'
			return
		}

		modelerInstance.save flush: true

		request.withFormat {
			form multipartForm {
				flash.message = message(code: 'default.created.message', args: [message(code: 'modeler.label', default: 'modeler'), modelerInstance.id])
				redirect modelerInstance
			}
			'*' { respond modelerInstance, [status: CREATED] }
		}
	}

	def edit(Modeler modelerInstance) {
		respond modelerInstance
	}

	@Transactional
	def update(Modeler modelerInstance) {
		if (modelerInstance == null) {
			notFound()
			return
		}

		if (modelerInstance.hasErrors()) {
			respond modelerInstance.errors, view: 'edit'
			return
		}

		modelerInstance.save flush: true

		request.withFormat {
			form multipartForm {
				flash.message = message(code: 'default.updated.message', args: [message(code: 'modeler.label', default: 'modeler'), modelerInstance.id])
				redirect modelerInstance
			}
			'*' { respond modelerInstance, [status: OK] }
		}
	}

	@Transactional
	def delete(Modeler modelerInstance) {

		if (modelerInstance == null) {
			notFound()
			return
		}

		modelerInstance.delete flush: true

		request.withFormat {
			form multipartForm {
				flash.message = message(code: 'default.deleted.message', args: [message(code: 'modeler.label', default: 'modeler'), modelerInstance.id])
				redirect action: "index", method: "GET"
			}
			'*' { render status: NO_CONTENT }
		}
	}

	protected void notFound() {
		request.withFormat {
			form multipartForm {
				flash.message = message(code: 'default.not.found.message', args: [message(code: 'modeler.label', default: 'modeler'), params.id])
				redirect action: "index", method: "GET"
			}
			'*' { render status: NOT_FOUND }
		}
	}
}