package modeller

class UserController {

    static user = new User()
    static projects = []

    def index = {

        projects.add new Bpm(name: "BPM-1")
        projects.add new Bpm(name: "BPM-2")
        projects.add new Bpm(name: "BPM-3")
        projects.add new Bpm(name: "BPM-4")
        projects.add new Bpm(name: "BPM-5")
        projects.add new Bpm(name: "BPM-6")

    }

    def list = {
        [projects:projects]
    }

    def addFav (bpmId) {
        def bpm = Bpm.get(bpmId)
        if(bpm){
            user.fav.add bpm
        }
    }

    def addOwn(bpmName) {
        user.own.add new Bpm(name: bpmName)
    }
}
