package org.hedspi.coffeeshop.service;

import static org.junit.Assert.assertEquals;

import org.hedspi.coffeeshop.domain.model.Event;
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

	@BeforeClass
	public static void setUp() throws Exception {
	}

	@Test
	public void test1Insert() {
		assertEquals(-1, eventService.insert(new Event(0, null, null, null, null)));
		assertEquals(-1, eventService.insert(new Event(0, null, null, null, "xyz1234")));
		assertEquals(-1, eventService.insert(new Event(0, null, null, null, "green")));
		assertEquals(-1, eventService.insert(new Event(0, null, null, "", null)));
		assertEquals(-1, eventService.insert(new Event(0, null, null, "", "xyz1234")));
		assertEquals(-1, eventService.insert(new Event(0, null, null, "", "green")));
		assertEquals(-1, eventService.insert(new Event(0, null, null, "2016-11-15 22:05:55", null)));
		assertEquals(-1, eventService.insert(new Event(0, null, null, "2016-11-15 22:05:55", "xyz1234")));
		assertEquals(-1, eventService.insert(new Event(0, null, null, "2016-11-15 22:05:55", "green")));
		assertEquals(-1, eventService.insert(new Event(0, null, "", null, null)));
		assertEquals(-1, eventService.insert(new Event(0, null, "", null, "xyz1234")));
		assertEquals(-1, eventService.insert(new Event(0, null, "", null, "green")));
		assertEquals(-1, eventService.insert(new Event(0, null, "", "", null)));
		assertEquals(-1, eventService.insert(new Event(0, null, "", "", "xyz1234")));
		assertEquals(-1, eventService.insert(new Event(0, null, "", "", "green")));
		assertEquals(-1, eventService.insert(new Event(0, null, "", "2016-11-15 22:05:55", null)));
		assertEquals(-1, eventService.insert(new Event(0, null, "", "2016-11-15 22:05:55", "xyz1234")));
		assertEquals(-1, eventService.insert(new Event(0, null, "", "2016-11-15 22:05:55", "green")));
		assertEquals(-1, eventService.insert(new Event(0, null, "2016-11-15 16:05:55", null, null)));
		assertEquals(-1, eventService.insert(new Event(0, null, "2016-11-15 16:05:55", null, "xyz1234")));
		assertEquals(-1, eventService.insert(new Event(0, null, "2016-11-15 16:05:55", null, "green")));
		assertEquals(-1, eventService.insert(new Event(0, null, "2016-11-15 16:05:55", "", null)));
		assertEquals(-1, eventService.insert(new Event(0, null, "2016-11-15 16:05:55", "", "xyz1234")));
		assertEquals(-1, eventService.insert(new Event(0, null, "2016-11-15 16:05:55", "", "green")));
		assertEquals(-1,
				eventService.insert(new Event(0, null, "2016-11-15 16:05:55", "2016-11-15 22:05:55", null)));
		assertEquals(-1,
				eventService.insert(new Event(0, null, "2016-11-15 16:05:55", "2016-11-15 22:05:55", "xyz1234")));
		assertEquals(-1,
				eventService.insert(new Event(0, null, "2016-11-15 16:05:55", "2016-11-15 22:05:55", "green")));
		assertEquals(-1, eventService.insert(new Event(0, "", null, null, null)));
		assertEquals(-1, eventService.insert(new Event(0, "", null, null, "xyz1234")));
		assertEquals(-1, eventService.insert(new Event(0, "", null, null, "green")));
		assertEquals(-1, eventService.insert(new Event(0, "", null, "", null)));
		assertEquals(-1, eventService.insert(new Event(0, "", null, "", "xyz1234")));
		assertEquals(-1, eventService.insert(new Event(0, "", null, "", "green")));
		assertEquals(-1, eventService.insert(new Event(0, "", null, "2016-11-15 22:05:55", null)));
		assertEquals(-1, eventService.insert(new Event(0, "", null, "2016-11-15 22:05:55", "xyz1234")));
		assertEquals(-1, eventService.insert(new Event(0, "", null, "2016-11-15 22:05:55", "green")));
		assertEquals(-1, eventService.insert(new Event(0, "", "", null, null)));
		assertEquals(-1, eventService.insert(new Event(0, "", "", null, "xyz1234")));
		assertEquals(-1, eventService.insert(new Event(0, "", "", null, "green")));
		assertEquals(-1, eventService.insert(new Event(0, "", "", "", null)));
		assertEquals(-1, eventService.insert(new Event(0, "", "", "", "xyz1234")));
		assertEquals(-1, eventService.insert(new Event(0, "", "", "", "green")));
		assertEquals(-1, eventService.insert(new Event(0, "", "", "2016-11-15 22:05:55", null)));
		assertEquals(-1, eventService.insert(new Event(0, "", "", "2016-11-15 22:05:55", "xyz1234")));
		assertEquals(-1, eventService.insert(new Event(0, "", "", "2016-11-15 22:05:55", "green")));
		assertEquals(-1, eventService.insert(new Event(0, "", "2016-11-15 16:05:55", null, null)));
		assertEquals(-1, eventService.insert(new Event(0, "", "2016-11-15 16:05:55", null, "xyz1234")));
		assertEquals(-1, eventService.insert(new Event(0, "", "2016-11-15 16:05:55", null, "green")));
		assertEquals(-1, eventService.insert(new Event(0, "", "2016-11-15 16:05:55", "", null)));
		assertEquals(-1, eventService.insert(new Event(0, "", "2016-11-15 16:05:55", "", "xyz1234")));
		assertEquals(-1, eventService.insert(new Event(0, "", "2016-11-15 16:05:55", "", "green")));
		assertEquals(-1,
				eventService.insert(new Event(0, "", "2016-11-15 16:05:55", "2016-11-15 22:05:55", null)));
		assertEquals(-1,
				eventService.insert(new Event(0, "", "2016-11-15 16:05:55", "2016-11-15 22:05:55", "xyz1234")));
		assertEquals(-1,
				eventService.insert(new Event(0, "", "2016-11-15 16:05:55", "2016-11-15 22:05:55", "green")));
		assertEquals(-1, eventService.insert(new Event(0, "歓迎会", null, null, null)));
		assertEquals(-1, eventService.insert(new Event(0, "歓迎会", null, null, "xyz1234")));
		assertEquals(-1, eventService.insert(new Event(0, "歓迎会", null, null, "green")));
		assertEquals(-1, eventService.insert(new Event(0, "歓迎会", null, "", null)));
		assertEquals(-1, eventService.insert(new Event(0, "歓迎会", null, "", "xyz1234")));
		assertEquals(-1, eventService.insert(new Event(0, "歓迎会", null, "", "green")));
		assertEquals(-1, eventService.insert(new Event(0, "歓迎会", null, "2016-11-15 22:05:55", null)));
		assertEquals(-1, eventService.insert(new Event(0, "歓迎会", null, "2016-11-15 22:05:55", "xyz1234")));
		assertEquals(-1, eventService.insert(new Event(0, "歓迎会", null, "2016-11-15 22:05:55", "green")));
		assertEquals(-1, eventService.insert(new Event(0, "歓迎会", "", null, null)));
		assertEquals(-1, eventService.insert(new Event(0, "歓迎会", "", null, "xyz1234")));
		assertEquals(-1, eventService.insert(new Event(0, "歓迎会", "", null, "green")));
		assertEquals(-1, eventService.insert(new Event(0, "歓迎会", "", "", null)));
		assertEquals(-1, eventService.insert(new Event(0, "歓迎会", "", "", "xyz1234")));
		assertEquals(-1, eventService.insert(new Event(0, "歓迎会", "", "", "green")));
		assertEquals(-1, eventService.insert(new Event(0, "歓迎会", "", "2016-11-15 22:05:55", null)));
		assertEquals(-1, eventService.insert(new Event(0, "歓迎会", "", "2016-11-15 22:05:55", "xyz1234")));
		assertEquals(-1, eventService.insert(new Event(0, "歓迎会", "", "2016-11-15 22:05:55", "green")));
		assertEquals(-1, eventService.insert(new Event(0, "歓迎会", "2016-11-15 16:05:55", null, null)));
		assertEquals(-1, eventService.insert(new Event(0, "歓迎会", "2016-11-15 16:05:55", null, "xyz1234")));
		assertEquals(-1, eventService.insert(new Event(0, "歓迎会", "2016-11-15 16:05:55", null, "green")));
		assertEquals(-1, eventService.insert(new Event(0, "歓迎会", "2016-11-15 16:05:55", "", null)));
		assertEquals(-1, eventService.insert(new Event(0, "歓迎会", "2016-11-15 16:05:55", "", "xyz1234")));
		assertEquals(-1, eventService.insert(new Event(0, "歓迎会", "2016-11-15 16:05:55", "", "green")));
		assertEquals(-1,
				eventService.insert(new Event(0, "歓迎会", "2016-11-15 16:05:55", "2016-11-15 22:05:55", null)));
		assertEquals(-1,
				eventService.insert(new Event(0, "歓迎会", "2016-11-15 16:05:55", "2016-11-15 22:05:55", "xyz1234")));
		assertEquals(1,
				eventService.insert(new Event(0, "歓迎会", "2016-11-15 16:05:55", "2016-11-15 22:05:55", "green")));

	}

}
