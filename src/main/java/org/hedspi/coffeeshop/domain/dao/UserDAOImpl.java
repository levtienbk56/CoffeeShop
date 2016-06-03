package org.hedspi.coffeeshop.domain.dao;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.hedspi.coffeeshop.domain.mapper.UserMapper;
import org.hedspi.coffeeshop.domain.model.User;
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

	public int delete(String username) {
		String sql = "DELETE FROM users as u WHERE u.username = ?";
		Object[] params = new Object[] { username };
		try {
			return this.getJdbcTemplate().update(sql, params);
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		} 
	}

	public User selectUser(String username) {
		String sql = "SELECT username,password,enabled,role FROM users WHERE username=?";
		Object[] params = new Object[] { username };
		UserMapper mapper = new UserMapper();
		User user = this.getJdbcTemplate().queryForObject(sql, params, mapper);
		return user;
	}

	public List<User> selectAll() {
		String sql = "SELECT * FROM users";
		UserMapper mapper = new UserMapper();
		try {
			return this.getJdbcTemplate().query(sql, mapper);
		} catch (CannotGetJdbcConnectionException e) {
			e.printStackTrace();
			return new ArrayList<User>();
		}
	}

	public int update(User user) {
		String sql = "UPDATE users SET password=?,enabled=?,role=? WHERE username=?";
		Object[] params = new Object[] {user.getPassword(), user.isEnabled(), user.getRole(),  user.getUsername()};
		try {
			return this.getJdbcTemplate().update(sql, params);
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		} 
	}

}
