package ocelot

class FormData {

	String key, fields, description

	//static belongsTo = [user: Member]

	static constraints = {
		key blank: false , unique: true
		fields nullable: true, maxSize: 10000, type: "text"
		description type: "text", blank: true
	}
}
