package org.hedspi.coffeeshop.controller;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

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

	@RequestMapping(value = "/")
	public String home() {
		SecurityContext sc = SecurityContextHolder.getContext();
		Authentication authentication = sc.getAuthentication();
		// RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

		String targetUrl = determineTargetUrl(authentication);
		return "redirect:" + targetUrl;
	}

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

	@RequestMapping(value = { "/login" }, method = RequestMethod.GET)
	public String loginPage(@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String msg, org.springframework.ui.Model model) {
		if (error != null) {
			model.addAttribute("error", "Invalid username and password!");
		}
		if (msg != null) {
			model.addAttribute("msg", "Logout successfully.");
		}
		return "LoginPage"; // definition in tilesFtl.xml
	}
	
	@RequestMapping(value = { "/change-pass" }, method = RequestMethod.POST)
	public @ResponseBody Map<String, String> changePass(@RequestParam("currentPass") String currentPass,
			@RequestParam("newPass") String newPass) {
		System.out.println("curpass: " + currentPass + ", newpass: " + newPass);
		
		User user = userdao.selectUser(MainController.getUserName());
		Map<String, String> map = new HashMap<String, String>();
		if(user == null){
			map.put("result", "fail");
			map.put("message", "Please sign in first!");
		}else if(!user.getPassword().equals(currentPass)){
			map.put("result", "fail");
			map.put("message", "Wrong password!");
		}else{
			user.setPassword(newPass);
			userdao.update(user);
			map.put("result", "success");
			map.put("message", "Password was changed");
		}
		return map;
	}

	@RequestMapping(value = { "/403" }, method = RequestMethod.GET)
	public String error403() {
		return "Error403Page"; // definition in tilesFtl.xml
	}

	// get current username
	public static String getUserName() {
		SecurityContext sc = SecurityContextHolder.getContext();
		Authentication authentication = sc.getAuthentication();

		return authentication.getName();
	}

}
