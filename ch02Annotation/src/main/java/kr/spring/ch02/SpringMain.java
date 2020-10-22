package kr.spring.ch02;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringMain {

	public static void main(String[] args) {
		//applicationContext.xml 설정파일을 읽어들여 IoC컨테이너를 생성
		AbstractApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		
		//@Autowired 어노테이션을 이용한 자동 설정(bean객체의 타입이 일치하면 의존 객체 주입)
		SystemMonitor monitor = (SystemMonitor) context.getBean("monitor");
		
		System.out.println(monitor.getPeriodTime() + ", " + monitor.getSender());
		
		//어플리케이션 종료시 컨테이너에 존재하는 모든 Bean(객체)를 종료. 자원정리
		context.close();
	}

}
