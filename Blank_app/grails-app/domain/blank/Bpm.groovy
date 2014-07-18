package blank
import grails.converters.*

class Bpm {

    String name
    Date dateCreated
    Date lastUpdated

    boolean temporal

    def User user


    static mapping = {
        batchSize 10
        autoTimestamp true
    }

    static constraints = {
        name blank: false, unique: true
    }

    def userAsJSON = {
        return this as JSON
    }

    def userAsXML = {
        return this as XML
    }
}
