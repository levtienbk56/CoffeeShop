package org.hedspi.coffeeshop.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.hedspi.coffeeshop.model.Condiment;
import org.springframework.jdbc.core.RowMapper;

public class CondimentMapper implements RowMapper<Condiment> {

	public Condiment mapRow(ResultSet rs, int arg1) throws SQLException {
		return new Condiment(rs.getInt("condiment_id"), rs.getString("name"), rs.getDouble("price"));
	}

}
