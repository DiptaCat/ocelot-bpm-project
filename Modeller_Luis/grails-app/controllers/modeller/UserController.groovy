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
        [projects:user.own]
    }

    def listFav = {
        [projects:user.fav]
    }

    def addFav (bpmId) {
        def bpm = Bpm.get(bpmId)
        if(bpm){
            user.fav.add bpm
        }
    }

    def add (String name) {
        projects.add new Bpm(name: name)
    }

    def addOwn (String name) {
        def bpm = new Bpm(name: name)
        user.own.add bpm
        projects.add bpm
    }
}
