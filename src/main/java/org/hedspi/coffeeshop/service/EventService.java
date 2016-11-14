package org.hedspi.coffeeshop.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hedspi.coffeeshop.domain.model.Event;
import org.hedspi.coffeeshop.mapper.EventMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventService {
	private static final Logger logger = LogManager.getLogger(EventService.class);
	@Autowired
	EventMapper eventMapper;

	public int insertEvent(Event event) {
		logger.entry(event);
		// validation
		if (!validate(event)) {
			return 0;
		}

		return eventMapper.insert(event);
	}

	private boolean validate(Event event) {
		return true;
	}

}
