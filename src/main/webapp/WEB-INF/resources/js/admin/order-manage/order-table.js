var token = $("meta[name='_csrf']").attr("content");
var header = $("meta[name='_csrf_header']").attr("content");

$(function() {
	$('#datetimepicker6').datetimepicker({
		locale: language,
		format : "LL"
	});
	$('#datetimepicker7').datetimepicker({
		locale: language,
		format : "LL",
		useCurrent : false
	// Important! See issue #1075
	});
	$("#datetimepicker6").on("dp.change", function(e) {
		$('#datetimepicker7').data("DateTimePicker").minDate(e.date);
	});
	$("#datetimepicker7").on("dp.change", function(e) {
		$('#datetimepicker6').data("DateTimePicker").maxDate(e.date);
	});

	// search action
	$('#btn-search-by-range').click(
			function() {
				var from = new Date($("#datetimepicker6")
						.data("DateTimePicker").date()).getTime();
				var to = new Date($("#datetimepicker7").data("DateTimePicker")
						.date()).getTime();
				console.log('from:' + from + ', to:' + to);
				if (from == "" || from == null || to == "" || to == null) {
					alert("Input Empty!");
					return;
				}
				$.ajax({
					type : "POST",
					url : "analysis/order-by-range",
					data : {
						dfrom : from,
						dto : to
					},
					timeout : 10000,
					beforeSend : function(xhr) {
						xhr.setRequestHeader(header, token);
					},
					success : function(data) {
						// clear current table
						table01.clear().draw();
						if (data != null) {
							for (i = 0; i < data.length; i++) {
								table01.row.add(
										[
												data[i].id,
												data[i].user.username,
												moment(data[i].purchaseTime)
														.format('LL'),
												data[i].total ]).draw();

								// show out
								console.log(data[i].id
										+ " "
										+ data[i].user.username
										+ " "
										+ moment(data[i].purchaseTime).format(
												'LL') + " " + data[i].total);
							}

							if (i == 0) {
								alert("No record found");
							}
						} else {
							alert('no data');
						}
					},
					error : function(e) {
						console.log("ERROR " + e);
					},
					done : function(e) {
						console.log("DONE " + e);
					}
				});
			});
});

function editOrder(element) {
	return;
}

$(document)
		.ready(
				function() {
					// click on order, get list cups
					$('#order-tbl tbody')
							.on(
									'click',
									'tr',
									function() {
										if ($(this).hasClass('selected')) {
											$(this).removeClass('selected');
										} else {
											table01.$('tr.selected')
													.removeClass('selected');
											$(this).addClass('selected');
										}

										var data = table01.row(this).data();
										var orderID = data[0]; // at first row
										$
												.ajax({
													type : "POST",
													url : "cups",
													data : {
														orderId : orderID
													},
													timeout : 10000,
													beforeSend : function(xhr) {
														xhr.setRequestHeader(header, token);
													},
													success : function(data) {
														// clear cups table
														table02.clear().draw();
														if (data != null) {
															for (i = 0; i < data.length; i++) {
																table02.row
																		.add(
																				[
																						data[i].coffeeName,
																						data[i].size,
																						data[i].condimentsName,
																						data[i].price ])
																		.draw();

															}

														}
													},
													error : function(e) {
														console.log("ERROR "
																+ e);
													},
													done : function(e) {
														console
																.log("DONE "
																		+ e);
													}
												});
									});
				});