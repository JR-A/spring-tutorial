package kr.spring.board.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
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
	
	//자바빈(VO) 초기화후 request에 등록  (CommandName은 jsp, controller에서 모두 일치시켜야함)
	@ModelAttribute
	public BoardCommand initCommand() {
		return new BoardCommand();
	}
	
	//GET방식으로 전송시 form으로 진입
	@RequestMapping(value="/insert.do", method=RequestMethod.GET)
	public String form() {
		return "insertForm";
	}
	
	//POST방식으로 전송시 글쓰기 전송된 데이터 처리, 유효성 처리
	@RequestMapping(value="/insert.do", method=RequestMethod.POST)
	public String submit(BoardCommand boardCommand, BindingResult result) {
		
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
	
	@RequestMapping("/list.do")
	public ModelAndView process(@RequestParam(value="pageNum", defaultValue="1") int currentPage) {
		
		//총 게시글 수
		int count = boardService.getBoardCount();
		
		//페이징처리
		PagingUtil page = new PagingUtil(currentPage, count, 10, 10, "list.do");
		/*
		 * PagingUtil(int currentPage, int totalCount, int rowCount, int pageCount, String pageUrl)
		 * currentPage : 현재페이지 
		 * totalCount : 전체 게시물 수 
		 * rowCount : 한 페이지의 게시물의 수 
		 * pageCount : 한 화면에 보여줄 페이지 수 
		 * pageUrl : 호출 페이지 url 
		 * addKey : 부가적인 key 없을 때는 null 처리 (&num=23형식으로 전달할 것)
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
}
