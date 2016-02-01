package org.hedspi.coffeeshop.controller;

import org.hedspi.coffeeshop.dao.UserDAO;
import org.hedspi.coffeeshop.dao.UserDAOImpl;
import org.hedspi.coffeeshop.model.User;

public class MainTest {

	public static void main(String[] args) {
	}

	private static void change(User user, String string) {
		user.setUsername(string);
		
	}
	
	

}
