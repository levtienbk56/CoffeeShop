var language = getLanguage();
$("#update-pass").click(function() {
	var curpass = $("#updatepw-modal input#curPass").val();
	var newpass = $("#updatepw-modal input#newPass").val();
	var reppass = $("#updatepw-modal input#repPass").val();
	if (curpass == '' || newpass == '' || reppass == '') {
		alertWarning();

		$("#changePassNotification").text(Message.getString().INPUT_EMPTY);
		console.log(Message.getString().INPUT_EMPTY);
		return false;
	}
	if (newpass.length < 4) {
		alertWarning();

		$("#changePassNotification").text(Message.getString().PWD_TOO_SHORT);
		return false;
	}

	if (newpass != reppass) {
		alertWarning();

		$("#changePassNotification").text(Message.getString().PWD_NOT_MATCH);
		return false;
	} else {
		requestChangePass(curpass, newpass);
		return false;
	}
});

function requestChangePass(curpass, newpass) {
	// unable button
	$("#update-pass").prop('disabled', true);

	// password
	$.ajax({
		type : "POST",
		url : URL_CHANGEPASS,
		data : {
			currentPass : curpass,
			newPass : newpass
		},
		timeout : 10000,
		beforeSend : function(xhr) {
			xhr.setRequestHeader(header, token);
		},
		success : function(data) {
			if (data.result == 'fail') {
				alertWarning();

				$("#changePassNotification").text(
						Message.getString().PWD_INCORECT);
				// enable button
				$("#update-pass").prop('disabled', false);
			} else if (data.result == 'success') {
				alertSuccess();

				$("#changePassNotification").text(
						Message.getString().PWD_CHANGED);
				
				// wait for 2 second, then logout
				var myInterval = setInterval(function() {
					window.location = URL_LOGOUT;
				}, 1500);
			}
		},
		error : function(e) {
			console.log("ERROR " + e);
		},
		done : function(e) {
			console.log("DONE " + e);
		}
	});
}

function alertWarning() {
	$("#changePassNotification").removeClass();
	$("#changePassNotification").addClass(
			"alert col-md-10 col-md-offset-1 alert-warning");
}

function alertSuccess() {
	$("#changePassNotification").removeClass();
	$("#changePassNotification").addClass(
			"alert col-md-10 col-md-offset-1 alert-success");
}