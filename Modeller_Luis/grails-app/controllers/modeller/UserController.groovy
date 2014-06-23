package modeller

class UserController {
    static user = new User()
    static projects = []

    def index = {
    }

    def list = {
        [projects:projects]
    }

    def listOwn = {
        [own:user.own]
    }

    def listFav = {
        [fav:user.fav]
    }

    def listRecent = {
        def max=projects.size()-1
        def min=max-5
        def rec=projects.subList(min,max)
        [rec:rec]
    }

    def addFav (String name) {

        user.fav.add new Bpm(name:name)

        render (view:'index.gsp')
    }

    def add (String name) {

        projects.add new Bpm(name: name)

        render (view:'index.gsp')
    }

    def addOwn (String name) {

        def bpm = new Bpm(name: name)

        user.own.add bpm

        projects.add bpm

        render (view:'index.gsp')
    }
}
