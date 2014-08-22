package blank

import grails.rest.RestfulController
import grails.transaction.Transactional

@Transactional(readOnly = true)
class CategoryItemController extends RestfulController{

    static responseFormats = ['json']

    CategoryItemController(){
        super(CategoryItem)
    }


}