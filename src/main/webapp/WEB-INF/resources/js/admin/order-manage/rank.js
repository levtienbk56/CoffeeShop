$(document).ready(function() {
	$.ajax({
		type : "POST",
		url : "analysis/pie-chart",
		data : {
			require : 'coffee-correlate'
		},
		timeout : 100000,
		success : function(data) {
			if (data != null) {
				var i = 0;
				var map;
				for (i = 0; i < data.length; i++) {
					map = data[i];
					console.log(map.label + ':' + map.data);
				}

				// put data to flot chart
				showFlotPieChart(data);
			} else {
				alert('cups empty');
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

// Flot Pie Chart
function showFlotPieChart(data) {

	// var data = [{
	// label: "Series 0",
	// data: 1
	// }, {
	// label: "Series 1",
	// data: 3
	// }, {
	// label: "Series 2",
	// data: 9
	// }, {
	// label: "Series 3",
	// data: 20
	// }];

	var plotObj = $.plot($("#flot-pie-chart"), data, {
		series : {
			pie : {
				show : true
			}
		},
		grid : {
			hoverable : true
		},
		tooltip : true,
		tooltipOpts : {
			content : "%p.0%, %s", // show percentages, rounding to 2 decimal
			// places
			shifts : {
				x : 20,
				y : 0
			},
			defaultTheme : false
		}
	});

}