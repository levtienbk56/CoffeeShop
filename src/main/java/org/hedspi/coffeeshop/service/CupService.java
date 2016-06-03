package org.hedspi.coffeeshop.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hedspi.coffeeshop.domain.dao.CoffeeDAO;
import org.hedspi.coffeeshop.domain.dao.CondimentDAO;
import org.hedspi.coffeeshop.domain.dao.CupDAO;
import org.hedspi.coffeeshop.domain.dao.OrderDAO;
import org.hedspi.coffeeshop.domain.model.Coffee;
import org.hedspi.coffeeshop.domain.model.Condiment;
import org.hedspi.coffeeshop.domain.model.Cup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CupService {
	@Autowired
	OrderDAO orderDao;
	@Autowired
	CupDAO cupdao;
	@Autowired
	CoffeeDAO coffeedao;
	@Autowired
	CondimentDAO condimentdao;
	
	public List<Map<String, Object>> mapDataTable(int orderId){
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		List<Cup> cups = cupdao.selectByOrderId(orderId);
		List<Coffee> coffees = coffeedao.selectAll();
		List<Condiment> condiments = condimentdao.selectAll();

		for (Cup cup : cups) {
			Map<String, Object> map = new HashMap<String, Object>();
			// replace with completed coffee object
			for (Coffee coffee : coffees) {
				if (cup.getCoffee().getId() == coffee.getId()) {
					cup.setCoffee(coffee);
					break;
				}
			}

			// replace with completed condiment object
			for (Condiment c1 : cup.getCondiments()) {
				for (Condiment c : condiments) {
					if (c1.getId() == c.getId()) {
						Collections.replaceAll(cup.getCondiments(), c1, c);
						break;
					}
				}
			}

			map.put("coffeeName", cup.getCoffee().getName());
			map.put("size", cup.getSize());
			map.put("condimentsName", cup.getCondimentsName());
			map.put("price", cup.getPrice());
			list.add(map);
		}
		return list;
	}

}
