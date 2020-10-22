package kr.spring.product;

public class Product {
	//핵심기능 수행 메서드
	public String launch() {
		System.out.println("launch() 메서드 출력");
		
		//예외 발생시 호출되는 공통 기능 테스트
		//System.out.println(20/0);
		
		return "[상품 출시]";
	}
}
