import ocelot.Model
import ocelot.User

class BootStrap {

    def init = { servletContext ->
        new User(name:"Luis", login:"luis").save()
        new User(name:"Julio", login: "julio").save()
		new Model(name: "Model_1", temporal: true, user: 1L).save()
    }

    def destroy = {
    }
}
