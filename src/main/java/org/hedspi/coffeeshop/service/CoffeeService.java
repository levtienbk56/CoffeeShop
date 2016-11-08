package org.hedspi.coffeeshop.service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hedspi.coffeeshop.domain.dao.CoffeeDAO;
import org.hedspi.coffeeshop.domain.model.Coffee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * business code for coffee objects
 * 
 * @author trungtran.vn
 *
 */
@Service
public class CoffeeService {
	private static final Logger logger = LogManager.getLogger(CoffeeService.class);

	@Autowired
	private CoffeeDAO coffeeDAO;

	public List<Map<String, Object>> mapDataTable() {
		List<Map<String, Object>> list = new LinkedList<Map<String, Object>>();

		List<Coffee> coffees = coffeeDAO.selectAll();
		for (Coffee cf : coffees) {
			Map<String, Object> map = new HashMap<>();
			map.put("id", cf.getId());
			map.put("name", cf.getName());
			map.put("price", cf.getPrice());
			map.put("available", cf.isEnabled());

			list.add(map);
		}

		return list;
	}

	/*
	 * validate coffee, by check with data in DB
	 */
	public boolean isAvailable(Coffee coffee) {
		logger.entry(coffee);
		Coffee c = coffeeDAO.select(coffee.getId());
		if (c == null) {
			return false;
		}
		// update newest price
		coffee.setPrice(c.getPrice());
		return true;
	}

}
