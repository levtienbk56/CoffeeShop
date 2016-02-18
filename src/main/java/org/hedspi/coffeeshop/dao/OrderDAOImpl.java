package org.hedspi.coffeeshop.dao;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.hedspi.coffeeshop.mapper.OrderMapper;
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

	public List<Order> selectAll() {
		String sql = "SELECT * FROM orders";
		OrderMapper mapper = new OrderMapper();
		try {
			return this.getJdbcTemplate().query(sql, mapper);
		} catch (CannotGetJdbcConnectionException e) {
			e.printStackTrace();
			return new ArrayList<Order>();
		}
	}

	/**
	 * select data for bar chart. data format:
	 * data[{label:xxx,data:yyy}...{label:xxx,data:yyy}]
	 */
	public List<Map<String, Object>> selectTotalDateCorrelate(Double year, Double month) {
		String sql = "SELECT date(purchase_time) AS label, sum(total) AS data FROM orders WHERE date_part('year', purchase_time)=? and date_part('month', purchase_time)=? GROUP BY label ORDER BY label ASC";
		Object[] params = new Object[] { year, month };
		List<Map<String, Object>> list = this.getJdbcTemplate().queryForList(sql, params);

		return list;
	}

	/**
	 * select data for STACK bar chart. 
	 * number cup of Coffee, by each day, in a requested month/year
	 * Format: {[name:xxx, mdate: yyy, mcup:zzz], [...]}
	 */
	public List<Map<String, Object>> selectTotalCoffeeCorrelation(Double year, Double month) {
		String sql = "select co.name as mname, date_part('day', o.purchase_time) as mdate, count(co.name) as mcup "
				+ "from orders as o, cups as c, coffees as co "
				+ "where c.order_id = o.order_id and c.coffee_id = co.coffee_id and date_part('year', o.purchase_time)=? and date_part('month', o.purchase_time)=? "
				+ "group by mname, mdate "
				+ "order by mname, mdate";
		Object[] params = new Object[] { year, month };
		List<Map<String, Object>> list = this.getJdbcTemplate().queryForList(sql, params);

		return list;
	}

	public List<Integer> selectYears() {
		String sql = "SELECT date_part('year', purchase_time) as myear FROM orders GROUP BY myear ORDER BY myear DESC";
		List<Integer> list = this.getJdbcTemplate().queryForList(sql, Integer.class);

		return list;
	}

	public List<Integer> selectMonths(Double year) {
		String sql = "SELECT date_part('month', purchase_time)AS mmonth FROM orders WHERE date_part('year', purchase_time)=? GROUP BY mmonth ORDER BY mmonth ASC";
		Object[] params = new Object[] { year };
		List<Integer> list = this.getJdbcTemplate().queryForList(sql, params, Integer.class);

		return list;
	}

	public int selectNumberCupOfCoffeeByDate(String coffeeName, Date date) {
		String sql = "select count(co.name) " + "from cups as c, coffees as co, orders as o"
				+ " where c.coffee_id=co.coffee_id and c.order_id = o.order_id and co.name = ? and date(o.purchase_time)=?";
		Object[] params = new Object[] { coffeeName, date };
		Object obj = this.getJdbcTemplate().queryForObject(sql, params, Integer.class);
		if (obj == null) {
			return 0;
		}
		return (Integer) obj;
	}

}
