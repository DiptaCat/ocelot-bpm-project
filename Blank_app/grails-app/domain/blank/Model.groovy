package blank


class Model {

    String name
    Date dateCreated
    Date lastUpdated
    boolean temporal

    static belongsTo = [user: User]
    static hasMany = [usersFavs: User]
    static mappedBy = [usersFavs: "none"]

    static constraints = {
        name blank: false, unique: 'user'
    }

    static mapping = {
        // batchSize 10
        autoTimestamp true
        usersFavs joinTable: [name: 'FavModels', column: 'UserId', key: 'ModelId']
        usersFavs cascade: 'save-update'
    }
}