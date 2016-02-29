package org.hedspi.coffeeshop.dao;

import java.util.List;

import javax.sql.DataSource;

import org.hedspi.coffeeshop.mapper.EventMapper;
import org.hedspi.coffeeshop.model.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class EventDAOImpl extends JdbcDaoSupport implements EventDAO {
	@Autowired
	public EventDAOImpl(DataSource dataSource) {
		this.setDataSource(dataSource);
	}

	public int insert(Event event) {
		String sql = "INSERT INTO events(title,time_start,time_end,color) VALUES(?,?,?,?)";
		Object[] params = new Object[] { event.getTitle(), event.getStart(), event.getEnd(), event.getColor() };
		try {
			return this.getJdbcTemplate().update(sql, params);
		} catch (CannotGetJdbcConnectionException e) {
			e.printStackTrace();
			return -1;
		} catch (DuplicateKeyException e) {
			e.printStackTrace();
			return 0;
		}
	}

	public int delete(int id) {
		String sql = "DELETE FROM events WHERE event_id = ?";
		Object[] params = new Object[] { id };
		try {
			return this.getJdbcTemplate().update(sql, params);
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

	public List<Event> selectAll() {
		String sql = "SELECT * FROM events  ORDER BY event_id";
		EventMapper mapper = new EventMapper();
		try {
			return this.getJdbcTemplate().query(sql, mapper);
		} catch (CannotGetJdbcConnectionException e) {
			e.printStackTrace();
			return null;
		}
	}

	public int update(Event event) {
		String sql = "UPDATE events SET title=?,time_start=?,time_end=?,color=? WHERE event_id=?";
		Object[] params = new Object[] { event.getTitle(), event.getStart(), event.getEnd(), event.getColor() };
		try {
			return this.getJdbcTemplate().update(sql, params);
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

}
