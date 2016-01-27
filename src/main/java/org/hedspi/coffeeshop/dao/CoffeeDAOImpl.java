package org.hedspi.coffeeshop.dao;

import java.util.List;

import javax.sql.DataSource;

import org.hedspi.coffeeshop.mapper.CoffeeMapper;
import org.hedspi.coffeeshop.model.Coffee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
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

	public int insertCoffee(Coffee coffee) {
		String sql = "INSERT INTO coffees(name,price) VALUES(?,?)";
		Object[] params = new Object[] { coffee.getName(), coffee.getPrice() };
		try {
			return this.getJdbcTemplate().update(sql, params);
		} catch (CannotGetJdbcConnectionException e) {
			e.printStackTrace();
			return -1;
		} catch (DuplicateKeyException e) {
			e.printStackTrace();
			return 0;
		}

	}

	public void deleteCoffee(int id) {
		// TODO Auto-generated method stub

	}

	public List<Coffee> selectAll() {
		String sql = "SELECT * FROM coffees";
		CoffeeMapper mapper = new CoffeeMapper();
		try {
			return this.getJdbcTemplate().query(sql, mapper);
		} catch (CannotGetJdbcConnectionException e) {
			e.printStackTrace();
			return null;
		}
	}

	public Coffee selectCoffee(int id) {
		String sql = "SELECT * FROM coffees WHERE coffee_id=?";
		Object[] params = new Object[] {id};
		CoffeeMapper rowMapper = new CoffeeMapper();
		Coffee c = this.getJdbcTemplate().queryForObject(sql, params, rowMapper);
		return c;
	}

}
