/**
 * Created by sergi on 08/08/14.
 */
var ocelotServices = angular.module('ocelotServices', ['ngResource']);

ocelotServices.factory("Palette", function($resource) {
    return $resource("/blank/api/palette/:id", null, {'update' : {method: 'PUT'}, 'query' : {method:'GET', isArray:true}});
});


ocelotServices.factory("PaletteItem", function($resource) {
    return $resource("/blank/api/palette/:id/paletteItem/:id", null, {'update' : {method: 'PUT'}, 'query' : {method:'GET', isArray:false}});
});

ocelotServices.factory("Category", function($resource) {
    return $resource("/blank/api/category/:id", null, {'update' : {method: 'PUT'}, 'query' : {method:'GET', isArray:true}});
});