package kr.spring.ch07.model;

public class LoginCommand {
	private String userId;
	private String password;
	
	//Getters and Setters
	public String getUserId() {
		return userId;
	}
	public String getPassword() {
		return password;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public String toString() {
		return "LoginCommand [userId=" + userId + ", password=" + password + "]";
	}
	
}
