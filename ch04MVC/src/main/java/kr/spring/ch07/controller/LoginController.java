package kr.spring.ch07.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.spring.ch07.model.LoginCommand;
import kr.spring.ch07.service.LoginCheckException;
import kr.spring.ch07.service.LoginService;
import kr.spring.ch07.validator.LoginCommandValidator;


@Controller
@RequestMapping("/login/login.do")
public class LoginController {
	
	//프로퍼티
	@Resource	//빈 등록시 프로퍼티명과 빈name일치하면 의존성 주입
	private LoginService loginService;
	
	//자동으로 Setter 생성함
	
	//자바빈(VO) 초기화후 request에 등록
	//@ModelAttribute("속성명") 속성명 생략하면 클래스명의 첫글자 소문자로 변환하여 속성명으로 사용(loginCommand)
	@ModelAttribute
	public LoginCommand initCommand() {
		return new LoginCommand();
	}
	
	//GET방식으로 전송시 form으로 진입
	@RequestMapping(method = RequestMethod.GET)
	public String form() {
		return "login/form";
	}
	
	//POST방식으로 전송시 유효성 체크
	@RequestMapping(method = RequestMethod.POST)
	public String submit(@ModelAttribute LoginCommand loginCommand, BindingResult result) {
		
		//자바빈 출력
		System.out.println("전송된 데이터 : " + loginCommand);
		
		//전송된 데이터 유효성 체크
		new LoginCommandValidator().validate(loginCommand, result);
		
		/*
		 * BindingResult에 유효성 체크 결과 오류에 대한 내용이 저장돼있으면 form을 호출
		 */
		if(result.hasErrors()) {
			return form();
		}
		
		//로그인 체크
		try{
			loginService.checkLogin(loginCommand);
			
			return "redirect:/index.jsp";	//로그인 성공시 redirect방식으로  메인페이지로 이동
			
		}catch(LoginCheckException e) {
								//에러코드
			result.reject("invalidIdOrPassword");	//매칭되는 필드 없이 에러코드만 생성시 reject함수 사용. validation.properties에 에러코드에 해당하는 에러메시지 적어주기
			
			return form();	//로그인 실패시 form호출 
		}
				
	}
}
