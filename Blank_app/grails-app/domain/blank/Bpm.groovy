package blank
import grails.converters.*
import groovy.sql.Sql
import org.springframework.jdbc.datasource.DataSourceUtils

class Bpm {

    String name
    Date dateCreated
    Date lastUpdated

    boolean temporal

    def User user

    def dataSource

    static mapping = {
        batchSize 10
        autoTimestamp true
    }

    static constraints = {
        name blank: false, unique: true
    }

    def beforeDelete = {
        try {
            Sql sql = Sql.newInstance(dataSource)
            sql.executeUpdate('delete from FavBpm where BpmId is ?', [this.id])
        } finally {}
    }

    def userAsJSON = {
        return this as JSON
    }

    def userAsXML = {
        return this as XML
    }
}
