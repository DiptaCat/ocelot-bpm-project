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

    def show(){
        def member = session.user
        def palette = Palette.get(params.id)
        def item = CustomPaletteItem.get(params.itemId)

        if(member.id != palette.user.id && item.palette.id == palette.id){
            render status: UNAUTHORIZED
            return
        }

        respond item
    }

	@Transactional
	def save() {
        def member = session.user
        def palette = Palette.get(params.id)

        if(member.id != palette.user.id){
            render status: UNAUTHORIZED
            return
        }

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

		palette.addToCustomPaletteItems(item).save flush: true, failOnError: true


		render status: OK
	}


	@Transactional
	def update() {
        def member = session.user

        println params.dump()

        def palette = Palette.get(params.id)

        if(member.id != palette.user.id){
            render status: UNAUTHORIZED
            return
        }
		def jsonReq = request.JSON

        println "params.itemId = "+params.itemId+" - jsonReq.id = "+jsonReq.ids
		if (params.itemId as int != jsonReq.id as int) {
			render status: CONFLICT
            return
		}

		def instance = CustomPaletteItem.get(params.itemId)

		if (instance == null) {
			render status: NOT_FOUND
            return
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
			category.removeFromCustomPaletteItems(instance)

			category = CategoryItem.get(jsonReq.category.id)
			category.addToCustomPaletteItems(instance)
		}

		instance.save flush: true

		render status: OK
	}

	@Transactional
	def delete() {
		//TODO check user
		CustomPaletteItem item = CustomPaletteItem.get params.itemId

		if (item == null) {
			render status: NOT_FOUND
		} else {
			item.delete flush: true
			render status: OK
		}
	}
}