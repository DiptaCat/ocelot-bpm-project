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

    void addFav (bpmId) {
        println "addFav"
        def bpm = Bpm.get(bpmId)
        if(bpm) user.fav.add bpm
    }

    void add (String name) {
        println "add"
        projects.add new Bpm(name: name)
    }

    void addOwn (String name) {
        println "addOwn"
        def bpm = new Bpm(name: name)

        user.own.add bpm

        projects.add bpm
    }
}
