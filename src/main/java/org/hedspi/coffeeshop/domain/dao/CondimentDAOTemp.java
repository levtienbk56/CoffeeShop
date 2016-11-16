package org.hedspi.coffeeshop.domain.dao;

import java.util.List;

import org.hedspi.coffeeshop.domain.model.Condiment;

public interface CondimentDAOTemp {
	int insert(Condiment condiment);

	int update(Condiment condiment);

	int delete(int id);

	Condiment select(int id);
	
	List<Condiment> selectAll();
	
	List<Condiment> selectAllActive();

}
