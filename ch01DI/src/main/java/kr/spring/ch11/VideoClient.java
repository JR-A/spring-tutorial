package kr.spring.ch11;

import java.util.Set;

public class VideoClient {
	//콜렉션 타입 프로퍼티(Set)
	private Set<Integer> subSet;

	//컨테이너가 Set<Integer>객체 생성 후 값들을 묶어 주입
	//Setter. 객체를 주입받는 메서드 (DI 프로퍼티 방식으로 의존 관계 설정)
	public void setSubSet(Set<Integer> subSet) {
		this.subSet = subSet;
	}

	@Override
	public String toString() {
		return "VideoClient [subSet=" + subSet + "]";
	}

}
