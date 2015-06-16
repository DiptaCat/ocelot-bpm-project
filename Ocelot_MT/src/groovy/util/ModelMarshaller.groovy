package util

import grails.converters.JSON
import ocelot.Model

class ModelMarshaller implements OcelotMarshaller {
	void register() {
		JSON.registerObjectMarshaller(Model) { Model model ->
			[
					id  : model.id,
					name: model.name,
					user: model.user,
					svg : model.svg,
					xml : model.xml,
					json: model.json
			]
		}
	}
}