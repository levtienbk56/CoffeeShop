package org.hedspi.coffeeshop.mapper;

import java.util.List;

import org.hedspi.coffeeshop.domain.model.Coffee;

public interface CoffeeMapper{
	public List<Coffee> selectAll();
//	public List<Coffee> selectAllAvailable();
//	public Coffee select(int id);
//	public int insert(Coffee coffee);
}
