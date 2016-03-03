package org.hedspi.coffeeshop.controller;

import java.util.ArrayList;
import java.util.List;

import org.hedspi.coffeeshop.model.User;

public class MainTest {

	public static void main(String[] args) {
		List<User> arr = new ArrayList<User>();
		User u = new User("sdd", "sss",	 true, "ADMIN");
		arr.add(u);
		u.setUsername("aaaaaaaaaaa");
		arr.add(u);
		System.out.println(arr);
	}

}
