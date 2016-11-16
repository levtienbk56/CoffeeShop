package org.hedspi.coffeeshop.domain.dao;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import org.hedspi.coffeeshop.domain.model.Order;

public interface OrderDAORemp {
	int insert(Order order);
	Order selectOrder(int id);
	int insertWithReturnId(Order order);
	int updatePrice(int id, double price);
	List<Order> selectAll();
	List<Order> selectByRange(Timestamp dfrom, Timestamp dto);
	List<Map<String, Object>> selectTotalDateCorrelate(Double year, Double month);
	public List<Map<String, Object>> selectTotalCoffeeCorrelation(Double year, Double month);
	List<Integer> selectYears();
	List<Integer> selectMonths(Double year);
	int selectNumberCupOfCoffeeByDate(String coffeeName, Date date);
}
