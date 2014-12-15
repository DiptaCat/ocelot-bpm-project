package ocelot

import grails.converters.JSON
import grails.transaction.Transactional

import static org.springframework.http.HttpStatus.*

@Transactional(readOnly = true)
class ModelController {

	def propertyService
	//static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

	def index(Integer max) {
		params.max = Math.min(max ?: 10, 100)
		[modelInstanceList: Model.list(params), modelInstanceCount: Model.count()]
	}

	def show(Model modelInstance) {
		respond modelInstance
	}

	def create() {
		respond new Model(params)
	}

	def edit(Model modelInstance) {
		respond modelInstance
	}

	@Transactional
	def save(Model modelInstance) {
		if (modelInstance == null) {
			notFound()
			return
		}

		if (modelInstance.hasErrors()) {
			respond modelInstance.errors, view: 'create'
			return
		}

		modelInstance.save flush: true

		request.withFormat {
			form multipartForm {
				flash.message = message(code: 'default.created.message', args: [message(code: 'Model.label', default: 'Model'), modelInstance.name])
				redirect modelInstance
			}
			'*' { respond modelInstance, [status: CREATED] }
		}
	}

	@Transactional
	def update(Model modelInstance) {
		if (modelInstance == null) {
			notFound()
			return
		}

		if (modelInstance.hasErrors()) {
			respond modelInstance.errors, view: 'edit'
			return
		}

		modelInstance.save flush: true

		request.withFormat {
			form multipartForm {
				flash.message = message(code: 'default.updated.message', args: [message(code: 'Model.label', default: 'Model'), modelInstance.name])
				redirect modelInstance
			}
			'*' { respond modelInstance, [status: OK] }
		}
	}

	@Transactional
	def delete(Model modelInstance) {

		if (modelInstance == null) {
			notFound()
			return
		}

		modelInstance.delete flush: true

		request.withFormat {
			form multipartForm {
				flash.message = message(code: 'default.deleted.message', args: [message(code: 'Model.label', default: 'Model'), modelInstance.name])
				redirect action: "index", method: "GET"
			}
			'*' { render status: NO_CONTENT }
		}
	}

	protected void notFound() {
		request.withFormat {
			form multipartForm {
				flash.message = message(code: 'default.not.found.message', args: [message(code: 'Model.label', default: 'Model'), params.id])
				redirect action: "index", method: "GET"
			}
			'*' { render status: NOT_FOUND }
		}
	}

	def display(Model modelInstance) {
		if (params.id) {
			if (modelInstance) render modelInstance as JSON
			else render(status: 404, text: 'Model not found')
		} else render Model.list() as JSON
	}

	def exportToFile() {

		//Model.get(params.modelId)
		//TODO call propertyservice to add attributes
		def model = Model.get(params.id)
		String xmlToConvert = propertyService.injectAttributes(model.xml,model.json)

		byte[] bytes = xmlToConvert.bytes

		response.contentType 'text/plain'
		response.setHeader "Content-disposition", "filename=\"model.bpmn\""
		response.contentLength = bytes.length
		response.outputStream << bytes
		response.outputStream.flush()
	}

	def list() {
		params.max = Math.min(max ?: 10, 100)
		[modelInstanceList: Model.list(params), modelInstanceCount: Model.count()]
	}
}
