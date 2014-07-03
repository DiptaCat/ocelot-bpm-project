package blank

class Bpm {

    String name

    def User user
    static belongsTo = [User]

    static constraints = {
    }
}
