package kr.spring.ch01.service;

import kr.spring.ch01.model.LoginCommand;

//DB연동되었다고 가정
public class LoginService {
	public void checkLogin(LoginCommand loginCommand) throws LoginCheckException {
		//테스트 용도로 id와 password가 일치하면 로그인 성공으로 간주
		//id와 password의 문자열이 불일치하는경우 로그인 실패
		if(!loginCommand.getId().equals(loginCommand.getPassword())) {
			System.out.println("인증 에러 - " + loginCommand.getId());
			
			throw new LoginCheckException("Invalid Id of Password");		//사용자 정의 예외 던짐
			
		}
	}
}
