package blank

class User {

    String name
    String login

    static hasMany = [bpms: Bpm]
    //static favs = [favs: Bpm]

    static constraints = {
        name blank: false
        login blank: false, unique: true
    }
}
