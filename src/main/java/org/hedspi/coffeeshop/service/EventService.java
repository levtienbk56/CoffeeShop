package org.hedspi.coffeeshop.service;

import java.util.List;

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

	/**
	 * 
	 * @param event
	 * @return 1: success<br>
	 *         -1: error<br>
	 */
	public int insert(Event event) {
		logger.entry(event);
		// validation
		if (validateBefore(event)) {
			try {
				return eventMapper.insert(event);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return -1;
	}

	/**
	 * 
	 * @param event
	 * @return 1: success<br>
	 *         0: no record found<br>
	 *         -1: error<br>
	 */
	public int update(Event event) {
		logger.entry(event);

		if (validateBefore(event)) {
			try {
				return eventMapper.update(event);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return -1;
	}

	public int delete(int id) {
		logger.entry(id);

		if (id > 0) {
			try {
				return eventMapper.delete(id);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return -1;
	}

	public List<Event> selectAll() {
		logger.entry();
		try {
			return eventMapper.selectAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private boolean validateBefore(Event event) {
		// not null
		if (event != null && event.getColor() != null && event.getTitle() != null && event.getStart() != null
				&& event.getEnd() != null) {
			// not empty
			if (event.getTitle().length() > 0 && event.getColor().length() > 0 && event.getStart().getTime() > 0
					&& event.getEnd().getTime() > 0) {
				// validate color
				String color = event.getColor().toLowerCase();
				if (color.equals("red") || color.equals("blue") || color.equals("orange") || color.equals("yellow")
						|| color.equals("green") || color.equals("violet"))
					return true;
			}
		}
		return false;
	}
}
