package org.hedspi.coffeeshop.dao;

import java.util.List;

import org.hedspi.coffeeshop.model.Condiment;

public interface CondimentDAO {
	int insert(Condiment condiment);

	int update(Condiment condiment);

	int delete(int id);

	Condiment select(int id);
	
	List<Condiment> selectAll();

}
