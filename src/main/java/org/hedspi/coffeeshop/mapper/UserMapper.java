package org.hedspi.coffeeshop.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.hedspi.coffeeshop.model.User;
import org.springframework.jdbc.core.RowMapper;

public class UserMapper implements RowMapper<User> {

	public User mapRow(ResultSet rs, int arg1) throws SQLException {
		// TODO Auto-generated method stub
		return new User(rs.getString("username"), rs.getString("password"), rs.getString("role"));
	}

}
