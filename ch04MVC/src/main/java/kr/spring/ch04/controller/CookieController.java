package kr.spring.ch04.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CookieController {
	
	//쿠키 생성
	@RequestMapping("/cookie/make.do")					//서블릿에서 사용되는 인자를 전달받고 싶다면
	public String make(HttpServletResponse response) {	//HttpServletResponse를 메서드 인자로 넣어주면 언제든 접근 가능. HttpServletRequest도 마찬가지
		
		//쿠키를 생성해서 클라이언트에 전송
		response.addCookie(new Cookie("auth", "1"));
		
		return "cookie/make";
	}
	
	//쿠키 출력
	/*
	 * @CookieValue(value="auth") -  @RequestParam과 유사하게 동작(SearchController.java참고)
	 * :쿠키값을 매개변수로 전달받을 수 있음. 해당 쿠키가 존재하지 않으면 기본적으로 400에러 발생
	 * 
	 * @CookieValue(value="auth", required=false)
	 * :해당 쿠키가 존재하지 않으면 null 값으로 전달
	 * 
	 * @CookieValue(value="auth", defaultValue="0")
	 * :쿠키가 존재하지 않으면 디폴트값으로 defaultValue에 지정한 값 전달
	 * 
	 * @CookieValue
	 * :매개변수명과 같은 쿠키 전달받음
	 * 
	 * @RequestParam과 달리 어노테이션 생략 불가. 쿠키값 null
	 */
	@RequestMapping("/cookie/view.do")			//쿠키 생성하기 전 쿠키 출력하려고 하면 에러발생하므로 디폴트값설정
	public String view(@CookieValue(value="auth", defaultValue="0") String auth) {
		
		System.out.println("auth 쿠키 : " + auth);
		
		return "cookie/view";
	}
	
}
