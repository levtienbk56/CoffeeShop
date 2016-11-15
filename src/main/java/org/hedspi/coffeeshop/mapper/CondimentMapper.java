package org.hedspi.coffeeshop.mapper;

import java.util.List;

import org.hedspi.coffeeshop.domain.model.Condiment;

public interface CondimentMapper {
	int insert(Condiment condiment) throws Exception;

	int update(Condiment condiment) throws Exception;

	int delete(int id) throws Exception;

	Condiment select(int id) throws Exception;

	List<Condiment> selectAll() throws Exception;

	List<Condiment> selectAllActive() throws Exception;

}
