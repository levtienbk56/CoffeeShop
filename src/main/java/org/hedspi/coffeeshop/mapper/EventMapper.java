package org.hedspi.coffeeshop.mapper;

import java.util.List;

import org.hedspi.coffeeshop.domain.model.Event;

public interface EventMapper {
	int insert(Event event);

	int delete(int id);

	List<Event> selectAll();

	int update(Event event);
}
