package org.hedspi.coffeeshop.domain.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.hedspi.coffeeshop.domain.model.Event;
import org.springframework.jdbc.core.RowMapper;

public class EventMapper implements RowMapper<Event> {

	public Event mapRow(ResultSet rs, int arg1) throws SQLException {
		return new Event(rs.getInt("events_id"), rs.getString("title"), rs.getTimestamp("time_start").toString(),
				rs.getTimestamp("time_end").toString(), rs.getString("color"));
	}

}
