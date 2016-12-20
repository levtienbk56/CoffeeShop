package org.hedspi.coffeeshop.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

	public List<Map<String, Object>> selectByOrderId(int orderId) {
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
