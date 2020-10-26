package kr.spring.ch06.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.spring.ch06.model.MemberInfo;
import kr.spring.ch06.validator.MemberInfoValidator;

@Controller
@RequestMapping("/account/create.do")
public class CreateAccountController {
	
	//자바빈(VO) 초기화후 request에 등록
	@ModelAttribute("command")
	public MemberInfo initCommand() {
		return new MemberInfo();
	}
	
	//GET방식으로 전송시 form으로 진입
	@RequestMapping(method = RequestMethod.GET)
	public String form() {
		return "account/creationForm";
	}
	
	//POST방식으로 전송시 유효성 체크
	@RequestMapping(method = RequestMethod.POST)
	public String submit(@ModelAttribute("command") MemberInfo memberInfo, BindingResult result) {
		 
		//자바빈 출력
		System.out.println("전송된 데이터 : " + memberInfo);
		
		//전송된 데이터 유효성체크
		new MemberInfoValidator().validate(memberInfo, result);
		
		/*
		 * BindingResult에 유효성 체크 결과 오류에 대한 내용이 저장돼있으면 form을 호출
		 */
		if(result.hasErrors()) {
			return "account/creationForm";
		}
		
		return "account/created";
	}
	
}
