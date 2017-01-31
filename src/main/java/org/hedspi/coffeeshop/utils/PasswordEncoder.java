package org.hedspi.coffeeshop.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoder {
	private static BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

	public String encode(String raw) {
		if (raw == null || raw.equals("")) {
			return "";
		}

		return encoder.encode(raw);
	}

	public static boolean match(String raw, String hash) {
		return encoder.matches(raw, hash);
	}

}
