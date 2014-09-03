package blank

class PaletteItem {

    String name, description,icon, svg, properties
    boolean activated

    static belongsTo = [palettes: Palette, category: CategoryItem]
    static hasMany = [palettes: Palette]

    static constraints = {
//        properties column: "properties", sqlType: "varchar(5000)"
//        icon column: "icon", sqlType: "varchar(5000)"
        svg maxSize: 5000
    }
}
