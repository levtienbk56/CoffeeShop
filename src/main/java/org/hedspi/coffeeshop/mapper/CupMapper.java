package org.hedspi.coffeeshop.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.hedspi.coffeeshop.model.Cup;
import org.springframework.jdbc.core.RowMapper;

public class CupMapper implements RowMapper<Cup> {

	public Cup mapRow(ResultSet rs, int arg1) throws SQLException {
		return new Cup(rs.getInt("cups_id"), rs.getInt("coffees_id"), rs.getInt("orders_id"), rs.getString("size"),
				rs.getString("condiments"), rs.getDouble("price"));
	}

}
