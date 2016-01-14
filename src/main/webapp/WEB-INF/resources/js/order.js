var rowCount = 1;

$(document).ready(function(){
	// calcute price
	calcuteCupPriceFunction("cup1");
});

// remove a cup
$(document).on('click', '#btn_remove_cup', function() {
	var str = $(this).parent('p').parent('td').parent('tr');
	str.hide();
});

// when click "Add a Cup" button
// create new empty row
function addCupFunction2() {
	$("div#row_to_insert table tbody tr").attr("id", 'cup'+(++rowCount));
	var str = $("div#row_to_insert table tbody").html();
	$("#table_order tr#add_new").before(str);
	
	// calcute price
	calcuteCupPriceFunction("cup1");
}

$(document).on('change', 'select[name=coffee-name]', function() {
	var obj = $(this).parent('td').parent('tr');
	// param: id of row <tr>
	calcuteCupPriceFunction(obj.attr("id"));
});
$(document).on('click', 'input[name=coffee-size]', function() {
	var obj = $(this).parent('td').parent('tr');
	// param: id of row <tr>
	calcuteCupPriceFunction(obj.attr("id"));
});
$(document).on('click', 'input[name=coffee-condiment]', function() {
	var obj = $(this).parent('td').parent('tr');
	// param: id of row <tr>
	calcuteCupPriceFunction(obj.attr("id"));
});
$(document).on('change', 'input[name=coffee-quantity]', function() {
	var obj = $(this).parent('td').parent('tr');
	// param: id of row <tr>
	calcuteCupPriceFunction(obj.attr("id"));
});

function calcuteCupPriceFunction(id) {
	var total = parseFloat(0.0);
	var coffee = $("tr#" + id + " select[name=coffee-name] option:selected").val();
	
	var bigSize = $("tr#" + id + " input[name=coffee-size]:checked").val();
	var quantity = $('tr#' + id + ' input[name=coffee-quantity]').val();

	$("tr#" + id + " input[name=coffee-condiment]:checked")
			.each(function() {
				total += parseFloat($(this).val());
			});

	if (typeof (coffee) !== "undefined") {
		total += parseFloat(coffee);
		if (bigSize == "big") {
			total += parseFloat(0.5);
		}

		// lastest cal
		total *= parseInt(quantity);
	}
	
	// update price on current row
	$("tr#" + id + " text[name=cup-price]").text(total.toFixed(2));
	
	
	//update total price
	var totalPrice;
	$("text[name='cup-price']").each(function(){
		totalPrice += parseFloat($(this).val());
	});
	alert(totalPrice);
	$("td#total_price h3 strong").text(totalPrice.toFixed(2));
}



















//$("button#btn_remove_cup").click(function() {
//alert("sdada");
//});
