package kr.spring.ch09.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.spring.ch09.model.MemberCommand;

@Controller
public class MemberWriteController {
	
	//자바빈(VO) 초기화후 request에 등록
	@ModelAttribute("command")
	public MemberCommand initCommand() {
		return new MemberCommand();
	}
	
	//GET방식으로 전송시 form으로 진입
	@RequestMapping(value="/member/write.do", method=RequestMethod.GET)
	public String form() {
		return "member/write";
	}
	
	//POST방식으로 전송시 유효성 체크
	//자바빈 앞에 @Valid 명시하면 유효성체크가능
	@RequestMapping(value="/member/write.do", method = RequestMethod.POST)
	public String submit(@ModelAttribute("command") @Valid MemberCommand memberCommand, BindingResult result) {
		
		//전송된 데이터 유효성 체크 -> @Valid 어노테이션으로 체크
		
		/*
		 * BindingResult에 유효성 체크 결과 오류에 대한 내용이 저장돼있으면 form을 호출
		 */
		if(result.hasErrors()) {
			return "member/write";
		}
		
		return "redirect:/index.jsp";
	}
}
