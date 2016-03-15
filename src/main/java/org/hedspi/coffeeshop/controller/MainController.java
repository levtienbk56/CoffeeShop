package org.hedspi.coffeeshop.controller;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.hedspi.coffeeshop.common.Constant;
import org.hedspi.coffeeshop.dao.UserDAO;
import org.hedspi.coffeeshop.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {

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
		return "pages/global/error403"; // definition in tilesFtl-common.xml
	}

	@RequestMapping(value = { "/404" }, method = RequestMethod.GET)
	public String error404() {
		return "pages/global/error404"; // definition in tilesFtl-common.xml
	}

	/**
	 * login function use Spring security
	 * 
	 * @see SpringSecurity
	 * @param error
	 * @param msg
	 * @param model
	 *            push notification through FreemarkerObject
	 * @return page name
	 */
	@RequestMapping(value = { "/login" }, method = RequestMethod.GET)
	public String loginPage(@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String msg, org.springframework.ui.Model model) {
		if (error != null) {
			model.addAttribute("error", Constant.LOGIN_FAIL);
		}
		if (msg != null) {
			model.addAttribute("msg", Constant.lOGIN_SUCCESS);
		}
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

}
