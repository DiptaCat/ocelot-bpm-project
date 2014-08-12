import blank.Palette
import blank.PaletteItem
import blank.User

class BootStrap {

    def init = { servletContext ->
        new User(name:"Luis", login:"luis").save()
        new User(name:"Julio", login: "julio").save()

        // Test Palette
        def userTask = new PaletteItem(name: "User Task",
                description: "This is a User Task and I don't know why it's used for",
                icon: "UT",
                category: "Task",
                activated: true)
        userTask.save(flush: true, failOnError: true)

        def dataStore = new PaletteItem(name: "Data Store",
                description: "This is a Data Store",
                icon: "DT",
                category: "Data",
                activated: true)

        dataStore.save(flush: true, failOnError: true)



        new Palette()
                .addToPaletteItems(userTask)
                .addToPaletteItems(dataStore)
        .save(flush: true, failOnError: true)


    }

    def destroy = {
    }
}
