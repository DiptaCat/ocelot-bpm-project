package blank

class User {

    String name
    String login

    static hasMany = [bpms: Bpm]
    def favouriteBPMs = []

    Date dateCreated

    static mapping = {
        autoTimestamp true
    }

    static constraints = {
        name blank: false
        login blank: false, unique: true
    }
}
