$(document).ready(
		function() {
			$(".dropdown").hover(
					function() {
						$('.dropdown-menu', this).not('.in .dropdown-menu')
								.stop(true, true).slideDown("400");
						$(this).toggleClass('open');
					},
					function() {
						$('.dropdown-menu', this).not('.in .dropdown-menu')
								.stop(true, true).slideUp("400");
						$(this).toggleClass('open');
					});
		});

function setCookie(cname, cvalue, exdays) {
	var d = new Date();
	d.setTime(d.getTime() + (exdays * 24 * 60 * 60 * 1000));
	var expires = "expires=" + d.toUTCString();
	document.cookie = cname + "=" + cvalue + "; " + expires;
}

function getCookie(cname) {
	var name = cname + "=";
	var ca = document.cookie.split(';');
	for (var i = 0; i < ca.length; i++) {
		var c = ca[i];
		while (c.charAt(0) == ' ')
			c = c.substring(1);
		if (c.indexOf(name) == 0)
			return c.substring(name.length, c.length);
	}
	return "";
}

function deleteCookie(name) {
	createCookie(name, "", -1);
}

function getLanguage() {
	var lang = getCookie('language');
	if (lang == null) {
		setCookie('language', 'en', 1);
		lang = 'en';
	}
	if(lang == 'jp'){
		lang = 'ja';
	}
	return lang;
}