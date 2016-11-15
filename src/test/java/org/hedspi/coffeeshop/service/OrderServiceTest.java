package org.hedspi.coffeeshop.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.hedspi.coffeeshop.domain.model.Order;
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
public class OrderServiceTest {
	@Autowired
	private OrderService orderService;
	private static Order order;
	private static int id;

	@BeforeClass
	public static void setUp() throws Exception {
		order = new Order(0, "test JUnit", new Date(), 999.9);
		id = 0;
		assertNotNull(order);
	}

	@Test
	public void test1Insert() {
		// username not exist
		assertFalse(orderService.insertOrder(order) > 0);

		// username exist
		order.getUser().setUsername("quyvd");
		assertTrue(orderService.insertOrder(order) > 0);

		id = order.getId();
	}

	@Test
	public void test2Update() {
		assertEquals(1, orderService.updateOrderPrice(id, 1234.5));

		// validate param false
		assertFalse(orderService.updateOrderPrice(id, -1234) > 0);
		assertFalse(orderService.updateOrderPrice(-123, 1234.5) > 0);
	}

	@AfterClass
	public static void tearDown() throws Exception {
		order = null;
		assertNull(order);
	}

}
