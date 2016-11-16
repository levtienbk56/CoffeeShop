package org.hedspi.coffeeshop.domain.dao;

import java.util.List;
import java.util.Map;

import org.hedspi.coffeeshop.domain.model.Cup;

public interface CupDAOTemp {
	int insert(int orderID, Cup cup);

	List<Map<String, Object>> selectCoffeeCorrelate();

	public List<Cup> selectByOrderId(int orderId);
}
