package kr.spring.ch03;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

//@Autowired 어노테이션 사용시 타입이 같은 객체가 여러 개 존재하면 오류 발생 테스트
//expected single matching bean but found 2: recorder1,recorder2  

// -> @Qualifier 어노테이션을 이용한 자동 설정 제한
public class SpringMain {
	public static void main(String[] args) {
		//applicationContext.xml 설정파일을 읽어들여 IoC컨테이너를 생성
		AbstractApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

		//@Autowired 어노테이션을 이용한 자동 설정(bean객체의 '타입'과 프로퍼티 타입이 일치하면 의존 객체 주입)
		SystemMonitor2 monitor = (SystemMonitor2) context.getBean("monitor2");

		System.out.println(monitor.getRecorder());

		//어플리케이션 종료시 컨테이너에 존재하는 모든 Bean(객체)를 종료. 자원정리
		context.close();
	}
}
