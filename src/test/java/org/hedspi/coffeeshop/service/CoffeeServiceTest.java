package org.hedspi.coffeeshop.service;

import static org.junit.Assert.assertEquals;

import org.hedspi.coffeeshop.domain.model.Coffee;
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
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@ContextConfiguration({ "file:src/main/webapp/WEB-INF/spring-security.xml",
		"file:src/main/webapp/WEB-INF/data-source-cfg.xml", "file:src/main/webapp/WEB-INF/transaction-cfg.xml",
		"file:src/main/webapp/WEB-INF/mybatis-cfg.xml", "file:src/main/webapp/WEB-INF/servlet-context.xml" })
@WebAppConfiguration("file:src/main/webapp") // default
public class CoffeeServiceTest {

	@Autowired
	CoffeeService coffeeService;
	static Coffee coffee;
	static int id;

	@BeforeClass
	public static void setUp() throws Exception {
	}

	@Test
	public void test1Insert() {
		assertEquals(-1, coffeeService.insert(new Coffee(0, null, 123.6, true)));
		assertEquals(-1, coffeeService.insert(new Coffee(0, null, 123.7, false)));
		assertEquals(-1, coffeeService.insert(new Coffee(0, "", 123.8, true)));
		assertEquals(-1, coffeeService.insert(new Coffee(0, "", 123.9, false)));
		assertEquals(1, coffeeService.insert(new Coffee(0, "junit test", 123.10, true)));
		assertEquals(-1, coffeeService.insert(new Coffee(0, "junit test", 123.11, true)));
		assertEquals(1, coffeeService.insert(new Coffee(0, "junit test 2", 123.10, false)));
		assertEquals(-1, coffeeService.insert(new Coffee(0, "junit test 2", 123.11, false)));
	}

}
