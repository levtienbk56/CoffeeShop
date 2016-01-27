function removeUser(username){
	var r = confirm("Are you sure to remove user: " + username);
	if(r ==  true){
		$.ajax({
			type : "POST",
			contentType : "application/json",
			url : "remove-user",
			data : JSON.stringify(username),
			dataType : 'json',
			timeout : 100000,
			success : function(data) {
				alert("Order success!");
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

function editUser(username){
	
}