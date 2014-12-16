package util

import grails.converters.JSON
import ocelot.CustomPaletteItem
import ocelot.PaletteItem

/**
 * Created by sergi on 16/12/14.
 */
class CustomPaletteItemMarshaller implements OcelotMarshaller {
    void register() {
        JSON.registerObjectMarshaller(CustomPaletteItem) { CustomPaletteItem paletteItem ->

            def pProps = paletteItem.props == null ? [] : JSON.parse(paletteItem.props.toString())

            [
                    id         : paletteItem.id,
                    name       : paletteItem.name,
                    description: paletteItem.description,
                    icon       : paletteItem.icon,
                    svg        : paletteItem.svg,
                    category   : [id: paletteItem.category.id, name: paletteItem.category.name],
                    activated  : paletteItem.activated,
                    props      : pProps,
                    level      : paletteItem.level,
                    bpmnElem   : paletteItem.bpmnElem
            ]
        }
    }
}