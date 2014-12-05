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
    this.modelData = {name: "", description: ""};

    this.model = function() {
        return this.modelData;
    };

    this.setName = function(name){
        console.log("SETNAME");
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

});