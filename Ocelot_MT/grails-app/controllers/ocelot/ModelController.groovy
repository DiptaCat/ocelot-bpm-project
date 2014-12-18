package ocelot

import grails.converters.JSON
import grails.rest.RestfulController
import grails.transaction.Transactional

import static org.springframework.http.HttpStatus.*

@Transactional(readOnly = true)
class ModelController extends RestfulController{

    static responseFormats = ['json']

	def propertyService

	def index() {
        params.max = Math.min(params.max ?: 10, 100)
        params.offset = params.offset == null ? 0 : params.offset

        def member = Member.get(1) //TODO this is a fake user!!!
        def models = Model.findAllByUser(member, params)

        if(models == null){
            respond []
        }else{
            respond models.collect{ Model m ->
                [
                        id : m.id,
                        svg : m.svg,
                        name : m.name,
                        description: m.description
                ]
            }
        }
	}

	def show() {
        //TODO check user

        def model = Model.get params.id

		respond model
	}

	@Transactional
	def save() {
        def member = Member.get(1) //TODO this is a fake user!!!

        def jsonReq = request.JSON

        def model = new Model()

        model.name = jsonReq.name
        model.description = jsonReq.description
        model.svg = jsonReq.svg
        model.xml = jsonReq.xml
        model.json = jsonReq.json

        member.addToModels(model)
        member.save flush: true, failOnError: true

        render status: CREATED
	}

	@Transactional
	def update() {
        //TODO check user
        def jsonReq = request.JSON

        def model = Model.get params.id

        ['name', 'description', 'svg', 'xml', 'json'].each {
            if(jsonReq[it] != null){
                model."$it" = jsonReq."$it"
            }
        }

//        model.name = jsonReq.name
//        model.description = jsonReq.description
//        model.svg = jsonReq.svg
//        model.xml = jsonReq.xml
//        model.json = jsonReq.json

        model.save(flush: true, failOnError: true)

        render status: OK
	}


	@Transactional
	def delete() {
        //TODO check user
        Model model = Model.get params.id

        if(model == null){
            render status: NOT_FOUND
        }else{
            model.delete flush: true
            render status: OK
        }
    }

	def list() {

		println "Number of Models => ${Model.count}"

		def response = Model.list().collect{Model m ->
			[
			        id: m.id,
					name: m.name,
					description: m.description
			]
		}
		response = [numModels: Model.count, models: response]
		respond response
	}

	def singleModel(){

		def model = null

		try {
			model = Model.get(params.id)
		}catch (Exception e){
			render status: BAD_REQUEST
		}

		if(model == null){
			render status: NOT_FOUND
		} else {
			def response = [
			        id: model.id,
					name: model.name,
					description: model.description,
					dateCreated: model.dateCreated,
					lastUpdated: model.lastUpdated,
					temporal: model.temporal,
					svg: model.svg,
					bpmn: propertyService.injectAttributes(model.xml, model.json)
			]

			respond response
		}

	}

}
