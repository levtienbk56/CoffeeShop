package org.hedspi.coffeeshop.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.hedspi.coffeeshop.domain.model.Condiment;
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
public class CondimentServiceTest {

	@Autowired
	CondimentService condimentService;
	static Condiment condiment;
	static int id;

	@BeforeClass
	public static void setUp() throws Exception {
		condiment = new Condiment(0, "test junit", 1.5, true);
		id = 0;
		assertNotNull(condiment);
	}

	@Test
	public void test1Insert() {
		assertEquals(1, condimentService.insert(condiment));
		id = condiment.getId();
	}

	@Test
	public void test2Update() {
		condiment.setName("test junit update");
		assertEquals(1, condimentService.update(condiment));
		assertEquals(0, condimentService.update(new Condiment(0, null, 0, false)));
		assertEquals(0, condimentService.update(new Condiment(1, "Zala", -1 , false)));
		
	}

	@Test
	public void test3Delete() {
		assertEquals(1, condimentService.delete(id));
		assertEquals(0, condimentService.delete(0));
	}

	@AfterClass
	public static void tearDown() {
		condiment = null;
	}
}
