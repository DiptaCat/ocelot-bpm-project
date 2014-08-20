package blank

class Palette {

    String name
//    static belongsTo = [user: User]
    static hasMany = [ paletteItems: PaletteItem]

    static constraints = {
    }
}
