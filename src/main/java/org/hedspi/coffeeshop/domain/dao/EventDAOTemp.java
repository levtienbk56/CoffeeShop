package org.hedspi.coffeeshop.domain.dao;

import java.util.List;

import org.hedspi.coffeeshop.domain.model.Event;

public interface EventDAOTemp {
	int insert(Event event);

	int delete(int id);

	List<Event> selectAll();

	int update(Event event);
}
