package blank

class PaletteItem {

    String name, description,icon, svg, properties
    boolean activated

    static belongsTo = [category: CategoryItem, palette: Palette]

    static constraints = {
        properties column: "properties", sqlType: "varchar(5000)"
    }
}
