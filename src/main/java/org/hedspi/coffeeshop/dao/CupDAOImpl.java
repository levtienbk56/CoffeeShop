package org.hedspi.coffeeshop.dao;

import javax.sql.DataSource;

import org.hedspi.coffeeshop.model.Cup;
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

	public int insert(Cup cup) {
		String sql = "INSERT INTO cups(order_id,coffee_id,size,condiments,price) VALUES(?,?,?,?,?)";
		Object[] params = new Object[] { cup.getOrderId(), cup.getCoffeeId(), cup.getSize(), cup.getCondiments(),
				cup.getPrice() };
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

}
