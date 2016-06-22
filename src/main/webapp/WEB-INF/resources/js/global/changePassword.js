var language = getLanguage();
$("#update-pass").click(
		function() {
			var curpass = $("#updatepw-modal input#curPass").val();
			var newpass = $("#updatepw-modal input#newPass").val();
			var reppass = $("#updatepw-modal input#repPass").val();
			console.log("currentPass:" + curpass);
			console.log("newPass:" + newpass);
			console.log("repeatPass:" + reppass);
			if (curpass == '' || newpass == '' || reppass == '') {
				$("#changePassNotification").removeClass();
				$("#changePassNotification").addClass(
						"alert col-md-10 col-md-offset-1 alert-warning");
				
				if (language == "ja") {
					$("#changePassNotification").text("入力に記入してください");
				} else {
					$("#changePassNotification").text("Input Empty!");
				}
				
				return false;
			}
			if (newpass != reppass) {
				$("#changePassNotification").removeClass();
				$("#changePassNotification").addClass(
						"alert col-md-10 col-md-offset-1 alert-warning");
				
				if (language == "ja") {
					$("#changePassNotification").text("新しいパスワードが一致していません！");
				} else {
					$("#changePassNotification").text("New Password is not matching!");
				}
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
			var message = "";
			if (data.result == 'fail') {
				if (language == "ja") {
					message = "間違ったパスワード!";
				} else {
					message = "Wrong password!";
				}
				$("#changePassNotification").removeClass();
				$("#changePassNotification").addClass(
						"alert col-md-10 col-md-offset-1 alert-warning");
				$("#changePassNotification").text(message);
				// enable button
				$("#update-pass").prop('disabled', false);
			} else if (data.result == 'success') {
				$("#changePassNotification").removeClass();
				$("#changePassNotification").addClass(
						"alert col-md-10 col-md-offset-1 alert-success");

				if (language == "ja") {
					message = "パスワードが更新された!";
				} else {
					message = "Password was changed!";
				}
				$("#changePassNotification").text(message);
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