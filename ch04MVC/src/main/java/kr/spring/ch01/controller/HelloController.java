package kr.spring.ch01.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

//Controller : 요청에 의해 호출되는 모델클래스로 사용하려면 반드시 @Controller 명시. 로직처리
@Controller
public class HelloController {
	/*
	 * @RequestMapping("/hello.do") : 
	 * 어노테이션 달아주면 hello.do요청이 오면 이 메서드(혹은 클래스)를 호출해야한다고 HandlerMapping에 등록됨
	 * HandlerMapping은 ActionMap.properties의 역할. 이미 내장되어있는 네이게이션. 우리가 만들지x
	 */
	@RequestMapping("/hello.do")
	public ModelAndView hello() {
		ModelAndView mav = new ModelAndView();
		
		//뷰 이름 지정
		mav.setViewName("hello"); //경로, .jsp확장자는 제외하고 명시
		
		//뷰에서 사용할 데이터 세팅
		mav.addObject("greeting", "안녕하세요");
		
		return mav;	//DispatcherServlet에 전달
	}
}
