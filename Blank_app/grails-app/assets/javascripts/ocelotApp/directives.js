/**
 * Created by sergi on 08/08/14.
 */

var app = angular.module('ocelotDirectives', []);

app.directive('loadAngularBootstrap', [function() {
    return function(scope, element, attrs) {
        angular.element('<script src="/blank/assets/bower_components/angular-bootstrap/ui-bootstrap-tpls.min.js?compile=false" type="text/javascript"></script>').appendTo(element);
    }
}]);