package org.hedspi.coffeeshop.dao;

import java.util.List;

import org.hedspi.coffeeshop.model.Coffee;

public interface CoffeeDAO {
	int insert(Coffee coffee);
	int delete(int id);
	List<Coffee> selectAll();
	Coffee selectCoffee(int id);
	int update(Coffee coffee);
	List<Coffee> selectAllActive();
}
