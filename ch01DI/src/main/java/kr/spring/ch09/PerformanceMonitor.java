package kr.spring.ch09;

import java.util.List;

public class PerformanceMonitor {
	//콜렉션 타입 프로퍼티(List)
	private List<Double> deviations;

	//컨테이너가 List<Double>객체 생성 후 값들을 묶어 주입
	//Setter. 객체를 주입받는 메서드 (DI 프로퍼티 방식으로 의존 관계 설정)
	public void setDeviations(List<Double> deviations) {
		this.deviations = deviations;
	}

	@Override
	public String toString() {
		return "PerformanceMonitor [deviations=" + deviations + "]";
	}

}
