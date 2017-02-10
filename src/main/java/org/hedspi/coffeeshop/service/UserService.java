package org.hedspi.coffeeshop.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hedspi.coffeeshop.common.Constant;
import org.hedspi.coffeeshop.controller.MainController;
import org.hedspi.coffeeshop.domain.model.User;
import org.hedspi.coffeeshop.mapper.UserMapper;
import org.hedspi.coffeeshop.utils.PasswordEncoder;
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
			if (user.getPassword() != null && user.getPassword().length() >= 4) {
				try {
					PasswordEncoder encoder = new PasswordEncoder();
					user.setPassword(encoder.encode(user.getPassword()));
					return userMapper.insert(user);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return -1;
	}

	/**
	 * 更新されるユーザーは「ROLE_ADMIN」の場合は、SUPERVISORだけ更新できます。
	 * または、SUPERVISORを更新できない。
	 * 
	 * @param user 更新されるユーザー
	 * @return 1: 成功<br>
	 *         0: ユーザーが見つかない<br>
	 *         -1: エラー発生<br>
	 */
	public int updateUser(User user) {
		logger.entry(user);

		if (validateBefore(user)) {
			try {
				boolean updatable = false;

				// 当座ユーザーを取り込む
				String username = MainController.getUserName();
				User currentAdmin = selectUser(username);
				// 更新したいユーザーを取り込む
				User updateUser = selectUser(user.getUsername());
				if (updateUser == null || currentAdmin == null) {
					return -1;
				}

				// 更新されるユーザーはAdminの場合：
				if (updateUser.getRole().equals(Constant.ROLE_ADMIN)) {
					if (currentAdmin.getUsername().equals(Constant.SUPERVISOR) && !updateUser.getUsername().equals(Constant.SUPERVISOR)) {
						updatable = true;
					} else {
						return 999;
					}
				} else if (updateUser.getRole().equals(Constant.ROLE_SELLER)) {
					updatable = true;
				}

				if (updatable) {
					return userMapper.update(user);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return -1;
	}

	public int changePassword(User user) {
		logger.entry(user);
		if (user.getUsername() != null && user.getPassword() != null && user.getPassword().length() >= 4) {
			try {
				PasswordEncoder encoder = new PasswordEncoder();
				user.setPassword(encoder.encode(user.getPassword()));
				return userMapper.changePassword(user);
			} catch (Exception ex) {
				ex.printStackTrace();
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

	/**
	 * パスワードは空にする。
	 * empty password before response to client
	 * 
	 * @return all user
	 */
	public List<User> selectAll() {
		try {
			List<User> users = userMapper.selectAll();
			for (User user : users) {
				user.setPassword("");
			}

			return users;
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
		if (user != null && user.getUsername() != null && user.getRole() != null
				&& user.getUsername().length() * user.getRole().length() > 0) {

			// validate ROLE
			if (user.getRole().equals("ADMIN") || user.getRole().equals("SELLER"))
				return true;
		}
		return false;
	}
}
