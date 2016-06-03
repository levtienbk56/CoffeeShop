package org.hedspi.coffeeshop.controller.seller.order;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hedspi.coffeeshop.controller.MainController;
import org.hedspi.coffeeshop.domain.dao.CoffeeDAO;
import org.hedspi.coffeeshop.domain.dao.CondimentDAO;
import org.hedspi.coffeeshop.domain.dao.CupDAO;
import org.hedspi.coffeeshop.domain.dao.OrderDAO;
import org.hedspi.coffeeshop.domain.model.Coffee;
import org.hedspi.coffeeshop.domain.model.Condiment;
import org.hedspi.coffeeshop.domain.model.Cup;
import org.hedspi.coffeeshop.domain.model.Order;
import org.hedspi.coffeeshop.service.OrderService;
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
	private static final Logger logger = LogManager.getLogger(OrderController.class);

	@Autowired
	CondimentDAO condimentdao;
	@Autowired
	CoffeeDAO coffeedao;
	@Autowired
	OrderDAO orderdao;
	@Autowired
	CupDAO cupdao;

	@Autowired
	private OrderService orderService;

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
		return logger.exit(orderService.validAndInsert(listCup));
	}
}
