package blank

class Bpm {

    String name
    Date dateCreated
    Date lastUpdated

    def User user
    static belongsTo = [User]

    static mapping = {
        autoTimestamp true
    }

    static constraints = {
        name blank: false
    }
}
