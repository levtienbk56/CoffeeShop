var confirmAction;
var coffee;
var language = getLanguage();
var token = $("meta[name='_csrf']").attr("content");
var header = $("meta[name='_csrf_header']").attr("content");

// get value from table, insert into modal
function editCoffee(element) {
	var id = element.parent('td').parent('tr').children('.td-id').text();
	var name = element.parent('td').parent('tr').children('.td-name').text();
	var price = element.parent('td').parent('tr').children('.td-price').text();
	var enabled = element.parent('td').parent('tr').children('.td-enabled')
			.text();
	console.log('edit ' + id + name + price + enabled);

	$('table#modal-tbl-edit').attr('name', id);
	$('table#modal-tbl-edit .td-id').text(id);
	$('table#modal-tbl-edit .td-name input').val(name);
	$('table#modal-tbl-edit .td-price input').val(price);
	if (enabled == 'true') {
		$('table#modal-tbl-edit .td-enabled select').val('true');
	} else {
		$('table#modal-tbl-edit .td-enabled select').val('false');
	}
}

// get username, request DELETE to server
function removeCoffee(arg) {
	var id = arg.attr('value').replace(/\s/g, '');
	var r = confirm("Are you sure to remove : " + id);
	if (r == true) {
		$.ajax({
			type : "POST",
			url : "coffees/remove",
			data : {
				coffeeId : id
			},
			timeout : 10000,
			beforeSend : function(xhr) {
				xhr.setRequestHeader(header, token);
			},
			success : function(data) {
				if (data.result == 'success') {
					console.log(data.message);
					alert(data.message)
				} else {
					console.log(data.message);
					alert(data.message)
				}
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
}

// get new user info and validate before request to server
$("#update-coffee")
		.click(
				function() {
					confirmAction = 'update-coffee';

					// clear notice in modal
					$(".modal-notice").removeClass(
							"alert alert-warning alert-success");
					$(".modal-notice").text("");

					// remove space: .replace(/\s/g, '')
					var id = $('table#modal-tbl-edit .td-id').text().replace(
							/\s/g, '');
					var name = $('table#modal-tbl-edit .td-name input').val()
							.replace(/\s/g, '');
					var price = $('table#modal-tbl-edit .td-price input').val()
							.replace(/\s/g, '');
					var enabled = $(
							'table#modal-tbl-edit .td-enabled select option:selected')
							.val();

					// validate name
					if (id == '' || name == '' || price == '') {
						$(".modal-notice").addClass("alert alert-warning");
						$(".modal-notice").text("Input Empty!!");
						return false;
					}

					// validate price
					if (parseFloat(price) < 0) {
						$(".modal-notice").addClass("alert alert-warning");
						$(".modal-notice").text("Invalid Price!");
						return false;
					}

					coffee = new Coffee(id, name, price, enabled);
					console.log(coffee);
					if (language == "ja") {
						showConfirmModal("コーヒーを更新しますか？");
					} else if (language == "en") {
						showConfirmModal("Are you sure to update coffee");
					}
					$("#modal-edit").modal('hide');
				});

// get new user info and validate before request to server
$("#insert-coffee")
		.click(
				function() {
					confirmAction = 'insert-coffee';

					// clear notice
					$(".modal-notice").removeClass(
							"alert alert-warning alert-success");
					$(".modal-notice").text("");

					// remove space: .replace(/\s/g, '')
					var name = $('table#modal-tbl-insert .td-name input').val()
							.replace(/\s/g, '');
					var price = $('table#modal-tbl-insert .td-price input')
							.val().replace(/\s/g, '');
					var enabled = $(
							'table#modal-tbl-insert .td-enabled select option:selected')
							.val();

					// validate name
					if (name == '' || price == '') {
						$(".modal-notice").addClass("alert alert-warning");
						$(".modal-notice").text("Input Empty!!");
						return false;
					}

					// validate price
					if (parseFloat(price) < 0) {
						$(".modal-notice").addClass("alert alert-warning");
						$(".modal-notice").text("Invalid Price!");
						return false;
					}

					coffee = new Coffee('0', name, price, enabled);
					console.log(coffee);
					if (language == "ja") {
						showConfirmModal("コーヒーを追加しますか？");
					} else if (language == "en") {
						showConfirmModal("Are you sure to insert coffee");
					}
					$("#modal-insert").modal('hide');
				});

// confirm ok, detect action
$("#confirm-modal .btn-success").click(function() {
	if (confirmAction == 'insert-coffee') {
		requestInsertCoffee(coffee);
	} else if (confirmAction == 'update-coffee') {
		requestUpdateCoffee(coffee);
	}
});

// request update coffee
function requestUpdateCoffee(coffee) {
	// unable button
	$("#update-coffee").prop('disabled', true);

	// show modal
	$("#modal-edit").modal('show');

	// clear notice in modal
	$(".modal-notice").removeClass("alert alert-warning alert-success");
	$(".modal-notice").text("");

	$.ajax({
		type : "POST",
		contentType : "application/json",
		url : "coffees/edit",
		data : JSON.stringify(coffee),
		dataType : 'json',
		timeout : 10000,
		beforeSend : function(xhr) {
			xhr.setRequestHeader(header, token);
		},
		success : function(data) {
			if (data.result == 'success') {
				$(".modal-notice").addClass("alert alert-success");
				$(".modal-notice").text(data.message);
			} else {
				$(".modal-notice").addClass("alert alert-warning");
				$(".modal-notice").text(data.message);
			}
			// wait 1.5s then reload page
			setInterval(function() {
				location.reload();
			}, 1500);
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

// request insert coffee
function requestInsertCoffee(coffee) {
	// unable button
	$("#insert-coffee").prop('disabled', true);

	// show modal
	$("#modal-insert").modal('show');

	// clear notice
	$(".modal-notice").removeClass("alert alert-warning alert-success");
	$(".modal-notice").text("");

	$.ajax({
		type : "POST",
		contentType : "application/json",
		url : "coffees/insert",
		data : JSON.stringify(coffee),
		dataType : 'json',
		timeout : 10000,
		beforeSend : function(xhr) {
			xhr.setRequestHeader(header, token);
		},
		success : function(data) {
			if (data.result == 'success') {
				$(".modal-notice").addClass("alert alert-success");
				$(".modal-notice").text(data.message);
			} else {
				$(".modal-notice").addClass("alert alert-warning");
				$(".modal-notice").text(data.message);
			}
			// wait 1.5s then reload page
			setInterval(function() {
				location.reload();
			}, 1500);
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