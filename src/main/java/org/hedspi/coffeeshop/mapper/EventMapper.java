package org.hedspi.coffeeshop.mapper;

import java.util.List;

import org.hedspi.coffeeshop.domain.model.Event;

public interface EventMapper {
	int insert(Event event) throws Exception;

	int delete(int id) throws Exception;

	List<Event> selectAll() throws Exception;

	int update(Event event) throws Exception;
}
