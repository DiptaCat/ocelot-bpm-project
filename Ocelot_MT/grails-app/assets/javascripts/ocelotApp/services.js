/**
 * Created by sergi on 08/08/14.
 */
var ocelotServices = angular.module('ocelotServices', ['ngResource']);

ocelotServices.factory("Palette", function ($resource) {
	return $resource("/ocelot/api/palette/:id", null, {'update': {method: 'PUT'}, 'query': {method: 'GET', isArray: true}});
});

ocelotServices.factory("Model", function ($resource) {
    return $resource("/ocelot/api/model/:id", null, {'update': {method: 'PUT'}, 'query': {method: 'GET', isArray: true}});
});

ocelotServices.factory("PaletteItem", function ($resource) {
	return $resource("/ocelot/api/palette/:id/paletteItem/:id", null, {'update': {method: 'PUT'}, 'query': {method: 'GET', isArray: false}});
});

ocelotServices.factory("Category", function ($resource) {
	return $resource("/ocelot/api/category/:id", null, {'update': {method: 'PUT'}, 'query': {method: 'GET', isArray: true}});
});

ocelotServices.factory("PaletteItem", function ($resource) {
    return $resource("/ocelot/api/palette/:id/paletteItem/:id", null, {'update': {method: 'PUT'}, 'query': {method: 'GET', isArray: false}});
});

//notice that this service uses service instead of factory because will be a shared service
ocelotServices.service("ModelService", function(){
    this.modelData = {name: "",
        description: "",
        xml : "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<bpmn2:definitions xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:bpmn2=\"http://www.omg.org/spec/BPMN/20100524/MODEL\" xmlns:bpmndi=\"http://www.omg.org/spec/BPMN/20100524/DI\" xmlns:dc=\"http://www.omg.org/spec/DD/20100524/DC\" xmlns:di=\"http://www.omg.org/spec/DD/20100524/DI\" xsi:schemaLocation=\"http://www.omg.org/spec/BPMN/20100524/MODEL BPMN20.xsd\" id=\"sample-diagram\" targetNamespace=\"http://bpmn.io/schema/bpmn\">\n  <bpmn2:process id=\"Process_1\" isExecutable=\"false\">\n    <bpmn2:StartEvent id=\"StartEvent_1\"/>\n  </bpmn2:process>\n  <bpmndi:BPMNDiagram id=\"BPMNDiagram_1\">\n    <bpmndi:BPMNPlane id=\"BPMNPlane_1\" bpmnElement=\"Process_1\">\n      <bpmndi:BPMNShape id=\"_BPMNShape_StartEvent_2\" bpmnElement=\"StartEvent_1\">\n        <dc:Bounds height=\"36.0\" width=\"36.0\" x=\"412.0\" y=\"240.0\"/>\n      </bpmndi:BPMNShape>\n    </bpmndi:BPMNPlane>\n  </bpmndi:BPMNDiagram>\n</bpmn2:definitions>",
        info : {}};

    this.model = function() {
        return this.modelData;
    };

    this.setName = function(name){
        this.modelData.name = name;
    };

    this.getName = function(){
      return this.modelData.name;
    };

    this.setDescription = function(description){
        this.modelData.description = description;
    };

    this.getDescription = function(){
        return this.modelData.description;
    };

    this.setXML = function(xml){
        this.modelData.xml = xml;
    };

    this.getXML = function(){
        return this.modelData.xml;
    };

    this.setInfo = function(info){
        this.modelData.info = info;
    };

    this.getInfo = function(){
        return this.modelData.info;
    };

});