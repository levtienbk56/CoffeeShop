package org.hedspi.coffeeshop.dao;

import java.util.List;

import org.hedspi.coffeeshop.model.Event;

public interface EventDAO {
	int insert(Event event);

	int delete(int id);

	List<Event> selectAll();

	int update(Event event);
}
