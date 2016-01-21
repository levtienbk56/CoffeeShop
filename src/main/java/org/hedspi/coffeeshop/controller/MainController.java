package org.hedspi.coffeeshop.controller;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hedspi.coffeeshop.dao.CoffeeDAO;
import org.hedspi.coffeeshop.dao.CondimentDAO;
import org.hedspi.coffeeshop.dao.CupDAO;
import org.hedspi.coffeeshop.dao.OrderDAO;
import org.hedspi.coffeeshop.model.Coffee;
import org.hedspi.coffeeshop.model.Condiment;
import org.hedspi.coffeeshop.model.Cup;
import org.hedspi.coffeeshop.model.CupWrapper;
import org.hedspi.coffeeshop.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {
	@Autowired
	CondimentDAO condimentdao;
	@Autowired
	CoffeeDAO coffeedao;
	@Autowired
	OrderDAO orderdao;
	@Autowired
	CupDAO cupdao;

	@RequestMapping(value = { "/order", "/" }, method = RequestMethod.GET)
	public String index(@ModelAttribute("model") ModelMap model) {
		List<Coffee> listCoffee = coffeedao.selectAll();
		List<Condiment> listCondiment = condimentdao.selectAll();
		model.addAttribute("listCondiment", listCondiment);
		model.addAttribute("listCoffee", listCoffee);

		// checkout
		for (Condiment con : listCondiment) {
			System.out.println(con.getName());
		}
		for (Coffee cof : listCoffee) {
			System.out.println(cof.getName());
		}
		return "OrderPage"; // definition in tilesFtl.xml
	}

	@RequestMapping(value = { "/" }, method = RequestMethod.POST)
	public @ResponseBody Order checkout(@RequestBody Map<String, CupWrapper> listCup) {
		printAjax(listCup);
		Order order = null;

		// TODO: create order, insert data (cup, order) into DB
		if (listCup != null) {
			double total = 0.0;
			order = new Order("quyvd", new Timestamp(new Date().getTime()), total);

			int orderId = orderdao.insertWithReturnId(order);
			System.out.println("=> orderId: " + orderId);

			// each cup
			for (String key : listCup.keySet()) {
				CupWrapper cupWrapper = listCup.get(key);
				int coffeeId = cupWrapper.getCoffee().getId();
				if (coffeeId > 0) {
					String size = cupWrapper.getCupSize().equals("1") ? "NORMAL" : "BIG";
					int quantity = cupWrapper.getQuantity();
					double price = cupWrapper.getPrice() / quantity;

					Set<String> setId = cupWrapper.getCondiments().keySet();
					String condimentIds = "";
					for (String cid : setId) {
						condimentIds += "," + cid;
					}
					if (condimentIds.length() > 0) {
						condimentIds = condimentIds.substring(1);
					}

					// insert cup into database
					Cup cup = new Cup(1, coffeeId, orderId, size, condimentIds, price);
					if (quantity >= 1) {
						for (int i = 0; i < quantity; i++) {
							cupdao.insert(cup);
							total += price;
						}
					}
				}

			}

			// update total price in order
			orderdao.updatePrice(orderId, total);
		}

		return order;

	}

	/*
	 * just check result
	 */
	private void printAjax(Map<String, CupWrapper> listCup) {
		System.out.println("Ajax size of Cups: " + listCup.size());
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

	@RequestMapping(value = { "/login" }, method = RequestMethod.GET)
	public String loginPage() {
		return "LoginPage"; // definition in tilesFtl.xml
	}

	@RequestMapping(value = { "/admin" }, method = RequestMethod.GET)
	public String adminPage() {
		return "AdminPage"; // definition in tilesFtl.xml
	}

	@RequestMapping(value = { "/403" }, method = RequestMethod.GET)
	public String error403() {
		return "Error403Page"; // definition in tilesFtl.xml
	}

}
