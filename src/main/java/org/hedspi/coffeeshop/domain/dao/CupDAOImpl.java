package org.hedspi.coffeeshop.domain.dao;

import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hedspi.coffeeshop.domain.mapper.CupMapper;
import org.hedspi.coffeeshop.domain.model.Cup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CupDAOImpl extends JdbcDaoSupport implements CupDAOTemp {
	private static final Logger logger = LogManager.getLogger(CupDAOImpl.class);

	@Autowired
	public CupDAOImpl(DataSource dataSource) {
		this.setDataSource(dataSource);

	}

	public int insert(int orderID, Cup cup) {
		logger.entry(orderID, cup);

		String sql = "INSERT INTO cups(orders_id, coffees_id, condiments, size, price) VALUES(?,?,?,?,?)";
		Object[] params = new Object[] { orderID, cup.getCoffee().getId(), cup.getCondimentsID(), cup.getSize(),
				cup.getPrice() };
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

	/**
	 * select data for pie chart. data format:
	 * data[{lable:xxx,data:yyy}...{lable:xxx,data:yyy}]
	 */
	public List<Map<String, Object>> selectCoffeeCorrelate() {
		String sql = "SELECT co.name as label, count(co.name) as data FROM coffees as co, cups as cu WHERE co.coffees_id = cu.coffees_id GROUP BY (co.name)";
		List<Map<String, Object>> list = this.getJdbcTemplate().queryForList(sql);

		return list;
	}

	public List<Cup> selectByOrderId(int orderId) {
		String sql = "SELECT * FROM cups where orders_id=?";
		Object[] params = new Object[] { orderId };
		CupMapper map = new CupMapper();
		try {
			return this.getJdbcTemplate().query(sql, params, map);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
