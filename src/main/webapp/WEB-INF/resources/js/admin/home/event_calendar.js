var eventSource = {};
var confirmAction;
var mEvent;
var language = getLanguage();
var token = $("meta[name='_csrf']").attr("content");
var header = $("meta[name='_csrf_header']").attr("content");

$(document).ready(function() {
	// init calendar
	$('#calendar').fullCalendar({
		lang : language,
		header : {
			left : 'today ',
			center : 'prev title next',
			right : 'month agendaWeek agendaDay '
		},

		// first day is Monday
		firstDay : 1,

		// set timezone to local UTC+7
		timezone : 'local',

		// day click
		dayClick : function(date, jsEvent, view) {
			var selectDate = date.format('yyyy-MM-dd');
			var calendarType = view.name; // month,
			// agendaWeek,
			// agendaDay

			// change the day's background color just for fun
			// $(this).css('background-color', 'red');
			$('#modal-insert').modal('show');
		},
		// event click
		eventClick : function(calEvent, jsEvent, view) {
			var str = 'Title: ' + calEvent.title + '\n';
			str += 'start: ' + calEvent.start + '\n';
			str += 'end: ' + calEvent.end;

			$('#modal-edit').modal('show');
			pushEventToEditModal(calEvent);
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
	$('.start-date').datetimepicker({
		format : "YYYY-MM-DD"
	});
	$('.end-date').datetimepicker({
		format : "YYYY-MM-DD",
		useCurrent : false
	});
	$(".start-date").on("dp.change", function(e) {
		$('.end-date').data("DateTimePicker").minDate(e.date);
	});
	$(".end-date").on("dp.change", function(e) {
		$('.start-date').data("DateTimePicker").maxDate(e.date);
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
		beforeSend : function(xhr) {
			xhr.setRequestHeader(header, token);
		},
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

function pushEventToEditModal(e) {
	var start = new Date(e.start);
	var sh = start.getHours();
	var sm = start.getMinutes();
	var ss = start.getSeconds();
	var sy = start.getFullYear();
	var sM = parseInt(start.getMonth() + 1);
	var sd = start.getDate();
	if (sm < 10)
		sm = '0' + sm;
	if (ss < 10)
		ss = '0' + ss;
	if (sM < 10)
		sM = '0' + sM;
	if (sd < 10)
		sd = '0' + sd;

	var end = new Date(e.end);
	var eh = end.getHours();
	var em = end.getMinutes();
	var es = end.getSeconds();
	var ey = end.getFullYear();
	var eM = parseInt(end.getMonth() + 1);
	var ed = end.getDate();

	if (em < 10)
		em = '0' + em;
	if (es < 10)
		es = '0' + es;
	if (eM < 10)
		eM = '0' + eM;
	if (ed < 10)
		ed = '0' + ed;

	$("input#id-edit").val(e.id);
	$("input#title-edit").val(e.title);
	$("input#start-time-edit").val(sh + ':' + sm + ':' + ss);
	$("input#start-date-edit").val(sy + '-' + sM + '-' + sd);
	$("input#end-time-edit").val(eh + ':' + em + ':' + es);
	$("input#end-date-edit").val(ey + '-' + eM + '-' + ed);
	$('select#colors-edit').val(e.color);
}

// when confirm update event
$("button#btn-update-event")
		.click(
				function() {
					confirmAction = 'update-event';

					// clear notice
					$(".modal-notice").removeClass(
							"alert alert-warning alert-success");
					$(".modal-notice").text("");

					var id = $("input#id-edit").val();
					var title = $("input#title-edit").val();
					var startTime = $("input#start-time-edit").val();
					var startDate = $("input#start-date-edit").val();
					var endTime = $("input#end-time-edit").val();
					var endDate = $("input#end-date-edit").val();
					var color = $('select#colors-edit option:selected').val();

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
					mEvent = new Event(id, title, start, end, color);
					console.log(mEvent);

					if (language == "ja") {
						showConfirmModal("イベントを更新しますか？");
					} else {
						showConfirmModal("Are you sure to update event?");
					}
					$("#modal-edit").modal('hide');
				});

// when confirm remove new event
$("button#btn-remove-event").click(function() {
	confirmAction = 'remove-event';

	var id = $("input#id-edit").val();
	mEvent = new Event(id, '', null, null, '');

	if (language == "ja") {
		showConfirmModal("イベントを削除しますか？");
	} else {
		showConfirmModal("Are you sure to delete event?");
	}
	$("#modal-edit").modal('hide');
});

// when confirm add new event
$("button#btn-insert-event")
		.click(
				function() {
					confirmAction = 'insert-event';

					// clear notice
					$(".modal-notice").removeClass(
							"alert alert-warning alert-success");
					$(".modal-notice").text("");

					var title = $("input#title-add").val();
					var startTime = $("input#start-time-add").val();
					var startDate = $("input#start-date-add").val();
					var endTime = $("input#end-time-add").val();
					var endDate = $("input#end-date-add").val();
					var color = $('select#colors-add option:selected').val();

					console.log('event: ' + title + startTime + startDate
							+ endTime + endDate + color);

					// validate input
					if (title == '' || startTime === '' || startDate == ''
							|| endTime === '' || endDate == '') {
						$(".modal-notice")
								.html(
										"<div class='text-center alert alert-warning'>Input Empty</div>");
						return false;
					}

					var start = new Date(startDate + ' ' + startTime);
					var end = new Date(endDate + ' ' + endTime);
					mEvent = new Event(0, title, start, end, color);
					console.log(mEvent);

					if (start == "Invalid Date" || end == "Invalid Date") {
						$(".modal-notice")
								.html(
										"<div class='text-center alert alert-warning'>Input Error! Invalid Date</div>");
						return false;
					}

					if (language == "ja") {
						showConfirmModal("イベントを追加しますか？");
					} else {
						showConfirmModal("Are you sure to insert event?");
					}
					$("#modal-insert").modal('hide');
				});

// confirm ok, detect action
$("#confirm-modal .btn-success").click(function() {
	if (confirmAction == 'insert-event') {
		requestInsertEvent(mEvent);
	} else if (confirmAction == 'update-event') {
		requestUpdateEvent(mEvent);
	} else if (confirmAction == 'remove-event') {
		requestRemoveEvent(mEvent);
	}
});

// send request: Add new Event
function requestInsertEvent(event) {
	// unable button
	$("#btn-insert-event").prop('disabled', true);

	// show modal again
	$("#modal-insert").modal('show');

	// clear notice
	$(".modal-notice").removeClass("alert alert-warning alert-success");
	$(".modal-notice").text("");

	$.ajax({
		type : "POST",
		contentType : "application/json",
		url : "/CoffeeShop/admin/calendar/insert-event",
		data : JSON.stringify(event),
		dataType : 'json',
		timeout : 100000,
		beforeSend : function(xhr) {
			xhr.setRequestHeader(header, token);
		},
		success : function(data) {
			var message = "";
			if (data.result == 'success') {
				$(".modal-notice").addClass("alert alert-success");
				if (language == "ja") {
					message = "イベントを追加した";
				} else {
					message = "Event added successful!";
				}
			} else {
				$(".modal-notice").addClass("alert alert-warning");
				if (language == "ja") {
					message = "イベントを追加するのを失敗した!";
				} else {
					message = "Error occurred!";
				}
			}
			$(".modal-notice").text(message);
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
}

// send request: update EVENT
function requestUpdateEvent(event) {
	// unable button
	$("#btn-update-event").prop('disabled', true);
	// unable button
	$("#btn-remove-event").prop('disabled', true);

	// show modal again
	$("#modal-edit").modal('show');

	// clear notice
	$(".modal-notice").removeClass("alert alert-warning alert-success");
	$(".modal-notice").text("");

	$.ajax({
		type : "POST",
		contentType : "application/json",
		url : "/CoffeeShop/admin/calendar/update-event",
		data : JSON.stringify(event),
		dataType : 'json',
		timeout : 100000,
		beforeSend : function(xhr) {
			xhr.setRequestHeader(header, token);
		},
		success : function(data) {
			var message = "";
			if (data.result == 'success') {
				$(".modal-notice").addClass("alert alert-success");
				if (language == "ja") {
					message = "イベントが変更させた";
				} else {
					message = "Event updated successful!";
				}
			} else {
				$(".modal-notice").addClass("alert alert-warning");
				if (language == "ja") {
					message = "イベントを変更するのを失敗した";
				} else {
					message = "Error occurred!";
				}
			}
			$(".modal-notice").text(message);

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
}

// send request: remove EVENT
function requestRemoveEvent(event) {
	// unable button
	$("#btn-remove-event").prop('disabled', true);
	// unable button
	$("#btn-update-event").prop('disabled', true);

	// show modal again
	$("#modal-edit").modal('show');
	// clear notice
	$(".modal-notice").removeClass("alert alert-warning alert-success");
	$(".modal-notice").text("");

	$.ajax({
		type : "POST",
		url : "/CoffeeShop/admin/calendar/remove-event",
		data : {
			eventID : event.id
		},
		timeout : 100000,
		beforeSend : function(xhr) {
			xhr.setRequestHeader(header, token);
		},
		success : function(data) {
			var message = "";
			if (data.result == 'success') {
				$(".modal-notice").addClass("alert alert-success");
				if (language == "ja") {
					message = "イベントが削除させた！";
				} else {
					message = "Event deleted successful!";
				}
			} else {
				$(".modal-notice").addClass("alert alert-warning");
				if (language == "ja") {
					message = "イベントを削除するのを失敗した！";
				} else {
					message = "Error occurred!";
				}
			}
			$(".modal-notice").text(message);

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
}