package org.hedspi.coffeeshop.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {

	public static void main(String[] args) throws ParseException {
		SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
		Date d2 = ft.parse("2015-03-22");
		System.out.println(d2);
	}

}
