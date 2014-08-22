package blank

class CategoryItem {
    String name

    static hasMany = [paletteItems: PaletteItem]

    static constraints = {
    }
}
