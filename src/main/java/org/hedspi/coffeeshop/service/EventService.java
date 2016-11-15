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
		if (validateBefore(event)) {
			try {
				return eventMapper.insert(event);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return 0;
	}

	public int updateEvent(Event event) {
		logger.entry(event);

		if (validateBefore(event)) {
			try {
				return eventMapper.update(event);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return 0;
	}

	public int deleteEvent(int id) {
		logger.entry(id);

		if (id > 0) {
			try {
				return eventMapper.delete(id);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return 0;
	}

	private boolean validateBefore(Event event) {
		if (event != null && event.getColor() != null && event.getTitle() != null && event.getStart() != null
				&& event.getEnd() != null && event.getTitle().length() * event.getColor().length()
						* event.getStart().getTime() * event.getEnd().getTime() > 0) {
			// TODO: validate color

			return true;
		}
		return false;
	}
}
