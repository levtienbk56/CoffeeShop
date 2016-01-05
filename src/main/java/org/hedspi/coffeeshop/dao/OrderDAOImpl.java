package org.hedspi.coffeeshop.dao;

import javax.sql.DataSource;

import org.hedspi.coffeeshop.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
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

	public void insert(Order order) {
		// TODO Auto-generated method stub
		
	}

	public Order selectOrder(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
