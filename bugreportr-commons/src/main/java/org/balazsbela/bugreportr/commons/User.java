package org.balazsbela.bugreportr.commons;

import java.io.Serializable;

public class User implements Serializable {
	protected int userId;
	protected String username;
	protected String password;

	public User() {

	}

	public User(int userId,String username,String password) {
		this.userId = userId;
		this.username = username;
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	public String getUsername() {
		return username;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}
