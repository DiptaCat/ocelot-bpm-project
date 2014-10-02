//= require jquery
//= require bootstrap.min.js
//= require bootstrap-editable.min.js
//= require ace.min.js
//= require ace-editable.min.js
//= require_self

if (typeof jQuery !== 'undefined') {
	(function ($) {
		$('#spinner,.spinner').ajaxStart(function () {
			$(this).fadeIn();
		}).ajaxStop(function () {
			$(this).fadeOut();
		});
	})(jQuery);
}

// Activació automàtica dels tabs actius per les vistes
$(document).ready(function () {
	if (location.hash !== '') $('a[href="' + location.hash + '"]').tab('show');
	$('a[data-toggle="tab"]').on('shown', function (e) {
		return location.hash = $(e.target).attr('href').substr(1);
	});

	// activació dels inputs datepicker
	$('.datepicker').datepicker({format: 'dd/mm/yyyy', weekStart: 1, language: 'ca', autoclose: true});

	$('*[title]:not([data-toggle])').addClass('tooltiped');
	$('*[data-toggle=popover]').popover();
	$('*[data-toggle=tooltip], .tooltiped').tooltip({container: 'body'});


});