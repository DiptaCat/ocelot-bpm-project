package ocelot

import palette.APaletteItem

class CustomPaletteItem extends APaletteItem{

    boolean activated //TODO think about boolean activated

    static belongsTo = [palettes: Palette, category: CategoryItem]
    static hasMany = [palettes: Palette]

    static constraints = {
        props nullable : true, maxSize: 5000
        svg maxSize: 5000
    }
}
