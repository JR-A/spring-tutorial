package kr.spring.ch12;

import java.util.Properties;

public class BookClient {
	//콜렉션 타입 프로퍼티(Properties)
	private Properties prop;
	
	//컨테이너가 Properties객체 생성 후 값들을 묶어 주입
	//Setter. 객체를 주입받는 메서드 (DI 프로퍼티 방식으로 의존 관계 설정)
	public void setProp(Properties prop) {
		this.prop = prop;
	}

	@Override
	public String toString() {
		return "BookClient [prop=" + prop + "]";
	}
	
}

