package ocelot

import grails.converters.JSON
import grails.transaction.Transactional
import org.camunda.bpm.model.bpmn.Bpmn
import org.camunda.bpm.model.bpmn.BpmnModelInstance
import org.camunda.bpm.model.bpmn.impl.BpmnModelConstants
import org.camunda.bpm.model.bpmn.impl.instance.BaseElementImpl
import org.camunda.bpm.model.bpmn.impl.instance.camunda.CamundaFormDataImpl
import org.camunda.bpm.model.bpmn.instance.BaseElement
import org.camunda.bpm.model.bpmn.instance.ExtensionElements
import org.camunda.bpm.model.bpmn.instance.StartEvent
import org.camunda.bpm.model.bpmn.instance.camunda.CamundaFormData
import org.camunda.bpm.model.bpmn.instance.camunda.CamundaFormField
import org.camunda.bpm.model.bpmn.instance.camunda.CamundaProperty
import org.codehaus.groovy.grails.web.json.JSONArray

@Transactional
class PropertyService {

	def injectAttributes(xmlBpmn2, jsonString) {

		if (!jsonString || jsonString.size() == 0) jsonString = "{}"

		//println "inject\nbpmn : $xmlBpmn2\njson : $jsonString"

		def node
		def jsonFieldKey
		def jsonAttributes = JSON.parse(jsonString)
		//String to InputStream => Convert InputStream to BpmnModelInstance
		BpmnModelInstance modelInstance = Bpmn.readModelFromStream(new ByteArrayInputStream(xmlBpmn2.getBytes("UTF-8")))

		//Go over all the given tasks
		for (int i = 0; i < jsonAttributes.size(); i++) {

			jsonFieldKey = jsonAttributes.keys()[i]
			JSONArray jsonArray = jsonAttributes.get(jsonFieldKey)
			node = modelInstance.getModelElementById(jsonFieldKey)

			//Go over all the given attributes of each given task
			for (int z = 0; z < jsonArray.size(); z++) {

				if (!jsonArray[z].value.toString().empty && node != null) {

					//Insert bpmn2 extension attribute
					if (jsonArray[z].extension.equals("bpmn"))
						node.setAttributeValue(jsonArray[z].name, jsonArray[z].value.toString())

					//Insert camunda extension attribute
					else if (jsonArray[z].extension.equals("camunda"))
						node.setAttributeValueNs(BpmnModelConstants.CAMUNDA_NS, jsonArray[z].name, jsonArray[z].value.toString())

					//Insert camunda formData
					else if (jsonArray[z].extension.equals("camundaFormData")) {

						//formData is an extensionElements subnode
						ExtensionElements extensionElements = modelInstance.newInstance(ExtensionElements.class)
						CamundaFormData formData = extensionElements.addExtensionElement(CamundaFormData.class)

						//node.addChildElement(extensionElements)
						node.setExtensionElements(extensionElements)

						def extension = extensionElements.getElementsQuery().list()
						def exten = node.getExtensionElements()
						def nodeElements = exten.getElementsQuery().list()

						println("node: " + node)
						println("Extension elements: " + extension)
						println("node Extension elements: " + nodeElements)

						CamundaFormField formField = modelInstance.newInstance(CamundaFormField)

						JSONArray values = jsonArray[z].value

						//Find and insert the given camunda:formField
						for (int y = 0; y < values.size(); y++) {
							formField.camundaId = values[y].id
							formField.camundaLabel = values[y].name
							formField.camundaType = values[y].type
							formField.camundaDefaultValue = values[y].value

							//Add camunda:formField to camunda:formData
							formData.addChildElement(formField)
						}

						//Add camunda:formData to the given task
						node.addChildElement(formData)
					}
				}
			}
		}

		//Return BpmnModelInstance as String
		Bpmn.convertToString(modelInstance)
	}
}
