package org.hedspi.coffeeshop.controller.admin;

import java.util.List;

import org.hedspi.coffeeshop.dao.OrderDAO;
import org.hedspi.coffeeshop.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/admin/orders")
public class OrderManageController {
	@Autowired
	OrderDAO orderDao;

	@RequestMapping(value = "/order-table", method = RequestMethod.GET)
	public String viewOrderTable(@ModelAttribute("model") ModelMap model) {
		List<Order> listOrder = orderDao.selectAll();
		
		model.addAttribute("listOrder", listOrder);
		return "OrderTablePage";
	}
	
	@RequestMapping(value = "/analysis", method = RequestMethod.GET)
	public String analyzeData(Model model) {
		return "AnalysisPage";
	}
	
	@RequestMapping(value = "/rank", method = RequestMethod.GET)
	public String rank(Model model) {
		return "RankPage";
	}
}
