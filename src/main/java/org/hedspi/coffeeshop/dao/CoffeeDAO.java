package org.hedspi.coffeeshop.dao;

import java.util.List;

import org.hedspi.coffeeshop.model.Coffee;

public interface CoffeeDAO {
	void insertCoffee(String name, double price, String spec);
	void deleteCoffee(int id);
	List<Coffee> selectAll();
	Coffee selectCoffee(int id);
	double selectPrice(int id);
}
