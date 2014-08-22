/**
 * Created by sergi on 08/08/14.
 */

var ocelotControllers = angular.module('ocelotControllers', []);

ocelotControllers.controller('PaletteCtrl', function ($scope, Palette, PaletteItem, Category) {
    //Get all categories available
    Category.query().$promise.then(function (data) {
        //Sort categories by its id
        data.sort(function (a, b) {
            return a.id - b.id;
        });

        //Get only the names we wanna use
        $scope.categories = data.map(function (category) {
            return category.name
        });
    });

    $scope.categoryGroup = {};
    Palette.get({id: 1}).$promise.then(function (palette) {

        $scope.paletteItems = palette.paletteItems;

        palette.paletteItems.map(function (item) {
            var category = item.category.name;

            $scope.categoryGroup[category] = $scope.categoryGroup[category] || [];

            $scope.categoryGroup[category].push(item);
        });
    });

    $scope.orderProp = 'name';

    $scope.change = function (item) {
        item.activated = !item.activated;
        // Notify server of changes
        PaletteItem.update({id: item.id}, item);
    };
});


//ocelotControllers.controller('PaletteCtrl', function ($scope, Palette, PaletteItem) {
//    $scope.accordionDict = {};
//    $scope.paletteItems = [];
//
//
//    var query = Palette.query();
//    query.$promise.then(function (data) {
//        var palette = data[0];
//        palette.paletteItems.map(function (element) {
//            PaletteItem.get({id: element.id}).$promise.then(function (item) {
//
//                $scope.paletteItems.push(item);
//
//                var category = item.category;
//
//                $scope.accordionDict[category] = $scope.accordionDict[category] || [];
//
//                if (item.activated) {
//                    $scope.accordionDict[category].push(item);
//                }
////                console.log(item);
//            });
//        })
//    });
//
//    $scope.change = function (item) {
//        item.activated = !item.activated;
//        // Notify server of changes
//        PaletteItem.update({id: item.id}, item);
//    };
//
//    $scope.orderProp = 'name';
//});


ocelotControllers.controller('PaletteItemCtrl', function ($scope, $routeParams, Category, PaletteItem) {
    //Get all categories available
    $scope.categories = Category.query();

    $scope.item = PaletteItem.get({id: $routeParams.itemId});

    $scope.save = function () {
        PaletteItem.update({id: $scope.item.id}, $scope.item);
    };


    $scope.changeActivated = function(){
        $scope.item.activated = !$scope.item.activated;
    }
});

ocelotControllers.controller('CreatePaletteItemCtrl', function ($scope, Category, PaletteItem) {
    //Get all categories available
    $scope.categories = Category.query();

    $scope.item = {name: "New Item", description: "Place a description here", icon: "No Icon", category: {id: 1}, activated: false};

    $scope.save = function () {
        PaletteItem.save($scope.item);
    };


    $scope.changeActivated = function(){
        $scope.item.activated = !$scope.item.activated;
    }
});