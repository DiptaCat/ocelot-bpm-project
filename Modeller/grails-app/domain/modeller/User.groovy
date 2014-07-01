package modeller

class User {

    String name

    static hasMany = [bpmn: Bpmn]

    static constraints = {
    }

}
