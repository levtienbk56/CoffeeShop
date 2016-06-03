package org.hedspi.coffeeshop.domain.dao;

import java.util.List;

import org.hedspi.coffeeshop.domain.model.Coffee;

public interface CoffeeDAO {
	int insert(Coffee coffee);
	int delete(int id);
	List<Coffee> selectAll();
	Coffee selectCoffee(int id);
	int update(Coffee coffee);
	List<Coffee> selectAllActive();
}
