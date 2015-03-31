//= require_tree bower_components
//= require services
//= require controllers
//= require directivesOut
//= require filters

/**
 * Created by sergi on 08/08/14.
 */

var app = angular.module('ocelot', [
	'ui.bootstrap',
	'ngRoute',
	'ocelotControllers',
	'ocelotServices',
	'ocelotDirectives',
	'ocelotFilters'
]);

app.config(function ($routeProvider) {
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
		otherwise({
			redirectTo: '/palette'
		});
});