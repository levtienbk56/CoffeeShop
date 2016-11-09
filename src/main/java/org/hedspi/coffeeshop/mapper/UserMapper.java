package org.hedspi.coffeeshop.mapper;

import java.util.List;

import org.hedspi.coffeeshop.domain.model.User;

public interface UserMapper {
	int insert(User user);

	int delete(String username);

	User select(String username);

	List<User> selectAll();

	int update(User user);
}
