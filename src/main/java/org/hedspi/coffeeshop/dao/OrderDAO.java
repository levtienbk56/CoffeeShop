package org.hedspi.coffeeshop.dao;

import org.hedspi.coffeeshop.model.Order;

public interface OrderDAO {
	int insert(Order order);
	Order selectOrder(int id);
	int insertWithReturnId(Order order);
	int updatePrice(int id, double price);
}
