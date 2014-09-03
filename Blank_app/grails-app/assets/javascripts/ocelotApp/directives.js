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
     link:  function (scope, element, attrs) {
         scope.$watch('categoryGroup', function(newValue, oldValue) {
             if (newValue !== oldValue) {
                 console.log("Palette has changed", newValue);
             }
         }, true);
     }
   };
});

app.directive('loadSvg', function($compile){

    function link(scope, element, attrs) {
        scope.$watch(attrs.loadSvg, function(value) {
            console.log(value);
            element.append(value);
        });
    }

    return {
        link: link
    };

});