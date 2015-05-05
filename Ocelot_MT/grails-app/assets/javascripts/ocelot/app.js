//= require_tree bower_components
//= require services
//= require controllers
//= require directivesOut
//= require filters

/**
 * Created by sergi on 08/08/14.
 */

var ocelotApp = angular.module('ocelotApp', [
	'ui.bootstrap',
	'ngRoute',
	'ocelotControllers',
	'ocelotServices',
	'ocelotDirectives',
	'ocelotFilters'
]);

ocelotApp.config(function ($routeProvider) {
	$routeProvider.
		when('/palette', {
			templateUrl: 'partials/palette/paletteList.html',
			controller: 'PaletteCtrl'
		}).
		when('/palette/:paletteId/paletteItem/create', {
			templateUrl: 'partials/palette/paletteCreate.html',
			controller: 'CreatePaletteItemCtrl'
		}).
		when('/palette/:paletteId/paletteItem/:itemId', {
			templateUrl: 'partials/palette/paletteDetail.html',
			controller: 'PaletteItemCtrl'
		}).
        when('/modeler', {
            templateUrl: 'partials/modeler/modeler.html',
            controller: 'ModelerCtrl'
        }).
        when('/modelerIndex', {
            templateUrl: 'partials/modeler/modelerIndex.html',
            controller: 'ModelCtrl'
        }).
		when('/formData', {	//TODO: make sure the controller is correct
			templateUrl: 'partials/formData/formDataList.html',
			controller: 'FormCtrl'
		}).
		when('/formData/create', {
			templateUrl: 'partials/formData/formDataCreate.html',
			controller: 'CreateFormCtrl'
		}).
		when('/formData/:formId', {
			templateUrl: 'partials/formData/formDataDetail.html',
			controller: 'DetailFormCtrl'
		}).
		otherwise({
			redirectTo: '/palette'
		});
});