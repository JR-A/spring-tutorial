package kr.spring.ch03;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/*
 * @Autowired 타입 체크
 * 생성자, 필드(프로퍼티), 메서드에 지정 가능
 * 메서드에 지정할 때는 setXXX 뿐만 아니라 일반 메서드에도 적용 가능!
 * 
 * @Autowired 어노테이션은 타입을 이용해서 자동적으로 프로퍼티 값을 설정하기 때문에
 * 해당 타입의 빈 객체가 존재하지 않거나, 빈 객체가 두 개 이상 존재할 경우 스프링 @Autowired 어노테이션이 적용된
 * 빈 객체를 생성할 때 예외를 발생
 * 
 * @Autowired(required=false)로 지정하면 해당 타입의 빈 객체가 존재하지 않더라도 스프링은 예외를 발생하지 않음
 * 기본값은 @Autowired(required=true)
 */
/*	@Autowired 어노테이션 사용시 타입이 같은 객체가 여러 개 존재하면 오류 발생 테스트
	expected single matching bean but found 2: recorder1,recorder2  

	-> @Qualifier 어노테이션을 이용한 자동 설정 제한
*/
public class SystemMonitor2 {
	//프로퍼티
	@Autowired
	@Qualifier("main")  //부가적인 식별자
	private Recorder recorder;
	
	//Getters and Setters
	public Recorder getRecorder() {
		return recorder;
	}

	public void setRecorder(Recorder recorder) {
		this.recorder = recorder;
	}
	
}
