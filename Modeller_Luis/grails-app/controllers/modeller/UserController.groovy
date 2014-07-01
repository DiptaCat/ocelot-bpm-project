package modeller

class UserController {

    def user = new User()
    def projects = []

    def index = {}

    def list = {
        [projects:projects]
    }

    def listOwn = {
        [own:user.hasMany]
    }

    def listFav = {
        [fav:user.fav]
    }

    def listRecent = {

        if(projects.size()<6) [rec: projects.reverse()]
        else {

            def max = projects.size()
            def min = max - 5
            def rec = projects.subList(min, max).reverse()
            [rec: rec]
        }
    }

    def addFav (long id) {

        def bpm = Bpmn.get(id)

        user.fav.add bpm

        render (view:'index.gsp')
    }

    def add (String name) {

        def bpm = new Bpmn(name: name).save()

        projects.add bpm

        render (view:'index.gsp')
    }

    def addOwn (String name) {

        def bpm = new Bpmn(name: name).save()

        user.own.add bpm

        projects.add bpm

        render (view:'index.gsp')
    }
}