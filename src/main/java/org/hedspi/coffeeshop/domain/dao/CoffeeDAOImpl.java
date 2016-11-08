package org.hedspi.coffeeshop.domain.dao;

import java.util.List;

import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hedspi.coffeeshop.domain.mapper.CoffeeMapper;
import org.hedspi.coffeeshop.domain.model.Coffee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CoffeeDAOImpl extends JdbcDaoSupport implements CoffeeDAO {
	private static final Logger logger = LogManager.getLogger(CoffeeDAOImpl.class);

	@Autowired
	public CoffeeDAOImpl(DataSource dataSource) {
		this.setDataSource(dataSource);

	}

	public int insert(Coffee coffee) {
		String sql = "INSERT INTO coffees(name,price,enabled) VALUES(?,?,?)";
		Object[] params = new Object[] { coffee.getName(), coffee.getPrice(), coffee.isEnabled() };
		try {
			return this.getJdbcTemplate().update(sql, params);
		} catch (DuplicateKeyException e) {
			e.printStackTrace();
			return 0;
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}

	}

	public int delete(int id) {
		String sql = "DELETE FROM coffees WHERE coffees_id = ?";
		Object[] params = new Object[] { id };
		try {
			return this.getJdbcTemplate().update(sql, params);
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

	public List<Coffee> selectAll() {
		String sql = "SELECT * FROM coffees  ORDER BY coffees_id";
		CoffeeMapper mapper = new CoffeeMapper();
		try {
			return this.getJdbcTemplate().query(sql, mapper);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public Coffee select(int id) {
		logger.entry(id);
		String sql = "SELECT * FROM coffees WHERE coffees_id=?";
		Object[] params = new Object[] { id };
		CoffeeMapper rowMapper = new CoffeeMapper();
		try {
			return this.getJdbcTemplate().queryForObject(sql, params, rowMapper);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public int update(Coffee coffee) {
		String sql = "UPDATE coffees SET name=?,price=?,enabled=? WHERE coffees_id=?";
		Object[] params = new Object[] { coffee.getName(), coffee.getPrice(), coffee.isEnabled(), coffee.getId() };
		try {
			return this.getJdbcTemplate().update(sql, params);
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

	public List<Coffee> selectAllActive() {
		String sql = "SELECT * FROM coffees where enabled=true  ORDER BY coffees_id";
		CoffeeMapper mapper = new CoffeeMapper();
		try {
			return this.getJdbcTemplate().query(sql, mapper);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
