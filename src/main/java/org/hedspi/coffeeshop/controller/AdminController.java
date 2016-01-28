package org.hedspi.coffeeshop.controller;

import java.util.List;

import org.hedspi.coffeeshop.dao.UserDAO;
import org.hedspi.coffeeshop.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {

	@Autowired
	UserDAO userdao;

	@RequestMapping(value = { "" }, method = RequestMethod.GET)
	public String adminPage() {
		return "AdminPage"; // definition in tilesFtl-admin.xml
	}

	@RequestMapping(value = { "/items" }, method = RequestMethod.GET)
	public String manageItems() {
		return "ItemsPage"; // definition in tilesFtl-admin.xml
	}

	@RequestMapping(value = { "/users" }, method = RequestMethod.GET)
	public String manageUsers(@ModelAttribute("model") ModelMap model) {
		List<User> listUser = userdao.selectAll();
		model.addAttribute("listUser", listUser);
		return "UsersPage"; // definition in tilesFtl-admin.xml
	}

	@RequestMapping(value = "/remove-user", method = RequestMethod.POST)
	public @ResponseBody User removeUser(@RequestBody String username) {
		System.out.println("request remove user=" + username);
		userdao.delete(username);
		User user = new User(username,"",false,"");
		return user;
	}

	@RequestMapping(value = "/edit-user", method = RequestMethod.POST)
	public @ResponseBody User editUser(@RequestBody String username) {
		System.out.println(username);
		User user = new User();
		return user;
	}
}
