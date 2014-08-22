package blank

import grails.converters.JSON
import grails.rest.RestfulController
import grails.transaction.Transactional

@Transactional(readOnly = true)
class PaletteController extends RestfulController {

    static responseFormats = ['json']

    PaletteController(){
        super(Palette)
    }

}
