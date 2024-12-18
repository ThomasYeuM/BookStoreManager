package model;

import java.io.Serializable;

public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private String username;
	private String email;
	private String password;
	private String role;
	private boolean isVerify;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isVerify() {
		return isVerify;
	}
	public void setVerify(boolean isVerify) {
		this.isVerify = isVerify;
	}
	public User(int id, String username, String email, String password, String role, boolean isVerify) {
		super();
		this.id = id;
		this.username = username;
		this.email = email;
		this.password = password;
		this.role=role;
		this.isVerify = isVerify;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", email=" + email + ", password=" + password
				+ ", role=" + role + ", isVerify=" + isVerify + "]";
	}
	
	
	
}
