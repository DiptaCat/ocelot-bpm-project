package blank

import grails.converters.*
import grails.rest.*

//@Resource(uri='/users')
class User {

    String name
    String login
    Date dateCreated

    static hasMany = [models: Model, favourites: Model]

    static constraints = {
        name blank:false, minSize:2
        login blank:false, unique:true, minSize:2
    }

    static mapping = {
        autoTimestamp true
    }

    String toString() {
        "${login}"
    }

    def userAsJSON = {
        return this as JSON
    }

    def userAsXML = {
        return this as XML
    }
}