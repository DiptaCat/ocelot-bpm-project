/**
 * Created by sergi on 08/08/14.
 */

var ocelotControllers = angular.module('ocelotControllers', []);

ocelotControllers.controller('PaletteCtrl', function ($scope, Palette, PaletteItem) {
    $scope.accordionDict = {};
    $scope.paletteItems = [];


    var query = Palette.query();
    query.$promise.then(function (data) {
        var palette = data[0];
        palette.paletteItems.map(function (element) {
            PaletteItem.get({id: element.id}).$promise.then(function (item) {

                $scope.paletteItems.push(item);

                var category = item.category;

                $scope.accordionDict[category] = $scope.accordionDict[category] || [];

                if (item.activated) {
                    $scope.accordionDict[category].push(item);
                }
//                console.log(item);
            });
        })
    });

    $scope.change = function (item) {
        item.activated = !item.activated;
        // Notify server of changes
        PaletteItem.update({id: item.id}, item);
    };

    $scope.orderProp = 'name';
});


ocelotControllers.controller('PaletteItemCtrl', function ($scope){
   $scope.putamerda = "puta merda";
});