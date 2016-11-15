package org.hedspi.coffeeshop.service;

import static org.junit.Assert.*;

import org.hedspi.coffeeshop.domain.model.Coffee;
import org.hedspi.coffeeshop.mapper.CoffeeMapper;
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
		coffee = new Coffee(0, "test junit", 100.5, true);
		id = 0;
		assertNotNull(coffee);
	}

	@Test
	public void test1Insert() {
		assertEquals(1, coffeeService.insert(coffee));
		id = coffee.getId();
	}

	@Test
	public void test2Update() {
		coffee.setName("test junit update");
		assertEquals(1, coffeeService.update(coffee));
	}

	@Test
	public void test3Delete() {
		assertEquals(1, coffeeService.delete(id));
	}

	@AfterClass
	public static void tearDown() {
		coffee = null;
	}
}
