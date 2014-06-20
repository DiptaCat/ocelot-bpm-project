package modeller

class Bpm {

    String name

    static constraints = {
        name blank: false, unique: true
    }
}
