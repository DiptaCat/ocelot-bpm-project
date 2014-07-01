package modeller

class Bpmn {

    String name

    static constraints = {
        name blank: false, unique: true
    }
}
