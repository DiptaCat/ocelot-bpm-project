package ocelot

import grails.converters.JSON
import grails.transaction.Transactional
import org.camunda.bpm.model.bpmn.Bpmn
import org.camunda.bpm.model.bpmn.BpmnModelInstance
import org.codehaus.groovy.grails.web.json.JSONArray

@Transactional
class PropertyService {

	def injectAttributes(xmlBpmn2, jsonString) {

		def node
		def jsonFieldKey
		def jsonAttributes = JSON.parse(jsonString)
		BpmnModelInstance modelInstance = Bpmn.readModelFromStream(new ByteArrayInputStream(xmlBpmn2.getBytes("UTF-8")))

		int i = 0

		while(i < jsonAttributes.size()) {

			jsonFieldKey = jsonAttributes.keys()[i]
			JSONArray jsonArray = jsonAttributes.get(jsonFieldKey)
			node = modelInstance.getModelElementById(jsonAttributes.keys()[i])

			for (int z = 0; z < jsonArray.size(); z++) {
				print "KEY:${jsonAttributes.keys()[i]}\t"
				print "Name => ${jsonArray[z].name}\t"
				print "Value => ${jsonArray[z].value}\t"
				println "Type => ${jsonArray[z].type}\n"

				/*TODO: if there is a camunda extension implement
					ELSE and use node.setAttributeValueNS(BpmnModelConstants.CAMUNDA_NS, "name", "value")
				 */
				if(!jsonArray[z].value.toString().empty)
					node.setAttributeValue(jsonArray[z].name, jsonArray[z].value.toString())
			}
			i++
		}

		Bpmn.convertToString(modelInstance)
	}
}
