package org.hedspi.coffeeshop.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.hedspi.coffeeshop.model.Event;
import org.springframework.jdbc.core.RowMapper;

public class EventMapper implements RowMapper<Event> {

	public Event mapRow(ResultSet rs, int arg1) throws SQLException {
		return new Event(rs.getInt("event_id"), rs.getString("title"), rs.getTimestamp("time_start"), rs.getTimestamp("time_end"),
				rs.getString("color"));
	}

}
