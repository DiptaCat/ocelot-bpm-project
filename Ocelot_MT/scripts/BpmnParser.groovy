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
	def jsonString = '''{"StartEvent_1":[],"Task_1nsex6a":[{"name":"isForCompensation","type":"Boolean","value":"true","$$hashKey":"083"}],"UserTask_04red4y":[{"name":"isForCompensation","type":"Boolean","value":"false","$$hashKey":"08P"},{"name":"id","type":"String","value":"UserTaskJulio","$$hashKey":"08P"}],"EndEvent_0tp30lc":[]}'''
	def xmlString = """<?xml version="1.0" encoding="UTF-8"?>
	<bpmn2:definitions xmlns:bpmn2="http://www.omg.org/spec/BPMN/20100524/MODEL" xmlns:bpmndi="http://www.omg.org/spec/BPMN/20100524/DI" xmlns:di="http://www.omg.org/spec/DD/20100524/DI" xmlns:dc="http://www.omg.org/spec/DD/20100524/DC" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" id="sample-diagram" targetNamespace="http://bpmn.io/schema/bpmn" xsi:schemaLocation="http://www.omg.org/spec/BPMN/20100524/MODEL BPMN20.xsd">
	  <bpmn2:process id="Process_1" isExecutable="false">
		<bpmn2:startEvent id="StartEvent_1">
		  <bpmn2:outgoing>SequenceFlow_09zq6ta</bpmn2:outgoing>
		</bpmn2:startEvent>
		<bpmn2:task id="Task_1nsex6a">
		  <bpmn2:incoming>SequenceFlow_09zq6ta</bpmn2:incoming>
		  <bpmn2:outgoing>SequenceFlow_16vfuvv</bpmn2:outgoing>
		</bpmn2:task>
		<bpmn2:sequenceFlow id="SequenceFlow_09zq6ta" sourceRef="StartEvent_1" targetRef="Task_1nsex6a" />
		<bpmn2:userTask id="UserTask_04red4y">
		  <bpmn2:incoming>SequenceFlow_16vfuvv</bpmn2:incoming>
		  <bpmn2:outgoing>SequenceFlow_15wmfh1</bpmn2:outgoing>
		</bpmn2:userTask>
		<bpmn2:sequenceFlow id="SequenceFlow_16vfuvv" sourceRef="Task_1nsex6a" targetRef="UserTask_04red4y" />
		<bpmn2:endEvent id="EndEvent_0tp30lc">
		  <bpmn2:incoming>SequenceFlow_15wmfh1</bpmn2:incoming>
		</bpmn2:endEvent>
		<bpmn2:sequenceFlow id="SequenceFlow_15wmfh1" sourceRef="UserTask_04red4y" targetRef="EndEvent_0tp30lc" />
	  </bpmn2:process>
	  <bpmndi:BPMNDiagram id="BPMNDiagram_1">
		<bpmndi:BPMNPlane id="BPMNPlane_1" bpmnElement="Process_1">
		  <bpmndi:BPMNShape id="_BPMNShape_StartEvent_2" bpmnElement="StartEvent_1">
			<dc:Bounds x="269" y="81" width="36" height="36" />
			<bpmndi:BPMNLabel>
			  <dc:Bounds x="242" y="117" width="90" height="20" />
			</bpmndi:BPMNLabel>
		  </bpmndi:BPMNShape>
		  <bpmndi:BPMNShape id="Task_1nsex6a_di" bpmnElement="Task_1nsex6a">
			<dc:Bounds x="355" y="59" width="100" height="80" />
		  </bpmndi:BPMNShape>
		  <bpmndi:BPMNEdge id="SequenceFlow_09zq6ta_di" bpmnElement="SequenceFlow_09zq6ta">
			<di:waypoint x="305" y="99" />
			<di:waypoint x="355" y="99" />
			<bpmndi:BPMNLabel>
			  <dc:Bounds x="285" y="89" width="90" height="20" />
			</bpmndi:BPMNLabel>
		  </bpmndi:BPMNEdge>
		  <bpmndi:BPMNShape id="UserTask_04red4y_di" bpmnElement="UserTask_04red4y">
			<dc:Bounds x="505" y="59" width="100" height="80" />
		  </bpmndi:BPMNShape>
		  <bpmndi:BPMNEdge id="SequenceFlow_16vfuvv_di" bpmnElement="SequenceFlow_16vfuvv">
			<di:waypoint x="455" y="99" />
			<di:waypoint x="505" y="99" />
			<bpmndi:BPMNLabel>
			  <dc:Bounds x="435" y="89" width="90" height="20" />
			</bpmndi:BPMNLabel>
		  </bpmndi:BPMNEdge>
		  <bpmndi:BPMNShape id="EndEvent_0tp30lc_di" bpmnElement="EndEvent_0tp30lc">
			<dc:Bounds x="687" y="81" width="36" height="36" />
			<bpmndi:BPMNLabel>
			  <dc:Bounds x="660" y="117" width="90" height="20" />
			</bpmndi:BPMNLabel>
		  </bpmndi:BPMNShape>
		  <bpmndi:BPMNEdge id="SequenceFlow_15wmfh1_di" bpmnElement="SequenceFlow_15wmfh1">
			<di:waypoint x="605" y="99" />
			<di:waypoint x="687" y="99" />
			<bpmndi:BPMNLabel>
			  <dc:Bounds x="601" y="89" width="90" height="20" />
			</bpmndi:BPMNLabel>
		  </bpmndi:BPMNEdge>
		</bpmndi:BPMNPlane>
	  </bpmndi:BPMNDiagram>
	</bpmn2:definitions>
	"""

	def jsonObj = grails.converters.JSON.parse(jsonString)

	String jsonFieldKey = ""

	int i = 0;

	def f = new File("/Users/detritus/Downloads/julio.bpmn")
	def items = new XmlParser().parseText(xmlString)


	BpmnModelInstance modelInstance = Bpmn.readModelFromStream(f.newInputStream())

	def node = modelInstance.getModelElementById("StartEvent_1")
	//node.setAttributeValue("name","StartEvent_1");
	node.setAttributeValueNs(BpmnModelConstants.CAMUNDA_NS, "assignee","Julio Bondia")
	println "Testing CamundaExtension => ${node.getAttributeValueNs(BpmnModelConstants.CAMUNDA_NS, "assignee")}"
	node.setAttributeValue("name","JulioBondia")
	//node.setAttributeValue("triggeredByElement","JulioBondia")
	//node.setAttributeValue("isForCompensation","true")
	//node.setAttributeValue("default","Julio Bondia")

	node = modelInstance.getModelElementById("UserTask_04red4y")
	node.setAttributeValue("isForCompensation","true")


	ExtensionElements extensionElements = modelInstance.newInstance(ExtensionElements.class)
	CamundaFormData camundaFormData = extensionElements.addExtensionElement(CamundaFormData.class)
	CamundaFormField formField = modelInstance.newInstance(CamundaFormField)
	formField.camundaId = 's'
	formField.camundaLabel = 'Toda'
	formField.camundaDefaultValue = 'Sergi Toda'
	formField.camundaType = 'String'
	camundaFormData.addChildElement(formField)
	modelInstance.getModelElementById("StartEvent_1").addChildElement(extensionElements)

	while(i < jsonObj.size()) {

		jsonFieldKey = jsonObj.keys()[i]
		JSONArray jsonArray = jsonObj.get(jsonFieldKey)
		node = modelInstance.getModelElementById(jsonObj.keys()[i])

		for (int z = 0; z < jsonArray.size(); z++) {
			print "KEY:${jsonObj.keys()[i]}\t"
			print "Name => ${jsonArray[z].name}\t"
			print "Value => ${jsonArray[z].value}\t"
			println "Type => ${jsonArray[z].type}\n"

			//if(!jsonArray[z].value.toString().empty)
				//node.setAttributeValue(jsonArray[z].name, jsonArray[z].value.toString())
				//node.setAttributeValueNs(BpmnModelConstants.CAMUNDA_NS,jsonArray[z].name, jsonArray[z].value.toString())
		}
		i++
	}

	//StartEvent startEvent = (StartEvent)modelInstance.getModelElementById("StartEvent_1")


	println "/++++++++++++++++++++++++++++++++++++++++++++++++++\\"

	println Bpmn.convertToString(modelInstance)
}

setDefaultTarget(bpmnParser)