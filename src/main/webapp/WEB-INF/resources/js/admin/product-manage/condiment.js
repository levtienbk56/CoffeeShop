var condiment;
var confirmAction;
var token = $("meta[name='_csrf']").attr("content");
var header = $("meta[name='_csrf_header']").attr("content");

// get value from table, insert into modal
function editCondiment(element) {
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
function removeCondiment(arg) {
	var id = arg.attr('value').replace(/\s/g, '');
	var r = confirm("Are you sure to remove : " + id);
	if (r == true) {
		$.ajax({
			type : "POST",
			url : "condiments/remove",
			data : {
				condimentId : id
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

// get update info, request UPDATE to server
$("#update-condiment")
		.click(
				function() {
					confirmAction = "update-condiment";

					// clear notice
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
						$(".modal-notice")
								.text(Message.getString().INPUT_EMPTY);
						return false;
					}

					// validate price
					if (parseFloat(price) < 0) {
						$(".modal-notice").addClass("alert alert-warning");
						$(".modal-notice").text(
								Message.getString().INPUT_INVALID_PRICE);
						return false;
					}
					condiment = new Condiment(id, name, price, enabled);
					console.log(condiment);

					showConfirmModal(Message.getString().CONDIMENT_UPDATE_CONFIRM);
					$("#modal-edit").modal('hide');
				});

// get new user info, request INSERT to server
$("#insert-condiment")
		.click(
				function() {
					confirmAction = "insert-condiment";

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
						$(".modal-notice")
								.text(Message.getString().INPUT_EMPTY);
						return false;
					}

					// validate price
					if (parseFloat(price) < 0) {
						$(".modal-notice").addClass("alert alert-warning");
						$(".modal-notice").text(
								Message.getString().INPUT_INVALID_PRICE);
						return false;
					}
					condiment = new Condiment('0', name, price, enabled);
					console.log(condiment);

					showConfirmModal(Message.getString().CONDIMENT_INSERT_CONFIRM);
					$("#modal-insert").modal('hide');
				});

// confirm ok, detect action
$("#confirm-modal .btn-success").click(function() {
	if (confirmAction == 'insert-condiment') {
		requestInsertCondiment();
	} else if (confirmAction == 'update-condiment') {
		requestUpdateCondiment();
	}
});

// request update to server
function requestUpdateCondiment() {
	console.log(condiment);
	// unable button
	$("#update-condiment").prop('disabled', true);

	// show modal
	$("#modal-edit").modal('show');

	// clear notice
	$(".modal-notice").removeClass("alert alert-warning alert-success");
	$(".modal-notice").text("");

	$.ajax({
		type : "POST",
		contentType : "application/json",
		url : "condiments/edit",
		data : JSON.stringify(condiment),
		dataType : 'json',
		timeout : 10000,
		beforeSend : function(xhr) {
			xhr.setRequestHeader(header, token);
		},
		success : function(data) {
			// unable button
			$("#update-condiment").prop('disabled', true);

			if (data.result == 'success') {
				$(".modal-notice").addClass("alert alert-success");
				$(".modal-notice").text(
						Message.getString().CONDIMENT_UPDATE_SUCCESS);
			} else {
				$(".modal-notice").addClass("alert alert-warning");
				$(".modal-notice").text(
						Message.getString().CONDIMENT_UPDATE_FAIL);
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
}

// request Insert to server
function requestInsertCondiment() {
	// unable button
	$("#insert-condiment").prop('disabled', true);

	// show modal
	$("#modal-insert").modal('show');

	// clear notice
	$(".modal-notice").removeClass("alert alert-warning alert-success");
	$(".modal-notice").text("");

	$.ajax({
		type : "POST",
		contentType : "application/json",
		url : "condiments/insert",
		data : JSON.stringify(condiment),
		dataType : 'json',
		timeout : 10000,
		beforeSend : function(xhr) {
			xhr.setRequestHeader(header, token);
		},
		success : function(data) {
			// unable button
			$("#insert-condiment").prop('disabled', true);

			if (data.result == 'success') {
				$(".modal-notice").addClass("alert alert-success");
				$(".modal-notice").text(
						Message.getString().CONDIMENT_INSERT_SUCCESS);
			} else {
				$(".modal-notice").addClass("alert alert-warning");
				$(".modal-notice").text(
						Message.getString().CONDIMENT_INSERT_FAIL);
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
}