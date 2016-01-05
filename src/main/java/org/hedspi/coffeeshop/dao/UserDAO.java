package org.hedspi.coffeeshop.dao;

import org.hedspi.coffeeshop.model.User;

public interface UserDAO {
	void insert(User user);
	void delete(User user);
}
