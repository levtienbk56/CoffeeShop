package org.hedspi.coffeeshop.controller;

import java.util.ArrayList;
import java.util.List;

import org.hedspi.coffeeshop.dao.CoffeeDAO;
import org.hedspi.coffeeshop.model.Coffee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {
	@Autowired
	CoffeeDAO coffeeDAO;

	@RequestMapping(value = { "/index", "/" })
	public String index() {
		return "index";
	}

	@RequestMapping(value = { "/coffees" })
	public ModelAndView getCoffeeAll() {
		List<Coffee> coffees = coffeeDAO.selectAll();
		String html = "";
		for (Coffee c : coffees) {
			html += "<p>" + c.toString() + "</p><br>";
		}
		return new ModelAndView("coffees", "html", html);
	}

}
