package org.hedspi.coffeeshop.dao;

import java.util.List;
import java.util.Map;

import org.hedspi.coffeeshop.model.Order;

public interface OrderDAO {
	int insert(Order order);
	Order selectOrder(int id);
	int insertWithReturnId(Order order);
	int updatePrice(int id, double price);
	List<Order> selectAll();
	List<Map<String, Object>> selectTotalDateCorrelate(Double year, Double month);
	List<Integer> selectYears();
	List<Integer> selectMonths(Double year);
}
