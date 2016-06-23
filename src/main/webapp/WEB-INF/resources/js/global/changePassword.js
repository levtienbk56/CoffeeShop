var language = getLanguage();
$("#update-pass").click(
		function() {
			var curpass = $("#updatepw-modal input#curPass").val();
			var newpass = $("#updatepw-modal input#newPass").val();
			var reppass = $("#updatepw-modal input#repPass").val();
			if (curpass == '' || newpass == '' || reppass == '') {
				$("#changePassNotification").removeClass();
				$("#changePassNotification").addClass(
						"alert col-md-10 col-md-offset-1 alert-warning");

				$("#changePassNotification").text(
						Message.getString().INPUT_EMPTY);
				console.log(Message.getString().INPUT_EMPTY);
				return false;
			}
			if (newpass != reppass) {
				$("#changePassNotification").removeClass();
				$("#changePassNotification").addClass(
						"alert col-md-10 col-md-offset-1 alert-warning");

				$("#changePassNotification").text(
						Message.getString().PWD_NOT_MATCH);
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
		url : "/CoffeeShop/change-pass",
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
				$("#changePassNotification").removeClass();
				$("#changePassNotification").addClass(
						"alert col-md-10 col-md-offset-1 alert-warning");
				$("#changePassNotification").text(
						Message.getString().PWD_INCORECT);
				// enable button
				$("#update-pass").prop('disabled', false);
			} else if (data.result == 'success') {
				$("#changePassNotification").removeClass();
				$("#changePassNotification").addClass(
						"alert col-md-10 col-md-offset-1 alert-success");

				$("#changePassNotification").text(
						Message.getString().PWD_CHANGED);
				// wait for 2
				// second, then
				// reload
				var myInterval = setInterval(function() {
					location.reload();
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