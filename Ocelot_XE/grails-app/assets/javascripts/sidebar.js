var APP_TITLE = "Task List";

$('.sidebar-collapse').on('click', function () {
	var title = $('#app-title').html();
	if (title == APP_TITLE) {
		$('#app-title').html('&nbsp;');
	}
	else {
		$('#app-title').html(APP_TITLE);
	}
});