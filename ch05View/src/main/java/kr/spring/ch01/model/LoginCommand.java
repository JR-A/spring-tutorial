package kr.spring.ch01.model;

public class LoginCommand {
	//프로퍼티
	private String id;
	private String password;
	
	//Getters and Setters
	public String getId() {
		return id;
	}
	public String getPassword() {
		return password;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
