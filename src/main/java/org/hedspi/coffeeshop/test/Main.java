package org.hedspi.coffeeshop.test;

import java.util.ArrayList;
import java.util.List;

import org.hedspi.coffeeshop.domain.model.User;

public class Main {

	public static void main(String[] args) {
		User a = new User("aaa");
		List<User> list = new ArrayList<>();
		list.add(a);
		a.setUsername("bbb");
		System.out.println(list.get(0).getUsername());
		
	}

}
