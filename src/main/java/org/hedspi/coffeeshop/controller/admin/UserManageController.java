package org.hedspi.coffeeshop.controller.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

	@Autowired
	UserDAO userdao;
	@Autowired
	CoffeeDAO coffeedao;

	@RequestMapping(value = { "" }, method = RequestMethod.GET)
	public String manageUsers(@ModelAttribute("model") ModelMap model) {
		List<User> listUser = userdao.selectAll();
		model.addAttribute("listUser", listUser);
		return "UsersPage"; // definition in tilesFtl-admin.xml
	}

	@RequestMapping(value = "/remove", method = RequestMethod.POST)
	public @ResponseBody Map<String, String> removeUser(@RequestParam("username") String username) {
		System.out.println("request remove user=" + username);
		int code = userdao.delete(username.toString().trim());

		Map<String, String> map = new HashMap<String, String>();
		if (code == 1) {
			map.put("result", "success");
			map.put("message", Constant.QUERY_DELETE_SUCCESS);
		} else {
			map.put("result", "fail");
			map.put("message", Constant.QUERY_FAIL);
		}
		return map;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public @ResponseBody Map<String, String> editUser(@RequestBody User user) {
		System.out.println(user.toString());

		int code = userdao.update(user);
		Map<String, String> map = new HashMap<String, String>();
		if (code == 1) {
			map.put("result", "success");
			map.put("message", Constant.QUERY_UPDATE_SUCCESS);
		} else {
			map.put("result", "fail");
			map.put("message", Constant.QUERY_FAIL);
		}
		return map;
	}

	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public @ResponseBody Map<String, String> insertUser(@RequestBody User user) {
		System.out.println(user.toString());

		int code = userdao.insert(user);
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
