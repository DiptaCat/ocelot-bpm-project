package blank

class Palette {

    String name

    static hasMany = [ paletteItems: PaletteItem]

    static constraints = {
    }
}
