import ocelot.CategoryItem
import ocelot.Member
import ocelot.Palette
import ocelot.PaletteItem
import org.springframework.web.context.support.WebApplicationContextUtils

class BootStrap {

	def initDatabase = true;

	def init = { servletContext ->

		// Get spring and load custom marshallers
		def springContext = WebApplicationContextUtils.getWebApplicationContext(servletContext)
		springContext.getBean("customObjectMarshallers").register()

		if (PaletteItem.count() == 0) {
			def user = new Member(name: "test", login: "test")

			//Category List
			def categories = ["Start Events", "Activities", "Structural", "Gateways", "Boundary Events", "Intermediate Events", "End Events", "Swimlanes", "Undefined"]

			categories.each {
				new CategoryItem(name: it).save()
			}

			def name, cat, icon, level, elem, props, fields, pItem

			def elements = new File("grails-app/utils/MainElements")

			elements.eachLine { line ->
				if (!line.isEmpty()) {
					if (!line.contains("svg")) {
						fields = line.tokenize("?")
						name = fields[0]
						cat = fields[1].toLong()
						icon = fields[2]
						level = fields[3]
						elem = "bpmn:"+fields[4]
						props = fields[5]
					} else {
						pItem = new PaletteItem(name: name, category: CategoryItem.get(cat), icon: icon, level: level, bpmnElem: elem, props: props, svg: line, activated: true, description: "no desc")
						pItem.save(flush: true, failOnError: true)
					}
				}
			}

			def events = new File("grails-app/utils/Events")

			events.eachLine { line ->
				if (!line.isEmpty()) {
					if (!line.contains("svg")) {
						fields = line.tokenize("?")
						name = fields[0]
						cat = fields[1].toLong()
						level = fields[2]
						elem = "bpmn:"+fields[3]
						props = fields[4]
					} else {
						pItem = new PaletteItem(name: name, category: CategoryItem.get(cat), icon: 'none(2).png', level: level, bpmnElem: elem, props: props, svg: line, activated: true, description: "no desc")
						pItem.save(flush: true, failOnError: true)
					}
				}
			}

			def palette = new Palette()

			user.palette = palette
			user.save flush: true, failOnError: true

			def e = PaletteItem.list()
			def id

			e.each { item ->
				id = item.categoryId
				CategoryItem.get(id).addToPaletteItems(item).save(flush: true)
				palette.addToPaletteItems(item)
			}

		}
	}

	def destroy = {
	}

}
