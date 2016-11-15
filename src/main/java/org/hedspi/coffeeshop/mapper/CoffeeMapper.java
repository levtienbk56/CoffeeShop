package org.hedspi.coffeeshop.mapper;

import java.util.List;

import org.hedspi.coffeeshop.domain.model.Coffee;

public interface CoffeeMapper {
	int insert(Coffee coffee) throws Exception;

	int delete(int id)  throws Exception;

	List<Coffee> selectAll()  throws Exception;

	Coffee select(int id)  throws Exception;

	int update(Coffee coffee)  throws Exception;

	List<Coffee> selectAllActive()  throws Exception;
}
