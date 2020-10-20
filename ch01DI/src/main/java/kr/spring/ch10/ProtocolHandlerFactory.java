package kr.spring.ch10;

import java.util.Map;

public class ProtocolHandlerFactory {
	//콜렉션 타입 프로퍼티(Map)
	private Map<String, Object> map;
	
	//컨테이너가 Map<String, Object>객체 생성 후 값들을 묶어 주입
	//Setter. 객체를 주입받는 메서드 (DI 프로퍼티 방식으로 의존 관계 설정)
	public void setMap(Map<String, Object> map) {
		this.map = map;
	}

	@Override
	public String toString() {
		return "ProtocolHandlerFactory [map=" + map + "]";
	}

}
