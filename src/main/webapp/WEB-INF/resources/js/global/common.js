var token = $("meta[name='_csrf']").attr("content");
var header = $("meta[name='_csrf_header']").attr("content");

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
	if (lang == 'jp') {
		lang = 'ja';
	}
	console.log('language: ' + lang);
	return lang;
}

// define message modul
var Message = (function() {
	var string = {
		INPUT_EMPTY : "Input Empty!",
		INPUT_INVALID_DATE : "Invalid date　time!",
		INPUT_INVALID_PRICE : "Invalid price!",
		DATA_EMPTY : "No record found!",
		PWD_TOO_SHORT : "Password length must be at least 4 character",
		PWD_NOT_MATCH : "New Password is not matching!",
		PWD_INCORECT : "Password is incorect!",
		PWD_CHANGED : "Password was changed successful!",
		NO_COFFEE : "choose a coffee first",
		CHECKOUT_CONFIRM : "Are you sure to checkout order?",
		CHECKOUT_FAIL : "Error occurred!",
		NEW_ORDER_CONFIRM : "Are you sure to create new order? Current cups'll be deleted.",
		EVENT_DELETE_CONFIRM : "Are you sure to delete event?",
		EVENT_DELETE_FAIL : "Error occurred!",
		EVENT_DELETE_SUCCESS : "Event deleted successful!",
		EVENT_UPDATE_CONFIRM : "Are you sure to update event?",
		EVENT_UPDATE_SUCCESS : "Event updated successful!",
		EVENT_UPDATE_FAIL : "Error occurred!",
		EVENT_INSERT_CONFIRM : "Are you sure to insert event?",
		EVENT_INSERT_SUCCESS : "Event added successful!",
		EVENT_INSERT_FAIL : "Error occurred!",
		USER_INSERT_CONFIRM : "Are you sure to insert new user?",
		USER_INSERT_SUCCESS : "User added successful!",
		USER_INSERT_FAIL : "Error Occurred!",
		USER_UPDATE_CONFIRM : "Are you sure to update user?",
		USER_UPDATE_SUCCESS : "User updated successful!",
		USER_UPDATE_FAIL : "Error Occurred!",
		COFFEE_UPDATE_CONFIRM : "Are you sure to update coffee?",
		COFFEE_UPDATE_SUCCESS : "Coffee updated successful!",
		COFFEE_UPDATE_FAIL : "Error Occurred!",
		COFFEE_INSERT_CONFIRM : "Are you sure to insert coffee?",
		COFFEE_INSERT_SUCCESS : "Coffee inserted successful!",
		COFFEE_INSERT_FAIL : "Error Occurred!",
		CONDIMENT_UPDATE_CONFIRM : "Are you sure to update condiment?",
		CONDIMENT_UPDATE_SUCCESS : "Condiment updated successful!",
		CONDIMENT_UPDATE_FAIL : "Error Occurred!",
		CONDIMENT_INSERT_CONFIRM : "Are you sure to insert Condiment?",
		CONDIMENT_INSERT_SUCCESS : "Condiment inserted successful!",
		CONDIMENT_INSERT_FAIL : "Error Occurred!",
	};
	if (getLanguage() == 'ja') {
		string = {
			INPUT_EMPTY : "入力に記入してください!",
			INPUT_INVALID_DATE : "日時をチェックしてください！",
			INPUT_INVALID_PRICE : "価格入力をチェックしてください！",
			DATA_EMPTY : "レコードが見つかりませんでした！",
			PWD_TOO_SHORT : "パスワードの長さが最小４字の必須",
			PWD_NOT_MATCH : "新しいパスワードが一致していません！",
			PWD_INCORECT : "パスワードが間違い！",
			PWD_CHANGED : "パスワードが変更された！",
			NO_COFFEE : "コーヒー種類をご選択ください！",
			CHECKOUT_CONFIRM : "オーダーをチェックアウトしますか?",
			NEW_ORDER_CONFIRM : "当座オーダーを削除して、新しいオーダーを作ります。よろしいですか？",
			EVENT_DELETE_CONFIRM : "イベントを削除しませんか？",
			EVENT_DELETE_FAIL : "イベントを削除するのを失敗した！",
			EVENT_DELETE_SUCCESS : "イベントが削除された！",
			EVENT_UPDATE_CONFIRM : "イベントを更新しませんか？",
			EVENT_UPDATE_SUCCESS : "イベントが変更された",
			EVENT_UPDATE_FAIL : "イベントを変更するのを失敗した",
			EVENT_INSERT_CONFIRM : "イベントを追加しませんか？",
			EVENT_INSERT_SUCCESS : "イベントが追加された",
			EVENT_INSERT_FAIL : "イベントを追加するのを失敗した!",
			USER_INSERT_CONFIRM : "ユーザーを追加しませんか？",
			USER_INSERT_SUCCESS : "新ユーザーが追加された！",
			USER_INSERT_FAIL : "新ユーザーを追加するのを失敗した！",
			USER_UPDATE_CONFIRM : "ユーザーを変更しませんか？",
			USER_UPDATE_SUCCESS : "ユーザーが変更された",
			USER_UPDATE_FAIL : "ユーザーを変更するのを失敗した！",
			COFFEE_UPDATE_CONFIRM : "コーヒーを更新しますか？",
			COFFEE_UPDATE_SUCCESS : "コーヒーが変更された！",
			COFFEE_UPDATE_FAIL : "コーヒーを変更するのを失敗した！",
			COFFEE_INSERT_CONFIRM : "コーヒーを追加しませんか？",
			COFFEE_INSERT_SUCCESS : "コーヒーが追加された！",
			COFFEE_INSERT_FAIL : "コーヒーを追加のを失敗した！",
			CONDIMENT_UPDATE_CONFIRM : "コンディメントを更新しますか？",
			CONDIMENT_UPDATE_SUCCESS : "コンディメントが変更された！",
			CONDIMENT_UPDATE_FAIL : "コンディメントを変更するのを失敗した！",
			CONDIMENT_INSERT_CONFIRM : "コンディメントを追加しませんか？",
			CONDIMENT_INSERT_SUCCESS : "コンディメントが追加された！",
			CONDIMENT_INSERT_FAIL : "コンディメントを追加のを失敗した！",

		}
	}
	return {
		getString : function() {
			return string;
		}
	}

}());
