package org.hedspi.coffeeshop.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hedspi.coffeeshop.domain.model.Coffee;
import org.hedspi.coffeeshop.domain.model.Condiment;
import org.hedspi.coffeeshop.domain.model.Cup;
import org.hedspi.coffeeshop.mapper.CupMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CupService {
	@Autowired
	CupMapper cupMapper;
	@Autowired
	CondimentService condimentService;
	@Autowired
	CoffeeService coffeeService;

	public List<Map<String, Object>> mapDataTable(int orderId) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		List<Cup> cups = selectByOrderId(orderId);
		List<Coffee> coffees = coffeeService.selectAll();
		List<Condiment> condiments = condimentService.selectAll();

		if (cups != null && coffees != null && condiments != null) {

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
		}
		return list;
	}

	int insert(int orderID, Cup cup) {
		if (orderID > 0 && validateBefore(cup)) {
			try {
				return cupMapper.insert(orderID, cup.getCoffee().getId(), cup.getCondimentsID(), cup.getSize(),
						cup.getPrice());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return -1;
	}

	List<Map<String, Object>> selectCoffeeCorrelate() {
		try {
			return cupMapper.selectCoffeeCorrelate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<Cup> selectByOrderId(int orderId) {
		if (orderId > 0) {
			try {
				return cupMapper.selectByOrderId(orderId);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	private boolean validateBefore(Cup cup) {
		if (cup != null && cup.getCoffee() != null && cup.getPrice() >= 0 && cup.getSize() != null) {
			return true;
		}
		return false;
	}

}
