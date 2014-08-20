package blank

class PaletteItem {

    String name, description,icon, properties
    boolean activated

    static belongsTo = [category: CategoryItem]

    static constraints = {
        properties column: "properties", sqlType: "varchar(5000)"
    }
}
