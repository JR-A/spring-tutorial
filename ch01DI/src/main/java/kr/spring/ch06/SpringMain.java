package kr.spring.ch06;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import kr.spring.ch05.SystemMonitor;

public class SpringMain {

	public static void main(String[] args) {
		//applicationContext.xml 설정파일을 읽어들여 IoC컨테이너를 생성 
		AbstractApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

		//DI 프로퍼티 설정 방식
		RegisterService registerService = (RegisterService)context.getBean("registerService");
		//의존 관계 제대로 설정해놓지 않으면 메서드 호출시 에러(객체 생성은 됨) - 생성자방식은 설정안해놓으면 객체 생성시 에러
		registerService.write();

		//어플리케이션 종료시 컨테이너에 존재하는 모든 Bean(객체)를 종료. 자원정리
		context.close();
	}

}
