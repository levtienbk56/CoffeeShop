package org.hedspi.coffeeshop.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hedspi.coffeeshop.domain.model.User;
import org.hedspi.coffeeshop.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	private static final Logger logger = LogManager.getLogger(UserService.class);
	@Autowired
	UserMapper userMapper;

	/**
	 * 
	 * @param user
	 * @return 1: success <br>
	 *         0: no record inserted<br>
	 *         -1: error<br>
	 */
	public int insertUser(User user) {
		logger.entry(user);
		if (validateBefore(user)) {
			try {
				return userMapper.insert(user);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return -1;
	}

	/**
	 * 
	 * @param user
	 * @return 1: success<br>
	 *         0: no record updated<br>
	 *         -1: error<br>
	 */
	public int updateUser(User user) {
		logger.entry(user);

		if (validateBefore(user)) {
			try {
				return userMapper.update(user);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return -1;
	}

	public User selectUser(String username) {
		if (username != null && !username.equals(""))
			try {
				return userMapper.select(username);
			} catch (Exception e) {
				e.printStackTrace();
			}
		return null;
	}

	public List<User> selectAll() {
		try {
			return userMapper.selectAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public int deleteUser(String username) {
		if (username != null && !username.equals(""))
			try {
				return userMapper.delete(username);
			} catch (Exception e) {
				e.printStackTrace();
			}
		return -1;
	}

	/**
	 * 
	 * @return >0: success <br>
	 *         =0: no record found <br>
	 *         -1: error<br>
	 */
	public int deleteAll() {
		try {
			return userMapper.deleteAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

	private boolean validateBefore(User user) {
		if (user != null && user.getUsername() != null && user.getPassword() != null && user.getRole() != null
				&& user.getUsername().length() * user.getPassword().length() * user.getRole().length() > 0) {

			// validate ROLE
			if (user.getRole().equals("ADMIN") || user.getRole().equals("SELLER"))
				return true;
		}
		return false;
	}
}
