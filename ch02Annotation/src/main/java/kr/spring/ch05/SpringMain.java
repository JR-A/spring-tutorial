package kr.spring.ch05;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringMain {
	public static void main(String[] args) {
		//applicationContextScan.xml 설정파일을 읽어들여 IoC컨테이너를 생성
		AbstractApplicationContext context = new ClassPathXmlApplicationContext("applicationContextScan.xml");
		
		//@Component 어노테이션을 클래스에 적용
		HomeController home = (HomeController) context.getBean("homeController");
		
		System.out.println(home);
		
		//어플리케이션 종료시 컨테이너에 존재하는 모든 Bean(객체)를 종료. 자원정리
		context.close();
	}
}
