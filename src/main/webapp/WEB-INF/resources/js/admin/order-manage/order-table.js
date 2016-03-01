var table;

$(function() {
	$('#datetimepicker6').datetimepicker({
		format : "LL"
	});
	$('#datetimepicker7').datetimepicker({
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

				$.ajax({
					type : "POST",
					url : "analysis/order-by-range",
					data : {
						dfrom : from,
						dto : to
					},
					timeout : 100000,
					success : function(data) {
						// clear current table
						table.clear().draw();
						if (data != null) {
							for (i = 0; i < data.length; i++) {
								table.row.add(
										[
												data[i].id,
												data[i].username,
												moment(data[i].purchaseTime)
														.format('LL'),
												data[i].total ]).draw();

								// show out
								console.log(data[i].id
										+ " "
										+ data[i].username
										+ " "
										+ moment(data[i].purchaseTime).format(
												'LL') + " " + data[i].total);
							}

							if(i==0){
								alert("No record found");
							}
						} else {
							alert('no data');
						}
						// location.reload();
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