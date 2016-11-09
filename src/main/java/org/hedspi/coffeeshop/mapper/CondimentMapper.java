package org.hedspi.coffeeshop.mapper;

import java.util.List;

import org.hedspi.coffeeshop.domain.model.Condiment;

public interface CondimentMapper {
	int insert(Condiment condiment);

	int update(Condiment condiment);

	int delete(int id);

	Condiment select(int id);

	List<Condiment> selectAll();

	List<Condiment> selectAllActive();

}
