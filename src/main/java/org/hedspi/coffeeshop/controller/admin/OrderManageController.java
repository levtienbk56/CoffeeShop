package org.hedspi.coffeeshop.controller.admin;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
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

		for (Map<String, Object> map : list) {
			System.out.println(map);
		}
		return list;
	}

	@RequestMapping(value = "/analysis/bar-chart", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> analyzeBarChartData(@RequestParam("year") String year,
			@RequestParam("month") String month) throws ParseException {
		System.out.println("year:" + year + ", month:" + month);
		List<Map<String, Object>> list = orderDao.selectTotalDateCorrelate(Double.parseDouble(year),
				Double.parseDouble(month));

		// convert date format[yyyy-mm-dd] to long NUMBER
		for (Map<String, Object> map : list) {
			Date date = (Date) map.get("label");
			map.put("label", date.getTime());
			System.out.println(map);
		}

		// insert empty date
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		int mdate = 1;

		while (true) {
			mdate++;
			String dateInString = year + "-" + month + "-" + mdate;
			try {
				java.util.Date date = (java.util.Date) formatter.parse(dateInString);
				System.out.println(date);
				System.out.println(formatter.format(date));

				// still in current month
				if (date.getMonth() + 1 != Integer.parseInt(month)) {
					break;
				}
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("label", date.getTime());
				map.put("data", 0);

				// check exist date in map
				boolean flag = false;
				for (Map<String, Object> m : list) {
					if (date.getTime() == (Long) m.get("label")) {
						flag = true;
						break;
					}
				}
				if (!flag) {
					list.add(map);
				}
			} catch (ParseException e) {
				e.printStackTrace();
				break;
			}
		}

		return list;
	}

	@RequestMapping(value = "/analysis/years", method = RequestMethod.POST)
	public @ResponseBody List<Integer> getYears() {
		List<Integer> list = orderDao.selectYears();

		for (Integer i : list) {
			System.out.println(i);
		}
		return list;
	}

	@RequestMapping(value = "/analysis/months", method = RequestMethod.POST)
	public @ResponseBody List<Integer> getMonths(@RequestParam("year") String year) {
		List<Integer> list = orderDao.selectMonths(Double.parseDouble(year));

		for (Integer i : list) {
			System.out.println(i);
		}
		return list;

	}

	@RequestMapping(value = "/rank", method = RequestMethod.GET)
	public String rank(Model model) {
		return "RankPage";
	}
}
