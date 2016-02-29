$(document).ready(function() {
	$('#calendar').fullCalendar({
		header : {
			left : 'today ',
			center : 'prev title next',
			right : 'month agendaWeek agendaDay '
		},

		// first day is Monday
		firstDay : 1,

		// day click
		dayClick : function(date, jsEvent, view) {
			var selectDate = date.format('yyyy-MM-dd');
			var calendarType = view.name; // month, agendaWeek, agendaDay

			// change the day's background color just for fun
			// $(this).css('background-color', 'red');
			$('#modal-add-event').modal('show');
		}

	});

	// select time when create new EVENT
	$('.timepicker').timepicker({
		'scrollDefault' : 'now',
		'step' : 10
	});

	// select date when create new EVENT
	$('#start-date').datetimepicker({
		format : "LL"
	});
	$('#end-date').datetimepicker({
		format : "LL",
		useCurrent : false
	});
	$("#start-date").on("dp.change", function(e) {
		$('#end-date').data("DateTimePicker").minDate(e.date);
	});
	$("#end-date").on("dp.change", function(e) {
		$('#start-date').data("DateTimePicker").maxDate(e.date);
	});

});
