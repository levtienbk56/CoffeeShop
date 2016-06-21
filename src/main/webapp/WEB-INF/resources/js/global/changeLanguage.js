$("#lang-en").click(function() {
	changeLanguage('en');
});

$("#lang-jp").click(function() {
	changeLanguage('jp');
});

function changeLanguage(lang) {
	$.ajax({
		type : "POST",
		url : "/CoffeeShop/change-locale",
		data : {
			language : lang
		},
		timeout : 10000,
		beforeSend : function(xhr) {
			xhr.setRequestHeader(header, token);
		},
		success : function(data) {
			// reload this page
			document.body.scrollTop = document.documentElement.scrollTop = 0;
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