package org.hedspi.coffeeshop.dao;

import java.util.List;

import org.hedspi.coffeeshop.model.Coffee;

public interface CoffeeDAO {
	int insertCoffee(Coffee coffee);
	void deleteCoffee(int id);
	List<Coffee> selectAll();
	Coffee selectCoffee(int id);
}
