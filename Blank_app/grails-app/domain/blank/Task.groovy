package blank

class Task {
    String name
    def User user
    Date dateCreated
    static belongsTo = [User]

    static mapping = {
        autoTimestamp true
    }
    static constraints = {
        name blank: false
    }
}
