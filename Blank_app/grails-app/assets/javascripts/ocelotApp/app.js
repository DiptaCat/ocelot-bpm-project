/**
 * Created by sergi on 08/08/14.
 */

//= require angular-route/angular-route



//= require services
//= require controllers
//= require directives

var ocelotApp = angular.module('ocelotApp', [
    'ui.bootstrap',
    'ngRoute',
    'ocelotControllers',
    'ocelotServices',
    'ocelotDirectives'
]);

ocelotApp.config(function($routeProvider){
    $routeProvider.
        when('/palette', {
            templateUrl: 'palettePartials/paletteList.html',
            controller: 'PaletteCtrl'
        }).
        when('/palette/:itemId', {
            templateUrl: 'POSR TEMPLATE Aquí',
            controller: 'PaletteItemCtrl'
        }).
        otherwise({
            redirectTo: '/palette'
        });
});