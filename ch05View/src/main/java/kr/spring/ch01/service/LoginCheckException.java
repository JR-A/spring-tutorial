package kr.spring.ch01.service;

//사용자 정의 예외
public class LoginCheckException extends Exception {
	
	public LoginCheckException(String message) {
		super(message);
	}
}
