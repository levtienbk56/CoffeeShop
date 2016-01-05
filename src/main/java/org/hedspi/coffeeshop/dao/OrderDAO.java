package org.hedspi.coffeeshop.dao;

import org.hedspi.coffeeshop.model.Order;

public interface OrderDAO {
	void insert(Order order);
	Order selectOrder(int id);
}
