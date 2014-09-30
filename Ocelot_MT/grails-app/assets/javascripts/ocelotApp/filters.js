/**
 * Created by sergi on 12/08/14.
 */

var app = angular.module('ocelotFilters', []);

app.filter('checkmark', function () {
	return function (input) {
		return input ? '\u2713' : '\u2718';
	};
});
