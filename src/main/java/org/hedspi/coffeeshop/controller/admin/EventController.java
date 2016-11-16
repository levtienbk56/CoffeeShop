package org.hedspi.coffeeshop.controller.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hedspi.coffeeshop.common.Constant;
import org.hedspi.coffeeshop.domain.model.Event;
import org.hedspi.coffeeshop.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/admin")
public class EventController {
	public static final Logger logger = LogManager.getLogger(EventController.class);

	@Autowired
	EventService eventService;

	@RequestMapping(value = { "" }, method = RequestMethod.GET)
	public String adminPage() {
		logger.entry();
		logger.trace("TracingEnter AdminController! return Admin home page.");
		logger.debug("debugging....");
		return "pages/admin/home/event-calendar";
	}

	@RequestMapping(value = { "/calendar/events" }, method = RequestMethod.POST)
	public @ResponseBody List<Event> showEvents() {
		logger.entry();

		List<Event> list = eventService.selectAll();
		return logger.exit(list);
	}

	@RequestMapping(value = { "/calendar/insert-event" }, method = RequestMethod.POST)
	public @ResponseBody Map<String, String> addEvent(@RequestBody Event event) {
		logger.entry(event);

		int code = eventService.insert(event);

		Map<String, String> map = new HashMap<String, String>();
		if (code == 1) {
			map.put("result", "success");
			map.put("message", Constant.QUERY_INSERT_SUCCESS);
		} else {
			logger.error("Query fail!");
			map.put("result", "fail");
			map.put("message", Constant.QUERY_FAIL);
		}
		return logger.exit(map);
	}

	@RequestMapping(value = { "/calendar/update-event" }, method = RequestMethod.POST)
	public @ResponseBody Map<String, String> updateEvent(@RequestBody Event event) {
		logger.entry(event);

		int code = eventService.update(event);

		Map<String, String> map = new HashMap<String, String>();
		if (code == 1) {
			map.put("result", "success");
			map.put("message", Constant.QUERY_UPDATE_SUCCESS);
		} else {
			logger.error("Query fail!");
			map.put("result", "fail");
			map.put("message", Constant.QUERY_FAIL);
		}
		return logger.exit(map);
	}

	@RequestMapping(value = { "/calendar/remove-event" }, method = RequestMethod.POST)
	public @ResponseBody Map<String, String> removeEvent(@RequestParam("eventID") Integer id) {
		logger.entry(id);

		int code = eventService.delete(id);

		Map<String, String> map = new HashMap<String, String>();
		if (code == 1) {
			map.put("result", "success");
			map.put("message", Constant.QUERY_DELETE_SUCCESS);
		} else {
			logger.error("Query fail!");
			map.put("result", "fail");
			map.put("message", Constant.QUERY_FAIL);
		}
		return logger.exit(map);
	}

}
