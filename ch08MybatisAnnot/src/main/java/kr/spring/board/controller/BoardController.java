package kr.spring.board.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import kr.spring.board.service.BoardService;
import kr.spring.board.vo.BoardCommand;
import kr.spring.util.PagingUtil;

@Controller
public class BoardController {
	@Resource
	private BoardService boardService;
	
	//로그 처리(로그 대상 지정)
	private Logger log = Logger.getLogger(this.getClass());
	
	//자바빈(VO) 초기화
	@ModelAttribute
	public BoardCommand initCommand() {
		return new BoardCommand();
	}
	
	@RequestMapping("/list.do")
	public ModelAndView getList(
			@RequestParam(value="pageNum",defaultValue="1") int currentPage) {
		
		//총 레코드 수
		int count = boardService.getBoardCount();
		
		//페이징 처리
		PagingUtil page = 
				new PagingUtil(currentPage,count,10,10,"list.do");
		
		//목록 호출
		List<BoardCommand> list = null;
		if(count > 0) {
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("start", page.getStartCount());
			map.put("end", page.getEndCount());
			
			list = boardService.getBoardList(map);
		}
		
		ModelAndView mav = new ModelAndView();
		//뷰 이름 설정
		mav.setViewName("selectList");
		//데이터 저장
		mav.addObject("count", count);
		mav.addObject("list",list);
		mav.addObject("pagingHtml", page.getPagingHtml());
		
		return mav;
	}
	
	//글쓰기 폼
	@RequestMapping(value="/insert.do",method=RequestMethod.GET)
	public String form() {
		return "insertForm";
	}
	
	//글쓰기 처리
	@RequestMapping(value="/insert.do",method=RequestMethod.POST)
	public String submit(@Valid BoardCommand boardCommand,
			         BindingResult result) {
		
		//유효성 체크 결과 오류가 있으면 폼 호출
		if(result.hasErrors()) {
			return form();
		}
		
		//글쓰기
		boardService.insertBoard(boardCommand);
		
		return "redirect:/list.do";
	}
	
	//글 상세
	@RequestMapping("/detail.do")
	public ModelAndView detail(@RequestParam int num) {
		BoardCommand board = 
				boardService.getBoard(num);
		
		return new ModelAndView("selectDetail","board",board);		
	}
	
	//수정 폼
	@RequestMapping(value="/update.do",method=RequestMethod.GET)
	public String formUpdate(@RequestParam int num,
			                 Model model) {
		
		model.addAttribute(
				"boardCommand", boardService.getBoard(num));
		
		return "updateForm";
	}	
	//글 수정 처리
	@RequestMapping(value="/update.do",method=RequestMethod.POST)
	public String submitUpdate(@Valid BoardCommand boardCommand,
			                   BindingResult result) {
		
		//유효성 체크 결과 오류가 있으면 폼을 호출
		if(result.hasErrors()) {
			return "updateForm";
		}
		
		//비밀번호 일치 여부 체크
		//DB에 저장된 비밀번호 구하기
		BoardCommand dbBoard = boardService.getBoard(
				                     boardCommand.getNum());
		
		//비밀번호 체크
		if(!dbBoard.getPasswd().equals(boardCommand.getPasswd())) {
			result.rejectValue("passwd", "invalidPassword");
			return "updateForm";
		}
		
		boardService.updateBoard(boardCommand);
		
		return "redirect:/list.do";
	}
	
	//글 삭제 폼
	@RequestMapping(value="/delete.do",method=RequestMethod.GET)
	public String formDelete(@RequestParam int num,
			              Model model) {
		BoardCommand boardCommand = new BoardCommand();
		boardCommand.setNum(num);
		
		model.addAttribute("boardCommand", boardCommand);
		
		return "deleteForm";
	}
	
	//글 삭제 처리
	@RequestMapping(value="/delete.do",method=RequestMethod.POST)
	public String submitDelete(@Valid BoardCommand boardCommand,
			                 BindingResult result) {
		//유효성 체크 결과 오류가 있으면 폼을 호출
		//비밀번호가 전송 여부만 체크
		if(result.hasFieldErrors("passwd")) {
			return "deleteForm";
		}
		
		//비밀번호 일치 여부 체크
		//DB에 저장된 비밀번호 구하기
		BoardCommand dbBoard = 
				boardService.getBoard(boardCommand.getNum());
		
		//비밀번호 체크
		if(!dbBoard.getPasswd().equals(boardCommand.getPasswd())) {
			result.rejectValue("passwd", "invalidPassword");
			return "deleteForm";
		}
		
		//글 삭제
		boardService.deleteBoard(boardCommand.getNum());
		
		return "redirect:/list.do";
	}
	
}








