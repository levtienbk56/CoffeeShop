package org.hedspi.coffeeshop.dao;

import javax.sql.DataSource;

import org.hedspi.coffeeshop.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class OrderDAOImpl extends JdbcDaoSupport implements OrderDAO {

	@Autowired
	public OrderDAOImpl(DataSource dataSource) {
		this.setDataSource(dataSource);
	}

	public int insert(Order order) {
		String sql = "INSERT INTO orders(username,purchase_time,total) VALUES(?,?,?)";
		Object[] params = new Object[] { order.getUsername(), order.getPurchaseTime(), order.getTotal() };
		try {
			return this.getJdbcTemplate().update(sql, params);
		} catch (CannotGetJdbcConnectionException e) {
			return -1;
		} catch (DuplicateKeyException e) {
			return 0;
		}
	}

	public Order selectOrder(int id) {

		return null;
	}

	public int insertWithReturnId(Order order) {
		String sql = "INSERT INTO orders(username,purchase_time,total) VALUES(?,?,?) RETURNING order_id";

		Object[] params = new Object[] { order.getUsername(), order.getPurchaseTime(), order.getTotal() };
		try {
			return this.getJdbcTemplate().queryForObject(sql, params, Integer.class);
		} catch (CannotGetJdbcConnectionException e) {
			return -1;
		} catch (DuplicateKeyException e) {
			return 0;
		}
	}

	public int updatePrice(int id, double price) {
		String sql = "UPDATE orders SET total=? WHERE order_id=?";
		Object[] params = new Object[] { price, id };
		try {
			return this.getJdbcTemplate().update(sql, params);
		} catch (CannotGetJdbcConnectionException e) {
			return -1;
		} catch (DuplicateKeyException e) {
			return 0;
		}
	}

}
