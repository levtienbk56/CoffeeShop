package org.hedspi.coffeeshop.dao;

import java.util.List;

import org.hedspi.coffeeshop.model.Condiment;

public interface CondimentDAO {
	void insert(Condiment condiment);

	void update(Condiment condiment);

	double delete(int id);

	double selectPrice(int id);
	
	List<Condiment> selectAll();

}
