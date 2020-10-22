package kr.spring.ch02.annot;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

/*
 * Advice : 언제 공통 기능을 핵심 로직에 적용할 것인지를 정의
 * 
 * 구현 가능한 Advice 종류와 그에 해당하는 어노테이션
 * 	 Annotation			Advice종류						설명
 * @Before			Before Advice			대상 객체의 메서드 호출 전에 공통 기능 실행 
 * @AfterReturning	After Returning Advice	대상 객체의 메서드가 예외없이 실행(전제조건)된 이후에 공통 기능 실행
 * @AfterThrowing	After Throwing Advice	대상 객체의 메서드가 실행 도중 예외를 반환하고 종료하는 경우에 공통 기능 실행
 * @After			After Advice			대상 객체의 메서드를 실행하는 도중 예외 발생 여부와 상관없이 메서드 실행 후 공통 기능 실행
 * 											(try-catch-finally의 finally 블럭과 비슷)
 * @Around			Around Advice			대상 객체의 메서드 실행 전, 후 또는 예외 발생 시점에 공통 기능 실행
 * 
 */

//공통기능 메서드 가진 클래스로 인정(공통관심사항)
@Aspect
public class MyFirstAspect {
	//어노테이션 사용하기 위해 핵심 기능을 검색하여 Pointcut으로 지정
	@Pointcut("execution(public String launch())")
	public void getPointcut() {}
	
	//@Before("getPointcut()")
	public void before() {
		System.out.println("Hello Before ! **핵심기능 메서드가 호출되기 전에 나온다!");
	}
	
	//@AfterReturning(value="getPointcut()", returning="msg")
	public void afterReturning(String msg) {
		System.out.println("Hello After Returning ! **핵심기능 메서드가 호출된 후에 나온다! 전달된 객체: " + msg);
	}
	
	//@AfterThrowing(value="getPointcut()", throwing="ex")
	public void afterThrowing(Throwable ex) {
		System.out.println("Hello After Throwing ! **예외가 생기면 나온다! " + ex);
	}
	
	//@After("getPointcut()")
	public void after() {
		//예외가 발생해도 실행됨
		System.out.println("Hello After ! **핵심기능 메서드가 호출된 후에 나온다!");
	}
	
	@Around("getPointcut()")
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
			System.out.println("Hello Around after ! **핵심기능 메서드가 호출된 후에 나온다! 반환된 객체 : " + s);
		}

		return s;
	}
}
