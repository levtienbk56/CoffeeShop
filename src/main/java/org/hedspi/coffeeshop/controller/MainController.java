package org.hedspi.coffeeshop.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.tiles.request.Request;
import org.hedspi.coffeeshop.dao.CoffeeDAO;
import org.hedspi.coffeeshop.dao.UserDAO;
import org.hedspi.coffeeshop.model.Coffee;
import org.hedspi.coffeeshop.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {

	@RequestMapping(value = { "/index", "/" }, method = RequestMethod.GET)
	public String index() {
		return "IndexPage"; // definition in tilesFtl.xml
	}

	@RequestMapping(value = { "/login" }, method = RequestMethod.GET)
	public String loginPage() {
		return "LoginPage"; // definition in tilesFtl.xml
	}

	@RequestMapping(value = { "/admin" }, method = RequestMethod.GET)
	public String adminPage() {
		return "AdminPage"; // definition in tilesFtl.xml
	}

	@RequestMapping(value = { "/403" }, method = RequestMethod.GET)
	public String error403() {
		return "Error403Page"; // definition in tilesFtl.xml
	}

}
