package org.hedspi.coffeeshop.dao;

import java.util.List;

import org.hedspi.coffeeshop.model.Order;

public interface OrderDAO {
	int insert(Order order);
	Order selectOrder(int id);
	int insertWithReturnId(Order order);
	int updatePrice(int id, double price);
	List<Order> selectAll();
}
