/**
 * Created by sergi on 08/08/14.
 */
var ocelotServices = angular.module('ocelotServices', ['ngResource']);

ocelotServices.factory("Palette", function($resource) {
    return $resource("/blank/paletteRest/:id", null, {'update' : {method: 'PUT'}, 'query' : {method:'GET', isArray:true}});
});


ocelotServices.factory("PaletteItem", function($resource) {
    return $resource("/blank/paletteItems/:id", null, {'update' : {method: 'PUT'}, 'query' : {method:'GET', isArray:false}});
});