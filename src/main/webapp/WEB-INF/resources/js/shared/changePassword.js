$(document).ready(
		function() {
			$("#update-pass").click(
					function() {
						var curpass = $("#updatepw-modal input#curPass").val();
						var newpass = $("#updatepw-modal input#newPass").val();
						var reppass = $("#updatepw-modal input#repPass").val();
						console.log("/" + curpass + "/" + newpass + "/"
								+ reppass + "/");
						if (newpass != reppass) {
							$("#changePassNotification").addClass(
									"alert-warning");
							$("#changePassNotification").text(
									"New Password is not match");
							return false;
						} else {
							// TODO: request ajax update password
							$.ajax({
								type : "POST",
								url : "change-pass",
								data : {
									currentPass : curpass,
									newPass : newpass
								},
								timeout : 100000,
								success : function(data) {
									console.log("result: " + data.result
											+ ", message: " + data.message);
									if (data.result == 'fail') {
										$("#changePassNotification").addClass(
												"alert-warning");
										$("#changePassNotification").text(
												data.message);
									} else if (data.result == 'success') {
										$("#changePassNotification").addClass(
												"alert-success");
										$("#changePassNotification").text(
												data.message);
										location.reload();
										alert("success");
									}
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
					});
		});
