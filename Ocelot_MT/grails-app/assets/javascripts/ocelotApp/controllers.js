/**
 * Created by sergi on 08/08/14.
 */

var ocelotControllers = angular.module('ocelotControllers', []);

ocelotControllers.controller('PaletteCtrl', function ($scope, Palette, PaletteItem, Category) {
	//Get all categories available
	$scope.paletteId = 1;

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
	Palette.get({id: $scope.paletteId}).$promise.then(function (palette) {

		$scope.paletteItems = palette.paletteItems;

		palette.paletteItems.map(function (item) {
			var category = item.category.name;

			$scope.categoryGroup[category] = $scope.categoryGroup[category] || [];

			$scope.categoryGroup[category].push(item);
		});
	});

	$scope.orderProp = 'name';
	$scope.level = 1;

	$scope.filterByLvl = function(item) {
		return item.level <= $scope.level;
	};

	$scope.change = function (item) {
		item.activated = !item.activated;
		// Notify server of changes
		PaletteItem.update({id: item.id}, item);
	};
});

ocelotControllers.controller('ModelerCtrl', function ($scope, Palette, PaletteItem, Category) {
    //Get all categories available
    $scope.paletteId = 1;

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
    Palette.get({id: $scope.paletteId}).$promise.then(function (palette) {

        $scope.paletteItems = palette.paletteItems;

        palette.paletteItems.map(function (item) {
            var category = item.category.name;

            $scope.categoryGroup[category] = $scope.categoryGroup[category] || [];

            $scope.categoryGroup[category].push(item);
        });
    });

    //TODO add scope variable that changes bpmn.io

    // This function selects an element from paletteModeler and notifies the modeler directive
    $scope.select = function(item){
        console.log("Item selected");
    }

    $scope.asdf = function(item){
      console.log("Item selected controller"+ item)
      $scope.selected = item;
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

	$scope.addProperty = function () {
		var property = {name: "", value: "", type: "string"};

		$scope.item.props = $scope.item.props || [];

		$scope.item.props.push(property);
	};


	$scope.removeProperty = function (index) {
		var props = $scope.item.props;

		if (props) {
			props.splice(index, 1);
		}
	};

	$scope.resetValue = function (property) {
		switch (property.type) {
			case "boolean":
				property.value = false;
				break;
			default :
				property.value = "";
		}
	};

	$scope.changeActivated = function () {
		$scope.item.activated = !$scope.item.activated;
	}
});

ocelotControllers.controller('CreatePaletteItemCtrl', function ($scope, $routeParams, Category, PaletteItem) {
	//Get all categories available
	$scope.categories = Category.query();

	$scope.item = {name: "New Item", description: "Place a description here", icon: "No Icon", category: {id: 1}, activated: false, props: []};

	$scope.save = function () {

		console.log("Route params = " + $routeParams.paletteId);

		PaletteItem.save({id: $routeParams.paletteId}, $scope.item);
	};


	$scope.addProperty = function () {
		var property = {name: "", value: "", type: "string"};
		$scope.item.props.push(property);
	};


	$scope.removeProperty = function (index) {
		var props = $scope.item.props;

		if (props) {
			props.splice(index, 1);
		}
	};

	$scope.resetValue = function (property) {
		switch (property.type) {
			case "boolean":
				property.value = false;
				break;
			default :
				property.value = "";
		}
	};

	$scope.changeActivated = function () {
		$scope.item.activated = !$scope.item.activated;
	}
});


ocelotControllers.controller('ProvesCtrl', function ($scope) {
	$scope.mySvg = '<svg height="90" version="1.1" width="110" xmlns="http://www.w3.org/2000/svg" style="overflow: hidden; position: relative;"><desc style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0);">Created with Raphaël 2.1.0</desc><defs style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0);"></defs><rect x="5" y="5" width="100" height="80" r="5" rx="5" ry="5" fill="#ffffff" stroke="#808080" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" stroke-opacity="1" id="svg_1" style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0); stroke-linecap: round; stroke-linejoin: round; stroke-opacity: 1;"></rect><text x="55" y="45" text-anchor="middle" font="10px &quot;Arial&quot;" stroke="none" fill="#000000" style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0); text-anchor: middle; font-style: normal; font-variant: normal; font-weight: normal; font-size: 12px; line-height: normal; font-family: Arial, Helvetica, sans-serif;" font-size="12px" font-family="Arial, Helvetica, sans-serif"><tspan dy="4" style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0);">Service Task</tspan></text><path fill="#ffffff" stroke="#808080" d="M20.347,4.895L17.786,7.455L18.729000000000003,9.732L22.353,9.732L22.353,13.114999999999998L18.731,13.114999999999998L17.788,15.392L20.351,17.955L17.958,20.346999999999998L15.396999999999998,17.785999999999998L13.119999999999997,18.729L13.119999999999997,22.352999999999998L9.736999999999998,22.352999999999998L9.736999999999998,18.730999999999998L7.46,17.788L4.897,20.35L2.506,17.958L5.066,15.397L4.124,13.12L0.49999999999999956,13.12L0.49999999999999956,9.736999999999998L4.1209999999999996,9.736999999999998L5.0649999999999995,7.4609999999999985L2.5029999999999997,4.897999999999998L4.895,2.505999999999998L7.455,5.065999999999998L9.732,4.124999999999998L9.732,0.4999999999999982L13.116,0.4999999999999982L13.116,4.120999999999999L15.392,5.063999999999998L17.954,2.5019999999999984Z" stroke-width="1.4999999999999998" stroke-linecap="round" stroke-linejoin="round" stroke-opacity="1" transform="matrix(0.8,0,0,0.8,7.2853,7.2853)" style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0); stroke-linecap: round; stroke-linejoin: round; stroke-opacity: 1;"></path><path fill="#ffffff" stroke="#808080" d="M15.141,11.426C15.141,13.477185,13.478186,15.14,11.427,15.14C9.3758145,15.14,7.7130000999999995,13.477185,7.7130000999999995,11.426C7.7130000999999995,9.3748141,9.375814499999999,7.7119997,11.427,7.7119997C13.478185999999999,7.7119997,15.141,9.3748141,15.141,11.426Z" stroke-width="1.4999999999999998" stroke-linecap="round" stroke-linejoin="round" stroke-opacity="1" transform="matrix(0.8,0,0,0.8,7.2854,7.2852)" style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0); stroke-linecap: round; stroke-linejoin: round; stroke-opacity: 1;"></path><path fill="#ffffff" stroke="#808080" d="M26.347,10.895L23.786,13.455L24.729000000000003,15.732L28.353,15.732L28.353,19.115L24.731,19.115L23.788,21.392L26.351,23.955L23.958,26.346999999999998L21.397,23.785999999999998L19.119999999999997,24.729L19.119999999999997,28.352999999999998L15.736999999999998,28.352999999999998L15.736999999999998,24.730999999999998L13.459999999999997,23.787999999999997L10.896999999999997,26.349999999999998L8.505999999999997,23.958L11.065999999999997,21.397L10.123999999999997,19.119999999999997L6.4999999999999964,19.119999999999997L6.4999999999999964,15.736999999999998L10.120999999999997,15.736999999999998L11.064999999999998,13.460999999999999L8.502999999999998,10.897999999999998L10.894999999999998,8.505999999999998L13.454999999999998,11.065999999999999L15.732,10.124999999999998L15.732,6.499999999999998L19.116,6.499999999999998L19.116,10.120999999999999L21.392,11.063999999999998L23.954,8.501999999999999Z" stroke-width="1.4999999999999998" stroke-linecap="round" stroke-linejoin="round" stroke-opacity="1" transform="matrix(0.8,0,0,0.8,8.4853,8.4853)" style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0); stroke-linecap: round; stroke-linejoin: round; stroke-opacity: 1;"></path><path fill="#ffffff" stroke="#808080" d="M21.141,17.426001C21.141,19.477186,19.478185999999997,21.140000999999998,17.427,21.140000999999998C15.375814,21.140000999999998,13.713,19.477186,13.713,17.426001C13.713,15.374815,15.375813999999998,13.712000999999999,17.427,13.712000999999999C19.478186,13.712000999999999,21.141,15.374814999999998,21.141,17.426001Z" stroke-width="1.4999999999999998" stroke-linecap="round" stroke-linejoin="round" stroke-opacity="1" transform="matrix(0.8,0,0,0.8,8.4854,8.4852)" style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0); stroke-linecap: round; stroke-linejoin: round; stroke-opacity: 1;"></path></svg>'
});