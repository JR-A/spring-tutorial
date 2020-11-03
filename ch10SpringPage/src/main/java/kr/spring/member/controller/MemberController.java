package kr.spring.member.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.spring.member.vo.MemberVO;

@Controller
public class MemberController {
	
	//로그 처리(로그 대상 지정)
	private Logger log = Logger.getLogger(this.getClass());
	
	//자바빈(VO) 초기화후 request에 등록
	@ModelAttribute
	public MemberVO initCommand() {
		return new MemberVO();
	}
	
	//회원 가입 폼
	@RequestMapping(value="/member/registerUser.do", method=RequestMethod.GET)
	public String form() {
		return "memberRegister"; //jsp 호출이 아닌 definition name 호출 (member.xml에 저장된 모듈화된 페이지)
	}
}
