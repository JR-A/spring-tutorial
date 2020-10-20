package kr.spring.ch12;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/*
 * 콜렉션 타입 프로퍼티 설정
 * 태그		컬렉션타입
 * <list>   java.util.List, 자바배열		배열
 * <map>	java.util.Map				<key, value>		- key와 value는 모든 타입가능
 * <set>	java.util.Set				집합
 * <props>	java.util.Properties		<프로퍼티이름, 프로퍼티값> - key와 value가 모두 String. 제네릭사용x
 */
public class SpringMain {

	public static void main(String[] args) {
		//applicationContext.xml 설정파일을 읽어들여 IoC컨테이너를 생성 
		AbstractApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

		//DI 프로퍼티 설정 방식 - 콜렉션 타입(Properties) 프로퍼티 설정
		BookClient bookClient = (BookClient) context.getBean("bookClient");
		
		System.out.println(bookClient);
		
		//어플리케이션 종료시 컨테이너에 존재하는 모든 Bean(객체)를 종료. 자원정리
		context.close();
	}

}
