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

@Controller
@RequestMapping(value = "/admin")
public class AdminController {

	@Autowired
	EventDAO eventDAO;

	@RequestMapping(value = { "" }, method = RequestMethod.GET)
	public String adminPage() {
		return "AdminPage"; // definition in tilesFtl-admin.xml
	}

	@RequestMapping(value = { "/calendar/events" }, method = RequestMethod.POST)
	public @ResponseBody List<Event> showEvents() {
		List<Event> list = eventDAO.selectAll();

		for (Event e : list) {
			System.out.println(e);
		}
		return list;
	}

	@RequestMapping(value = { "/calendar/insert-event" }, method = RequestMethod.POST)
	public @ResponseBody Map<String, String> addEvent(@RequestBody Event event) {
		System.out.println(event);

		int code = 1;
		code = eventDAO.insert(event);

		Map<String, String> map = new HashMap<String, String>();
		if (code == 1) {
			map.put("result", "success");
			map.put("message", Constant.QUERY_INSERT_SUCCESS);
		} else {
			map.put("result", "fail");
			map.put("message", Constant.QUERY_FAIL);
		}
		return map;
	}

}
