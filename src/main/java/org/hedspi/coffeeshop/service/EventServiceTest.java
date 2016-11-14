package org.hedspi.coffeeshop.service;

import static org.junit.Assert.*;

import java.sql.Timestamp;
import java.util.Date;

import org.hedspi.coffeeshop.domain.model.Event;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "file:src/main/webapp/WEB-INF/spring-security.xml",
		"file:src/main/webapp/WEB-INF/data-source-cfg.xml", "file:src/main/webapp/WEB-INF/transaction-cfg.xml",
		"file:src/main/webapp/WEB-INF/mybatis-cfg.xml", "file:src/main/webapp/WEB-INF/servlet-context.xml" })
@WebAppConfiguration("file:src/main/webapp") // default
public class EventServiceTest {
	@Autowired
	private EventService eventService;
	private Event event;

	@Before
	public void setUp() throws Exception {
		Timestamp start = new Timestamp(new Date().getTime());
		Timestamp end = new Timestamp(new Date().getTime() + 1000 * 3600 * 24);
		event = new Event(0, "test junit", start, end, "red");
	}

	@Test
	public void testInsertEvent() {
		assertNotNull(eventService);
		assertNotNull(event);
		assertEquals(1, eventService.insertEvent(event));
	}

	@After
	public void tearDown() throws Exception {
		event = null;
		assertNull(event);
	}

}
