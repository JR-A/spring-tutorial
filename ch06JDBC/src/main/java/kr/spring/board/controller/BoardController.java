package kr.spring.board.controller;

import java.util.List;

import javax.annotation.Resource;

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
import kr.spring.board.validator.BoardValidator;
import kr.spring.board.vo.BoardCommand;
import kr.spring.util.PagingUtil;

/*
 *  					@Component
 *  						| 
 *  			ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
 *				|			| 			|
 *			@Repository  @Service 	@Controller
 *  
 *  ------
 *  @Repository, @Service, and @Controller are specializations of @Component for more specific use cases
 *	------
 *  @Component : Spring에서 관리되는 객체임을 표시하기 위해 사용하는 가장 기본적인 annotation. 
 *  			즉, scan-auto-detection과 dependency injection을 사용하기 위해서 사용되는 가장 기본적인 어노테이션이다.
 *  @Repository : Persistence Layer에서 DAO. 영속성을 가지는 속성(파일, 데이터베이스 등)
 *  @Service : Business Layer에서 Service. 트랜잭션 처리
 *  @Controller : Presentation Layer에서 Controller. Web MVC 코드에 사용되는 어노테이션. @RequestMapping 어노테이션을 해당 어노테이션 밑에서만 사용가능.
 *  ------
 *  스캔 대상으로 등록하려면 @Component 써주어야 하지만
 *  @Controller가 @Component에서 분화되었으므로 @Controller 명시해도 자동으로 스캔 대상으로 등록
 */
@Controller
public class BoardController {
	
	//프로퍼티
	@Resource
	private BoardService boardService;  //컨테이너에 정의되어있으므로 주입받음(root-context.xml에서 빈 자동스캔)
	//자동으로 Setter 생성함
	
	//로그처리(로그 대상 지정) - src/main/resources 의 log4j.xml 변경
	private Logger log = Logger.getLogger(this.getClass());		//this:BoardController객체, this.getClass():BoardController클래스 
	/*
	 * 로그 레벨
	 * FATAL : 가장 심각한 오류
	 * ERROR : 일반적인 오류
	 * WARN  : 주의를 요하는 경우 (오류는 아님)
	 * INFO  : 런타임시 관심있는 내용 표시. 로그인, 상태 변경과 같은 정보성 메시지
	 * DEBUG : 시스템 흐름과 관련된 상세 정보. 개발시 디버깅 용도로 사용할 메시지 - 일반적으로 개발시 지정(FATAL~DEBUG까지 출력)
	 * TRACE : 가장 상세한 정보 
	 */
	
	//자바빈(VO) 초기화후 request에 등록  (CommandName은 jsp, controller에서 모두 일치시켜야함)
	@ModelAttribute
	public BoardCommand initCommand() {
		return new BoardCommand();
	}
	
	//글 쓰기 폼 - GET방식으로 전송시
	@RequestMapping(value="/insert.do", method=RequestMethod.GET)
	public String form() {
		return "insertForm";
	}
	
	//글 쓰기 - POST방식으로 전송시 (전송된 데이터 처리, 유효성 체크)
	@RequestMapping(value="/insert.do", method=RequestMethod.POST)
	public String submit(BoardCommand boardCommand, BindingResult result) {	//ModelAttribute("boardCommand")가 생략됨
		
		//전송된 데이터 유효성 체크
		new BoardValidator().validate(boardCommand, result);
		
		/*
		 * BindingResult에 유효성 체크 결과 오류에 대한 내용이 저장돼있으면 form을 호출
		 */
		if(result.hasErrors()) {
			return form();
		}
		
		//에러가 없으면 글 등록
		boardService.insertBoard(boardCommand);
		
		//홈화면으로 이동
		return "redirect:/list.do";
	}
	
	//게시글 목록
	@RequestMapping("/list.do")			//selectList.jsp의 하단 pagingHtml에 링크 모두 걸려있음. 페이지번호 클릭시 pageNum을 get방식으로 넘겨줌
	public ModelAndView process(@RequestParam(value="pageNum", defaultValue="1") int currentPage) {
		
		//총 게시글 수
		int count = boardService.getBoardCount();
		
		//페이징처리
		PagingUtil page = new PagingUtil(currentPage, count, 10, 10, "list.do");
		/*
		 * PagingUtil(int currentPage, int totalCount, int rowCount, int pageCount, String pageUrl)
		 * currentPage : 현재페이지 
		 * totalCount : 전체 게시물 수 
		 * rowCount : 한 페이지에 보여줄 게시물의 수
		 * pageCount : 한 화면에 보여줄 페이지 수 
		 * pageUrl : 호출 페이지 url
		 */
		
		List<BoardCommand> list = null;
		if(count > 0) {
			list = boardService.getBoardList(page.getStartCount(), page.getEndCount());
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
	
	
	//글 상세 정보
	@RequestMapping("/detail.do")
	public ModelAndView detail(@RequestParam int num) {
		
		//로그 처리
		if(log.isDebugEnabled()) {	//로그 레벨이 DEBUG 포함이면. Check whether this category is enabled for the DEBUG Level. 
			log.debug("<<num>> : " + num);
		}
		
		//글 번호로 게시글 정보 가져오기
		BoardCommand board = boardService.getBoard(num);
		
								//뷰이름, 뷰에 전달할 속성명, 속성값
		return new ModelAndView("selectDetail", "board", board);
	}
	
	//글 수정 폼 - GET방식으로 전송시
	@RequestMapping(value="/update.do", method=RequestMethod.GET)
	public String formUpdate(@RequestParam int num, Model model) {	//Model 객체는 데이터만 저장. 컨테이너가 제공
		
		//글 번호로 게시글 정보 가져오기
		BoardCommand boardCommand = boardService.getBoard(num);
		
		//로그 처리
		if(log.isDebugEnabled()) {
			log.debug("<<BoardCommand>> : " + boardCommand);
		}
		
		//모델에 데이터 세팅			 속성명			속성값
		model.addAttribute("boardCommand",boardCommand);
		
		return "updateForm";
	}
	
	
	//글 수정 - POST방식으로 전송시
	@RequestMapping(value="/update.do", method=RequestMethod.POST)
	public String submitUpdate(BoardCommand boardCommand, BindingResult result) {
		
		//전송된 데이터 유효성 체크
		new BoardValidator().validate(boardCommand, result);
		
		/*
		 * BindingResult에 유효성 체크 결과 오류에 대한 내용이 저장돼있으면 form을 호출
		 */
		if(result.hasErrors()) {
			return "updateForm";
		}
		
		//비밀번호 일치 여부 체크
		//DB에 저장된 비밀번호 구하기
		BoardCommand dbBoard = boardService.getBoard(boardCommand.getNum());
		
		//비밀번호 체크
		if(!dbBoard.getPasswd().equals(boardCommand.getPasswd())) {	//일치하지 않으면 result에 에러 저장후 폼 호출
								//필드		에러코드
			result.rejectValue("passwd", "invalidPassword");
			return "updateForm";
		}
		
		//글 수정
		boardService.updateBoard(boardCommand);
		
		return "redirect:/list.do";
	}
}
