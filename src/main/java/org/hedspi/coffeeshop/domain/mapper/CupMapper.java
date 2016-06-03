package org.hedspi.coffeeshop.domain.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.hedspi.coffeeshop.domain.model.Coffee;
import org.hedspi.coffeeshop.domain.model.Condiment;
import org.hedspi.coffeeshop.domain.model.Cup;
import org.springframework.jdbc.core.RowMapper;

public class CupMapper implements RowMapper<Cup> {

	public Cup mapRow(ResultSet rs, int arg1) throws SQLException {
		Cup cup = new Cup();

		cup.setId(rs.getInt("cups_id"));
		cup.setCoffee(new Coffee(rs.getInt("coffees_id")));
		cup.setSize(rs.getString("size"));

		// split condiments
		String str = rs.getString("condiments");
		if (!str.equals(null) && !str.equals("") && !str.equals("\'\'")) {
			// remove character
			str = str.replaceAll("[^ \\d]", "");

			String[] arr = str.trim().split(" ");
			for (int i = 0; i < arr.length; i++) {
				if (arr[i].equals(null) || arr[i].equals(""))
					break;
				cup.addCondiment(new Condiment(Integer.parseInt(arr[i])));
			}
		}

		return cup;
	}

}
