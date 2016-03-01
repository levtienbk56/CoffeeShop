function Event(id, title, start, end, color) {
	this.id = id;
	this.title = title;
	this.start = start;
	this.end = end;
	this.color = color;
}

var eventSource = {};

$(document).ready(function() {
	// init calendar
	$('#calendar').fullCalendar({
		header : {
			left : 'today ',
			center : 'prev title next',
			right : 'month agendaWeek agendaDay '
		},

		// first day is Monday
		firstDay : 1,
		
		// set timezone to local UTC+7
		timezone: 'local',

		// day click
		dayClick : function(date, jsEvent, view) {
			var selectDate = date.format('yyyy-MM-dd');
			var calendarType = view.name; // month,
			// agendaWeek,
			// agendaDay

			// change the day's background color just for fun
			// $(this).css('background-color', 'red');
			$('#modal-add-event').modal('show');
		},
		// event click
		eventClick : function(calEvent, jsEvent, view) {
			var str = 'Title: ' + calEvent.title + '\n';
			str += 'start: ' + calEvent.start + '\n';
			str += 'end: ' + calEvent.end;
			alert(str);
		}

	});

	// request data
	requestEventsForCalendar();

	// select time when create new EVENT
	$('.timepicker').timepicker({
		'scrollDefault' : 'now',
		'step' : 10, // 10 minutes
		'show2400' : true,
		'showOnFocus' : true,
		'timeFormat' : 'G:i:s'
	});

	// select date when create new EVENT
	$('#start-date').datetimepicker({
		format : "YYYY-MM-DD"
	});
	$('#end-date').datetimepicker({
		format : "YYYY-MM-DD",
		useCurrent : false
	});
	$("#start-date").on("dp.change", function(e) {
		$('#end-date').data("DateTimePicker").minDate(e.date);
	});
	$("#end-date").on("dp.change", function(e) {
		$('#start-date').data("DateTimePicker").maxDate(e.date);
	});

});

// find all events data in DB then show in calendar
function requestEventsForCalendar() {
	eventSource = {
		events : [ {
			title : 'Event1',
			start : '2016-02-28 16:10:00',
			end : '2016-02-28 19:20:00',
			color : 'yellow'
		}, {
			title : 'Event2',
			start : '2016-02-29 12:30:00',
			end : '2016-02-29 14:30:00',
			color : 'green'
		}
		// etc...
		]
	};

	$.ajax({
		type : "POST",
		url : "/CoffeeShop/admin/calendar/events",
		data : {},
		timeout : 100000,
		success : function(data) {
			// print out
			for (var i = 0; i < data.length; i++) {
				console.log(data[i].id + data[i].title + data[i].start
						+ data[i].end + data[i].color);
			}

			// show on calendar
			eventSource.events = data;
			$('#calendar').fullCalendar('addEventSource', eventSource);
		},
		error : function(e) {
			console.log("ERROR " + e);
		},
		done : function(e) {
			console.log("DONE " + e);
		}
	});
}

$("button#add-event")
		.click(
				function() {
					var title = $("input#title").val();
					var startTime = $("input#start-time").val();
					var startDate = $("input#start-date").val();
					var endTime = $("input#end-time").val();
					var endDate = $("input#end-date").val();
					var color = $('select option:selected').val();

					console.log('event: ' + title + startTime + startDate
							+ endTime + endDate + color);

					// validate input
					if (title == '' || startTime == '' || startDate == ''
							|| endTime == '' || endDate == '') {
						$(".modal-notice")
								.html(
										"<div class='text-center alert alert-warning'>Input Empty</div>");
						return false;
					}

					var start = new Date(startDate + ' ' + startTime);
					var end = new Date(endDate + ' ' + endTime);
					var mEvent = new Event(0, title, start, end, color);
					console.log(mEvent);
					return addEventRequest(mEvent);
				});
function addEventRequest(event) {
	$.ajax({
		type : "POST",
		contentType : "application/json",
		url : "/CoffeeShop/admin/calendar/insert-event",
		data : JSON.stringify(event),
		dataType : 'json',
		timeout : 100000,
		success : function(data) {
			if (data.result == 'success') {
				$(".modal-notice").html(
						"<div class='text-center alert alert-success'>"
								+ data.message + "</div>");
			} else {
				$(".modal-notice").html(
						"<div class='text-center alert alert-warning'>"
								+ data.message + "</div>");
			}
			// wait 1.5s then reload page
			setInterval(function() {
				location.reload();
			}, 1500);
		},
		error : function(e) {
			console.log("ERROR " + e);
		},
		done : function(e) {
			console.log("DONE " + e);
		}
	});
	return false;
}
