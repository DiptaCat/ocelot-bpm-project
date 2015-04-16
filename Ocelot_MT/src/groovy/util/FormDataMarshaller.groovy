package util

import grails.converters.JSON
import ocelot.FormData

class FormDataMarshaller implements OcelotMarshaller{
	void register() {
		JSON.registerObjectMarshaller(FormData) { FormData formData ->

			def fFields = formData.fields == null ? [] : JSON.parse(formData.fields.toString())

			[
					id         : formData.id,
					key        : formData.key,
					description : formData.description,
					fields     : fFields,
			]
		}
	}
}
