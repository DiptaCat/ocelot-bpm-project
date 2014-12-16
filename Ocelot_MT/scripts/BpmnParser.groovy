import org.camunda.bpm.engine.form.FormData
import org.camunda.bpm.engine.form.FormField
import org.camunda.bpm.engine.form.StartFormData
import org.camunda.bpm.engine.impl.form.FormDataImpl
import org.camunda.bpm.model.bpmn.Bpmn
import org.camunda.bpm.model.bpmn.BpmnModelInstance
import org.camunda.bpm.model.bpmn.impl.BpmnModelConstants
import org.camunda.bpm.model.bpmn.instance.ExtensionElements
import org.camunda.bpm.model.bpmn.instance.StartEvent
import org.camunda.bpm.model.bpmn.instance.camunda.CamundaFormData
import org.camunda.bpm.model.bpmn.instance.camunda.CamundaFormField
import org.codehaus.groovy.grails.web.json.JSONArray


includeTargets << grailsScript("_GrailsInit")

target(bpmnParser: "The description of the script goes here!") {
	// TODO: Implement script here
	def jsonString = '''{"UserTask_14xgz32":[{"extension":"bpmn","name":"id","type":"String","value":""},{"extension":"bpmn","name":"name","type":"String","value":"Julio"},{"extension":"camunda","name":"assignee","type":"String","value":""},{"extension":"camunda","name":"candidateUsers","type":"String","value":""},{"extension":"camunda","name":"candidateGroups","type":"String","value":""},{"extension":"camunda","name":"formKey","type":"String","value":""},{"extension":"camunda","name":"dueDate","type":"String","value":""},{"extension":"camunda","name":"followUpDate","type":"String","value":""},{"extension":"camunda","name":"priority","type":"Integer","value":"3"},{"extension":"camunda","name":"async","type":"Boolean","value":"false"},{"extension":"camunda","name":"exclusive","type":"Boolean","value":true},{"extension":"camunda","name":"jobFailedRetryTimeCycle","type":"String","value":""},{"extension":"bpmn","name":"isForCompensation","type":"Boolean","value":true},{"extension":"camunda","name":"documentation","type":"String","value":"Hola Julio"}],"StartEvent_1":[],"EndEvent_02m9m6l":[]}'''
	def xmlString = """<?xml version="1.0" encoding="UTF-8"?>
<bpmn2:definitions xmlns:bpmn2="http://www.omg.org/spec/BPMN/20100524/MODEL" xmlns:bpmndi="http://www.omg.org/spec/BPMN/20100524/DI" xmlns:di="http://www.omg.org/spec/DD/20100524/DI" xmlns:dc="http://www.omg.org/spec/DD/20100524/DC" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" id="sample-diagram" targetNamespace="http://bpmn.io/schema/bpmn" xsi:schemaLocation="http://www.omg.org/spec/BPMN/20100524/MODEL BPMN20.xsd"><bpmn2:process id="Julio_Bondia" name="Julio_Bondia" isExecutable="true"><bpmn2:startEvent id="StartEvent_1"><bpmn2:outgoing>SequenceFlow_0zto0da</bpmn2:outgoing></bpmn2:startEvent><bpmn2:userTask id="UserTask_14xgz32"><bpmn2:incoming>SequenceFlow_0zto0da</bpmn2:incoming><bpmn2:outgoing>SequenceFlow_1gu5510</bpmn2:outgoing></bpmn2:userTask><bpmn2:sequenceFlow id="SequenceFlow_0zto0da" sourceRef="StartEvent_1" targetRef="UserTask_14xgz32" /><bpmn2:endEvent id="EndEvent_02m9m6l"><bpmn2:incoming>SequenceFlow_1gu5510</bpmn2:incoming></bpmn2:endEvent><bpmn2:sequenceFlow id="SequenceFlow_1gu5510" sourceRef="UserTask_14xgz32" targetRef="EndEvent_02m9m6l" /></bpmn2:process><bpmndi:BPMNDiagram id="BPMNDiagram_1"><bpmndi:BPMNPlane id="BPMNPlane_1" bpmnElement="Julio_Bondia"><bpmndi:BPMNShape id="_BPMNShape_StartEvent_2" bpmnElement="StartEvent_1"><dc:Bounds x="412" y="240" width="36" height="36" /></bpmndi:BPMNShape><bpmndi:BPMNShape id="UserTask_14xgz32_di" bpmnElement="UserTask_14xgz32"><dc:Bounds x="530" y="218" width="100" height="80" /></bpmndi:BPMNShape><bpmndi:BPMNEdge id="SequenceFlow_0zto0da_di" bpmnElement="SequenceFlow_0zto0da"><di:waypoint x="448" y="258" /><di:waypoint x="530" y="258" /><bpmndi:BPMNLabel><dc:Bounds x="444" y="248" width="90" height="20" /></bpmndi:BPMNLabel></bpmndi:BPMNEdge><bpmndi:BPMNShape id="EndEvent_02m9m6l_di" bpmnElement="EndEvent_02m9m6l"><dc:Bounds x="712" y="240" width="36" height="36" /><bpmndi:BPMNLabel><dc:Bounds x="685" y="276" width="90" height="20" /></bpmndi:BPMNLabel></bpmndi:BPMNShape><bpmndi:BPMNEdge id="SequenceFlow_1gu5510_di" bpmnElement="SequenceFlow_1gu5510"><di:waypoint x="630" y="258" /><di:waypoint x="712" y="258" /><bpmndi:BPMNLabel><dc:Bounds x="626" y="248" width="90" height="20" /></bpmndi:BPMNLabel></bpmndi:BPMNEdge></bpmndi:BPMNPlane></bpmndi:BPMNDiagram></bpmn2:definitions>"""

	def jsonObj = grails.converters.JSON.parse(jsonString)

	String jsonFieldKey = ""

	int i = 0;

	def f = new File("/Users/detritus/Downloads/julio.bpmn")
	def items = new XmlParser().parseText(xmlString)


	//BpmnModelInstance modelInstance = Bpmn.readModelFromStream(f.newInputStream())
	BpmnModelInstance modelInstance = Bpmn.readModelFromStream(new ByteArrayInputStream(xmlString.getBytes("UTF-8")))
	println Bpmn.convertToString(modelInstance)

	//def node = modelInstance.getModelElementById("StartEvent_1")
	//node.setAttributeValue("name","StartEvent_1");
	//node.setAttributeValueNs(BpmnModelConstants.CAMUNDA_NS, "assignee","Julio Bondia")
	//println "Testing CamundaExtension => ${node.getAttributeValueNs(BpmnModelConstants.CAMUNDA_NS, "assignee")}"
	//node.setAttributeValue("name","JulioBondia")
	//node.setAttributeValue("triggeredByElement","JulioBondia")
	//node.setAttributeValue("isForCompensation","true")
	//node.setAttributeValue("default","Julio Bondia")

	def node = modelInstance.getModelElementById("UserTask_14xgz32")
	node.setAttributeValue("isForCompensation","true")
	node.setAttributeValueNs(BpmnModelConstants.CAMUNDA_NS,"failedJobRetryTimeCycle","true")
	println 'asdasd'


	/*
	ExtensionElements extensionElements = modelInstance.newInstance(ExtensionElements.class)
	CamundaFormData camundaFormData = extensionElements.addExtensionElement(CamundaFormData.class)
	CamundaFormField formField = modelInstance.newInstance(CamundaFormField)
	formField.camundaId = 's'
	formField.camundaLabel = 'Toda'
	formField.camundaDefaultValue = 'Sergi Toda'
	formField.camundaType = 'String'
	camundaFormData.addChildElement(formField)
	modelInstance.getModelElementById("StartEvent_1").addChildElement(extensionElements) */

	while(i < jsonObj.size()) {

		jsonFieldKey = jsonObj.keys()[i]
		JSONArray jsonArray = jsonObj.get(jsonFieldKey)
		node = modelInstance.getModelElementById(jsonFieldKey)

		for (int z = 0; z < jsonArray.size(); z++) {
			print "KEY:${jsonObj.keys()[i]}\t"
			print "Name => ${jsonArray[z].name}\t"
			print "Value => ${jsonArray[z].value}\n"

			if(!jsonArray[z].value.toString().empty) {

				if(!jsonArray[z].extension.equals("camunda"))
					node.setAttributeValue(jsonArray[z].name, jsonArray[z].value.toString())
				else
					node.setAttributeValueNs(BpmnModelConstants.CAMUNDA_NS, jsonArray[z].name, jsonArray[z].value.toString())
			}
		}
		i++
	}

	//StartEvent startEvent = (StartEvent)modelInstance.getModelElementById("StartEvent_1")


	println "/++++++++++++++++++++++++++++++++++++++++++++++++++\\"

	println Bpmn.convertToString(modelInstance)
}

setDefaultTarget(bpmnParser)