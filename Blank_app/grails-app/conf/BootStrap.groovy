import blank.CategoryItem
import blank.Palette
import blank.PaletteItem
import blank.User
import org.springframework.web.context.support.WebApplicationContextUtils

class BootStrap {

    def init = { servletContext ->

        // Get spring and load custom marshallers
        def springContext = WebApplicationContextUtils.getWebApplicationContext(servletContext)
        springContext.getBean("customObjectMarshallers").register()


        new User(name: "Luis", login: "luis").save()
        new User(name: "Julio", login: "julio").save()


        // Category List
        def categories = ["Start Events", "Activities", "Structural", "Gateways", "Boundary Events", "Intermediate Events", "End Events"]

        categories.each {
            new CategoryItem(name: it).save()
        }





        CategoryItem test = CategoryItem.get(1)

        // Test Palette
        def userTask = new PaletteItem(
                name: "User Task",
                description: "This is a User Task and I don't know why it's used for",
                icon: "message.png",
                svg: "images/svg/usertask.svg",
                activated: true)

        userTask.save()

        def dataStore = new PaletteItem(
                name: "Data Store",
                description: "This is a Data Store",
                icon: "delete.png",
                svg: "images/svg/servicetask.svg",
                activated: true)

        dataStore.save()

        test.addToPaletteItems(userTask)
        test.addToPaletteItems(dataStore)

        new Palette(name: "Ramona")
                .addToPaletteItems(userTask)
                .addToPaletteItems(dataStore)
                .save(flush: true, failOnError: true)


    }

    def destroy = {
    }
}
