package kr.spring.ch04;

public class Camera {
	//프로퍼티
	private int number;
	
	//Setter. 객체를 주입받는 메서드
	public void setNumber(int number) {
		this.number = number;
	}

	@Override
	public String toString() {
		return "Camera [number=" + number + "]";
	}
	
}
