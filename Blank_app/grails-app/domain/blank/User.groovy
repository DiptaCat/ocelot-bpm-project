package blank


class User {

    String name
    String login
    Date dateCreated

	//static hasOne = [palette: Palette] //TODO què és un user una persona d'un model o un usuari que es pot loggejar....

    static hasMany = [models: Model, favourites: Model]
    static mappedBy = [favourites: "none"]

    static constraints = {
//	    palette nullable: true
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