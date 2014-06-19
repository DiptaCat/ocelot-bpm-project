package modeller

class Bpm {

    String name
    static seachable = [only: 'name']

    static constraints = {
        name blank: false, unique: true
    }
}
