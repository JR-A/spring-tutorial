package kr.spring.ch01.pojo;

import org.aspectj.lang.ProceedingJoinPoint;

/*
 * Advice : 언제 공통 기능을 핵심 로직에 적용할 것인지를 정의
 * 
 * 구현 가능한 Advice 종류
 * 		종류								설명
 * Before Advice			대상 객체의 메서드 호출 전에 공통 기능 실행
 * After Returning Advice	대상 객체의 메서드가 예외없이 실행(전제조건)된 이후에 공통 기능 실행
 * After Throwing Advice	대상 객체의 메서드가 실행 도중 예외를 반환하고 종료하는 경우에 공통 기능 실행
 * After Advice				대상 객체의 메서드를 실행하는 도중 예외 발생 여부와 상관없이 메서드 실행 후 공통 기능 실행
 * 							(try-catch-finally의 finally 블럭과 비슷)
 * Around Advice			대상 객체의 메서드 실행 전, 후 또는 예외 발생 시점에 공통 기능 실행
 * 
 */

public class MyFirstAdvice {
	//공통기능 수행 메서드
	//핵심기능메서드 시작 직전에 동작하는 메서드
	public void before() {
		System.out.println("Hello Before ! **핵심기능 메서드가 호출되기 전에 나온다!");
	}
	//공통기능 수행 메서드
	//핵심기능메서드 호출이 예외를 반환하지 않고 종료했을 때 동작하는 메서드
	public void afterReturning(String msg) {
		System.out.println("Hello After Returning ! **핵심기능 메서드가 호출된 후에 나온다! 전달된 객체: " + msg);
	}
	//공통기능 수행 메서드
	//핵심기능메서드가 호출이 예외를 던졌을 때 동작하는 메서드
	public void afterThrowing(Throwable ex) {
		System.out.println("Hello After Throwing ! **예외가 생기면 나온다! " + ex);
	}
	//공통기능 수행 메서드
	//핵심기능메서드가 실행 도중 예외 발생 여부에 상관없이 메서드 종료후에 동작하는 메서드
	public void after() {
		//예외가 발생해도 실행됨
		System.out.println("Hello After ! **핵심기능 메서드가 호출된 후에 나온다!");
	}
	//공통기능 수행 메서드
	//핵심기능메서드 실행 전, 후 또는 예외 발생 시점에 동작하는 메서드
	public String around(ProceedingJoinPoint joinPoint) throws Throwable {
		System.out.println("Hello Around before ! **핵심기능 메서드가 호출되기 전에 나온다!");
		String s = null;
		
		//try~catch~finally 구조로 명시해야 예외가 발생해도 메서드 실행 후 공통기능을 수행 가능
		try {
			//핵심 기능이 수행 후 데이터 반환
			s = (String)joinPoint.proceed();
			
		}catch(Exception e) {
			System.out.println("Hello Around after throwing !");
			e.printStackTrace();
		}finally {
			System.out.println("Hello Around after ! **핵심기능 메서드가 호출된 후에 나온다! 반한된 객체 : " + s);
		}
		
		return s;
	}
}
