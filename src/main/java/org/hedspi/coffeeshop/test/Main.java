package org.hedspi.coffeeshop.test;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {

	public static void main(String[] args) throws ParseException {
		SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
		Date d2 = new Date();
		Timestamp timestamp = new Timestamp(d2.getTime());

		System.out.println(Timestamp.valueOf(timestamp.toString()));
	}

}
