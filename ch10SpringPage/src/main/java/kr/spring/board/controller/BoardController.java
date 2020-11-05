package kr.spring.board.controller;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import kr.spring.board.service.BoardService;

@Controller
public class BoardController {
	
	//프로퍼티
	@Resource
	BoardService boardService;
	
	//로그 처리(로그 대상 지정)
	private Logger log = Logger.getLogger(this.getClass());
	
	//게시판 목록
	@RequestMapping("/board/list.do")
	public ModelAndView process() {
		
		ModelAndView mav = new ModelAndView();
		//뷰 이름 설정
		mav.setViewName("boardList");	//definition name (member.xml에 저장된 모듈화된 페이지)
		//데이터 저장
		
		return mav;
	}
}
