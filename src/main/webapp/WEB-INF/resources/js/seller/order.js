var listCup = {}; // request data
var totalPrice = 0.0; // current price of order
var rowCount = 0;
var coffeeChosen = 0;
var confirmAction;
var language = getLanguage();

/*
 * remove a cup when click remove button
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
 * when click "Add a Cup" button, create new empty row as a cup, then append to
 * table
 */
function addCupFunction() {
	var cupID = ++rowCount;
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
 * when click a dropdown, select coffee name. name,id,price of selected coffee
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
	dest.children('.name').html('<h4><strong>' + name + '</strong></h4>');
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
	if (size != undefined && size.toUpperCase() == 'BIG') {
		listCup[cupID].size = "BIG";
	} else {
		listCup[cupID].size = "NORMAL";
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

	var condiments = [];

	$("tr[name=" + cupID + "] input[name=coffee-condiment]:checked").each(
			function() {
				var div = $(this).next();
				var id = div.children('.id').text();
				var name = div.children('.name').text();
				var price = div.children('.price').text();
				var condiment = new Condiment(id, name, price);
				console.log('condiment: ' + "(" + id + "," + name + "," + price
						+ ")");

				condiments.push(condiment);
			});

	// update value in object
	listCup[cupID].condiments = condiments;

	// update price
	updatePriceFunction(cupID);
}

/*
 * this function reload price when each action select
 */
function updatePriceFunction(cupID) {
	// update price on current row
	$("tr[name=" + cupID + "] text[name=cup-price]").text(
			listCup[cupID].getPrice());
	console
			.log("update cup " + cupID + ", Price: "
					+ listCup[cupID].getPrice());
	// update total price
	updateTotalPriceFunction();
}

function updateTotalPriceFunction() {
	totalPrice = parseFloat(0);
	for (key in listCup) {
		totalPrice += parseFloat(listCup[key].getPrice());
	}

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
	if (pay <= 0) {
		return;
	}
	var refund = parseFloat(0);

	console.log("pay: " + pay + ", total: " + totalPrice);

	if (pay > totalPrice) {
		refund = pay - totalPrice;
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

$("button#btn-new-order").click(function() {
	confirmAction = 'new-order';
	var msg;
	if (language == 'ja') {
		msg = "当座オーダーを削除して、新しいオーダーを作ります。よろしいですか？";
	} else if (language == 'en') {
		msg = "Are you sure to create new order? Current cups'll be deleted.";
	}
	showConfirmModal(msg);
});

/*
 * checkout button clicked. validate input & open confirm modal
 */
$("button#btn-checkout").click(function() {
	confirmAction = 'checkout-order';
	var msg;

	// validate coffee selected?
	if (!checkCoffeeSelected()) {
		if (language == "ja") {
			showAlertModal('コーヒー種類をご選択ください！');
		} else {
			showAlertModal('choose a coffee first');
		}
		return;
	}

	if (language == "ja") {
		showConfirmModal("オーダーをチェックアウトしますか?");
	} else if (language == "en") {
		showConfirmModal("Are you sure to checkout order?");
	}
});

// confirm Modal: OK, process to checkout
$("#confirm-modal .btn-success").click(function() {
	if (confirmAction == 'new-order') {
		location.reload();
	} else if (confirmAction == 'checkout-order') {
		// request to server
		requestCheckoutOrder();
	}
});

/*
 * send request checkout order to server
 */
function requestCheckoutOrder() {
	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");

	// disable checkout button
	disableCheckoutButton(true);
	getListCupLength();
	showListCup();

	$.ajax({
		type : "POST",
		contentType : "application/json",
		url : "order",
		data : JSON.stringify(listCup),
		dataType : 'json',
		timeout : 10000,
		beforeSend : function(xhr) {
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
			xhr.setRequestHeader(header, token);
		},
		success : function(data) {
			// alert("Order success! total=" + data.total);
			$('#revieworder-modal').modal('show');
			// data as order object
			for (var i = 0; i < data.cups.length; i++) {
				var cup = data.cups[i];

				var coffeeName = cup.coffee.name;
				var price = parseFloat(cup.coffee.price);
				if (cup.size.toUpperCase() == "BIG")
					price *= 1.2;

				var condiments = cup.condiments;
				var condimentStr = '';
				for (var k = 0; k < condiments.length; k++) {
					condimentStr += condiments[k].name + ', ';
					price += condiments[k].price;
				}

				var str = "<tr> <td>" + coffeeName + "</td> <td>" + cup.size
						+ "</td><td>" + condimentStr
						+ "</td><td class='text-center'>" + price
						+ "</td></tr>";
				// insert into order-table
				$('#table-revieworder tr#revieworder-total').before(str);
			}
			$("#revieworder-modal tr#revieworder-total strong.price").text(
					data.total);
			return false;
		},
		error : function(e) {
			console.log("ERROR " + e);
		},
		done : function(e) {
			console.log("DONE " + e);
		}
	});
}

function showListCup() {
	for (key in listCup) {
		console.log(listCup[key]);
	}
}

function checkCoffeeSelected() {
	for (key in listCup) {
		cf = listCup[key].coffee;
		if (cf == null || cf.id == 0)
			return false;
	}
	return true;
}

// reload page when close review order modal
$("#revieworder-modal .modal-close").click(function() {
	location.reload();
});
