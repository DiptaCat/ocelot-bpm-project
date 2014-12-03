package ocelot

class Model {

	String name
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
		svg type: "text", nullable: true
		xml type: "text", nullable: true
		json type: "text", nullable: true
	}

	static mapping = {
		// batchSize 10
		autoTimestamp true
		/*usersFavs joinTable: [name: 'FavModels', column: 'UserId', key: 'ModelId']
		usersFavs cascade: 'save-update'*/
	}
}