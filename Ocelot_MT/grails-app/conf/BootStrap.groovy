import blank.User

class BootStrap {

    def init = { servletContext ->
        new User(name:"Luis", login:"luis").save()
        new User(name:"Julio", login: "julio").save()
    }

    def destroy = {
    }
}
