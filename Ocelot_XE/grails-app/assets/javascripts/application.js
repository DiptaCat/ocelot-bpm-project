//= require jquery
//= require bootstrap.min.js
//= require bootstrap-editable.min.js
//= require ace.min.js
//= require ace-editable.min.js
//= require_self

/*
	//= require jquery-ui-1.10.3.custom.min.js
	//= require jquery.ui.touch-punch.min.js
	//= require jquery.gritter.min.js
	//= require jquery.slimscroll.min.js
	//= require jquery.easy-pie-chart.min.js
	//= require jquery.hotkeys.min.js
	//= require jquery.maskedinput.min.js
	//= require jquery.autosize.min.js
	//= require jquery.datatables.bootstrap.js

	//= require bootstrap-timepicker.min.js
	//= require bootstrap-wysiwyg.min.js
	//= require bootstrap-editable.min.js

	//= require ace-extra.min.js
	//= require ace-elements.min.js

	//= require typeahead-bs2.min.js
	//= require bootbox.min.js
	//= require select2.min.js
	//= require fuelux.spinner.min.js

	//= require_tree .
*/

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