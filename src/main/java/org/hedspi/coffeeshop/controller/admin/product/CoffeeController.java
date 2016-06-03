package org.hedspi.coffeeshop.controller.admin.product;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hedspi.coffeeshop.common.Constant;
import org.hedspi.coffeeshop.domain.dao.CoffeeDAO;
import org.hedspi.coffeeshop.domain.model.Coffee;
import org.hedspi.coffeeshop.service.CoffeeService;
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
@RequestMapping(value = "/admin/product/coffees")
public class CoffeeController {
	public static final Logger logger = LogManager.getLogger(CoffeeController.class);

	@Autowired
	CoffeeDAO coffeedao;
	
	@Autowired
	CoffeeService coffeeService;

	@RequestMapping(value = { "" }, method = RequestMethod.GET)
	public String manageCoffees(@ModelAttribute("model") ModelMap model) {
		logger.entry();

		List<Map<String, Object>> listCoffee = coffeeService.mapDataTable();
		model.addAttribute("listCoffee", listCoffee);
		return "pages/admin/product-manage/coffee"; 
	}

	@RequestMapping(value = { "/remove" }, method = RequestMethod.POST)
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

	@RequestMapping(value = { "/edit" }, method = RequestMethod.POST)
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

	@RequestMapping(value = "/insert", method = RequestMethod.POST)
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
}
