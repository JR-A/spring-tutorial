package kr.spring.ch15;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/*
 * 스프링 빈의 범위 설정
 * singleton	스프링 컨테이너에 한 개의 Bean 객체만 존재(기본값). 여러 번 호출해도 컨테이너에 만들어진 하나의 객체만 불러옴(hash값이 같음)
 * prototype	빈을 사용할 때마다 객체 생성
 */
public class SpringMain {

	public static void main(String[] args) {
		//applicationContext.xml 설정파일을 읽어들여 IoC컨테이너를 생성 
		AbstractApplicationContext context = new ClassPathXmlApplicationContext("applicationContext2.xml");
		
		//빈 객체 범위 설정 : 서로 다른 범위 빈에 대한 의존 처리
		Executor executor = (Executor) context.getBean("executor");
		System.out.println(executor + ", " + executor.getWorker());
		System.out.println("--------------------------------------");
		
		Executor executor2 = (Executor) context.getBean("executor");
		System.out.println(executor2 + ", " + executor2.getWorker());
		
		//어플리케이션 종료시 컨테이너에 존재하는 모든 Bean(객체)를 종료. 자원정리
		context.close();
	}

}
