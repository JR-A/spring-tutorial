package kr.spring.ch14;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringMain {

	public static void main(String[] args) {
		//applicationContext.xml 설정파일을 읽어들여 IoC컨테이너를 생성 
		AbstractApplicationContext context = new ClassPathXmlApplicationContext("applicationContext2.xml");

		//DI 프로퍼티 설정 방식 - 자동(ByType) : 프로퍼티 타입을 이용한 의존 관계 자동 설정
		//동일한 타입의 객체가 존재하면 오류 발생
		SystemMonitor monitor = (SystemMonitor)context.getBean("systemMonitor");
		
		System.out.println(monitor);

		//어플리케이션 종료시 컨테이너에 존재하는 모든 Bean(객체)를 종료. 자원정리
		context.close();
	}

}
