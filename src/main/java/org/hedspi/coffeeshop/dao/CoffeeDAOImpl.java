package org.hedspi.coffeeshop.dao;

import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.hedspi.coffeeshop.mapper.CoffeeMapper;
import org.hedspi.coffeeshop.model.Coffee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CoffeeDAOImpl extends JdbcDaoSupport implements CoffeeDAO {

	@Autowired
	public CoffeeDAOImpl(DataSource dataSource) {
		this.setDataSource(dataSource);

	}

	public void insertCoffee(String name, double price, String spec) {
		// TODO Auto-generated method stub

	}

	public void deleteCoffee(int id) {
		// TODO Auto-generated method stub

	}

	public List<Coffee> selectAll() {
		String sql = "SELECT * FROM coffee";
		CoffeeMapper mapper = new CoffeeMapper();
		return this.getJdbcTemplate().query(sql, mapper);
	}

	public Coffee selectCoffee(int id) {
		Coffee c = new Coffee();
		return c;
	}

	public double selectPrice(int id) {
		String sql = "Select price from coffee where id=?";

		Object[] params = new Object[] { id };

		double price = this.getJdbcTemplate().queryForObject(sql, params, Double.class);
		return price;
	}

}
