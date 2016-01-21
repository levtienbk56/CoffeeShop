package org.hedspi.coffeeshop.dao;

import java.util.List;

import javax.sql.DataSource;

import org.hedspi.coffeeshop.mapper.CondimentMapper;
import org.hedspi.coffeeshop.model.Condiment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CondimentDAOImpl extends JdbcDaoSupport implements CondimentDAO {

	@Autowired
	public CondimentDAOImpl(DataSource dataSource) {
		this.setDataSource(dataSource);
	}

	public int insert(Condiment condiment) {
		String sql = "INSERT INTO condiments(name, price) VALUES(?,?)";
		Object[] params = new Object[] { condiment.getName(), condiment.getPrice() };
		try {
			return this.getJdbcTemplate().update(sql, params);
		} catch (CannotGetJdbcConnectionException e) {
			return -1;
		} catch (DuplicateKeyException e) {
			return 0;
		}
	}

	public void update(Condiment condiment) {
		// TODO Auto-generated method stub

	}

	public double delete(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	public double selectPrice(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	public List<Condiment> selectAll() {
		String sql = "SELECT * FROM condiments";

		Object[] params = new Object[] {};
		CondimentMapper mapper = new CondimentMapper();

		List<Condiment> list = this.getJdbcTemplate().query(sql, params, mapper);
		return list;
	}

}
