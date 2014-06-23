package modeller

class UserController {
    static user = new User()
    static projects = []

    def index = {
        /*def name, index
        def op = 0

        while(op != 4){

            printMenu()

            println("> Please enter an option: ")
            op = input()

            switch (op){
                case "1":
                    println("> Insert the BPMs name: ")
                    name = input()
                    add(name)
                    break

                case "2":
                    println("> Insert the BPMs name: ")
                    name = input()
                    addOwn(name)
                    break

                case "3":
                    listAll()
                    println("> Insert the BPMs index: ")
                    index = input()
                    user.fav.add projects.get(index)
                    break

                case "4":
                    println("Bye")
                    break
            }
        }*/
    }

    def listAll = {
        projects.eachWithIndex{ list, i ->
            println("$i. $list.name")
        }
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

    def addFav (bpmId) {
        println "addFav"
        def bpm = Bpm.get(bpmId)
        if(bpm) user.fav.add bpm
    }

    def add (String name) {
        println "add"
        projects.add new Bpm(name: name)
    }

    def addOwn (String name) {
        println "addOwn"
        def bpm = new Bpm(name: name)

        user.own.add bpm

        projects.add bpm
    }

    def input = {
        System.in.newReader().readLine()
    }

    def printMenu = {
        println("1. Add new BPM")
        println("2. Add own BPM")
        println("3. Add BPM to fav")
        println("4. Exit")
    }
}
