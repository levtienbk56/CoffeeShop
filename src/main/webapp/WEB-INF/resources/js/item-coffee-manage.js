function Coffee(id, name, price, enabled) {
	this.id = id;
	this.name = name;
	this.price = price;
	this.enabled = enabled;
}

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
			timeout : 100000,
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
$(".tbl-responsive")
		.click(
				function() {
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
					console
							.log('update coffee: ' + id + name + price
									+ enabled);

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

					var coffee = new Coffee(id, name, price, enabled);

					$.ajax({
						type : "POST",
						contentType : "application/json",
						url : "coffees/edit",
						data : JSON.stringify(coffee),
						dataType : 'json',
						timeout : 100000,
						success : function(data) {
							// unable button
							$("#update-coffee").prop('disabled', true);
							
							if (data.result == 'success') {
								$(".modal-notice").addClass(
										"alert alert-success");
								$(".modal-notice").text(data.message);
							} else {
								$(".modal-notice").addClass(
										"alert alert-warning");
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
				});

// get new user info, request INSERT to server
$("#insert-coffee")
		.click(
				function() {
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
					console.log('insert coffee: ' + name + price + enabled);

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

					var coffee = new Coffee('0', name, price, enabled);

					$.ajax({
						type : "POST",
						contentType : "application/json",
						url : "coffees/insert",
						data : JSON.stringify(coffee),
						dataType : 'json',
						timeout : 100000,
						success : function(data) {
							// unable button
							$("#insert-coffee").prop('disabled', true);
							
							if (data.result == 'success') {
								$(".modal-notice").addClass(
										"alert alert-success");
								$(".modal-notice").text(data.message);
							} else {
								$(".modal-notice").addClass(
										"alert alert-warning");
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
				});