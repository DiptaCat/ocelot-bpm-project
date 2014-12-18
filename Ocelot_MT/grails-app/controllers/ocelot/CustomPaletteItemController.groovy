package ocelot

import grails.rest.RestfulController
import grails.transaction.Transactional

import static org.springframework.http.HttpStatus.*

@Transactional(readOnly = true)
class CustomPaletteItemController extends RestfulController {

	static responseFormats = ['json']

	CustomPaletteItemController() {
		super(CustomPaletteItem)
	}

	@Transactional
	def save() {

		def jsonReq = request.JSON

		def item = new CustomPaletteItem()

		item.name = jsonReq.name
		item.description = jsonReq.description
		item.activated = jsonReq.activated
		item.props = jsonReq.props

		//TODO a user should upload it's own svg and icon
		item.icon = "wrench"
		item.svg = '<svg height="90" version="1.1" width="110" xmlns="http://www.w3.org/2000/svg" style="overflow: hidden; position: relative;"><desc style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0);">Created with Raphaël 2.1.0</desc><defs style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0);"></defs><rect x="5" y="5" width="100" height="80" r="5" rx="5" ry="5" fill="#ffffff" stroke="#808080" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" stroke-opacity="1" id="svg_1" style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0); stroke-linecap: round; stroke-linejoin: round; stroke-opacity: 1;"></rect><text x="55" y="45" text-anchor="middle" font="10px &quot;Arial&quot;" stroke="none" fill="#000000" style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0); text-anchor: middle; font-style: normal; font-variant: normal; font-weight: normal; font-size: 12px; line-height: normal; font-family: Arial, Helvetica, sans-serif;" font-size="12px" font-family="Arial, Helvetica, sans-serif"><tspan dy="4" style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0);">' + item.name + '</tspan></text></svg>'

		item.level = jsonReq.level
		item.bpmnElem = "bpmn:Task"

		def category = CategoryItem.get(jsonReq.category.id)
		category.addToCustomPaletteItems(item)

		item.save(flush: true)

		def palette = Palette.get(params.id)

		palette.addToCustomPaletteItems(item).save flush: true, failOnError: true


		render status: OK
	}


	@Transactional
	def update() {
		def jsonReq = request.JSON

		if (params.id != jsonReq.id) {
			render status: CONFLICT
		}

		def instance = CustomPaletteItem.get(params.id)

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

		//COSA MEVA
//        println("Hola carapolla " + JSON.parse(jsonReq.props.toString()))

		if (instance.category.id != jsonReq.category.id) {
			def category = CategoryItem.get(instance.category.id)
			category.removeFromCustomPaletteItems(instance)

			category = CategoryItem.get(jsonReq.category.id)
			category.addToCustomPaletteItems(instance)
		}

		instance.save flush: true

		//TODO preguntar a Ruben què fer en cas d'error

//        println instance.dump()

		render status: OK
	}

	@Transactional
	def delete() {
		//TODO check user
		CustomPaletteItem item = CustomPaletteItem.get params.id

		if (item == null) {
			render status: NOT_FOUND
		} else {
			item.delete flush: true
			render status: OK
		}
	}
}