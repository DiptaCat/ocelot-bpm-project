package blank


class User {

    String name
    String login
    Date dateCreated

    static hasMany = [models: Model, favourites: Model]
    static mappedBy = [favourites: "none"]

    static constraints = {
        name blank:false, minSize:2
        login blank:false, unique:true, minSize:2
    }

    static mapping = {
        autoTimestamp true
        favourites joinTable: [name: 'FavModels', column: 'ModelId', key: 'UserId']
        favourites cascade: 'save-update'
    }

    String toString() {
        "${login}"
    }
}