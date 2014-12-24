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

		if (initDatabase) {
			def user = new Member(name: "test", login: "test")

			//Category List
			def categories = ["Start Events", "Activities", "Structural", "Gateways", "Boundary Events", "Intermediate Events", "End Events", "Swimlanes", "Udefined"]

			categories.each {
				new CategoryItem(name: it).save()
			}

			File current = new File("grails-app/utils/")
			def filesPath = current.getAbsolutePath()
			current.delete()

			current = new File("scripts")
			def scriptPath = current.getAbsolutePath()
			current.delete()

			new ProcessBuilder(scriptPath + "/insertToDatabase", filesPath).start().waitFor()

			def palette = new Palette(name: "TestPalette")

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
