package ocelot

import grails.converters.JSON
import grails.rest.RestfulController
import grails.transaction.Transactional

import static org.springframework.http.HttpStatus.*

@Transactional(readOnly = true)
class ModelController extends RestfulController {

	static responseFormats = ['json']

	def propertyService

	def index() {
		params.max = Math.min(params.max ?: 10, 100)
		params.offset = params.offset == null ? 0 : params.offset

		def models = Model.findAllByUser(session.user, params)

		if (models) {
			respond models.collect { Model m ->
				[
						id         : m.id,
						svg        : m.svg,
						name       : m.name,
						description: m.description
				]
			}
		}

		[]
	}

	def show() {
		//TODO check user
		def member = session.user

		def model = Model.get params.id

		if (model.user.id == member.id) {
			respond model
		} else {
			render status: UNAUTHORIZED
		}


	}

	@Transactional
	def save() {
		def param = request.JSON

		def model = new Model()

		model.name = param.name
		model.description = param.description
		model.xml = param.xml
		model.json = param.json
		model.svg = param.svg
		model.user = session.user

		model.save flush: true

		render status: CREATED
	}

	@Transactional
	def update() {

		def jsonReq = request.JSON
		def model = Model.get params.id

		if (model.user.name != session.user.name) {
			render status: UNAUTHORIZED
		} else {
			model.properties = jsonReq
			model.user = session.user
			model.json = jsonReq.json

			model.save flush: true, failOnError: true

			render status: OK
		}
	}


	@Transactional
	def delete() {
		//TODO check user
		def member = session.user

		Model model = Model.get params.id

		if (model == null) {
			render status: NOT_FOUND
		} else if (model.user.id == member.id) {
			model.delete flush: true
			render status: OK
		} else {
			render status: UNAUTHORIZED
		}
	}

	def list() {

		def response = Model.list().collect { [id: it.id, name: it.name, description: it.description] }
		response = [numModels: Model.count, models: response]
		respond response
	}

	def export() {

		def model = null

		try {
			model = Model.get(params.id)

		} catch (e) {
			render status: BAD_REQUEST
		}

		if (model) {

			def response = [
					id         : model.id,
					name       : model.name,
					description: model.description,
					dateCreated: model.dateCreated,
					lastUpdated: model.lastUpdated,
					temporal   : model.temporal,
					svg        : model.svg,
					bpmn       : propertyService.injectAttributes(model.xml, model.json)
			]

			respond response

		} else {
			render status: NOT_FOUND
		}
	}
}
