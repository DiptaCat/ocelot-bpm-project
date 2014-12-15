package ocelot

import grails.transaction.Transactional
import org.camunda.bpm.model.bpmn.Bpmn
import org.camunda.bpm.model.bpmn.BpmnModelInstance
import org.camunda.bpm.model.bpmn.impl.BpmnModelConstants
import org.codehaus.groovy.grails.web.json.JSONArray

@Transactional
class PropertyService {

	def injectAttributes(xmlBpmn2, jsonAttributes) {

		Node node
		def jsonFieldKey
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

				node.setAttributeValueNs(BpmnModelConstants.CAMUNDA_NS,jsonArray[z].name, jsonArray[z].value.toString())
			}
			i++
		}

		Bpmn.convertToString(modelInstance)
	}
}
