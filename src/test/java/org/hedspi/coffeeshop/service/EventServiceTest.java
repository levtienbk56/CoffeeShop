package org.hedspi.coffeeshop.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.sql.Timestamp;
import java.util.Date;

import org.hedspi.coffeeshop.domain.model.Event;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING) // test method by order
@ContextConfiguration({ "file:src/main/webapp/WEB-INF/spring-security.xml",
		"file:src/main/webapp/WEB-INF/data-source-cfg.xml", "file:src/main/webapp/WEB-INF/transaction-cfg.xml",
		"file:src/main/webapp/WEB-INF/mybatis-cfg.xml", "file:src/main/webapp/WEB-INF/servlet-context.xml" })
@WebAppConfiguration("file:src/main/webapp") // default
public class EventServiceTest {
	@Autowired
	private EventService eventService;
	private static Event event;
	private static int id;
	private static Timestamp start;
	private static Timestamp end;

	@BeforeClass
	public static void setUp() throws Exception {
		start = new Timestamp(new Date().getTime());
		end = new Timestamp(new Date().getTime() + 1000 * 3600 * 24);
		event = new Event(0, "test junit", start, end, "red");
		id = 0;

		assertNotNull(event);
	}

	@Test
	public void test1Insert() {
		assertEquals(1, eventService.insertEvent(event));
		id = event.getId();

		assertEquals(0, eventService.insertEvent(new Event(0, "", null, null, "")));
		assertEquals(0, eventService.insertEvent(new Event(0, "test junit", start, end, "")));
		assertEquals(0, eventService.insertEvent(new Event(0, "", start, end, "test junit")));
	}

	@Test
	public void test2Update() {
		assertEquals(1, eventService.updateEvent(event));
		assertEquals(0, eventService.updateEvent(new Event(-1, "", null, null, "")));
		assertEquals(0, eventService.updateEvent(new Event(0, "test junit", start, end, "")));
		assertEquals(0, eventService.updateEvent(new Event(999999, "", start, end, "test junit")));
	}

	@Test
	public void test3Delete() {
		assertEquals(1, eventService.deleteEvent(id));
		assertEquals(0, eventService.deleteEvent(0));
		assertEquals(0, eventService.deleteEvent(1000000));
	}

	@AfterClass
	public static void tearDown() throws Exception {
		event = null;
		assertNull(event);
	}

}
