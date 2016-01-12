package org.hedspi.coffeeshop.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.hedspi.coffeeshop.model.Coffee;
import org.springframework.jdbc.core.RowMapper;

public class CoffeeMapper implements RowMapper<Coffee> {

	public Coffee mapRow(ResultSet rs, int arg1) throws SQLException {
		return new Coffee(rs.getInt("id"), rs.getString("name"), rs.getString("spec"), rs.getDouble("price"));
	}

}