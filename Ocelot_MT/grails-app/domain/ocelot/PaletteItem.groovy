package ocelot

import palette.APaletteItem

class PaletteItem extends APaletteItem{

    boolean activated

    static belongsTo = [category: CategoryItem]

    static constraints = {
//        properties column: "properties", sqlType: "varchar(5000)"
//        icon column: "icon", sqlType: "varchar(5000)"
        props nullable : true, maxSize: 5000
        svg maxSize: 5000
    }
}
