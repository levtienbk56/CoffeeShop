package org.hedspi.coffeeshop.service;

import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hedspi.coffeeshop.domain.model.Order;
import org.hedspi.coffeeshop.mapper.CoffeeMapper;
import org.hedspi.coffeeshop.mapper.CondimentMapper;
import org.hedspi.coffeeshop.mapper.CupMapper;
import org.hedspi.coffeeshop.mapper.EventMapper;
import org.hedspi.coffeeshop.mapper.OrderMapper;
import org.hedspi.coffeeshop.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MainService {
	private static final Logger logger = LogManager.getLogger(MainService.class);

	@Autowired
	CoffeeMapper coffeeMapper;
	@Autowired
	CondimentMapper condimentMapper;
	@Autowired
	EventMapper eventMapper;
	@Autowired
	UserMapper userMapper;
	@Autowired
	CupMapper cupMapper;
	@Autowired
	OrderMapper orderMapper;

	public void test() {
		logger.debug(orderMapper.insert(new Order(0, "quyvd", new Date(), 25.5)));
		String condiments = "";
		String size = "BIG";
		logger.debug(cupMapper.insert(7, 3, condiments, size, 4.5));
		logger.debug(cupMapper.selectCoffeeCorrelate());
	}

}
