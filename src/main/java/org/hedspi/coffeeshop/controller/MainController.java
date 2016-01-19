package org.hedspi.coffeeshop.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.tiles.request.Request;
import org.hedspi.coffeeshop.dao.CoffeeDAO;
import org.hedspi.coffeeshop.dao.CondimentDAO;
import org.hedspi.coffeeshop.dao.UserDAO;
import org.hedspi.coffeeshop.model.Coffee;
import org.hedspi.coffeeshop.model.Condiment;
import org.hedspi.coffeeshop.model.Order;
import org.hedspi.coffeeshop.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {
	@Autowired
	CondimentDAO condimentdao;
	@Autowired
	CoffeeDAO coffeedao;

	@RequestMapping(value = { "/order", "/" }, method = RequestMethod.GET)
	public String index(@ModelAttribute("model") ModelMap model) {
		List<Coffee> listCoffee = coffeedao.selectAll();
		List<Condiment> listCondiment = condimentdao.selectAll();
		model.addAttribute("listCondiment", listCondiment);
		model.addAttribute("listCoffee", listCoffee);

		// checkout
		for (Condiment con : listCondiment) {
			System.out.println(con.getName());
		}
		for (Coffee cof : listCoffee) {
			System.out.println(cof.getName());
		}
		return "OrderPage"; // definition in tilesFtl.xml
	}

	@RequestMapping(value = { "/" }, method = RequestMethod.POST)
	public @ResponseBody Order checkout(@RequestBody User acc) {
		System.out.println("Ajax: " + acc.getUsername() + acc.getPassword() + acc.getRole());
		Order order = new Order(1, new Timestamp(Calendar.getInstance().getTimeInMillis()), 15.2);
		return order;

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
