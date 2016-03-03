package org.hedspi.coffeeshop.controller.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hedspi.coffeeshop.common.Constant;
import org.hedspi.coffeeshop.dao.CoffeeDAO;
import org.hedspi.coffeeshop.dao.CondimentDAO;
import org.hedspi.coffeeshop.model.Coffee;
import org.hedspi.coffeeshop.model.Condiment;
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
	public static final Logger logger = LogManager.getLogger(ItemManageController.class);

	@Autowired
	CoffeeDAO coffeedao;
	@Autowired
	CondimentDAO condimentdao;

	@RequestMapping(value = { "/coffees" }, method = RequestMethod.GET)
	public String manageCoffees(@ModelAttribute("model") ModelMap model) {
		logger.entry();

		List<Coffee> listCoffee = coffeedao.selectAll();
		model.addAttribute("listCoffee", listCoffee);
		return "ItemsCoffeesPage"; // definition in tilesFtl-admin.xml
	}

	@RequestMapping(value = { "/coffees/remove" }, method = RequestMethod.POST)
	public @ResponseBody Map<String, String> removeCoffee(@RequestParam("coffeeId") String id) {
		logger.entry(id);

		int code = coffeedao.delete(Integer.parseInt(id));

		Map<String, String> map = new HashMap<String, String>();
		if (code == 1) {
			map.put("result", "success");
			map.put("message", Constant.QUERY_DELETE_SUCCESS);
		} else {
			map.put("result", "fail");
			map.put("message", Constant.QUERY_FAIL);
		}
		return logger.exit(map);
	}

	@RequestMapping(value = { "/coffees/edit" }, method = RequestMethod.POST)
	public @ResponseBody Map<String, String> editCoffee(@RequestBody Coffee coffee) {
		logger.entry(coffee);

		int code = coffeedao.update(coffee);

		Map<String, String> map = new HashMap<String, String>();
		if (code == 1) {
			map.put("result", "success");
			map.put("message", Constant.QUERY_UPDATE_SUCCESS);
		} else {
			map.put("result", "fail");
			map.put("message", Constant.QUERY_FAIL);
		}
		return logger.exit(map);
	}

	@RequestMapping(value = "/coffees/insert", method = RequestMethod.POST)
	public @ResponseBody Map<String, String> insertCoffee(@RequestBody Coffee coffee) {
		logger.entry(coffee);

		int code = coffeedao.insert(coffee);
		Map<String, String> map = new HashMap<String, String>();
		if (code == 1) {
			map.put("result", "success");
			map.put("message", Constant.QUERY_INSERT_SUCCESS);
		} else {
			map.put("result", "fail");
			map.put("message", Constant.QUERY_FAIL);
		}
		return logger.exit(map);
	}

	@RequestMapping(value = { "/condiments" }, method = RequestMethod.GET)
	public String manageCondiments(@ModelAttribute("model") ModelMap model) {
		logger.entry();

		List<Condiment> listCondiment = condimentdao.selectAll();
		model.addAttribute("listCondiment", listCondiment);
		return "ItemsCondimentsPage"; // definition in tilesFtl-admin.xml
	}

	@RequestMapping(value = { "/condiments/remove" }, method = RequestMethod.POST)
	public @ResponseBody Map<String, String> removeCondiment(@RequestParam("condimentId") String id) {
		logger.entry(id);

		int code = condimentdao.delete(Integer.parseInt(id));

		Map<String, String> map = new HashMap<String, String>();
		if (code == 1) {
			map.put("result", "success");
			map.put("message", Constant.QUERY_DELETE_SUCCESS);
		} else {
			map.put("result", "fail");
			map.put("message", Constant.QUERY_FAIL);
		}
		return logger.exit(map);
	}

	@RequestMapping(value = { "/condiments/edit" }, method = RequestMethod.POST)
	public @ResponseBody Map<String, String> editCondiment(@RequestBody Condiment condiment) {
		logger.entry(condiment);

		int code = condimentdao.update(condiment);

		Map<String, String> map = new HashMap<String, String>();
		if (code == 1) {
			map.put("result", "success");
			map.put("message", Constant.QUERY_UPDATE_SUCCESS);
		} else {
			map.put("result", "fail");
			map.put("message", Constant.QUERY_FAIL);
		}
		return logger.exit(map);
	}

	@RequestMapping(value = "/condiments/insert", method = RequestMethod.POST)
	public @ResponseBody Map<String, String> insertCondiment(@RequestBody Condiment condiment) {
		logger.entry();

		int code = condimentdao.insert(condiment);
		Map<String, String> map = new HashMap<String, String>();
		if (code == 1) {
			map.put("result", "success");
			map.put("message", Constant.QUERY_INSERT_SUCCESS);
		} else {
			map.put("result", "fail");
			map.put("message", Constant.QUERY_FAIL);
		}
		return logger.exit(map);
	}
}
