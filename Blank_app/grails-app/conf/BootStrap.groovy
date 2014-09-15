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
//                props: "[]",
                svg: '<svg height=\"90\" version=\"1.1\" width=\"110\" xmlns=\"http://www.w3.org/2000/svg\" style=\"overflow: hidden; position: relative;\"><desc style=\"-webkit-tap-highlight-color: rgba(0, 0, 0, 0);\">Created with Raphaël 2.1.0</desc><defs style=\"-webkit-tap-highlight-color: rgba(0, 0, 0, 0);\"></defs><rect x=\"5\" y=\"5\" width=\"100\" height=\"80\" r=\"5\" rx=\"5\" ry=\"5\" fill=\"#ffffff\" stroke=\"#ff8c00\" stroke-width=\"2\" stroke-linecap=\"round\" stroke-linejoin=\"round\" stroke-opacity=\"1\" id=\"svg_1\" style=\"-webkit-tap-highlight-color: rgba(0, 0, 0, 0); stroke-linecap: round; stroke-linejoin: round; stroke-opacity: 1;\"></rect><text x=\"55\" y=\"45\" text-anchor=\"middle\" font=\"10px &quot;Arial&quot;\" stroke=\"none\" fill=\"#000000\" style=\"-webkit-tap-highlight-color: rgba(0, 0, 0, 0); text-anchor: middle; font-style: normal; font-variant: normal; font-weight: normal; font-size: 12px; line-height: normal; font-family: Arial, Helvetica, sans-serif;\" font-size=\"12px\" font-family=\"Arial, Helvetica, sans-serif\"><tspan dy=\"-3.203125\" style=\"-webkit-tap-highlight-color: rgba(0, 0, 0, 0);\">Business Rule</tspan><tspan dy=\"14.399999999999999\" x=\"55\" style=\"-webkit-tap-highlight-color: rgba(0, 0, 0, 0);\">Task</tspan></text><rect x=\"10\" y=\"9\" width=\"17\" height=\"12\" r=\"0\" rx=\"0\" ry=\"0\" fill=\"#ffffff\" stroke=\"#808080\" stroke-width=\"1\" stroke-linecap=\"round\" stroke-linejoin=\"round\" stroke-opacity=\"1\" style=\"-webkit-tap-highlight-color: rgba(0, 0, 0, 0); stroke-linecap: round; stroke-linejoin: round; stroke-opacity: 1;\"></rect><rect x=\"10\" y=\"9\" width=\"17\" height=\"4\" r=\"0\" rx=\"0\" ry=\"0\" fill=\"#808080\" stroke=\"#808080\" stroke-width=\"1\" stroke-linecap=\"round\" stroke-linejoin=\"round\" stroke-opacity=\"1\" style=\"-webkit-tap-highlight-color: rgba(0, 0, 0, 0); stroke-linecap: round; stroke-linejoin: round; stroke-opacity: 1;\"></rect><path fill=\"none\" stroke=\"#808080\" d=\"M2,10L19,10M7,4L7,14\" stroke-width=\"1\" stroke-linecap=\"butt\" stroke-linejoin=\"butt\" stroke-opacity=\"1\" transform=\"matrix(1,0,0,1,8,7)\" style=\"-webkit-tap-highlight-color: rgba(0, 0, 0, 0); stroke-linecap: butt; stroke-linejoin: round; stroke-opacity: 1;\"></path></svg>',
                activated: true)

        userTask.save()

        def dataStore = new PaletteItem(
                name: "Data Store",
                description: "This is a Data Store",
                icon: "delete.png",
                //props: "[]",
                svg: '<svg height="90" version="1.1" width="110" xmlns="http://www.w3.org/2000/svg" style="overflow: hidden; position: relative;"><desc style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0);">Created with Raphaël 2.1.0</desc><defs style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0);"></defs><rect x="5" y="5" width="100" height="80" r="5" rx="5" ry="5" fill="#ffffff" stroke="#ff8c00" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" stroke-opacity="1" id="svg_1" style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0); stroke-linecap: round; stroke-linejoin: round; stroke-opacity: 1;"></rect><text x="55" y="45" text-anchor="middle" font="10px &quot;Arial&quot;" stroke="none" fill="#000000" style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0); text-anchor: middle; font-style: normal; font-variant: normal; font-weight: normal; font-size: 12px; line-height: normal; font-family: Arial, Helvetica, sans-serif;" font-size="12px" font-family="Arial, Helvetica, sans-serif"><tspan dy="4" style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0);">Receive Task</tspan></text><path fill="#ffffff" stroke="#808080" d="M7,10L7,20L23,20L23,10ZM7,10L15,16L23,10" stroke-width="1" stroke-linecap="round" stroke-linejoin="round" stroke-opacity="1" transform="matrix(1,0,0,1,5,5)" style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0); stroke-linecap: round; stroke-linejoin: round; stroke-opacity: 1;"></path></svg>',
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
