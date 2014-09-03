package task.list

class TaskList {

    String name
    Date dateCreated


    static mapping = {
        autoTimestamp true
    }
    static constraints = {
        name blank: false
    }
}
