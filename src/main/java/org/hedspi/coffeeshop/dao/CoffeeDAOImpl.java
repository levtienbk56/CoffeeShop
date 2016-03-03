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

	public int insert(Coffee coffee) {
		String sql = "INSERT INTO coffees(name,price,enabled) VALUES(?,?,?)";
		Object[] params = new Object[] { coffee.getName(), coffee.getPrice(), coffee.isEnabled() };
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

	public int delete(int id) {
		String sql = "DELETE FROM coffees WHERE coffee_id = ?";
		Object[] params = new Object[] { id };
		try {
			return this.getJdbcTemplate().update(sql, params);
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

	public List<Coffee> selectAll() {
		String sql = "SELECT * FROM coffees  ORDER BY coffee_id";
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
		Object[] params = new Object[] { id };
		CoffeeMapper rowMapper = new CoffeeMapper();
		Coffee c = this.getJdbcTemplate().queryForObject(sql, params, rowMapper);
		return c;
	}

	public int update(Coffee coffee) {
		String sql = "UPDATE coffees SET name=?,price=?,enabled=? WHERE coffee_id=?";
		Object[] params = new Object[] { coffee.getName(), coffee.getPrice(), coffee.isEnabled(), coffee.getId() };
		try {
			return this.getJdbcTemplate().update(sql, params);
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

	public List<Coffee> selectAllActive() {
		String sql = "SELECT * FROM coffees where enabled=true  ORDER BY coffee_id";
		CoffeeMapper mapper = new CoffeeMapper();
		try {
			return this.getJdbcTemplate().query(sql, mapper);
		} catch (CannotGetJdbcConnectionException e) {
			e.printStackTrace();
			return null;
		}
	}

}
