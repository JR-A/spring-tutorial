package kr.spring.ch01;

import org.springframework.beans.factory.annotation.Required;

/*
 * @Required 어노테이션을 이용한 필수 프로퍼티 검사
 * 메서드에만 표시! 프로퍼티에 표시 불가능함 
 */
public class Camera {
	//프로퍼티
	private int number;
	
	//Setter. 객체를 주입받는 메서드
	@Required
	public void setNumber(int number) {
		this.number = number;
	}

	@Override
	public String toString() {
		return "Camera [number=" + number + "]";
	}
	
}
