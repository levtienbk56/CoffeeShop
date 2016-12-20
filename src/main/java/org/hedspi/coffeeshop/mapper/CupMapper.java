package org.hedspi.coffeeshop.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface CupMapper {
	int insert(@Param("orderId") int orderId, @Param("coffeeId") int coffeeId, @Param("condiments") String condiments,
			@Param("size") String size, @Param("price") double price);

	List<Map<String, Object>> selectByOrderId(int orderId);

	List<Map<String, Object>> selectCoffeeCorrelate();
}
