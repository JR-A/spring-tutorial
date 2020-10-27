package kr.spring.ch01.controller;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.spring.ch01.model.LoginCommand;
import kr.spring.ch01.service.LoginCheckException;
import kr.spring.ch01.service.LoginService;
import kr.spring.ch01.validator.LoginCommandValidator;


@Controller
public class LoginController {
	
	//프로퍼티
	@Autowired	//빈 등록시 프로퍼티 타입과 빈 타입일치하면 의존성 주입
	private LoginService loginService;

	//자동으로 Setter 생성함

	//자바빈(VO) 초기화후 request에 등록  (CommandName은 jsp, controller에서 모두 일치시켜야함)
	@ModelAttribute("login")
	public LoginCommand initCommand() {
		return new LoginCommand();
	}

	//GET방식으로 전송시 form으로 진입
	@RequestMapping(value="/login/login.do", method = RequestMethod.GET)
	public String form() {
		return "loginForm";
	}

	//POST방식으로 전송시 유효성 체크
	@RequestMapping(value="/login/login.do", method = RequestMethod.POST)
					//위 자바빈 초기화에서 @ModelAttribute로 request에 등록시 속성명 설정했으면 똑같이 적어주기
	public String submit(@ModelAttribute("login") LoginCommand loginCommand, BindingResult result) {

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
			
			e.printStackTrace();
							//에러코드
			result.reject("invalidIdOrPassword");	//매칭되는 필드 없이 에러코드만 생성시 reject함수 사용. validation.properties에 에러코드에 해당하는 에러메시지 적어주기

			return form();	//로그인 실패시 form호출 
		}
	}
}
