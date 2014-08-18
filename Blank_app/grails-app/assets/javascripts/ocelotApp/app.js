/**
 * Created by sergi on 08/08/14.
 */

//= require angular-route/angular-route



//= require services
//= require controllers
//= require directives
//= require filters

var ocelotApp = angular.module('ocelotApp', [
    'ui.bootstrap',
    'ngRoute',
    'ocelotControllers',
    'ocelotServices',
    'ocelotDirectives',
    'ocelotFilters'
]);

ocelotApp.config(function($routeProvider){
    $routeProvider.
        when('/palette', {
            templateUrl: 'palettePartials/paletteList.html',
            controller: 'PaletteCtrl'
        }).
        when('/palette/:itemId', {
            templateUrl: 'palettePartials/paletteDetail.html',
            controller: 'PaletteItemCtrl'
        }).
        otherwise({
            redirectTo: '/palette'
        });
});