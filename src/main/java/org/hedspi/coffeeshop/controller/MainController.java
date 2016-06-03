package org.hedspi.coffeeshop.controller;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hedspi.coffeeshop.common.Constant;
import org.hedspi.coffeeshop.controller.seller.order.OrderController;
import org.hedspi.coffeeshop.domain.dao.UserDAO;
import org.hedspi.coffeeshop.domain.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.LocaleResolver;

@Controller
public class MainController {
	public static final Logger logger = LogManager.getLogger(OrderController.class);

	@Autowired
	LocaleResolver localeResolver;
	@Autowired
	UserDAO userdao;

	/**
	 * @see SpringSecurity
	 * @return page address
	 */
	@RequestMapping(value = "/")
	public String home() {
		SecurityContext sc = SecurityContextHolder.getContext();
		Authentication authentication = sc.getAuthentication();
		// RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

		String targetUrl = determineTargetUrl(authentication);
		return "redirect:" + targetUrl;
	}

	/**
	 * @see SpringSecurity
	 * @param authentication
	 * @return page name
	 */
	protected String determineTargetUrl(Authentication authentication) {
		String url = "";

		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

		String role = null;

		for (GrantedAuthority a : authorities) {
			role = a.getAuthority();
		}

		if (role.equals("ADMIN")) {
			url = "admin";
		} else if (role.equals("SELLER")) {
			url = "order";
		} else {
			url = "403";
		}
		return url;
	}

	@RequestMapping(value = { "/403" }, method = RequestMethod.GET)
	public String error403() {
		return "pages/global/error403";
	}

	@RequestMapping(value = { "/404" }, method = RequestMethod.GET)
	public String error404() {
		return "pages/global/error404";
	}

	/**
	 * login function use Spring security with notification message. also set
	 * language to default (='en')
	 * 
	 * @see SpringSecurity
	 * @param error
	 * @param msg
	 * @param model
	 *            push notification through FreemarkerObject
	 * @return page name
	 */
	@RequestMapping(value = { "/login" }, method = RequestMethod.GET)
	public String loginPage(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String msg, org.springframework.ui.Model model) {
		if (error != null) {
			model.addAttribute("error", Constant.LOGIN_FAIL);
		}
		if (msg != null) {
			model.addAttribute("msg", Constant.lOGIN_SUCCESS);
		}

		// set language to default
		Cookie cookie = null;
		if (request.getCookies() != null) {
			for (Cookie c : request.getCookies()) {
				if (c != null && c.getName().equals("language")) {
					cookie = c;
					break;
				}
			}
		}

		if (cookie == null) {
			cookie = new Cookie("language", "en");
		}
		cookie.setValue("en");
		cookie.setMaxAge(1000 * 60 * 60 * 24); // 1day
		response.addCookie(cookie);

		return "pages/global/login";
	}

	/**
	 * update password for user
	 * 
	 * @param currentPass
	 * @param newPass
	 * @return Map{"result" : "aaa", message : "bbb"}
	 */
	@RequestMapping(value = { "/change-pass" }, method = RequestMethod.POST)
	public @ResponseBody Map<String, String> changePass(@RequestParam("currentPass") String currentPass,
			@RequestParam("newPass") String newPass) {
		System.out.println("curpass: " + currentPass + ", newpass: " + newPass);

		User user = userdao.selectUser(MainController.getUserName());
		Map<String, String> map = new HashMap<String, String>();
		if (user == null) {
			map.put("result", "fail");
			map.put("message", Constant.CHANGE_PASS_FAIL);
		} else if (!user.getPassword().equals(currentPass)) {
			map.put("result", "fail");
			map.put("message", Constant.CHANGE_PASS_WRONG);
		} else {
			user.setPassword(newPass);
			userdao.update(user);
			map.put("result", "success");
			map.put("message", Constant.CHANGE_PASS_SUCCESS);
		}
		return map;
	}

	/**
	 * get current username of user
	 * 
	 * @return username as String
	 */
	public static String getUserName() {
		SecurityContext sc = SecurityContextHolder.getContext();
		Authentication authentication = sc.getAuthentication();

		return authentication.getName();
	}

	/**
	 * request to change language. Save language into cookie & localeResolver
	 * 
	 * @param request
	 * @param response
	 * @param lang
	 *            language("en" or "jp")
	 */
	@RequestMapping(value = { "/change-locale" }, method = RequestMethod.POST)
	public void changeLocale(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("language") String lang) {
		logger.entry(lang);

		Cookie cookie = null;
		if (request.getCookies() != null) {
			for (Cookie c : request.getCookies()) {
				if (c != null && c.getName().equals("language")) {
					cookie = c;
					break;
				}
			}
		}

		if (cookie == null) {
			cookie = new Cookie("language", "en");
		}
		cookie.setValue(lang);
		cookie.setMaxAge(1000 * 60 * 60 * 24); // 1day
		response.addCookie(cookie);

		// change locale
		localeResolver.setLocale(request, response, StringUtils.parseLocaleString(lang));
		return;
	}

}
