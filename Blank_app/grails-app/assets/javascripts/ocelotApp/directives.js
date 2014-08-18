/**
 * Created by sergi on 08/08/14.
 */

var app = angular.module('ocelotDirectives', []);

app.directive('loadAngularBootstrap', [function() {
    return function(scope, element, attrs) {
        angular.element('<script src="/blank/assets/bower_components/angular-bootstrap/ui-bootstrap-tpls.min.js?compile=false" type="text/javascript"></script>').appendTo(element);
    }
}]);

app.directive('showPalette', function(){
   return {
     restrict: 'E',
     templateUrl: 'palettePartials/palette.html',
     controller: function($scope, Palette, PaletteItem){
         $scope.accordionDict = {};
         var query = Palette.query();
         query.$promise.then(function (data) {
             var palette = data[0];
             palette.paletteItems.map(function (element) {
                 PaletteItem.get({id: element.id}).$promise.then(function (item) {

                     var category = item.category;

                     $scope.accordionDict[category] = $scope.accordionDict[category] || [];

                     if (item.activated) {
                         $scope.accordionDict[category].push(item);
                     }
//                     console.log(item);
                 });
             })
         });
     }
   };
});