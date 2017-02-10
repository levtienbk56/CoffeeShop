package org.hedspi.coffeeshop.controller.admin.user;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hedspi.coffeeshop.common.Constant;
import org.hedspi.coffeeshop.controller.MainController;
import org.hedspi.coffeeshop.domain.model.User;
import org.hedspi.coffeeshop.service.UserService;
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
	UserService userService;

	/**
	 * 全てユーザーをリストする。
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "" }, method = RequestMethod.GET)
	public String manageUsers(@ModelAttribute("model") ModelMap model) {
		logger.entry();

		List<User> users = userService.selectAll();
		if (users == null) {
			users = new ArrayList<>();
		}
		model.addAttribute("listUser", users);
		return "pages/admin/user-manage/user";
	}

	@RequestMapping(value = "/remove", method = RequestMethod.POST)
	public @ResponseBody Map<String, String> removeUser(@RequestParam("username") String username) {
		logger.entry(username);

		int code = userService.deleteUser(username.toString().trim());

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
		Map<String, String> map = new HashMap<String, String>();

		int code = userService.updateUser(user);
		if (code == 1) {
			map.put("result", "success");
			map.put("message", Constant.QUERY_UPDATE_SUCCESS);
		} else if (code == 999) {
			map.put("result", "nopermission");
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

		int code = userService.insertUser(user);
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
