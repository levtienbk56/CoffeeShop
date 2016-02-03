package org.hedspi.coffeeshop.controller.admin;

import java.util.List;
import java.util.Map;

import org.hedspi.coffeeshop.dao.CupDAO;
import org.hedspi.coffeeshop.dao.OrderDAO;
import org.hedspi.coffeeshop.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/admin/orders")
public class OrderManageController {
	@Autowired
	OrderDAO orderDao;
	@Autowired
	CupDAO cupdao;

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

	@RequestMapping(value = "/analysis/pie-chart", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> analyzePieChartData(@RequestParam("require") String require) {
		List<Map<String, Object>> list = cupdao.selectCoffeeCorrelate();
		System.out.println("required code: " + require);
		
		for(Map<String, Object> map: list){
			System.out.println(map);
		}
		return list;
	}

	@RequestMapping(value = "/rank", method = RequestMethod.GET)
	public String rank(Model model) {
		return "RankPage";
	}
}
