package kr.spring.ch06;

public class RegisterService {
	//프로퍼티
	private RegisterDAO registerDAO;
	
	//RegisterService는 RegisterDAO에 의존!
	//Setter. 객체를 주입받는 메서드
	public void setRegisterDAO(RegisterDAO registerDAO) {
		this.registerDAO = registerDAO;
	}
	
	public void write() {
		System.out.println("RegisterService의 write() 메서드 실행");
		
		registerDAO.insert();
	}
}
