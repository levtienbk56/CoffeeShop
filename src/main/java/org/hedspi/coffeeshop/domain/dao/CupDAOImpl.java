package org.hedspi.coffeeshop.domain.dao;

import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.hedspi.coffeeshop.domain.mapper.CupMapper;
import org.hedspi.coffeeshop.domain.model.Cup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CupDAOImpl extends JdbcDaoSupport implements CupDAO {

	@Autowired
	public CupDAOImpl(DataSource dataSource) {
		this.setDataSource(dataSource);

	}

	public int insert(int orderID, Cup cup) {
		String sql = "INSERT INTO cups(orders_id, coffees_id, condiments, size, price) VALUES(?,?,?,?,?)";
		Object[] params = new Object[] { orderID, cup.getCoffee().getId(), cup.getCondimentsID(), cup.getSize(), cup.getPrice()};
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
		return this.getJdbcTemplate().query(sql, params, map);
	}

}
