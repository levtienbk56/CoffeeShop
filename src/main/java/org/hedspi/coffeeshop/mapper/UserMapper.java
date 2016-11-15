package org.hedspi.coffeeshop.mapper;

import java.util.List;

import org.hedspi.coffeeshop.domain.model.User;

public interface UserMapper {
	int insert(User user) throws Exception;

	int delete(String username) throws Exception;

	User select(String username) throws Exception;

	List<User> selectAll() throws Exception;

	int update(User user) throws Exception;
	
	int deleteAll() throws Exception;
}
