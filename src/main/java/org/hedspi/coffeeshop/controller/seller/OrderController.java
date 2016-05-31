package org.hedspi.coffeeshop.controller.seller;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hedspi.coffeeshop.controller.MainController;
import org.hedspi.coffeeshop.dao.CoffeeDAO;
import org.hedspi.coffeeshop.dao.CondimentDAO;
import org.hedspi.coffeeshop.dao.CupDAO;
import org.hedspi.coffeeshop.dao.OrderDAO;
import org.hedspi.coffeeshop.model.Coffee;
import org.hedspi.coffeeshop.model.Condiment;
import org.hedspi.coffeeshop.model.Cup;
import org.hedspi.coffeeshop.model.Order;
import org.hedspi.coffeeshop.model.User;
import org.hedspi.coffeeshop.utils.NumberHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class OrderController {
	public static final Logger logger = LogManager.getLogger(OrderController.class);

	@Autowired
	CondimentDAO condimentdao;
	@Autowired
	CoffeeDAO coffeedao;
	@Autowired
	OrderDAO orderdao;
	@Autowired
	CupDAO cupdao;

	/**
	 * open order page
	 * 
	 * @param model
	 *            contain list of coffee & condiment
	 * @param modell
	 *            contain username
	 * @return page name
	 */
	@RequestMapping(value = { "/order" }, method = RequestMethod.GET)
	public String index(Locale locale, @ModelAttribute("model") ModelMap model, Model modell) {
		logger.entry();

		List<Coffee> listCoffee = coffeedao.selectAllActive();
		List<Condiment> listCondiment = condimentdao.selectAllActive();
		model.addAttribute("listCondiment", listCondiment);
		model.addAttribute("listCoffee", listCoffee);

		modell.addAttribute("username", MainController.getUserName());

		return "pages/seller/order";
	}

	/**
	 * check customer chosen Cups, then insert order data into DB
	 * 
	 * @see #insertOrderIntoDataBase(Order order)
	 * @param listCup
	 *            Customer chosen Cups
	 * @return summary orders
	 */
	@RequestMapping(value = { "/order" }, method = RequestMethod.POST)
	public @ResponseBody Order checkout(@RequestBody Map<String, Cup> listCup) {
		logger.entry();

		Order order = new Order();
		if (listCup != null && listCup.size() > 0) {

			double total = 0.0;
			// each cup
			for (String key : listCup.keySet()) {
				Cup cup = listCup.get(key);

				// check coffee
				if (!checkCoffee(cup.getCoffee())) {
					System.out.println("request error: coffee=" + cup.getCoffee().toString());
					continue;
				}

				// check size
				if (!checkSize(cup.getSize())) {
					System.out.println("request error: size=" + cup.getSize());
					continue;
				}

				// check condiments
				if (!checkCondiment(cup.getCondiments())) {
					System.out.println("request error: condiments=" + cup.getCondimentsID());
					continue;
				}

				// put cup into order
				order.addCup(cup);
				if (cup.getSize().equals("BIG")) {
					total += cup.getPrice() * 1.2;
				} else {
					total += cup.getPrice();
				}
			}

			order.setTotal(NumberHelper.round(total, 2));

			// time to insert into database
			insertOrderIntoDataBase(order);
		}

		return order;
	}
	
	@RequestMapping(value = { "/test" }, method = RequestMethod.POST)
	public Integer test(@RequestBody List<Condiment> condiments) {
		logger.entry();
		logger.debug("test success " + condiments.get(0).toString()); 
		return 123;
	}

	/*
	 * validate list condiment, by check in DB
	 */
	private boolean checkCondiment(List<Condiment> condiments) {
		for (Condiment condiment : condiments) {

			// check if this condimentID is exist in database
			Condiment c = condimentdao.select(condiment.getId());
			if (c.equals(null)){
				return false;
			}
			
			// update newest price
			condiment.setPrice(c.getPrice());
		}
		return true;
	}

	/*
	 * validate size
	 */
	private boolean checkSize(String cupSize) {
		return (cupSize.equals("BIG") || cupSize.equals("NORMAL")) ? true : false;
	}

	/*
	 * validate coffee, by check in DB
	 */
	private boolean checkCoffee(Coffee coffee) {
		Coffee c = coffeedao.selectCoffee(coffee.getId());
		if (c.equals(null)) {
			return false;
		}
		// update newest price
		coffee.setPrice(c.getPrice());

		return true;
	}

	/*
	 * insert after checking success
	 */
	private void insertOrderIntoDataBase(Order order) {
		String username = MainController.getUserName();
		Timestamp timestamp = new Timestamp(new Date().getTime());

		order.setUser(new User(username));
		order.setPurchaseTime(timestamp);

		int orderId = orderdao.insertWithReturnId(order);

		for (Cup cup : order.getCups()) {
			cupdao.insert(orderId, cup);
		}
	}

}
