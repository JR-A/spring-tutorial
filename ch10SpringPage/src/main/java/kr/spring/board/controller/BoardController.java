package kr.spring.board.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import kr.spring.board.service.BoardService;
import kr.spring.board.vo.BoardVO;
import kr.spring.member.vo.MemberVO;

@Controller
public class BoardController {
	
	//프로퍼티
	@Resource
	BoardService boardService;
	
	//로그 처리(로그 대상 지정)
	private Logger log = Logger.getLogger(this.getClass());

	//자바빈(VO) 초기화후 request에 등록
	@ModelAttribute
	public BoardVO initCommand() {
		return new BoardVO();
	}
	
	//게시판 목록
	@RequestMapping("/board/list.do")
	public ModelAndView process() {
		
		ModelAndView mav = new ModelAndView();
		//뷰 이름 설정
		mav.setViewName("boardList");	//definition name (member.xml에 저장된 모듈화된 페이지)
		//데이터 저장
		
		return mav;
	}
	
	//글 등록 폼 - GET방식으로 전송시
	@RequestMapping(value="/board/write.do", method=RequestMethod.GET)
	public String form() {
		return "boardWrite";	//definition name
	}
	
	//글 등록 처리 - POST방식으로 전송시
	@RequestMapping(value="/board/write.do", method=RequestMethod.POST)		//ip주소					유저의 회원번호
	public String submit(@Valid BoardVO boardVO, BindingResult result, HttpServletRequest request, HttpSession session) {
		
		//로그처리
		if(log.isDebugEnabled()) {
			log.debug("<<게시판 글 등록>> : " + boardVO);
		}
		
		//BindingResult에 유효성 체크 결과 오류에 대한 내용이 저장돼있으면 form을 호출
		if(result.hasErrors()) {
			return form();
		}
		
		//ip주소 세팅
		boardVO.setIp(request.getRemoteAddr());
		
		//회원 번호 세팅
		MemberVO vo = (MemberVO)session.getAttribute("user");
		boardVO.setMem_num(vo.getMem_num());
		//boardVO.setMem_num(((MemberVO)session.getAttribute("user")).getMem_num()); 변수 사용하지 않고 바로 명시해도 됨

		//글 등록 처리
		boardService.insertBoard(boardVO);
		
		return "redirect:/board/list.do";
		
	}
}
