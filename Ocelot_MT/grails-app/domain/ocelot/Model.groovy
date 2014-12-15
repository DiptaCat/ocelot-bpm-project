package ocelot

class Model {

	String name //, description
	Date dateCreated
	Date lastUpdated
	boolean temporal
	String svg
	String xml
	String json

	static belongsTo = [user: Member]
	boolean favourite = false
	/*static hasMany = [usersFavs: Member]
	static mappedBy = [usersFavs: "none"]*/

	static constraints = {
		name blank: false, unique: 'user'
		svg nullable: true
		xml nullable: true
		json nullable: true
	}

	static mapping = {
		svg type: "text"
		xml type: "text"
		json type: "text"

		// batchSize 10
		autoTimestamp true
		/*usersFavs joinTable: [name: 'FavModels', column: 'UserId', key: 'ModelId']
		usersFavs cascade: 'save-update'*/
	}
}