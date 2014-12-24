/**
 * Created by sergi on 08/08/14.
 */

var app = angular.module('ocelotDirectives', []);

app.directive('loadAngularBootstrap', [function () {
	return function (scope, element) {
		angular.element('<script src="/ocelot/assets/bower_components/angular-bootstrap/ui-bootstrap-tpls.min.js?compile=false" type="text/javascript"></script>').appendTo(element);
	}
}]);

app.directive('showPalette', function () {
	return {
		restrict: 'E',
		templateUrl: 'palettePartials/palette.html',
		link: function (scope) {
			scope.$watch('categoryGroup', function (newValue, oldValue) {
				if (newValue !== oldValue) {
					console.log("Palette has changed", newValue);
				}
			}, true);
		}
	};
});

app.directive('loadSvg', function ($compile) {

	function link(scope, element, attrs) {
		scope.$watch(attrs.loadSvg, function (value) {
			element.append(value);
		});
	}

	return {
		link: link
	};

});

app.directive('paletteModeler', function () {
	return {
		restrict: 'E',
		templateUrl: 'modelerPartials/paletteModeler.html',
		link: function (scope) {
			scope.$watch('categoryGroup', function (newValue, oldValue) {
				if (newValue !== oldValue) {
					console.log("Palette has changed", newValue);
				}
			}, true);
		}
	}
});

app.directive('properties', function () {
	return {
		restrict: 'E',
		templateUrl: 'modelerPartials/properties.html'
	}
});

/*This directive draws a modeler using bpmn-js. In order to know which element is selected
in the canvas it uses the scope variable canvasSelectedItem -Look at ContextPadProvider-
  */
app.directive('drawModeler', function () {

	function link(scope, element, attrs) {
		var BpmnModeler = require('bpmn-js/lib/Modeler');
		var CliModule = require('bpmn-js-cli');

        // XML is extracted from the modeler controller
        var xml = scope.bpmnXML;
        var modeler = new BpmnModeler({
			container: element[0],
			additionalModules: [ CliModule ],
			cli: { bindTo: 'cli' }
		});

        scope.modelerInstance = modeler;

		modeler.importXML(xml);

		scope.$watch(attrs.drawModeler, function () {
			var newElem = cli.append(scope.canvasSelectedItem.id, scope.paletteSelectedItem.bpmnElem, '150,0');
            if(scope.paletteSelectedItem.type === "custom"){
                cli.setLabel(newElem, scope.paletteSelectedItem.name);
            }
		});

        var downloadLink = $('#js-download-diagram');
        var downloadSvgLink = $('#js-download-svg');

        function saveSVG(done) {
            modeler.saveSVG(done);
        }

        function saveDiagram(done) {

            modeler.saveXML({ format: true }, function(err, xml) {
                done(err, xml);
            });
        }

        function setEncoded(link, name, data) {
            var encodedData = encodeURIComponent(data);

            if (data) {
                link.addClass('active').attr({
                    'href': 'data:application/bpmn20-xml;charset=UTF-8,' + encodedData,
                    'download': name
                });
            } else {
                link.removeClass('active');
            }
        }

        var _ = require('lodash');

        var exportArtifacts = _.debounce(function() {

            saveSVG(function(err, svg) {
                setEncoded(downloadSvgLink, 'diagram.svg', err ? null : svg);
            });

            saveDiagram(function(err, xml) {
                //TODO send message to Server
                setEncoded(downloadLink, 'diagram.bpmn', err ? null : xml);
            });
        }, 500);

        modeler.on('commandStack.changed', exportArtifacts);
	}

	return {
		link: link
	};
});