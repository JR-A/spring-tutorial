package kr.spring.ch05.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import kr.spring.ch05.model.SearchCommand;
import kr.spring.ch05.service.SearchService;

@Controller
public class GameSearchController {
	//DI
	@Resource
	private SearchService searchService;
	
	@RequestMapping("/search/main.do")
	public String main() {
		return "search/main";
	}
	
	/*
	 * 	@ModelAttribute("command")
	 *	:1. form에서 전송된 데이터를 request에서 반환받아, NewArticleCommand(자바빈) 객체 생성하여 데이터저장 후 메서드 매개변수로 전달
	 *	 2. command라는 속성명으로(변경가능) request에 자바빈을 속성값으로 저장 (setAttribute하는것과 같음)
	 *	 3. 프로퍼티 생성하면 set메서드 이용해서 프로퍼티 방식으로 의존성 주입
	 *
	 *	@ModelAttribute만 기재하고 속성명을 표시하지 않으면
	 *	:자바빈 클래스명의 앞글자를 소문자로 변환하여 속성명으로 간주. 자바빈 생성하여 속성값으로 저장 newArticleCommand -> newArticleCommand
	 *	
	 *	@ModelAttribute 생략 가능!
	 *	:자바빈 클래스명의 앞글자를 소문자료 변환하여 속성명으로 간주. 자바빈 생성하여 속성값으로 저장
	 *	
	 */
	//우리가 자바빈 new로 생성해서 set함수 쓰는 것이 아니라 @ModelAttribute 어노테이션 이용해서 자동으로 자바빈 만든다
	//파라미터 name과 자바빈의 필드 이름이 같으면 자동으로 binding
	@RequestMapping("/search/game.do")
	public ModelAndView search(@ModelAttribute("command") SearchCommand command) {
		
		String result = searchService.search(command);
		
		ModelAndView mav = new ModelAndView();
		
		//뷰 이름 지정
		mav.setViewName("search/game"); //경로, .jsp확장자는 제외하고 명시
		
		//뷰에서 사용할 데이터 세팅
		mav.addObject("searchResult", result);
		
		return mav; //DispatcherServlet에 전달
	}
}
