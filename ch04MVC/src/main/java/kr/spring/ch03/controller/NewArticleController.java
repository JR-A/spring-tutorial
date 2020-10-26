package kr.spring.ch03.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.spring.ch03.model.NewArticleCommand;
import kr.spring.ch03.service.ArticleService;

/*
 * 같은 주소 호출해도 전송방식에따라(GET/POST) 다른 메서드 호출!
 */

@Controller
@RequestMapping("/article/newArticle.do")
public class NewArticleController {
	//프로퍼티
	@Resource
	private ArticleService articleService;
	
	//Setter. 객체를 주입받는 메서드
	public void setArticleService(ArticleService articleService) {
		this.articleService = articleService;
	}

	@RequestMapping(method=RequestMethod.GET)
	public String form() {

		return "article/newArticleForm"; //form화면 호출
		//data없이 view만 반환할 떄는 ModelAndView객체 말고 String으로 반환가능
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
	@RequestMapping(method=RequestMethod.POST)
	public String submit(NewArticleCommand command) {	//@ModelAttribute생략한 경우
		
		//우리가 자바빈 new로 생성해서 set함수 쓰는 것이 아니라 @ModelAttribute 어노테이션 이용해서 자동으로 자바빈 만든다
		//파라미터 name과 자바빈의 필드 이름이 같으면 자동으로 binding
		articleService.writeArticle(command);
		
		return "article/newArticleSubmitted";
	}
	
}
