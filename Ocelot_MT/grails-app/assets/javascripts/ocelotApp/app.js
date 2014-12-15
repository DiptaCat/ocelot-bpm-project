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
			templateUrl: 'palettePartials/paletteList.html',
			controller: 'PaletteCtrl'
		}).
		when('/palette/:paletteId/paletteItem/create', {
			templateUrl: 'palettePartials/paletteCreate.html',
			controller: 'CreatePaletteItemCtrl'
		}).
		when('/palette/:paletteId/paletteItem/:itemId', {
			templateUrl: 'palettePartials/paletteDetail.html',
			controller: 'PaletteItemCtrl'
		}).
		when('/proves', {
			templateUrl: 'palettePartials/proves.html',
			controller: 'ProvesCtrl'
		}).
        when('/modeler', {
            templateUrl: 'modelerPartials/modeler.html',
            controller: 'ModelerCtrl'
        }).
        when('/modelerIndex', {
            templateUrl: 'modelerPartials/modelerIndex.html',
            controller: 'ModelCtrl'
        }).
		otherwise({
			redirectTo: '/palette'
		});
});