package kr.spring.board.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
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
	
	//프로퍼티
	@Resource
	private BoardService boardService;
	
	//로그처리(로그 대상 지정) - src/main/resources 의 log4j.xml 변경
	private Logger log = Logger.getLogger(this.getClass());		//this:BoardController객체, this.getClass():BoardController클래스 
	
	//자바빈(VO) 초기화후 request에 등록  (CommandName은 jsp, controller에서 모두 일치시켜야함)
	@ModelAttribute
	public BoardCommand initCommand() {
		return new BoardCommand();
	}
		
	//게시글 목록
	@RequestMapping("list.do")		//selectList.jsp의 하단 pagingHtml에 링크 모두 걸려있음. 페이지번호 클릭시 pageNum을 get방식으로 넘겨줌
	public ModelAndView getList(@RequestParam(value="pageNum" , defaultValue="1") int currentPage) {
		
		//총 게시글 수
		int count = boardService.getBoardCount();
		
		//페이징 처리
		PagingUtil page = new PagingUtil(currentPage, count, 10, 10, "list.do");
		/*
		 * PagingUtil(int currentPage, int totalCount, int rowCount, int pageCount, String pageUrl)
		 * currentPage : 현재페이지 
		 * totalCount : 전체 게시물 수 
		 * rowCount : 한 페이지에 보여줄 게시물의 수
		 * pageCount : 한 화면에 보여줄 페이지 수 
		 * pageUrl : 호출 페이지 url
		 */
		
		//게시물 목록 가져오기
		List<BoardCommand> list = null;
		if(count > 0) {
			//현재 페이지에 보여줄 시작, 끝 행번호 구해서 Map에 담기
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("start", page.getStartCount());
			map.put("end", page.getEndCount());
			
			//게시글 목록 구하기
			list = boardService.getBoardList(map);
		}
		
		ModelAndView mav = new ModelAndView();
		
		//뷰 이름 지정
		mav.setViewName("selectList"); //경로, .jsp확장자는 제외하고 명시
		
		//뷰에서 사용할 데이터 세팅
		mav.addObject("count", count);
		mav.addObject("list", list);
		mav.addObject("pagingHtml", page.getPagingHtml());
		
		return mav; //DispatcherServlet에 전달
	}
	
	//글 쓰기 폼 - GET방식으로 전송시
	@RequestMapping(value="/insert.do", method=RequestMethod.GET)
	public String form() {
		return "insertForm";
	}
	
	//글 쓰기 - POST방식으로 전송시 (전송된 데이터 처리, 유효성 체크)
	//자바빈 앞에 @Valid 명시하면 어노테이션 방식으로 유효성체크가능
	@RequestMapping(value="/insert.do", method=RequestMethod.POST)
	public String submit(@Valid BoardCommand boardCommand, BindingResult result) { //ModelAttribute("boardCommand")가 생략됨
		
		//전송된 데이터 유효성 체크 -> @Valid 어노테이션으로 체크
		
		/*
		 * BindingResult에 유효성 체크 결과 오류에 대한 내용이 저장돼있으면 form을 호출
		 */
		if(result.hasErrors()) {
			return form();
		}
		
		//에러가 없으면 글 등록
		boardService.insertBoard(boardCommand);
		
		//글 목록으로 이동
		return "redirect:/list.do";
	}
}
