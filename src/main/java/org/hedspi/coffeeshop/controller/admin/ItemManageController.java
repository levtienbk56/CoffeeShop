package org.hedspi.coffeeshop.controller.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hedspi.coffeeshop.dao.CoffeeDAO;
import org.hedspi.coffeeshop.dao.CondimentDAO;
import org.hedspi.coffeeshop.dao.UserDAO;
import org.hedspi.coffeeshop.model.Coffee;
import org.hedspi.coffeeshop.model.Condiment;
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

@Controller
@RequestMapping(value = "/admin/items")
public class ItemManageController {

	@Autowired
	CoffeeDAO coffeedao;
	@Autowired
	CondimentDAO condimentdao;

	@RequestMapping(value = { "/coffees" }, method = RequestMethod.GET)
	public String manageCoffees(@ModelAttribute("model") ModelMap model) {
		List<Coffee> listCoffee = coffeedao.selectAll();
		model.addAttribute("listCoffee", listCoffee);
		return "ItemsCoffeesPage"; // definition in tilesFtl-admin.xml
	}
	
	@RequestMapping(value = { "/condiments" }, method = RequestMethod.GET)
	public String manageCondiments(@ModelAttribute("model") ModelMap model) {
		List<Condiment> listCondiment = condimentdao.selectAll();
		model.addAttribute("listCondiment", listCondiment);
		return "ItemsCondimentsPage"; // definition in tilesFtl-admin.xml
	}
}
