package org.hedspi.coffeeshop.controller.admin;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hedspi.coffeeshop.common.Constant;
import org.hedspi.coffeeshop.dao.CoffeeDAO;
import org.hedspi.coffeeshop.dao.CondimentDAO;
import org.hedspi.coffeeshop.dao.CupDAO;
import org.hedspi.coffeeshop.dao.OrderDAO;
import org.hedspi.coffeeshop.model.Coffee;
import org.hedspi.coffeeshop.model.Condiment;
import org.hedspi.coffeeshop.model.Cup;
import org.hedspi.coffeeshop.model.CupWrapper;
import org.hedspi.coffeeshop.model.Order;
import org.hedspi.coffeeshop.model.OrderWrapper;
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
	OrderDAO orderDao;
	@Autowired
	CupDAO cupdao;
	@Autowired
	CoffeeDAO coffeedao;
	@Autowired
	CondimentDAO condimentdao;

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
			@RequestParam("month") String month) throws ParseException {

		logger.entry(year, month);

		// format: [{'label' : label1, 'data' : [arrdata1]}, {...}, ...]
		List<Map<String, Object>> listReturn = new ArrayList<Map<String, Object>>();
		// format: [{'mname' : name1, 'mdate' : date1, 'mcup' : cupcount1 },
		// {...}, ...]
		List<Map<String, Object>> listData = orderDao.selectTotalCoffeeCorrelation(new Double(year), new Double(month));
		List<Coffee> coffees = coffeedao.selectAll();

		// each coffee
		for (Coffee coffee : coffees) {
			// is arrdata
			// format: {'day1' : cupcount1, 'day2' : cupcount2, ... }
			Map<Double, Object> mapDay = new TreeMap<Double, Object>();

			// get data by each date
			for (Map<String, Object> map : listData) {
				if (map.get("mname").equals(coffee.getName())) {
					mapDay.put((Double) map.get("mdate"), map.get("mcup"));
				}
			}

			// cause there is any day in month EMPTY data
			// need to add ['day': 0]
			SimpleDateFormat formatter = new SimpleDateFormat(Constant.DATE_SIMPLE_FORMAT);
			double mday = 0;
			while (true) {
				mday++;
				String dateInString = year + "-" + month + "-" + mday;
				java.util.Date date = (java.util.Date) formatter.parse(dateInString);
				// still in current month
				if (date.getMonth() + 1 != Integer.parseInt(month)) {
					break;
				}

				// add ['day': 0] into MAP
				if (!mapDay.containsKey(mday))
					mapDay.put((double) mday, 0);
			}

			// format: {'label' : label1, 'data' : [arrdata1]}
			Map<String, Object> mapReturn = new HashMap<String, Object>();
			mapReturn.put("label", coffee.getName());
			mapReturn.put("data", mapDay);

			// add this map into list coffee
			listReturn.add(mapReturn);

		}

		for (Map<String, Object> map : listReturn) {
		}
		return logger.exit(listReturn);
	}

	/**
	 * get data from DB, push out BarChart
	 * 
	 * @param year
	 *            order's year
	 * @param month
	 *            order's month
	 * @return List of records
	 * @throws ParseException
	 */
	@RequestMapping(value = "/analysis/bar-chart", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> analyzeBarChartData(@RequestParam("year") String year,
			@RequestParam("month") String month) throws ParseException {
		logger.entry(year, month);

		List<Map<String, Object>> list = orderDao.selectTotalDateCorrelate(Double.parseDouble(year),
				Double.parseDouble(month));

		// convert date format[yyyy-mm-dd] to long NUMBER
		for (Map<String, Object> map : list) {
			Date date = (Date) map.get("label");
			map.put("label", date.getTime());
		}

		// insert empty date
		SimpleDateFormat formatter = new SimpleDateFormat(Constant.DATE_SIMPLE_FORMAT);
		int mdate = 0;

		while (true) {
			++mdate;
			String dateInString = year + "-" + month + "-" + mdate;
			try {
				java.util.Date date = (java.util.Date) formatter.parse(dateInString);

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
				logger.catching(e);
				break;
			}
		}

		return logger.exit(list);
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

		List<Order> list = orderDao.selectByRange(new Timestamp(Long.parseLong(dfrom)),
				new Timestamp(Long.parseLong(dto)));
		return logger.exit(list);
	}

	/**
	 * 
	 * @param orderId
	 * @return List of map{'coffeeName':xxx, 'size':xxx, 'condiments':xxx,
	 *         'price':xxx}
	 */
	@RequestMapping(value = "/cups", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> getCupsOfOrder(@RequestParam("orderId") Integer orderId) {
		logger.entry(orderId);

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		List<Cup> cups = cupdao.selectByOrderId(orderId);
		List<Coffee> coffees = coffeedao.selectAll();
		List<Condiment> condiments = condimentdao.selectAll();

		for (Cup cup : cups) {
			Map<String, Object> map = new HashMap<String, Object>();
			// get coffeeName
			int coffeeId = cup.getCoffeeId();
			String coffeeName = "";
			for (Coffee c : coffees) {
				if (coffeeId == c.getId()) {
					coffeeName = c.getName();
					break;
				}
			}

			// get size
			String size = cup.getSize();

			// get condiments
			String condimentNames = "";
			String condimentIds = cup.getCondiments();
			condimentIds = condimentIds.replace("[", "");
			condimentIds = condimentIds.replace("]", "");
			List<String> listIds = Arrays.asList(condimentIds.split("\\s*,\\s*"));
			for (String id : listIds) {
				for (Condiment c : condiments) {
					if (id.equals("" + c.getId())) {
						condimentNames += c.getName() + ", ";
						break;
					}
				}
			}

			// get price
			double price = cup.getPrice();

			map.put("coffeeName", coffeeName);
			map.put("size", size);
			map.put("condiments", condimentNames);
			map.put("price", price);
			list.add(map);
		}

		return logger.exit(list);
	}

	/**
	 * all of years that has order in DB
	 * 
	 * @return list of years
	 */
	@RequestMapping(value = "/analysis/years", method = RequestMethod.POST)
	public @ResponseBody List<Integer> getYears() {
		logger.entry();
		List<Integer> list = orderDao.selectYears();

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
		List<Integer> list = orderDao.selectMonths(Double.parseDouble(year));

		return logger.exit(list);

	}

	@RequestMapping(value = "/rank", method = RequestMethod.GET)
	public String rank(Model model) {
		logger.entry();
		return "RankPage";
	}
}
