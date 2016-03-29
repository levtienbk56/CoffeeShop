function User(username, password, enabled, role) {
	this.username = username;
	this.password = password;
	this.enabled = enabled;
	this.role = role;
}

var confirmAction;
var user;
var language = getLanguage();

// get value from table, insert into modal
function editUser(element) {
	var username = element.parent('td').parent('tr').children('.td-username')
			.text();
	var password = element.parent('td').parent('tr').children('.td-password')
			.text();
	var enabled = element.parent('td').parent('tr').children('.td-enabled')
			.text();
	var role = element.parent('td').parent('tr').children('.td-role').text();
	console.log('edit ' + username + password + enabled + role);

	$('table#modal-tbl-edit').attr('name', username);
	$('table#modal-tbl-edit .td-username').text(username);
	$('table#modal-tbl-edit .td-password input').val(password);
	if (enabled == 'true') {
		$('table#modal-tbl-edit .td-enabled select').val('true');
	} else {
		$('table#modal-tbl-edit .td-enabled select').val('false');
	}
	if (role == 'SELLER') {
		$('table#modal-tbl-edit .td-role select').val('SELLER');
	} else {
		$('table#modal-tbl-edit .td-role select').val('ADMIN');
	}
}

// open confirm insert modal
$("#insert-user").click(function() {
	confirmAction = 'insert-user';
	
	// clear notice
	$(".modal-notice").removeClass("alert alert-warning alert-success");
	$(".modal-notice").text("");

	// remove space: .replace(/\s/g, '')
	var username = $('table#modal-tbl-insert .td-username input').val()
			.replace(/\s/g, '');
	var password = $('table#modal-tbl-insert .td-password input').val()
			.replace(/\s/g, '');
	var enabled = $('table#modal-tbl-insert .td-enabled select option:selected')
			.val();
	var role = $('table#modal-tbl-insert .td-role select option:selected')
			.val();

	console.log('insert ' + username + password + enabled + role);

	if (username == '' || password == ''
			|| (role != 'ADMIN' && role != 'SELLER')) {
		$(".modal-notice").addClass("alert alert-warning");
		$(".modal-notice").text("Input Empty!");
		return false;
	}

	user = new User(username, password, enabled, role);
	
	if(language=="ja"){
		showConfirmModal("ユーザーを追加しますか？");
	}else{
		showConfirmModal("Are you sure to insert user");
	}
	$("#modal-insert").modal('hide');
});

// open confirm update modal
$("#update-user").click(function() {
	confirmAction = 'update-user';
	
	// clear notice
	$(".modal-notice").removeClass("alert alert-warning alert-success");
	$(".modal-notice").text("");

	// remove space: .replace(/\s/g, '')
	var username = $('table#modal-tbl-edit .td-username').text().replace(/\s/g,
			'');
	var password = $('table#modal-tbl-edit .td-password input').val().replace(
			/\s/g, '');
	var enabled = $('table#modal-tbl-edit .td-enabled select option:selected')
			.val();
	var role = $('table#modal-tbl-edit .td-role select option:selected').val();

	if (username == '' || password == ''
			|| (role != 'ADMIN' && role != 'SELLER')) {
		$(".modal-notice").addClass("alert alert-warning");
		$(".modal-notice").text("Input Empty!");
		return false;
	}
	console.log('update ' + username + password + enabled + role);

	user = new User(username, password, enabled, role);
	
	if(language == "ja"){
		showConfirmModal("ユーザーを更新しますか?");
	}else{
		showConfirmModal("Are you sure to update user?");
	}
	$("#modal-edit").modal('hide');
});

// confirm ok, detect action
$("#confirm-modal .btn-success").click(function() {
	if (confirmAction == 'insert-user') {
		requestInsertUser(user);
	} else if (confirmAction == 'update-user') {
		requestUpdateUser(user);
	}
});

// send ajax request to INSERT user
function requestInsertUser(user) {
	// show modal again
	$("#modal-insert").modal('show');
	// clear notice
	$(".modal-notice").removeClass("alert alert-warning alert-success");
	$(".modal-notice").text("");
	
	// send ajax
	$.ajax({
		type : "POST",
		contentType : "application/json",
		url : "users/insert",
		data : JSON.stringify(user),
		dataType : 'json',
		timeout : 100000,
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
}

// send request ajax to UPDATE user
function requestUpdateUser(user) {
	// show modal again
	$("#modal-edit").modal('show');
	// clear notice
	$(".modal-notice").removeClass("alert alert-warning alert-success");
	$(".modal-notice").text("");
	
	// send AJAX
	$.ajax({
		type : "POST",
		contentType : "application/json",
		url : "users/edit",
		data : JSON.stringify(user),
		dataType : 'json',
		timeout : 100000,
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
}