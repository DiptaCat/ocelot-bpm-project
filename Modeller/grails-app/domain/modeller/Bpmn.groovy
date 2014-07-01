package modeller

class Bpmn {

    String name

    def User user
    static belongsTo = [User]

    static constraints = {
        name unique: true, blank: false
    }

}
