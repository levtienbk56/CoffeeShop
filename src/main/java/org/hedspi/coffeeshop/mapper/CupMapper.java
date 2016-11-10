package org.hedspi.coffeeshop.mapper;

import java.util.List;
import java.util.Map;

import org.hedspi.coffeeshop.domain.model.Cup;

public interface CupMapper {
	int insert(int orderID, int coffeeId, String condiments, String size, double price);

	List<Map<String, Object>> selectCoffeeCorrelate();

	public List<Cup> selectByOrderId(int orderId);
}
