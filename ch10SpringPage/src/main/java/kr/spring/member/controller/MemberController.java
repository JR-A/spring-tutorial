package kr.spring.member.controller;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.spring.member.service.MemberService;
import kr.spring.member.vo.MemberVO;

@Controller
public class MemberController {
	
	//프로퍼티
	@Resource
	private MemberService memberService;
	
	//로그 처리(로그 대상 지정)
	private Logger log = Logger.getLogger(this.getClass());
	
	//자바빈(VO) 초기화후 request에 등록
	@ModelAttribute
	public MemberVO initCommand() {
		return new MemberVO();
	}
	 
	//회원 가입 폼 - GET방식으로 전송시
	@RequestMapping(value="/member/registerUser.do", method=RequestMethod.GET)
	public String form() {
		return "memberRegister"; //jsp 호출이 아닌 definition name 호출 (member.xml에 저장된 모듈화된 페이지)
	}
	
	//회원 가입 처리 - POST방식으로 전송시
	@RequestMapping(value="/member/registerUser.do", method=RequestMethod.POST)
	public String submit(@Valid MemberVO memberVO, BindingResult result) 
	{
		//전송된 데이터 유효성 체크 -> @Valid 어노테이션으로 체크
		
		//로그 처리
		if(log.isDebugEnabled()) {
			log.debug("<<회원 가입>> : " + memberVO);
		}
		
		/*
		 * BindingResult에 유효성 체크 결과 오류에 대한 내용이 저장돼있으면 form을 호출
		 */
		if(result.hasErrors()) {
			return form();
		}
		
		//에러가 없으면 회원 가입 처리
		memberService.insertMember(memberVO);
		
		return "redirect:/main/main.do";	//회원가입 완료 창 보여주고싶으면 View만들어야함
	}
}