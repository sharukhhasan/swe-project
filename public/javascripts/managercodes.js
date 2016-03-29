$( document ).ready(function() {
	if($('input[name=role]:checked').val() != 'admin' || $('input[name=role]:checked').val() != 'manager') {
		$("#managerid_field").hide();
	}


$('input[name=role]').on('change', function() {
	if($('input[name=role]:checked').val() == 'admin' || $('input[name=role]:checked').val() == 'manager') {
		$("#managerid_field").show();
	} else {
		$("#managerid_field").hide();
	}
});

});