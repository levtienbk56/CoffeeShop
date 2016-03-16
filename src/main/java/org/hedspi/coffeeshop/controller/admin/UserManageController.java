package org.hedspi.coffeeshop.controller.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hedspi.coffeeshop.common.Constant;
import org.hedspi.coffeeshop.dao.CoffeeDAO;
import org.hedspi.coffeeshop.dao.UserDAO;
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
@RequestMapping(value = "/admin/users")
public class UserManageController {

	public static final Logger logger = LogManager.getLogger(UserManageController.class);
	@Autowired
	UserDAO userdao;
	@Autowired
	CoffeeDAO coffeedao;

	@RequestMapping(value = { "" }, method = RequestMethod.GET)
	public String manageUsers(@ModelAttribute("model") ModelMap model) {
		logger.entry();

		List<User> listUser = userdao.selectAll();
		model.addAttribute("listUser", listUser);
		return "pages/admin/user-manage/user";
	}

	@RequestMapping(value = "/remove", method = RequestMethod.POST)
	public @ResponseBody Map<String, String> removeUser(@RequestParam("username") String username) {
		logger.entry(username);

		int code = userdao.delete(username.toString().trim());

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

	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public @ResponseBody Map<String, String> editUser(@RequestBody User user) {
		logger.entry(user);

		int code = userdao.update(user);
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

	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public @ResponseBody Map<String, String> insertUser(@RequestBody User user) {
		logger.entry(user);

		int code = userdao.insert(user);
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
}
