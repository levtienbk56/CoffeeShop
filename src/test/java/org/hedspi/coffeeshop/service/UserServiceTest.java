package org.hedspi.coffeeshop.service;

import static org.junit.Assert.*;
import org.hedspi.coffeeshop.domain.model.User;
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
public class UserServiceTest {
	@Autowired
	private UserService userService;

	@Before
	public void test0Setup() throws Exception {
		System.out.println("test0Setup");

		assertNotNull(userService);
		assertTrue(userService.deleteAll() >= 0);
		assertEquals(1, userService.insertUser(new User("admin", "testhrs131@@", false, "ADMIN")));
	}

	@Test
	public void test1Insert() {
		System.out.println("test1Insert");

		assertEquals(-1, userService.insertUser(new User(null, null, true, null)));
		assertEquals(-1, userService.insertUser(new User(null, null, true, "ADMIN")));
		assertEquals(-1, userService.insertUser(new User(null, null, true, "SELLER")));
		assertEquals(-1, userService.insertUser(new User(null, null, false, null)));
		assertEquals(-1, userService.insertUser(new User(null, null, false, "ADMIN")));
		assertEquals(-1, userService.insertUser(new User(null, null, false, "SELLER")));
		assertEquals(-1, userService.insertUser(new User(null, "", true, null)));
		assertEquals(-1, userService.insertUser(new User(null, "", true, "ADMIN")));
		assertEquals(-1, userService.insertUser(new User(null, "", true, "SELLER")));
		assertEquals(-1, userService.insertUser(new User(null, "", false, null)));
		assertEquals(-1, userService.insertUser(new User(null, "", false, "ADMIN")));
		assertEquals(-1, userService.insertUser(new User(null, "", false, "SELLER")));
		assertEquals(-1, userService.insertUser(new User(null, "testhrs126@@", true, null)));
		assertEquals(-1, userService.insertUser(new User(null, "testhrs127@@", true, "ADMIN")));
		assertEquals(-1, userService.insertUser(new User(null, "testhrs128@@", true, "SELLER")));
		assertEquals(-1, userService.insertUser(new User(null, "testhrs129@@", false, null)));
		assertEquals(-1, userService.insertUser(new User(null, "testhrs130@@", false, "ADMIN")));
		assertEquals(-1, userService.insertUser(new User(null, "testhrs131@@", false, "SELLER")));
		assertEquals(-1, userService.insertUser(new User("", null, true, null)));
		assertEquals(-1, userService.insertUser(new User("", null, true, "ADMIN")));
		assertEquals(-1, userService.insertUser(new User("", null, true, "SELLER")));
		assertEquals(-1, userService.insertUser(new User("", null, false, null)));
		assertEquals(-1, userService.insertUser(new User("", null, false, "ADMIN")));
		assertEquals(-1, userService.insertUser(new User("", null, false, "SELLER")));
		assertEquals(-1, userService.insertUser(new User("", "", true, null)));
		assertEquals(-1, userService.insertUser(new User("", "", true, "ADMIN")));
		assertEquals(-1, userService.insertUser(new User("", "", true, "SELLER")));
		assertEquals(-1, userService.insertUser(new User("", "", false, null)));
		assertEquals(-1, userService.insertUser(new User("", "", false, "ADMIN")));
		assertEquals(-1, userService.insertUser(new User("", "", false, "SELLER")));
		assertEquals(-1, userService.insertUser(new User("", "testhrs126@@", true, null)));
		assertEquals(-1, userService.insertUser(new User("", "testhrs127@@", true, "ADMIN")));
		assertEquals(-1, userService.insertUser(new User("", "testhrs128@@", true, "SELLER")));
		assertEquals(-1, userService.insertUser(new User("", "testhrs129@@", false, null)));
		assertEquals(-1, userService.insertUser(new User("", "testhrs130@@", false, "ADMIN")));
		assertEquals(-1, userService.insertUser(new User("", "testhrs131@@", false, "SELLER")));
		assertEquals(-1, userService.insertUser(new User("admin", null, true, null)));
		assertEquals(-1, userService.insertUser(new User("admin", null, true, "ADMIN")));
		assertEquals(-1, userService.insertUser(new User("admin", null, true, "SELLER")));
		assertEquals(-1, userService.insertUser(new User("admin", null, false, null)));
		assertEquals(-1, userService.insertUser(new User("admin", null, false, "ADMIN")));
		assertEquals(-1, userService.insertUser(new User("admin", null, false, "SELLER")));
		assertEquals(-1, userService.insertUser(new User("admin", "", true, null)));
		assertEquals(-1, userService.insertUser(new User("admin", "", true, "ADMIN")));
		assertEquals(-1, userService.insertUser(new User("admin", "", true, "SELLER")));
		assertEquals(-1, userService.insertUser(new User("admin", "", false, null)));
		assertEquals(-1, userService.insertUser(new User("admin", "", false, "ADMIN")));
		assertEquals(-1, userService.insertUser(new User("admin", "", false, "SELLER")));
		assertEquals(-1, userService.insertUser(new User("admin", "testhrs126@@", true, null)));
		assertEquals(-1, userService.insertUser(new User("admin", "testhrs127@@", true, "ADMIN")));
		assertEquals(-1, userService.insertUser(new User("admin", "testhrs128@@", true, "SELLER")));
		assertEquals(-1, userService.insertUser(new User("admin", "testhrs129@@", false, null)));
		assertEquals(-1, userService.insertUser(new User("admin", "testhrs130@@", false, "ADMIN")));
		assertEquals(-1, userService.insertUser(new User("admin", "testhrs131@@", false, "SELLER")));
		assertEquals(-1, userService.insertUser(new User("testhrs-126", null, true, null)));
		assertEquals(-1, userService.insertUser(new User("testhrs-127", null, true, "ADMIN")));
		assertEquals(-1, userService.insertUser(new User("testhrs-128", null, true, "SELLER")));
		assertEquals(-1, userService.insertUser(new User("testhrs-129", null, false, null)));
		assertEquals(-1, userService.insertUser(new User("testhrs-130", null, false, "ADMIN")));
		assertEquals(-1, userService.insertUser(new User("testhrs-131", null, false, "SELLER")));
		assertEquals(-1, userService.insertUser(new User("testhrs-135", "", true, null)));
		assertEquals(-1, userService.insertUser(new User("testhrs-136", "", true, "ADMIN")));
		assertEquals(-1, userService.insertUser(new User("testhrs-137", "", true, "SELLER")));
		assertEquals(-1, userService.insertUser(new User("testhrs-138", "", false, null)));
		assertEquals(-1, userService.insertUser(new User("testhrs-139", "", false, "ADMIN")));
		assertEquals(-1, userService.insertUser(new User("testhrs-140", "", false, "SELLER")));
		assertEquals(-1, userService.insertUser(new User("testhrs-144", "testhrs126@@", true, null)));
		assertEquals(1, userService.insertUser(new User("testhrs-145", "testhrs127@@", true, "ADMIN")));
		assertEquals(1, userService.insertUser(new User("testhrs-146", "testhrs128@@", true, "SELLER")));
		assertEquals(-1, userService.insertUser(new User("testhrs-147", "testhrs129@@", false, null)));
		assertEquals(1, userService.insertUser(new User("testhrs-148", "testhrs130@@", false, "ADMIN")));
		assertEquals(1, userService.insertUser(new User("testhrs-149", "testhrs131@@", false, "SELLER")));
	}

}
