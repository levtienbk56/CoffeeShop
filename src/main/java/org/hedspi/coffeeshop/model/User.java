package org.hedspi.coffeeshop.model;

public class User {
	private String username;
	private String password;
	private boolean enabled = true;
	private String role;

	public User() {

	}

	public User(String username) {
		this.username = username;
	}

	public User(String username, String password, boolean enabled, String role) {
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

	public void setUserName(String userName) {
		this.username = userName;
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

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
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
