package kr.spring.ch04;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringMain {

	public static void main(String[] args) {
		//applicationContext.xml 설정파일을 읽어들여 IoC컨테이너를 생성
		AbstractApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		
		//@Resource 어노테이션을 이용한 프로퍼티 설정(bean객체의 '이름'과 프로퍼티명이 일치하면 의존 관계 주입)
		HomeController controller = (HomeController) context.getBean("homeController");
		
		System.out.println(controller);
		
		//어플리케이션 종료시 컨테이너에 존재하는 모든 Bean(객체)를 종료. 자원정리
		context.close();
	}

}
 