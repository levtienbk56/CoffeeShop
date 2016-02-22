var rowCount = 0;
var coffeeChosen = 0;
var ORDER = {
	totalPrice : 0
}

/*
 * object list Cup
 */
var listCup = {};

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
	this.coffee = new Coffee("0", "", 0);
	this.cupSize = "NORMAL";
	this.quantity = 1;
	this.price = 0;
	this.condiments = {};
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
	// update total price
	updateTotalPriceFunction();

	// checkout button stage
	if (parseFloat(getListCupLength()) > 0) {
		disableCheckoutButton(false);
	} else {
		disableCheckoutButton(true);
	}
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
	
	disableCheckoutButton(false);
}

/*
 * when click a dropdown select coffee name name,id,price of selected coffee
 * will show on above <text> then create Coffee object, save into current cup
 */
function onSelectCoffeeNameFunction(element) {
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
function onSelectSizeFunction(element) {
	// ID of current cup
	var cupID = element.parent('td').parent('tr').attr("name");

	// update value size in object
	var size = $("tr[name=" + cupID + "] input[name=coffee-size]:checked")
			.val();
	console.log("size: " + size);
	if (size == 'big') {
		listCup[cupID].cupSize = "BIG";
		console.log("size: " + listCup[cupID].cupSize);
	} else {
		listCup[cupID].cupSize = "NORMAL";
		console.log("size: " + listCup[cupID].cupSize);
	}

	// update price
	updatePriceFunction(cupID);
};

/*
 * user update condiment
 */
function onSelectCondimentFunction(element) {
	// ID of current cup
	var cupID = element.parent('td').parent('tr').attr('name');

	var condiments = {};

	$("tr[name=" + cupID + "] input[name=coffee-condiment]:checked").each(
			function() {
				var div = $(this).next();
				var id = div.children('.id').text();
				var name = div.children('.name').text();
				var price = div.children('.price').text();
				var condiment = new Condiment(id, name, price);
				console.log('condiment: ' + "(" + id + "," + name + "," + price
						+ ")");

				condiments[id] = condiment;
			});

	// update value in object
	listCup[cupID].condiments = condiments;

	// update price
	updatePriceFunction(cupID);
}

/*
 * user update quantity
 */
function onSelectQuantityFunction(element) {
	var cupID = element.parent('td').parent('tr').attr("name");

	// update value in object
	var quantity = element.val();
	console.log("quantity: " + quantity);
	listCup[cupID].quantity = quantity;

	// update price
	updatePriceFunction(cupID);
};

/*
 * this function reload price when each action select
 */
function updatePriceFunction(cupID) {
	var key;
	var cupPrice = parseFloat(0.0);

	// get value from object
	var coffee = listCup[cupID].coffee;
	var size = listCup[cupID].cupSize;
	var sizeInt = 1;
	if(size == 'BIG') sizeInt = 1.2;

	// get condiment value
	var condiments = listCup[cupID].condiments;
	var condimentPrice = parseFloat(0.0);
	for (key in condiments) {
		condimentPrice += parseFloat(condiments[key].price);
	}
	console.log('condimentPrice:' + condimentPrice);

	var quantity = listCup[cupID].quantity;

	cupPrice += (parseFloat(coffee.price) * parseFloat(sizeInt) + condimentPrice)
			* parseFloat(quantity);
	console.log('cupPrice ' + cupID + " :" + cupPrice);

	// update price in Object
	listCup[cupID].price = cupPrice;

	// update price on current row
	$("tr[name=" + cupID + "] text[name=cup-price]").text(cupPrice.toFixed(2));

	// update total price
	updateTotalPriceFunction();
}

function updateTotalPriceFunction() {
	var totalPrice = parseFloat(0);
	for (key in listCup) {
		var p = listCup[key].price;
		totalPrice += parseFloat(p);

	}
	ORDER['totalPrice'] = totalPrice;

	// update on html page
	$("td#total_price h3 strong").text(totalPrice.toFixed(2));

	// update refund
	updateRefundFunction();
}

/*
 * calcute money must to refund to customer
 */
function updateRefundFunction() {
	var pay = parseFloat($("td#customer_pay input").val());
	var total = parseFloat(ORDER.totalPrice);
	var refund = parseFloat(0);

	console.log("pay: " + pay + ", total: " + total);

	if (pay > total) {
		refund = pay - total;
	}
	$("td#customer_refund h4 strong").text(refund.toFixed(2));
}

function getListCupLength() {
	var i;
	var count = parseInt(0);
	for (i in listCup) {
		count += parseInt(1);
	}

	console.log("list length: " + count);
	return count;
}

function disableCheckoutButton(flag) {
	$("#btn-checkout").prop("disabled", flag);
}
function newOrderFunction() {
	location.reload();
}

/* 
 * test ajax
 */
function checkoutFunction() {
	// check coffee selected?
	if(!checkCoffeeSelected()){
		alert('choose a coffee first');
		return;
	}
	
	// disable checkout button
	disableCheckoutButton(true);	getListCupLength();

	$.ajax({
		type : "POST",
		contentType : "application/json",
		url : "order",
		data : JSON.stringify(listCup),
		dataType : 'json',
		timeout : 100000,
		success : function(data) {
			alert("Order success! total=" + data.total);
			location.reload();
		},
		error : function(e) {
			console.log("ERROR " + e);
		},
		done : function(e) {
			console.log("DONE " + e);
		}
	});
}

function checkCoffeeSelected(){
	var p = "0";
	for (key in listCup) {
		p = listCup[key].coffee.id;
		if(p == "0") return false;
	}
	return true;
}