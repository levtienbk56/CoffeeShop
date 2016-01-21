package org.hedspi.coffeeshop.dao;

import javax.sql.DataSource;

import org.hedspi.coffeeshop.mapper.UserMapper;
import org.hedspi.coffeeshop.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserDAOImpl extends JdbcDaoSupport implements UserDAO {

	@Autowired
	public UserDAOImpl(DataSource dataSource) {
		this.setDataSource(dataSource);
	}

	public int insert(User user) {
		String sql = "INSERT INTO users(username,password,enabled,role) VALUES(?,?,?,?)";
		Object[] params = new Object[] { user.getUsername(), user.getPassword(), user.isEnabled(), user.getRole() };
		try {
			return this.getJdbcTemplate().update(sql, params);
		} catch (CannotGetJdbcConnectionException e) {
			return -1;
		} catch (DuplicateKeyException e) {
			return 0;
		}

	}

	public void delete(User user) {
		// TODO Auto-generated method stub

	}

	public User getUser(String username) {
		String sql = "SELECT username,password,role FROM users WHERE username=?";
		Object[] params = new Object[] { username };
		UserMapper mapper = new UserMapper();
		User user = this.getJdbcTemplate().queryForObject(sql, params, mapper);
		return user;
	}

}
