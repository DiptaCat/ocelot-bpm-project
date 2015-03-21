package ocelot

import grails.converters.JSON
import grails.transaction.Transactional
import org.camunda.bpm.model.bpmn.Bpmn
import org.camunda.bpm.model.bpmn.BpmnModelInstance
import org.camunda.bpm.model.bpmn.impl.BpmnModelConstants
import org.camunda.bpm.model.bpmn.instance.ExtensionElements
import org.camunda.bpm.model.bpmn.instance.camunda.CamundaFormData
import org.camunda.bpm.model.bpmn.instance.camunda.CamundaFormField
import org.codehaus.groovy.grails.web.json.JSONArray

@Transactional
class PropertyService {

	def injectAttributes(xmlBpmn2, jsonString) {

        if (!jsonString || jsonString.size()==0) jsonString = "{}"

        println "inject\nbpmn : $xmlBpmn2\njson : $jsonString"

		def node
		def jsonFieldKey
		def jsonAttributes = JSON.parse(jsonString)
		//String to InputStream => Convert InputStream to BpmnModelInstance
		BpmnModelInstance modelInstance = Bpmn.readModelFromStream(new ByteArrayInputStream(xmlBpmn2.getBytes("UTF-8")))


		//Go over all the given tasks
		for(int i = 0; i < jsonAttributes.size(); i++) {

			jsonFieldKey = jsonAttributes.keys()[i]
			JSONArray jsonArray = jsonAttributes.get(jsonFieldKey)
			node = modelInstance.getModelElementById(jsonFieldKey)

			//Go over all the given attributes of each given task
			for (int z = 0; z < jsonArray.size(); z++) {

				if(!jsonArray[z].value.toString().empty && node != null) {

					//Insert bpmn2 extension attribute
					if(jsonArray[z].extension.equals("bpmn"))
						node.setAttributeValue(jsonArray[z].name, jsonArray[z].value.toString())

					//Insert camunda extension attribute
					else if (jsonArray[z].extension.equals("camunda"))
						node.setAttributeValueNs(BpmnModelConstants.CAMUNDA_NS, jsonArray[z].name, jsonArray[z].value.toString())

					//Insert camunda formData
					else if (jsonArray[z].extension.equals("camundaFormData")){

						//formData is an extensionElements subnode
						ExtensionElements extensionElements = modelInstance.newInstance(ExtensionElements.class)
						CamundaFormData camundaFormData = extensionElements.addExtensionElement(CamundaFormData.class)
						CamundaFormField formField = modelInstance.newInstance(CamundaFormField)

						//Find and insert the given camunda:formField
						for(int y = 0; y < jsonArray[z].value[y].size(); y++) {
							formField.camundaId = jsonArray[z].value[y].id
							formField.camundaLabel = jsonArray[z].value[y].label
							formField.camundaType = jsonArray[z].value[y].type
							formField.camundaDefaultValue = jsonArray[z].value[y].defaultValue

							//Add camunda:formField to camunda:formData
							camundaFormData.addChildElement(formField)
						}

						//Add camunda:formData to the given task
						node.addChildElement(camundaFormData)
					}
				}
			}
		}

		//Return BpmnModelInstance as String
		Bpmn.convertToString(modelInstance)
	}
}
