package ocelot

import grails.converters.JSON
import grails.rest.RestfulController
import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class FormDataController extends RestfulController {

	static responseFormats = ['json']

	def list() {
 			def forms = FormData.findAll()
		def list = []

		if(forms){
			list = forms.collect{ FormData f ->
				[
					id : f.id,
					key : f.key,
					description: f.description,
					fields : f.fields
				]
			}

		}

		respond list
	}

	def show() {
		//def member = session.user
		def form = FormData.get(params.formId)

		/*if(member.id != form.user.id){
			render status: UNAUTHORIZED
			return
		}*/

		respond form
	}

	@Transactional
	def save() {
		//def member = session.user

		def jsonReq = request.JSON

		def form = new FormData()

		form.key = jsonReq.key
		form.description = jsonReq.description
		form.fields = jsonReq.fields

		form.save(flush: true)

		render status: OK
	}

	@Transactional
	def update() {

		println params.dump()

		def jsonReq = request.JSON

		if (params.formId as int != jsonReq.id as int) {
			render status: CONFLICT
			return
		}

		def instance = FormData.get(params.formId)

		if (instance == null) {
			render status: NOT_FOUND
			return
		}

		instance.key = jsonReq.key
		instance.description = jsonReq.description
		instance.fields = jsonReq.fields.toString()

		instance.save flush: true

		render status: OK
	}

	@Transactional
	def delete() {

		FormData form = FormData.get params.formId

		if (form == null) {
			render status: NOT_FOUND
		} else {
			form.delete flush: true
			render status: OK
		}
	}
}