/**
 * Created by sergi on 08/08/14.
 */

var ocelotControllers = angular.module('ocelotControllers', []);

ocelotControllers.controller('PaletteCtrl', function ($scope, Palette, PaletteItem) {
    $scope.accordionDict = {};


    var query = Palette.query();
    query.$promise.then(function(data){
        var palette = data[0];
        palette.paletteItems.map(function (element) {
            PaletteItem.get({id: element.id}).$promise.then(function(item){
                var category = item.category;

                $scope.accordionDict[category] = $scope.accordionDict[category] || [];
                if(item.activated){
                    $scope.accordionDict[category].push(item);
                }
                console.log(item);
            });


        })
    });

    $scope.orderProp = 'name';

});