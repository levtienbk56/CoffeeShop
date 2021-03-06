package org.hedspi.coffeeshop.domain.dao;

import java.util.List;

import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hedspi.coffeeshop.domain.mapper.CondimentMapper;
import org.hedspi.coffeeshop.domain.model.Condiment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CondimentDAOImpl extends JdbcDaoSupport implements CondimentDAOTemp {
	private static final Logger logger = LogManager.getLogger(CondimentDAOImpl.class);

	@Autowired
	public CondimentDAOImpl(DataSource dataSource) {
		this.setDataSource(dataSource);
	}

	public int insert(Condiment condiment) {
		String sql = "INSERT INTO condiments(name, price, enabled) VALUES(?,?,?)";
		Object[] params = new Object[] { condiment.getName(), condiment.getPrice(), condiment.isEnabled() };
		try {
			return this.getJdbcTemplate().update(sql, params);
		} catch (DuplicateKeyException e) {
			return 0;
		} catch (Exception e) {
			return -1;
		}
	}

	public int update(Condiment condiment) {
		String sql = "UPDATE condiments SET name=?,price=?,enabled=? WHERE condiments_id=?";
		Object[] params = new Object[] { condiment.getName(), condiment.getPrice(), condiment.isEnabled(),
				condiment.getId() };
		try {
			return this.getJdbcTemplate().update(sql, params);
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

	public int delete(int id) {
		String sql = "DELETE FROM condiments WHERE condiments_id=?";
		Object[] params = new Object[] { id };
		try {
			return this.getJdbcTemplate().update(sql, params);
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

	public Condiment select(int id) {
		logger.entry(id);
		String sql = "SELECT * FROM condiments WHERE condiments_id=?";
		CondimentMapper rowMapper = new CondimentMapper();
		Object[] params = new Object[] { id };
		try {
			return this.getJdbcTemplate().queryForObject(sql, rowMapper, params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<Condiment> selectAll() {
		String sql = "SELECT * FROM condiments ORDER BY condiments_id";

		Object[] params = new Object[] {};
		CondimentMapper mapper = new CondimentMapper();

		try {
			return this.getJdbcTemplate().query(sql, params, mapper);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<Condiment> selectAllActive() {
		String sql = "SELECT * FROM condiments where enabled=true ORDER BY condiments_id";

		Object[] params = new Object[] {};
		CondimentMapper mapper = new CondimentMapper();

		try {
			return this.getJdbcTemplate().query(sql, params, mapper);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
