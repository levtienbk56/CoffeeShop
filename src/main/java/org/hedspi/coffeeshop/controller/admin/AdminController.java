package org.hedspi.coffeeshop.controller.admin;

import org.hedspi.coffeeshop.dao.CoffeeDAO;
import org.hedspi.coffeeshop.dao.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {

	@Autowired
	UserDAO userdao;
	@Autowired
	CoffeeDAO coffeedao;

	@RequestMapping(value = { "" }, method = RequestMethod.GET)
	public String adminPage() {
		return "AdminPage"; // definition in tilesFtl-admin.xml
	}
}
