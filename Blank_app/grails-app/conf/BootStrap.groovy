import blank.User

class BootStrap {

    def init = { servletContext ->
        new User(name:"Luis", login:"guapo").save()
        new User(name:"Julio", login: "feo").save()
    }

    def destroy = {
    }
}
