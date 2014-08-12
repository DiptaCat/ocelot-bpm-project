package blank

import grails.rest.Resource

@Resource(uri='/paletteItems', formats=['json', 'xml'])
class PaletteItem {

    String name
    String description
    String category //TODO This should be an enum or whatever
    String icon
    boolean activated


    static constraints = {
    }
}
