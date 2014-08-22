package util

import blank.Palette
import blank.PaletteItem
import grails.converters.JSON

/**
 * Created by sergi on 18/08/14.
 */
class PaletteMarshaller implements OcelotMarshaller{
    void register(){
        JSON.registerObjectMarshaller(Palette){Palette palette ->
            [
                    id: palette.id,
                    paletteItems: palette.paletteItems.collect{ PaletteItem paletteItem ->
                        [
                                id: paletteItem.id,
                                name: paletteItem.name,
                                description: paletteItem.description,
                                icon: paletteItem.icon,
                                category: [id: paletteItem.category.id, name: paletteItem.category.name],
                                activated: paletteItem.activated
                        ]

                    }
            ]
        }
    }
}
