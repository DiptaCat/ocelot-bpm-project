package blank

class Palette {

    String name

    static hasMany = [ paletteItems: PaletteItem, customPaletteItems: CustomPaletteItem]

    static constraints = {
    }
}
