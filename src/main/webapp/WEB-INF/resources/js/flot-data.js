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

// flot stack bar
function updateStackBarChart(year, month) {
	/*
	 * data: 
	 * [{'label' : label1, 'data' : [[data1, tick_position1], [], ....]}
	 * ,{'label' : label2, 'data' : [[data2, tick_position2], [], ....]}
	 * ,{...}] 
	 */
	var barOption = {
		series : {
			stack : true,
			shadowSize : 0,
			lines : {
				show : false,
				fill : true,
				steps : false
			},
			bars : {
				show : true,
				align : "center",
				barWidth : 0.7
			}
		},
		xaxis : {
			tickLength : 0,
			color : "#474e54",
			min : 0,
			max : 31,
			ticks : [ 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16,
					17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31 ],
			font : {
				size : 14,
				weight : 300
			// family: "OpenSans Light"
			}
		},
		yaxis : {
			min:0
		}, 
		grid : {
			hoverable : true,
			color : "#474e54",
			backgroundColor : {
				colors : [ "#fff", "#fff" ]
			},
			borderWidth : {
				top : 0,
				right : 0,
				bottom : 2,
				left : 2
			}
		},
		tooltip : true,
		tooltipOpts : {
			content : "%y"
		},
	};


	$.ajax({
		type : "POST",
		url : "analysis/stack-bar-chart",
		data : {
			year : year,
			month : month
		},
		timeout : 100000,
		success : function(data) {
			if (data != null) {
				var i = 0, j = 0;

				var mydata = [];
				for (i = 0; i < data.length; i++) {
					var key;
					var arr = [];
					var arr1 = data[i].data;
					for (key in arr1) {
						arr.push([key, arr1[key]]);
					}
					;
					var map = {
						'label' : data[i].label,
						'data' : arr
					};
					mydata.push(map);
					console.log(map.label + ':' + map.data);
				}

				$.plot("#flot-stack-bar-chart", mydata, barOption);
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
}

// Flot Bar Chart
// input: year & month
function updateBarChart(year, month) {
	var barOptions = {
		series : {
			bars : {
				show : true,
				barWidth : 43200000
			}
		},
		xaxis : {
			mode : "time",
			timeformat : "%d",
			minTickSize : [ 1, "day" ]
		},
		grid : {
			show: true,
			hoverable : true
		},
		legend : {
			show : true
		},
		tooltip : true,
		tooltipOpts : {
			content : "%y"
		},
		colors : [ "#0000FF" ]
	};

	var barData = {
		label : "bar",
		data : [ [ 1354521600000, 1000 ], [ 1355040000000, 2000 ],
				[ 1355223600000, 3000 ], [ 1355306400000, 4000 ],
				[ 1355487300000, 5000 ], [ 1355571900000, 6000 ] ]
	};

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
				if (i > 0) {

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
	if (valueSelected != "---") {
		loadMonth(valueSelected);
	} else {
		$("#select_month").html("<option>---</option>");
	}
});

// when select month changed
// reload bar chart
$('select#select_month').on('change', function(e) {
	var optionSelected = $("option:selected", this);
	var month = this.value;

	var year = $("#select_year option:selected").text();
	console.log("year:" + year + ",month:" + month);

	if (year != "---" && month != "---") {
		updateBarChart(year, month);
		updateStackBarChart(year, month);
	}
});