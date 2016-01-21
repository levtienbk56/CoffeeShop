package org.hedspi.coffeeshop.dao;

import org.hedspi.coffeeshop.model.User;

public interface UserDAO {
	int insert(User user);
	void delete(User user);
	User getUser(String username);
}
