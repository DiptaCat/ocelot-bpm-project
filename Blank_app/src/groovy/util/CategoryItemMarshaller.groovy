package util

import blank.CategoryItem
import grails.converters.JSON

/**
 * Created by sergi on 18/08/14.
 */
class CategoryItemMarshaller implements OcelotMarshaller{
    void register(){
        JSON.registerObjectMarshaller(CategoryItem){ CategoryItem categoryItem ->
            [
                    id: categoryItem.id,
                    name: categoryItem.name
            ]

        }
    }
}
