package blank

/*import grails.rest.*
import groovy.sql.Sql*/

//@Resource(uri='/models')
class Model {

    String name
    Date dateCreated
    Date lastUpdated
    boolean temporal

    static belongsTo = [user: User]
    static hasMany = [usersFavs: User]
    static mappedBy = [usersFavs: "none"]

    //def dataSource

    static constraints = {
        name blank: false, unique: 'user'
    }

    static mapping = {
        // batchSize 10
        autoTimestamp true
        usersFavs joinTable: [name: 'FavModels', column: 'UserId', key: 'ModelId']
        usersFavs cascade: 'save-update'
    }

    /*def beforeDelete = {
        Sql sql = Sql.newInstance(dataSource)
        sql.executeUpdate('delete from FavModels where ModelId is ?', [this.id])
    }*/
}