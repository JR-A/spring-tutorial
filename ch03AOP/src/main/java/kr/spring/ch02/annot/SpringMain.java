package kr.spring.ch02.annot;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import kr.spring.product.Product;

public class SpringMain {

	public static void main(String[] args) {
		//applicationContextAnnot.xml 설정파일을 읽어들여 IoC컨테이너를 생성 
		AbstractApplicationContext context = new ClassPathXmlApplicationContext("applicationContextAnnot.xml");

		//핵심 기능 실행
		Product p = (Product) context.getBean("product");

		p.launch();
		
		//어플리케이션 종료시 컨테이너에 존재하는 모든 Bean(객체)를 종료. 자원정리
		context.close();
	}

}
