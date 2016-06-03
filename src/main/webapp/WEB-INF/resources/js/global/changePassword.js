$("#update-pass")
		.click(
				function() {
					var curpass = $("#updatepw-modal input#curPass").val();
					var newpass = $("#updatepw-modal input#newPass").val();
					var reppass = $("#updatepw-modal input#repPass").val();
					console.log("currentPass:" + curpass);
					console.log("newPass:" + newpass);
					console.log("repeatPass:" + reppass);
					if (curpass == '' || newpass == '' || reppass == '') {
						$("#changePassNotification").removeClass();
						$("#changePassNotification")
								.addClass(
										"alert col-md-10 col-md-offset-1 alert-warning");
						$("#changePassNotification").text("Input Empty!");
						return false;
					}
					if (newpass != reppass) {
						$("#changePassNotification").removeClass();
						$("#changePassNotification")
								.addClass(
										"alert col-md-10 col-md-offset-1 alert-warning");
						$("#changePassNotification").text(
								"New Password is not match");
						return false;
					} else {
						// password
						$
								.ajax({
									type : "POST",
									url : "/CoffeeShop/change-pass",
									data : {
										currentPass : curpass,
										newPass : newpass
									},
									timeout : 100000,
									success : function(data) {
										console.log("result: " + data.result
												+ ", message: " + data.message);
										if (data.result == 'fail') {
											$("#changePassNotification")
													.removeClass();
											$("#changePassNotification")
													.addClass(
															"alert col-md-10 col-md-offset-1 alert-warning");
											$("#changePassNotification").text(
													data.message);
										} else if (data.result == 'success') {
											$("#changePassNotification")
													.removeClass();
											$("#changePassNotification")
													.addClass(
															"alert col-md-10 col-md-offset-1 alert-success");
											$("#changePassNotification").text(
													data.message);
											// wait for 2
											// second, then
											// reload
											var myInterval = setInterval(
													function() {
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
						return false;
					}
				});