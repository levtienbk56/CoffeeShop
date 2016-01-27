package org.hedspi.coffeeshop.controller;

import org.hedspi.coffeeshop.model.User;

public class MainTest {

	public static void main(String[] args) {
	User user = new User("aaa", "sdada", true, "ADMIN");
	change(user, "bbb");
	System.out.println(user.toString());

	}

	private static void change(User user, String string) {
		user.setUsername(string);
		
	}
	
	

}
