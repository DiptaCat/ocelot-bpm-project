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
        if(projects.size()<6){
            [rec:projects]
        } else {
            def max = projects.size() - 1
            def min = max - 5
            def rec = projects.subList(min, max)
            [rec: rec]
        }
    }

    def addFav (long id) {

        def bpm = Bpm.get(id)

        if(user.fav.contains(bpm) == false) user.fav.add bpm

        render (view:'index.gsp')
    }

    def add (String name) {

        projects.add new Bpm(name: name).save()

        render (view:'index.gsp')
    }

    def addOwn (String name) {

        def bpm = new Bpm(name: name).save()

        user.own.add bpm

        projects.add bpm

        render (view:'index.gsp')
    }
}
