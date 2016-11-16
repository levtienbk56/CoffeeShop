package org.hedspi.coffeeshop.service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hedspi.coffeeshop.domain.model.Coffee;
import org.hedspi.coffeeshop.mapper.CoffeeMapper;
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
	CoffeeMapper coffeeMapper;

	public List<Map<String, Object>> mapDataTable() {
		List<Map<String, Object>> list = new LinkedList<Map<String, Object>>();

		List<Coffee> coffees = selectAll();
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
		Coffee c = select(coffee.getId());
		if (c == null) {
			return false;
		}
		// update newest price
		coffee.setPrice(c.getPrice());
		return true;
	}

	public int insert(Coffee coffee) {
		if (validateBefore(coffee))
			try {
				return coffeeMapper.insert(coffee);
			} catch (Exception e) {
				e.printStackTrace();
			}
		return -1; // false
	}

	public int delete(int id) {
		if (id > 0)
			try {
				return coffeeMapper.delete(id);
			} catch (Exception e) {
				e.printStackTrace();
			}
		return -1; // false
	}

	public List<Coffee> selectAll() {
		try {
			return coffeeMapper.selectAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public Coffee select(int id) {
		if (id > 0)
			try {
				return coffeeMapper.select(id);
			} catch (Exception e) {
				e.printStackTrace();
			}
		return null;
	}

	public int update(Coffee coffee) {
		if (validateBefore(coffee))
			try {
				return coffeeMapper.update(coffee);
			} catch (Exception e) {
				e.printStackTrace();
			}
		return -1;
	}

	public List<Coffee> selectAllActive() {
		try {
			return coffeeMapper.selectAllActive();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private boolean validateBefore(Coffee coffee) {
		if (coffee != null && coffee.getName() != null && !coffee.getName().equals("") && coffee.getPrice() >= 0) {
			return true;
		}

		return false;
	}

}
