package ocelot

import grails.rest.RestfulController
import grails.transaction.Transactional
import static org.springframework.http.HttpStatus.*

@Transactional(readOnly = false)
class PaletteItemController extends RestfulController {

	static responseFormats = ['json']

	PaletteItemController() {
		super(PaletteItem)
	}

	@Override
	def save() {
        def member = session.user
        def palette = Palette.get(params.id)

        if(member.id != palette.user.id){
            render status: UNAUTHORIZED
            return
        }

		def jsonReq = request.JSON

		def item = new PaletteItem()

		item.name = jsonReq.name
		item.description = jsonReq.description
		item.activated = jsonReq.activated
		item.props = jsonReq.props

		//TODO dar al usuario la opcion de subir su svg y subir o seleccionar de una lista el icono
		item.icon = "wrench"
		item.svg = "hola"

		item.level = jsonReq.level
		item.bpmnElem = "bpmn:UndefinedTask"

		def category = CategoryItem.get(jsonReq.category.id)
		category.addToPaletteItems(item)

		item.save(flush: true)

		palette.addToPaletteItems(item).save flush: true, failOnError: true


		render status: OK
	}


	@Override
	def update() {
        //TODO check user!!

		def jsonReq = request.JSON

        if(member.id != palette.user.id){
            render status: UNAUTHORIZED
            return
        }

		if (params.id != jsonReq.id) {
			render status: CONFLICT
		}

		def instance = PaletteItem.get(params.id)

		if (instance == null) {
			render status: NOT_FOUND
		}

		instance.name = jsonReq.name
		instance.description = jsonReq.description
		instance.props = jsonReq.props.toString()
		instance.activated = jsonReq.activated
		instance.icon = jsonReq.icon
		instance.level = jsonReq.level
		instance.bpmnElem = jsonReq.bpmnElem

		if (instance.category.id != jsonReq.category.id) {
			def category = CategoryItem.get(instance.category.id)
			category.removeFromPaletteItems(instance)

			category = CategoryItem.get(jsonReq.category.id)
			category.addToPaletteItems(instance)
		}

		instance.save flush: true

		render status: OK
	}

}
