package org.hedspi.coffeeshop.domain.dao;

import java.util.List;

import org.hedspi.coffeeshop.domain.model.User;

public interface UserDAOTemp {
	int insert(User user);
	int delete(String username);
	User select(String username);
	List<User> selectAll();
	int update(User user);
}
