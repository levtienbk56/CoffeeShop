package org.hedspi.coffeeshop.mapper;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.hedspi.coffeeshop.domain.model.Order;

public interface OrderMapper {
	int insert(Order order);

	int updatePrice(@Param("id")int id,@Param("price") double price);

	List<Map<String, Object>> selectTotalDateCorrelate(@Param("year") Double year, @Param("month") Double month);

	List<Map<String, Object>> selectTotalCoffeeCorrelation(@Param("year") Double year, @Param("month") Double month);

	List<Integer> selectYears();

	List<Integer> selectMonths(Double year);

	int selectNumberCupOfCoffeeByDate(@Param("coffeeName") String coffeeName, @Param("date") Date date);

	List<Order> selectByRange(@Param("dfrom") Timestamp dfrom, @Param("dto") Timestamp dto);
}
