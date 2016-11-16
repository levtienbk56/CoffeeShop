package org.hedspi.coffeeshop.controller.admin.product;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hedspi.coffeeshop.common.Constant;
import org.hedspi.coffeeshop.domain.model.Condiment;
import org.hedspi.coffeeshop.service.CondimentService;
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
@RequestMapping(value = "/admin/product/condiments")
public class CondimentController {
	public static final Logger logger = LogManager.getLogger(CondimentController.class);
	
	@Autowired
	private CondimentService condimentService;

	@RequestMapping(value = { "" }, method = RequestMethod.GET)
	public String manageCondiments(@ModelAttribute("model") ModelMap model) {
		logger.entry();

		List<Map<String, Object>> listCondiment = condimentService.mapDataTable();
		model.addAttribute("listCondiment", listCondiment);
		return "pages/admin/product-manage/condiment"; 
	}

	@RequestMapping(value = { "/remove" }, method = RequestMethod.POST)
	public @ResponseBody Map<String, String> removeCondiment(@RequestParam("condimentId") String id) {
		logger.entry(id);

		int code = condimentService.delete(Integer.parseInt(id));

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
	public @ResponseBody Map<String, String> editCondiment(@RequestBody Condiment condiment) {
		logger.entry(condiment);

		int code = condimentService.update(condiment);

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
	public @ResponseBody Map<String, String> insertCondiment(@RequestBody Condiment condiment) {
		logger.entry();

		int code = condimentService.insert(condiment);
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
