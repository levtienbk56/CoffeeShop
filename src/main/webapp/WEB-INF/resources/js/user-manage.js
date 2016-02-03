function User(username, password, enabled, role){
	this.username = username;
	this.password = password;
	this.enabled = enabled;
	this.role = role;
}

// get username, request delete to server
function removeUser(arg){
	var username = arg.attr('value').replace(/\s/g, '');
	var r = confirm("Are you sure to remove user: " + username);
	if(r ==  true){
		$.ajax({
			type : "POST",
			url : "orders/analysis/pie-chart",
			data : {username:username},
			timeout : 100000,
			success : function(data) {
				if(data!=null){
					console.log("removed user:" + data.username);					
				}else{
					alert('user not exist!');
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

// get value from table, insert into modal
function editUser(element){
	var username = element.parent('td').parent('tr').children('.td-username').text();
	var password = element.parent('td').parent('tr').children('.td-password').text();
	var enabled = element.parent('td').parent('tr').children('.td-enabled').text();
	var role = element.parent('td').parent('tr').children('.td-role').text();
	console.log('edit ' + username + password + enabled + role);
	
	$('table#modal-tbl-edit-user').attr('name', username);
	$('table#modal-tbl-edit-user .td-username').text(username);
	$('table#modal-tbl-edit-user .td-password input').val(password);
	if(enabled == 'true'){
		$('table#modal-tbl-edit-user .td-enabled input').prop('checked', true);
	}else{
		$('table#modal-tbl-edit-user .td-enabled input').prop('checked', false);
	}
	$('table#modal-tbl-edit-user .td-role input').val(role);	
}

// get update info, request to server
function updateUser(){
	// remove space: .replace(/\s/g, '')
	var username = $('table#modal-tbl-edit-user .td-username').text().replace(/\s/g, '');
	var password = $('table#modal-tbl-edit-user .td-password input').val().replace(/\s/g, '');
	var role = $('table#modal-tbl-edit-user .td-role input').val().replace(/\s/g, '');	
	var enabled = $('table#modal-tbl-edit-user .td-enabled input').prop('checked');
	
	if(username == '' || password == '' || (role != 'ADMIN' && role != 'SELLER')){
		alert('Input empty. Role must be ADMIN or SELLER');
		return;
	}
	console.log('update ' + username + password + enabled + role);
	
	var user = new User(username, password, enabled, role);
	
	$.ajax({
		type : "POST",
		contentType : "application/json",
		url : "users/edit",
		data : JSON.stringify(user),
		dataType : 'json',
		timeout : 100000,
		success : function(data) {
			if(data!=null){
				console.log('update success!');					
			}else{
				console.log('update fail: user not exist!');
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

// get new user info, request to server
function insertUser(){
	// remove space: .replace(/\s/g, '')
	var username = $('table#modal-tbl-insert-user .td-username input').val().replace(/\s/g, '');
	var password = $('table#modal-tbl-insert-user .td-password input').val().replace(/\s/g, '');
	var role = $('table#modal-tbl-insert-user .td-role input').val().replace(/\s/g, '');	
	var enabled = $('table#modal-tbl-insert-user .td-enabled input').prop('checked');
	console.log('insert ' + username + password + enabled + role);
	
	if(username == '' || password == '' || (role != 'ADMIN' && role != 'SELLER')){
		alert('Input empty. Role must be ADMIN or SELLER');
		return;
	}
	
	var user = new User(username, password, enabled, role);
	
	$.ajax({
		type : "POST",
		contentType : "application/json",
		url : "users/insert",
		data : JSON.stringify(user),
		dataType : 'json',
		timeout : 100000,
		success : function(data) {
			if(data.code == 1){
				console.log('insert success!');					
			}else if(data.code == 0){
				alert('insert fail: duplicate username!');
				console.log('insert fail: duplicate username!');
			}else{
				alert('insert fail: database interupt!');
				console.log('insert fail: database interupt!');
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