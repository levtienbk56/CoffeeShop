var rowCount = 0;

var ORDER = {
	totalPrice: 0
}
/*
 * object list Cup
 */
var listCup = [];

function Coffee(id, name, price) {
	this.id = id;
	this.name = name;
	this.price = price;
}

function Condiment(id, name, price) {
	this.id = id;
	this.name = name;
	this.price = price;
}

function Cup(id) {
	this.id = id;
	this.coffee = new Coffee("", "", 0);
	this.size = 1;
	this.quantity = 1;
	this.condiments = [];
	this.price = 0;
}

/*
 * remove a cup when click button
 */
function removeCupFunction(element) {
	var str = element.parent('p').parent('td').parent('tr');
	var cupID = str.attr("name");

	// delete cup in Object
	delete listCup[cupID];

	// hide html element
	str.hide();
	//update total price
	updateTotalPriceFunction();
};

/*
 * when click "Add a Cup" button create new empty row, as a cup
 */
function addCupFunction() {
	var cupID = 'cup' + (++rowCount);
	$("div#row_to_insert table tbody tr").attr("name", cupID);
	var str = $("div#row_to_insert table tbody").html();

	// append html as a cup
	$("#table_order tr#add_new").before(str);
	$("div#row_to_insert table tbody tr").attr("name", "cup0");
	// create cup object
	var cup = new Cup(cupID);
	listCup[cupID] = cup;
}

/*
 * when click a dropdown select coffee name name,id,price of selected coffee
 * will show on above <text> then create Coffee object, save into current cup
 */
function updateCoffeeNameFunction(element) {
	// get value
	var name = element.children('.name').text();
	var price = element.children('.price').text();
	var id = element.children('.id').text();

	// put out coffee name
	var dest = element.parent('div').parent('div').parent('td').children(
			'.alert');
	dest.children('.name').html('<strong>' + name + '</strong>');
	dest.children('.price').text(price);
	dest.children('.id').text(id);

	// save coffee in current Cup
	var cupID = element.parent('div').parent('div').parent('td').parent('tr')
			.attr('name');
	var coffee = new Coffee(id, name, price);
	listCup[cupID].coffee = coffee;

	// update price:
	updatePriceFunction(cupID);
	console.log("selected coffee: (" + id + "," + name + "," + price + ")");
}

/*
 * user update size
 */
$(document).on('click', 'input[name=coffee-size]', function() {
	// ID of current cup
	var cupID = $(this).parent('td').parent('tr').attr("name");

	// update value size in object
	var size = $("tr[name=" + cupID + "] input[name=coffee-size]:checked").val();
	console.log("size: " + size);
	if (size == 'big') {
		listCup[cupID].size = 1.5;
	} else {
		listCup[cupID].size = 1;
	}

	// update price
	updatePriceFunction(cupID);
});

/*
 * user update condiment
 */
$(document).on('click', 'input[name=coffee-condiment]', function() {
	// ID of current cup
	var cupID = $(this).parent('td').parent('tr').attr('name');

	var condiments = [];

	// TODO: get value from html, update value in object
	$("tr[name=" + cupID + "] input[name=coffee-condiment]:checked").each(function() {
		var div = $(this).next();
		var id = div.children('.id').text();
		var name = div.children('.name').text();
		var price = div.children('.price').text();
		var condiment = new Condiment(id, name, price);
		console.log('condiment: ' + "(" + id + "," + name + "," + price + ")");

		condiments[id] = condiment;
	});

	// update value in object
	listCup[cupID].condiments = condiments;

	// update price
	updatePriceFunction(cupID);
});

/*
 * user update quantity
 */
$(document).on('change', 'input[name=coffee-quantity]', function() {

	var cupID = $(this).parent('td').parent('tr').attr("name");

	// update value in object
	var quantity = $('tr[name=' + cupID + '] input[name=coffee-quantity]').val();
	console.log("quantity: " + quantity);
	listCup[cupID].quantity = quantity;

	// update price
	updatePriceFunction(cupID);
});

/*
 * this function reload price when each action select menu
 */
function updatePriceFunction(cupID) {
	var key;
	var cupPrice = parseFloat(0.0);

	// get value from object
	var coffee = listCup[cupID].coffee;
	var size = listCup[cupID].size;

	// get condiment value
	var condiments = listCup[cupID].condiments;
	var condimentPrice = parseFloat(0.0);
	for (key in condiments) {
		condimentPrice += parseFloat(condiments[key].price);
	}
	console.log('condimentPrice:' + condimentPrice);

	var quantity = listCup[cupID].quantity;

	cupPrice += (parseFloat(coffee.price) * parseFloat(size) + condimentPrice)
			* parseFloat(quantity);
	console.log('cupPrice ' + cupID + " :" + cupPrice);

	// update price in Object
	listCup[cupID].price = cupPrice;

	// update price on current row
	$("tr[name=" + cupID + "] text[name=cup-price]").text(cupPrice.toFixed(2));

	// update total price
	updateTotalPriceFunction();
}

function updateTotalPriceFunction(){
	var totalPrice = parseFloat(0);
	for (key in listCup) {
		var p = listCup[key].price;
		totalPrice += parseFloat(p);

	}
	ORDER['totalPrice'] = totalPrice;

	// update on html page
	$("td#total_price h3 strong").text(totalPrice.toFixed(2));
}

/*
 * ajax function, send order to controller
 */
function checkoutFunction() {
	   var txt = "tien";
	   $.post(
				"", 
				{name: encodeURIComponent(txt)},
				function(result){
					alert(result);
	});
}