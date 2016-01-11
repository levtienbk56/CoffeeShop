package org.hedspi.coffeeshop.controller;

import java.util.ArrayList;
import java.util.List;

import org.hedspi.coffeeshop.dao.CoffeeDAO;
import org.hedspi.coffeeshop.model.Coffee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {
	@Autowired
	CoffeeDAO coffeeDAO;

	@RequestMapping(value = { "/index", "/" }, method = RequestMethod.GET)
	public String index() {
		return "IndexPage"; 		// definition in tilesFtl.xml
	}
	@RequestMapping(value = { "/login" }, method = RequestMethod.GET)
	public String login() {
		return "LoginPage"; 		// definition in tilesFtl.xml
	}

}
