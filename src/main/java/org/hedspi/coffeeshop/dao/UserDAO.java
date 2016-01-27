package org.hedspi.coffeeshop.dao;

import java.util.List;

import org.hedspi.coffeeshop.model.User;

public interface UserDAO {
	int insert(User user);
	void delete(User user);
	User selectUser(String username);
	List<User> selectAll();
}
