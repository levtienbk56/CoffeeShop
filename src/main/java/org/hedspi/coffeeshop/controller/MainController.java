package org.hedspi.coffeeshop.controller;

import java.util.Collection;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {

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
	public String loginPage() {
		return "LoginPage"; // definition in tilesFtl.xml
	}
	
	@RequestMapping(value = { "/403" }, method = RequestMethod.GET)
	public String error403() {
		return "Error403Page"; // definition in tilesFtl.xml
	}

}
