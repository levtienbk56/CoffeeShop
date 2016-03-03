package org.hedspi.coffeeshop.controller;

import java.util.Arrays;
import java.util.List;

public class MainTest {

	public static void main(String[] args) {
		String str = "[3, 4, 5]";
		str = str.replace("[", "");
		str =str.replace("]", "");
		List<String> list = Arrays.asList(str.split("\\s*,\\s*"));
		for(String s: list){
			System.out.println(s);
		}
	}

}
