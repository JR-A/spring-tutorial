package kr.spring.ch05;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/*
 * <context:component-scan> 태그를 추가하면 스프링은 지정한 패키지에서 
 * @Component어노테이션(혹은 하위 어노테이션)이 적용된 클래스를 검색하여 빈으로 등록
 * 우리가 빈 정의할 필요가 없어진다!
 * 
 * 자동 등록된 아이디(빈객체 name)는 클래스 이름의 첫글자를 소문자로 바꾼 것
 * ex) HomeController클래스의 빈 객체 이름 -> homeController
 * 
 * 빈의 이름을 지정하고 싶으면 @Component("home")과 같이 명시 or
 * @Component
 * @named("home") 명시
 */
@Component
public class HomeController {
	//Camera 클래스에 의존
	//자동으로 만들어진 빈 객체를 타입체크하여 
	@Autowired
	private Camera camera;
	
	//Setter. 객체를 주입받을 메서드
	public void setCamera(Camera camera) {
		this.camera = camera;
	}

	@Override
	public String toString() {
		return "HomeController [camera=" + camera + "]";
	}
	
}
