package org.hedspi.coffeeshop.domain.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.hedspi.coffeeshop.domain.model.Order;
import org.springframework.jdbc.core.RowMapper;

public class OrderMapper implements RowMapper<Order> {

	public Order mapRow(ResultSet rs, int arg1) throws SQLException {
		return new Order(rs.getInt("orders_id"), rs.getString("username"), rs.getTimestamp("purchase_time").toString(), rs.getDouble("total"));
	}

}
