package org.hedspi.coffeeshop.controller.admin.order;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hedspi.coffeeshop.domain.model.Order;
import org.hedspi.coffeeshop.service.CupService;
import org.hedspi.coffeeshop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/admin/orders")
public class OrderManageController {
	public static final Logger logger = LogManager.getLogger(OrderManageController.class);

	@Autowired
	private CupService cupService;
	@Autowired
	private OrderService orderService;

	@RequestMapping(value = "/order-table", method = RequestMethod.GET)
	public String viewOrderTable() {
		logger.entry();
		return "pages/admin/order-manage/table";
	}

	@RequestMapping(value = "/analysis", method = RequestMethod.GET)
	public String analyzeData(Model model) {
		logger.entry();
		return "pages/admin/order-manage/analysis";
	}

	/**
	 * get data from DB, push out StackBarChart
	 * 
	 * @param year
	 *            order's year
	 * @param month
	 *            order's month
	 * @return list of record
	 * @throws ParseException
	 */
	@RequestMapping(value = "/analysis/stack-bar-chart", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> analyzeStackBarChartData(@RequestParam("year") String year,
			@RequestParam("month") String month) {
		logger.entry(year, month);

		return logger.exit(orderService.initStackBarChartData(year, month));
	}

	/**
	 * get data from DB, push out BarChart
	 * 
	 * @param year
	 *            order's year
	 * @param month
	 *            order's month
	 * @return List of records
	 */
	@RequestMapping(value = "/analysis/bar-chart", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> analyzeBarChartData(@RequestParam("year") String year,
			@RequestParam("month") String month) {
		logger.entry(year, month);

		return logger.exit(orderService.initBarChartData(year, month));
	}

	/**
	 * select List of ORDER by Range of DATE
	 * 
	 * @param dfrom
	 *            query order from day
	 * @param dto
	 *            query order to day
	 * @return list of records
	 */
	@RequestMapping(value = "/analysis/order-by-range", method = RequestMethod.POST)
	public @ResponseBody List<Order> selectByRange(@RequestParam("dfrom") String dfrom,
			@RequestParam("dto") String dto) {
		logger.entry(dfrom, dto);

		List<Order> list = orderService.selectByRange(new Timestamp(Long.parseLong(dfrom)),
				new Timestamp(Long.parseLong(dto)));
		return logger.exit(list);
	}

	/**
	 * 
	 * @param orderId
	 * @return List of map{'coffeeName':xxx, 'size':xxx, 'condimentsName':xxx,
	 *         'price':xxx}
	 */
	@RequestMapping(value = "/cups", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> getCupsOfOrder(@RequestParam("orderId") Integer orderId) {
		logger.entry(orderId);

		return logger.exit(this.cupService.selectByOrderId(orderId));
	}

	/**
	 * all of years that has order in DB
	 * 
	 * @return list of years
	 */
	@RequestMapping(value = "/analysis/years", method = RequestMethod.POST)
	public @ResponseBody List<Integer> getYears() {
		logger.entry();
		List<Integer> list = orderService.selectYears();

		return logger.exit(list);
	}

	/**
	 * all of month that has order in DB
	 * 
	 * @param year
	 *            order's year
	 * @return list of orders
	 */
	@RequestMapping(value = "/analysis/months", method = RequestMethod.POST)
	public @ResponseBody List<Integer> getMonths(@RequestParam("year") String year) {
		logger.entry(year);
		List<Integer> list = orderService.selectMonths(Double.parseDouble(year));

		return logger.exit(list);

	}
}
