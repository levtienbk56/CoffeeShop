package org.hedspi.coffeeshop.controller.seller;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import org.hedspi.coffeeshop.model.CupWrapper;
import org.hedspi.coffeeshop.model.Order;
import org.hedspi.coffeeshop.model.OrderWrapper;
import org.hedspi.coffeeshop.utils.NumberHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.LocaleResolver;

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
	
	@Autowired
	LocaleResolver localeResolver;
	
	@RequestMapping(value = { "/locale" }, method = RequestMethod.POST)
	public void changeLocale(HttpServletRequest request, HttpServletResponse response,@RequestParam("language") String lang) {
		logger.entry();
		
		localeResolver.setLocale(request, response, StringUtils.parseLocaleString(lang)); 
		return ; // definition in tilesFtl.xml
	}

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
		
		logger.debug("locale:" + locale.getLanguage());
		if (locale.getLanguage().equals(new Locale("jp").getLanguage())){
			return "OrderJPPage";
		}
		return "OrderPage"; // definition in tilesFtl.xml
	}


	/**
	 * check customer chosen Cups, then insert order data into DB
	 * 
	 * @see #insertOrderIntoDataBase(OrderWrapper orderWrapper)
	 * @param listCup
	 *            Customer chosen Cups
	 * @return summary orders
	 */
	@RequestMapping(value = { "/order" }, method = RequestMethod.POST)
	public @ResponseBody OrderWrapper checkout(@RequestBody Map<String, CupWrapper> listCup) {
		logger.entry();
		
		printAjax(listCup);
		OrderWrapper orderWrapper = new OrderWrapper();
		if (listCup != null && listCup.size() > 0) {

			double total = 0;
			// each cup
			for (String key : listCup.keySet()) {
				CupWrapper cup = listCup.get(key);
				CupWrapper cupInOrder = new CupWrapper();

				// check coffee
				if (!checkCoffee(cupInOrder, cup.getCoffee())) {
					System.out.println("request error: cup=" + cup.getCoffee().getId());
					break;
				}

				// check size
				if (!checkSize(cupInOrder, cup.getCupSize())) {
					System.out.println("request error: size=" + cup.getCupSize());
					break;
				}

				// check condiments
				if (!checkCondiment(cupInOrder, cup.getCondiments())) {
					System.out.println("request error: condiments=" + cup.getCondiments().keySet());
					break;
				}

				// check quantity
				if (!checkQuantity(cupInOrder, cup.getQuantity())) {
					System.out.println("request error: quantity=" + cup.getQuantity());
					break;
				}
				orderWrapper.getCups().put(key, cupInOrder);
				total += cupInOrder.getPrice();
			}

			orderWrapper.setTotal(NumberHelper.round(total, 2));
		}

		// time to insert into database
		insertOrderIntoDataBase(orderWrapper);

		return orderWrapper;
	}

	/*
	 * validate quantity value, then save into orderWrapper
	 */
	private boolean checkQuantity(CupWrapper cupInOrder, Integer quantity) {
		if (quantity != null && quantity > 0) {
			cupInOrder.setQuantity(quantity);
			cupInOrder.setPrice(cupInOrder.getPrice() * quantity);
			return true;
		}
		return false;
	}

	/*
	 * validate list condiment, by check in DB
	 */
	private boolean checkCondiment(CupWrapper cupInOrder, Map<String, Condiment> condiments) {
		double price = 0;
		for (String key : condiments.keySet()) {
			Condiment c = condimentdao.select(condiments.get(key).getId());
			if (c == null)
				return false;

			cupInOrder.getCondiments().put("" + c.getId(), c);
			price += c.getPrice();
		}
		cupInOrder.setPrice(cupInOrder.getPrice() + price);
		return true;
	}

	/*
	 * validate size
	 */
	private boolean checkSize(CupWrapper cupInOrder, String cupSize) {
		if (cupSize.equals("BIG") || cupSize.equals("NORMAL")) {
			cupInOrder.setCupSize(cupSize);
			if (cupSize.equals("BIG")) {
				cupInOrder.setPrice(cupInOrder.getPrice() * 1.2);
			}
			return true;
		}
		return false;
	}

	/*
	 * get real coffee data in database
	 */
	private boolean checkCoffee(CupWrapper cupInOrder, Coffee coffee) {
		Coffee c = coffeedao.selectCoffee(coffee.getId());
		if (c != null) {
			cupInOrder.setCoffee(c);
			cupInOrder.setPrice(cupInOrder.getPrice() + c.getPrice()); // price
			return true;
		}
		return false;
	}

	/*
	 * just check result
	 */
	private void printAjax(Map<String, CupWrapper> listCup) {
		if (listCup != null) {
			for (String key : listCup.keySet()) {
				CupWrapper cup = listCup.get(key);

				System.out.println("-key:" + key);
				System.out.println("\t coffeeID:" + cup.getCoffee().getId());
				System.out.println("\t size:" + cup.getCupSize());
				System.out.println("\t quantity:" + cup.getQuantity());
				System.out.println("\t price:" + cup.getPrice());

				Map<String, Condiment> condiments = cup.getCondiments();
				System.out.println("\t condiment size:" + condiments.size());
				for (String k : condiments.keySet()) {
					Condiment condiment = condiments.get(k);
					System.out.println("\t\t condimentID:" + condiment.getId());
					System.out.println("\t\t\t name:" + condiment.getName());
					System.out.println("\t\t\t price:" + condiment.getPrice());
				}

			}
		}
	}

	/*
	 * insert after checking success
	 */
	private void insertOrderIntoDataBase(OrderWrapper orderWrapper) {
		String username = MainController.getUserName();
		Timestamp timestamp = new Timestamp(new Date().getTime());
		double total = orderWrapper.getTotal();

		Order order = new Order(username, timestamp, total);
		int orderId = orderdao.insertWithReturnId(order);

		for (String key : orderWrapper.getCups().keySet()) {
			CupWrapper cupWrapper = orderWrapper.getCups().get(key);

			// insert cup into database
			Cup cup = new Cup(0, cupWrapper.getCoffee().getId(), orderId, cupWrapper.getCupSize(),
					cupWrapper.getCondiments().keySet().toString(),
					NumberHelper.round(cupWrapper.getPrice() / cupWrapper.getQuantity(), 2));
			if (cupWrapper.getQuantity() >= 1) {
				for (int i = 0; i < cupWrapper.getQuantity(); i++) {
					cupdao.insert(cup);
				}
			}
		}
	}

}
