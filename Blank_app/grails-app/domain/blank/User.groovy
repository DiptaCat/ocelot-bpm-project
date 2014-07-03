package blank

class User {

    String name
    String login

    static hasMany = [bpms: Bpm]

    static constraints = {
    }
}
