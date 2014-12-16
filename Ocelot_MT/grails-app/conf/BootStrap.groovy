import ocelot.CategoryItem
import ocelot.Member
import ocelot.Palette
import ocelot.PaletteItem
import ocelot.Member
import org.springframework.web.context.support.WebApplicationContextUtils

class BootStrap {

	def init = { servletContext ->

		// Get spring and load custom marshallers
		def springContext = WebApplicationContextUtils.getWebApplicationContext(servletContext)
		springContext.getBean("customObjectMarshallers").register()

		if(true) {
			new Member(name: "Luis", login: "luis").save()
			new Member(name: "Julio", login: "julio").save()

			//Category List
			def categories = ["Start Events", "Activities", "Structural", "Gateways", "Boundary Events", "Intermediate Events", "End Events", "Swimlanes", "Nope"]

			categories.each {
				new CategoryItem(name: it).save()
			}

			File current = new File( "grails-app/utils/" )
			def path = current.getAbsolutePath()
			println(path)
			current.delete()

			new ProcessBuilder(path+"/insertToDatabase", path).start().waitFor()

			new Palette(name:"TestPalette").save(flush: true, failOnError: true)

			def p = Palette.get(1)
			def e = PaletteItem.list()
			def id;

			e.each { item ->
				id = item.categoryId
				CategoryItem.get(id).addToPaletteItems(item).save(flush: true)
				p.addToPaletteItems(item)
			}
			p.save()

		}
	}

	def destroy = {
	}

}
