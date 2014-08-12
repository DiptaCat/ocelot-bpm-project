package blank

import grails.rest.*

//TODO this class should disappear because it's redundant if a user hasMany palettItem

@Resource(uri='/paletteRest', formats=['json', 'xml'])
class Palette {

//    static belongsTo = [user: User]
    static hasMany = [ paletteItems: PaletteItem]

    static constraints = {
    }
}
