package org.hedspi.coffeeshop.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hedspi.coffeeshop.mapper.CoffeeMapper;
import org.hedspi.coffeeshop.mapper.CondimentMapper;
import org.hedspi.coffeeshop.mapper.EventMapper;
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

	public void test() {
		logger.debug(coffeeMapper.selectAllActive());
		logger.debug(coffeeMapper.select(1));
		
		logger.debug(coffeeMapper.selectAllActive());
		logger.debug(coffeeMapper.select(1));
		
		logger.debug(coffeeMapper.selectAllActive());
		logger.debug(coffeeMapper.select(1));
		
		logger.debug(coffeeMapper.selectAllActive());
		logger.debug(coffeeMapper.select(1));
		
		logger.debug(condimentMapper.selectAll());
		
		logger.debug(condimentMapper.select(1));
		
		logger.debug(userMapper.selectAll());
		
		logger.debug(userMapper.select("quyvd"));
		
		logger.debug(eventMapper.selectAll());
	}

}
