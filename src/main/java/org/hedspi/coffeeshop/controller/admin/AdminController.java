package org.hedspi.coffeeshop.controller.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hedspi.coffeeshop.common.Constant;
import org.hedspi.coffeeshop.dao.EventDAO;
import org.hedspi.coffeeshop.model.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {
	public static final Logger logger = LogManager.getLogger(AdminController.class);

	@Autowired
	EventDAO eventDAO;

	@RequestMapping(value = { "" }, method = RequestMethod.GET)
	public String adminPage() {
		logger.entry();
		logger.trace("Enter AdminController! return Admin home page.");
		return "AdminPage"; // definition in tilesFtl-admin.xml
	}

	@RequestMapping(value = { "/calendar/events" }, method = RequestMethod.POST)
	public @ResponseBody List<Event> showEvents() {
		logger.entry();
		List<Event> list = eventDAO.selectAll();

		for (Event e : list) {
			System.out.println(e);
		}
		return list;
	}

	@RequestMapping(value = { "/calendar/insert-event" }, method = RequestMethod.POST)
	public @ResponseBody Map<String, String> addEvent(@RequestBody Event event) {
		logger.entry(event);
		System.out.println(event);

		int code = 1;
		code = eventDAO.insert(event);

		Map<String, String> map = new HashMap<String, String>();
		if (code == 1) {
			logger.info("Query success");
			map.put("result", "success");
			map.put("message", Constant.QUERY_INSERT_SUCCESS);
		} else {
			logger.error("Query fail!");
			map.put("result", "fail");
			map.put("message", Constant.QUERY_FAIL);
		}
		return map;
	}

}
