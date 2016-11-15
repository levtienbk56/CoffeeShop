package org.hedspi.coffeeshop.domain.model;

public class User {
	private String username;
	private String password;
	private Boolean enabled = true;
	private String role;

	public User() {

	}

	public User(String username) {
		this.username = username;
	}

	/**
	 * 
	 * 
	 * @param username　ユーザネーム
	 * @param password　PWD
	 * @param enabled　有効フラグ
	 * @param role　役割
	 */
	public User(String username, String password, Boolean enabled, String role) {
		this.username = username;
		this.password = password;
		this.enabled = enabled;
		this.role = role;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public String toString() {
		return "{" + username + "," + password + "," + enabled + "," + role + "}";

	}

	@Override
	public boolean equals(Object other) {
		if (!(other instanceof User)) {
			return false;
		}

		User that = (User) other;
		return this.username.equals(that.username) && this.password.equals(that.password)
				&& (this.enabled == that.enabled) && this.role.equals(that.role);
	}
}
