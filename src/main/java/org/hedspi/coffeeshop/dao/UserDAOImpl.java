package org.hedspi.coffeeshop.dao;

import javax.sql.DataSource;

import org.hedspi.coffeeshop.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserDAOImpl extends JdbcDaoSupport implements UserDAO {

	@Autowired
	public UserDAOImpl(DataSource dataSource){
		this.setDataSource(dataSource);
	}
	public void insert(User user) {
		// TODO Auto-generated method stub

	}

	public void delete(User user) {
		// TODO Auto-generated method stub

	}

}
