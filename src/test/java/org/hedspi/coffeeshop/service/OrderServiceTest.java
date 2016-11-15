package org.hedspi.coffeeshop.service;

import static org.junit.Assert.assertEquals;

import org.hedspi.coffeeshop.domain.model.Order;
import org.junit.Before;
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

	@Before
	public void test0SetUp() throws Exception {
	}

	@Test
	public void test1Insert() {
		assertEquals(-1, orderService.insertOrder(new Order(0, null, null, null)));
		assertEquals(-1, orderService.insertOrder(new Order(0, null, null, 10.5)));
		assertEquals(-1, orderService.insertOrder(new Order(0, null, "", null)));
		assertEquals(-1, orderService.insertOrder(new Order(0, null, "", 10.5)));
		assertEquals(-1, orderService.insertOrder(new Order(0, null, "2016-11-15 16:05:55", null)));
		assertEquals(-1, orderService.insertOrder(new Order(0, null, "2016-11-15 16:05:55", 10.5)));
		assertEquals(-1, orderService.insertOrder(new Order(0, "", null, null)));
		assertEquals(-1, orderService.insertOrder(new Order(0, "", null, 10.5)));
		assertEquals(-1, orderService.insertOrder(new Order(0, "", "", null)));
		assertEquals(-1, orderService.insertOrder(new Order(0, "", "", 10.5)));
		assertEquals(-1, orderService.insertOrder(new Order(0, "", "2016-11-15 16:05:55", null)));
		assertEquals(-1, orderService.insertOrder(new Order(0, "", "2016-11-15 16:05:55", 10.5)));
		assertEquals(-1, orderService.insertOrder(new Order(0, "test not exist seller", null, null)));
		assertEquals(-1, orderService.insertOrder(new Order(0, "test not exist seller", null, 10.5)));
		assertEquals(-1, orderService.insertOrder(new Order(0, "test not exist seller", "", null)));
		assertEquals(-1, orderService.insertOrder(new Order(0, "test not exist seller", "", 10.5)));
		assertEquals(-1, orderService.insertOrder(new Order(0, "test not exist seller", "2016-11-15 16:05:55", null)));
		assertEquals(-1, orderService.insertOrder(new Order(0, "test not exist seller", "2016-11-15 16:05:55", 10.5)));
		assertEquals(-1, orderService.insertOrder(new Order(0, "seller", null, null)));
		assertEquals(-1, orderService.insertOrder(new Order(0, "seller", null, 10.5)));
		assertEquals(-1, orderService.insertOrder(new Order(0, "seller", "", null)));
		assertEquals(-1, orderService.insertOrder(new Order(0, "seller", "", 10.5)));
		assertEquals(-1, orderService.insertOrder(new Order(0, "seller", "2016-11-15 16:05:55", null)));
		assertEquals(1, orderService.insertOrder(new Order(0, "seller", "2016-11-15 16:05:55", 10.5)));

	}
}
