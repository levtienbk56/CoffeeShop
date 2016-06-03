package org.hedspi.coffeeshop.service;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hedspi.coffeeshop.common.Constant;
import org.hedspi.coffeeshop.controller.MainController;
import org.hedspi.coffeeshop.domain.dao.CoffeeDAO;
import org.hedspi.coffeeshop.domain.dao.CupDAO;
import org.hedspi.coffeeshop.domain.dao.OrderDAO;
import org.hedspi.coffeeshop.domain.model.Coffee;
import org.hedspi.coffeeshop.domain.model.Condiment;
import org.hedspi.coffeeshop.domain.model.Cup;
import org.hedspi.coffeeshop.domain.model.Order;
import org.hedspi.coffeeshop.domain.model.User;
import org.hedspi.coffeeshop.utils.NumberHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class OrderService {
	private static final Logger logger = LogManager.getLogger(OrderService.class);

	@Autowired
	private OrderDAO orderDAO;
	@Autowired
	private CupDAO cupDAO;
	@Autowired
	private CoffeeDAO coffeeDAO;

	@Autowired
	private CoffeeService coffeeService;
	@Autowired
	private CondimentService condimentService;

	public Order validAndInsert(@RequestBody Map<String, Cup> listCup) {
		Order order = new Order();
		if (listCup != null && listCup.size() > 0) {

			double total = 0.0;
			// each cup
			for (String key : listCup.keySet()) {
				Cup cup = listCup.get(key);

				// check coffee
				if (!coffeeService.isAvailable(cup.getCoffee())) {
					logger.error("order request fail", cup.getCoffee());
					continue; // next cup
				}

				// check size
				if (!cup.getSize().equals("BIG") && !cup.getSize().equals("NORMAL")) {
					logger.error("order request fail", cup.getSize());
					continue;
				}

				// check condiments
				for (Condiment cd : cup.getCondiments()) {
					if (!condimentService.isAvailable(cd)) {
						logger.error("order request fail", cd);
						continue;
					}
				}

				// put cup into order
				order.addCup(cup);
				total += cup.getPrice();
			}

			order.setTotal(NumberHelper.round(total, 2));

			// time to insert into database
			String username = MainController.getUserName();
			Timestamp timestamp = new Timestamp(new Date().getTime());

			order.setUser(new User(username));
			order.setPurchaseTime(timestamp);

			int orderId = orderDAO.insertWithReturnId(order);

			for (Cup cup : order.getCups()) {
				cupDAO.insert(orderId, cup);
			}
		}
		return order;
	}

	public List<Map<String, Object>> initBarChartData(String year, String month) {
		List<Map<String, Object>> list = orderDAO.selectTotalDateCorrelate(Double.parseDouble(year),
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
				Calendar ca = Calendar.getInstance();
				ca.setTime(date);

				// still in current month
				if (ca.get(Calendar.MONTH) + 1 != Integer.parseInt(month)) {
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
		return list;
	}

	/**
	 * Stack Bar Chart counts number cup of each CoffeeType, devide follow each
	 * Day.
	 * 
	 * @param year
	 * @param month
	 * @return is a map data: [{CoffeeType_1 : [{day_1 : xxx},{...},...{day_31 :
	 *         xxx}]},{CoffeeType_2 : [{day_1 : xxx},{...},...{day_31 :
	 *         xxx}]},...]
	 */
	public List<Map<String, Object>> initStackBarChartData(String year, String month) {
		/* format: [{'label' : xxx, 'data' : [xxx]}, {...}, ...] */
		List<Map<String, Object>> listReturn = new ArrayList<Map<String, Object>>();

		/*
		 * format: [{'mname' : xxx, 'mdate' : xxx, 'mcup' : xxx }, {...}, ...]
		 */
		List<Map<String, Object>> listData = orderDAO.selectTotalCoffeeCorrelation(new Double(year), new Double(month));

		List<Coffee> coffees = coffeeDAO.selectAll();

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
				java.util.Date date;
				try {
					date = (java.util.Date) formatter.parse(dateInString);
				} catch (ParseException e) {
					logger.error("ParseException", dateInString);
					e.printStackTrace();
					date = new Date();
				}

				// any method of Date is deprecated. Use Calendar instead
				Calendar ca = Calendar.getInstance();
				ca.setTime(date);
				// still in current month
				if (ca.get(Calendar.MONTH) + 1 != Integer.parseInt(month)) {
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
		return listReturn;
	}
}
