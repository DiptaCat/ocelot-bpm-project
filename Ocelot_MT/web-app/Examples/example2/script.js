angular.module('docsTimeDirective', [])
	.controller('Controller', ['$scope', function ($scope) {
		$scope.form = "";
		$scope.item = "";

		$scope.asdf = function (item) {
			$scope.form = item;
		};
	}])
	.directive('myCurrentTime', ['$interval', 'dateFilter', function () {

		function link(scope, element, attrs) {
			var BpmnViewer = require('bpmn-js');

			var xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<bpmn2:definitions xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:bpmn2=\"http://www.omg.org/spec/BPMN/20100524/MODEL\" xmlns:bpmndi=\"http://www.omg.org/spec/BPMN/20100524/DI\" xmlns:dc=\"http://www.omg.org/spec/DD/20100524/DC\" xmlns:di=\"http://www.omg.org/spec/DD/20100524/DI\" xsi:schemaLocation=\"http://www.omg.org/spec/BPMN/20100524/MODEL BPMN20.xsd\" id=\"sample-diagram\" targetNamespace=\"http://bpmn.io/schema/bpmn\">\n  <bpmn2:process id=\"Process_1\" isExecutable=\"false\">\n    <bpmn2:StartEvent id=\"StartEvent_1\"/>\n  </bpmn2:process>\n  <bpmndi:BPMNDiagram id=\"BPMNDiagram_1\">\n    <bpmndi:BPMNPlane id=\"BPMNPlane_1\" bpmnElement=\"Process_1\">\n      <bpmndi:BPMNShape id=\"_BPMNShape_StartEvent_2\" bpmnElement=\"StartEvent_1\">\n        <dc:Bounds height=\"36.0\" width=\"36.0\" x=\"412.0\" y=\"240.0\"/>\n      </bpmndi:BPMNShape>\n    </bpmndi:BPMNPlane>\n  </bpmndi:BPMNDiagram>\n</bpmn2:definitions>";
			var viewer = new BpmnViewer(element[0]);

			viewer.importXML(xml, function(err) {

				if (err) {
					console.log('error rendering', err);
				} else {
					console.log('rendered');
				}
			});

			scope.$watch(attrs.myCurrentTime, function (value) {
				console.log("hola");
				scope.pimpollo = value;
			});
		}

		return {
			link: link
		};
	}]);