package org.hedspi.coffeeshop.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.hedspi.coffeeshop.model.Order;
import org.springframework.jdbc.core.RowMapper;

public class OrderMapper implements RowMapper<Order> {

	public Order mapRow(ResultSet rs, int arg1) throws SQLException {
		return new Order(rs.getInt("order_id"), rs.getString("username"), rs.getTimestamp("purchase_time"), rs.getDouble("total"));
	}
}
