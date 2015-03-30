package ocelot

import grails.converters.JSON

class MainController {

    def index() {

        redirect(uri: "/palette#/modelerIndex")
        return
    }

    def load(){

    }

    def _properties(PaletteItem element){
        [props: JSON.parse(element.props.toString())]
    }
}
