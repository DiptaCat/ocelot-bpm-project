package blank

class Bpm {

    String name
    Date dateCreated
    Date dateUpdated

    def User user
    static belongsTo = [User]

    static constraints = {
        name blank: false
    }
}
