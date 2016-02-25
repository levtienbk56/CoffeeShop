package org.hedspi.coffeeshop.controller.admin;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.hedspi.coffeeshop.common.Constant;
import org.hedspi.coffeeshop.dao.CoffeeDAO;
import org.hedspi.coffeeshop.dao.CupDAO;
import org.hedspi.coffeeshop.dao.OrderDAO;
import org.hedspi.coffeeshop.model.Coffee;
import org.hedspi.coffeeshop.model.Order;
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
	@Autowired
	OrderDAO orderDao;
	@Autowired
	CupDAO cupdao;
	@Autowired
	CoffeeDAO coffeedao;

	@RequestMapping(value = "/order-table", method = RequestMethod.GET)
	public String viewOrderTable() {
		return "OrderTablePage";
	}

	@RequestMapping(value = "/analysis", method = RequestMethod.GET)
	public String analyzeData(Model model) {
		return "AnalysisPage";
	}
	/**
	 * get data from DB, push out PieChart
	 * @param require nothing
	 * @return list of record of Cup (select by count(number of cup))
	 */

	@RequestMapping(value = "/analysis/pie-chart", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> analyzePieChartData(@RequestParam("require") String require) {
		List<Map<String, Object>> list = cupdao.selectCoffeeCorrelate();
		System.out.println("analyzePieChartData:\t" + "required code: " + require);

		for (Map<String, Object> map : list) {
			System.out.println(map);
		}
		return list;
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

		System.out.println("analyzeStackBarChartData:\t" + "year:" + year + ", month:" + month);

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
			System.out.println(map);
		}
		return listReturn;
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
		System.out.println("analyzeBarChartData:\t" + "year:" + year + ", month:" + month);
		List<Map<String, Object>> list = orderDao.selectTotalDateCorrelate(Double.parseDouble(year),
				Double.parseDouble(month));

		// convert date format[yyyy-mm-dd] to long NUMBER
		for (Map<String, Object> map : list) {
			Date date = (Date) map.get("label");
			map.put("label", date.getTime());
			System.out.println(map);
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
				e.printStackTrace();
				break;
			}
		}

		return list;
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
		System.out.println("from: " + dfrom + ", to: " + dto);
		List<Order> list = orderDao.selectByRange(new Timestamp(Long.parseLong(dfrom)),
				new Timestamp(Long.parseLong(dto)));
		return list;
	}

	/**
	 * all of years that has order in DB
	 * 
	 * @return list of years
	 */
	@RequestMapping(value = "/analysis/years", method = RequestMethod.POST)
	public @ResponseBody List<Integer> getYears() {
		List<Integer> list = orderDao.selectYears();

		for (Integer i : list) {
			System.out.println(i);
		}
		return list;
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
