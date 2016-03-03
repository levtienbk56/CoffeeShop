package org.hedspi.coffeeshop.dao;

import java.util.List;
import java.util.Map;

import org.hedspi.coffeeshop.model.Cup;

public interface CupDAO {
	int insert(Cup cup);

	List<Map<String, Object>> selectCoffeeCorrelate();

	public List<Cup> selectByOrderId(int orderId);
}
