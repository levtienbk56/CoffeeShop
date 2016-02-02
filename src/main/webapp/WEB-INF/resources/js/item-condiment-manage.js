function Condiment(id, name, price){
	this.id = id;
	this.name = name;
	this.price = price;
}

// get username, request delete to server
function removeCondiment(arg){
	var id = arg.attr('value').replace(/\s/g, '');
	var r = confirm("Are you sure to remove : " + id);
	if(r ==  true){
		$.ajax({
			type : "POST",
			url : "condiments/remove",
			data : {condimentId : id},
			timeout : 100000,
			success : function(data) {
				if(data.code == 1){
					console.log("removed condiment:" + data.code);					
				}else if(data.code == 0){
					alert('condiment does not exist!');
				}else{
					alert('database interupt!');
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
function editCondiment(element){
	var id = element.parent('td').parent('tr').children('.td-id').text();
	var name = element.parent('td').parent('tr').children('.td-name').text();
	var price = element.parent('td').parent('tr').children('.td-price').text();
	console.log('edit ' + id + name + price);
	
	$('table#modal-tbl-edit').attr('name', id);
	$('table#modal-tbl-edit .td-id').text(id);
	$('table#modal-tbl-edit .td-name input').val(name);
	$('table#modal-tbl-edit .td-price input').val(price);
}

// get update info, request to server
function updateCondiment(){
	// remove space: .replace(/\s/g, '')
	var id = $('table#modal-tbl-edit .td-id').text().replace(/\s/g, '');
	var name = $('table#modal-tbl-edit .td-name input').val().replace(/\s/g, '');
	var price = $('table#modal-tbl-edit .td-price input').val().replace(/\s/g, '');
	
	if(id == '' || name == '' || price == ''){
		alert('Input empty!');
		return;
	}
	console.log('update ' + id + name + price);
	
	var condiment = new Condiment(id, name, price);
	
	$.ajax({
		type : "POST",
		contentType : "application/json",
		url : "condiments/edit",
		data : JSON.stringify(condiment),
		dataType : 'json',
		timeout : 100000,
		success : function(data) {
			if(data.code  == 1){
				console.log('update success!');					
			}else if(data.code == 0){
				console.log('update fail: condiment exist');
			}else{
				console.log('database interupt!');
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
function insertCondiment(){
	// remove space: .replace(/\s/g, '')
	var name = $('table#modal-tbl-insert .td-name input').val().replace(/\s/g, '');
	var price = $('table#modal-tbl-insert .td-price input').val().replace(/\s/g, '');
	console.log('insert ' + name + price);
	
	if(name == '' || price == ''){
		alert('Input empty!');
		return;
	}
	
	var condiment = new Condiment('0',name,price);
	
	$.ajax({
		type : "POST",
		contentType : "application/json",
		url : "condiments/insert",
		data : JSON.stringify(condiment),
		dataType : 'json',
		timeout : 100000,
		success : function(data) {
			if(data.code == 1){
				console.log('insert success!');					
			}else if(data.code == 0){
				alert('insert fail: duplicate!');
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