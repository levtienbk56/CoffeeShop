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

// Flot Multiple Axes Line Chart
$(function() {
	var oilprices = [ [ 1167692400000, 61.05 ], [ 1167778800000, 58.32 ],
			[ 1167865200000, 57.35 ], [ 1167951600000, 56.31 ],
			[ 1220824800000, 106.34 ] ];
	var exchangerates = [ [ 1167606000000, 0.7580 ], [ 1167692400000, 0.7580 ],
			[ 1167778800000, 0.75470 ], [ 1167865200000, 0.75490 ],
			[ 1220911200000, 0.40050 ] ];

	function euroFormatter(v, axis) {
		return v.toFixed(axis.tickDecimals) + "€";
	}

	function doPlot(position) {
		$.plot($("#flot-line-chart-multi"), [ {
			data : oilprices,
			label : "Oil price ($)"
		}, {
			data : exchangerates,
			label : "USD/EUR exchange rate",
			yaxis : 2
		} ], {
			xaxes : [ {
				mode : 'time'
			} ],
			yaxes : [ {
				min : 0
			}, {
				// align if we are to the right
				alignTicksWithAxis : position == "right" ? 1 : null,
				position : position,
				tickFormatter : euroFormatter
			} ],
			legend : {
				position : 'sw'
			},
			grid : {
				hoverable : true
			// IMPORTANT! this is needed for tooltip to work
			},
			tooltip : true,
			tooltipOpts : {
				content : "%s for %x was %y",
				xDateFormat : "%y-%0m-%0d",

				onHover : function(flotItem, $tooltipEl) {
					// console.log(flotItem, $tooltipEl);
				}
			}

		});
	}

	doPlot("left ");

	$("button").click(function() {
		doPlot($(this).text());
	});
});

// Flot Bar Chart
// input: year & month
function updateBarChart() {
	var barOptions = {
		series : {
			bars : {
				show : true,
				barWidth : 43200000
			}
		},
		xaxis : {
			mode : "time",
			timeformat : "%m/%d",
			minTickSize : [ 1, "day" ]
		},
		grid : {
			hoverable : true
		},
		legend : {
			show : false
		},
		tooltip : true,
		tooltipOpts : {
			content : "x: %x, y: %y"
		},
		colors : [ "#0000FF" ]
	};

	var barData = {
		label : "bar",
		data : [ [ 1354521600000, 1000 ], [ 1355040000000, 2000 ],
				[ 1355223600000, 3000 ], [ 1355306400000, 4000 ],
				[ 1355487300000, 5000 ], [ 1355571900000, 6000 ] ]
	};
	
	var year = $("#select_year option:selected").text();
	var month = $("#select_month option:selected").text();
	
	if(year == "---" || month == "---") return;
	console.log("year:" +year + ",month:" +month);
	
	$.ajax({
		type : "POST",
		url : "analysis/bar-chart",
		data : {
			year : year, 
			month : month
		},
		timeout : 100000,
		success : function(data) {
			if (data != null) {
				var i = 0;
				var map;
				var mdata = [];
				for (i = 0; i < data.length; i++) {
					map = data[i];
					console.log(map.label + ':' + map.data);
					mdata.push([ map.label, map.data ])
				}

				barData.data = mdata;
				// put data to flot chart
				$.plot($("#flot-bar-chart"), [ barData ], barOptions);
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

};

// load year
$(function() {
	$.ajax({
		type : "POST",
		url : "analysis/years",
		timeout : 100000,
		success : function(data) {
			if (data != null) {
				$("#select_year").html("<option>---</option>");
				var i;
				for (i = 0; i < data.length; i++) {
					$("#select_year")
							.append("<option>" + data[i] + "</option>");
				}
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

// request months of a year
function loadMonth(year) {
	$.ajax({
		type : "POST",
		url : "analysis/months",
		data : {
			year : year
		},
		timeout : 100000,
		success : function(data) {
			if (data != null) {
				$("#select_month").html("<option>---</option>");
				var i;
				for (i = 0; i < data.length; i++) {
					$("#select_month").append(
							"<option>" + data[i] + "</option>");
				}
				
				// if there month data
				if(i>0){
					
				}
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

// when select year changed
$('select#select_year').on('change', function(e) {
	var optionSelected = $("option:selected", this);
	var valueSelected = this.value;
	if(valueSelected != "---") {
		loadMonth(valueSelected);
	}else{
		$("#select_month").html("<option>---</option>");
	}
});

// when select month changed
// reload bar chart
$('select#select_month').on('change', function(e) {
	var optionSelected = $("option:selected", this);
	var valueSelected = this.value;
	if(valueSelected != "---") updateBarChart();
});