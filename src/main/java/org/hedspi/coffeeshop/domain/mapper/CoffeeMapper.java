package org.hedspi.coffeeshop.domain.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.hedspi.coffeeshop.domain.model.Coffee;
import org.springframework.jdbc.core.RowMapper;

public class CoffeeMapper implements RowMapper<Coffee> {

	public Coffee mapRow(ResultSet rs, int arg1) throws SQLException {
		return new Coffee(rs.getInt("coffees_id"), rs.getString("name"), rs.getDouble("price"), rs.getBoolean("enabled"));
	}

}
