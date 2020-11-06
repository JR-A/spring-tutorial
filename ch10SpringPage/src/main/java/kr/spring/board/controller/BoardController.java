package kr.spring.board.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
import kr.spring.board.vo.BoardVO;
import kr.spring.member.vo.MemberVO;
import kr.spring.util.PagingUtil;

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
	public ModelAndView process(@RequestParam(value="pageNum", defaultValue="1") int currentPage, 
								@RequestParam(value="keyfield", defaultValue="") String keyfield,
								@RequestParam(value="keyword", defaultValue="") String keyword) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("keyfield", keyfield);
		map.put("keyword", keyword);
		
		//총 게시글 수 (또는 검색된 게시글 수)
		int count = boardService.selectRowCount(map);
		
		if(log.isDebugEnabled()) {
			log.debug("<<count>> : " + count);
		}
		
		//페이징처리
		//PagingUtil(String keyfield, String keyword, int currentPage, int totalCount, int rowCount, int pageCount, String pageUrl)
		PagingUtil page = new PagingUtil(keyfield, keyword, currentPage, count, 10, 10, "list.do");
		
		map.put("start", page.getStartCount());
		map.put("end", page.getEndCount());
		
		if(log.isDebugEnabled()) {
			log.debug("<<map>> : " + map);
		}
		
		List<BoardVO> list = null;
		if(count > 0) {
			//게시글 목록
			list = boardService.selectList(map);
			
			if(log.isDebugEnabled()) {
				log.debug("<<글 목록>> : " + list);
			}
		}
		
		ModelAndView mav = new ModelAndView();
		//뷰 이름 설정
		mav.setViewName("boardList");	//definition name (member.xml에 저장된 모듈화된 페이지)
		//데이터 저장
		mav.addObject("count", count);		//총 게시글 수 (또는 검색된 게시글 수)
		mav.addObject("list", list);		//글 목록
		mav.addObject("pagingHtml", page.getPagingHtml());	//페이지 표시 문자열
		
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
	
	//글 상세
	@RequestMapping("/board/detail.do")
	public ModelAndView process(@RequestParam int board_num) {
		if(log.isDebugEnabled()) {
			log.debug("<<글 상세>> : " + board_num);
		}
		
		//해당 글의 조회수 증가
		boardService.updateHit(board_num);
		
		//게시글 정보(글 번호로 게시글 객체 반환)
		BoardVO board = boardService.selectBoard(board_num);
		
		//ModelAndView(String viewName, String modelName, Object modelObject)
		//viewName은 board.xml의 definition name
		return new ModelAndView("boardView", "board", board);
	}
	
	//게시글 이미지 출력 - boardView.jsp에서 호출
	@RequestMapping("/board/imageView.do")
	public ModelAndView viewImage(@RequestParam int board_num) {
		
		BoardVO board = boardService.selectBoard(board_num);
		
		ModelAndView mav = new ModelAndView();
		//뷰 이름 설정
		mav.setViewName("imageView"); //파일 다운로드시 사용하는 viewResolver에 의해, mav를 반환하면 dispatcherServlet이 같은 beanName가진 bean호출
		//데이터 저장
		mav.addObject("imageFile", board.getUploadfile());	//byte[]
		mav.addObject("filename", board.getFilename());		//String
		
		return mav;
	}
	
	//글 수정 폼 - GET방식으로 전송시
	@RequestMapping(value="/board/update.do", method=RequestMethod.GET)
	public String form(@RequestParam int board_num, Model model) {
		
		BoardVO boardVO = boardService.selectBoard(board_num);
		
		model.addAttribute("boardVO", boardVO);
		
		return "boardModify"; //definition name
	}
	
	//글 수정 처리 - POST방식으로 전송시
	@RequestMapping(value="/board/update.do", method=RequestMethod.POST)      	//ip주소					유저의 회원번호
	public String submitUpdate(@Valid BoardVO boardVO, BindingResult result, HttpServletRequest request, HttpSession session, Model model) {
		
		if(log.isDebugEnabled()) {
			log.debug("<<글 수정>> : " + boardVO);
		}
		
		//BindingResult에 유효성 체크 결과 오류에 대한 내용이 저장돼있으면 form을 호출
		if(result.hasErrors()) {
			return "boardModify";	//definition name
		}
		
		//ip주소 세팅
		boardVO.setIp(request.getRemoteAddr());
		
		//회원 번호 세팅
		MemberVO user = (MemberVO)session.getAttribute("user");
		boardVO.setMem_num(user.getMem_num());
		//boardVO.setMem_num(((MemberVO)session.getAttribute("user")).getMem_num()); 변수 사용하지 않고 바로 명시해도 됨

		//글 수정 처리
		boardService.updateBoard(boardVO);
		
		//View에 표시할 메시지 (model은 view에 데이터 전달하기위해 사용, 컨테이너가 제공)
		model.addAttribute("message", "글 수정 완료!");
		model.addAttribute("url", request.getContextPath()+ "/board/list.do");
		
		//Tiles 설정에 아래 뷰 이름이 없으면 단독으로 JSP 호출(우선순위1에 해당사항 없으므로 우선순위2인 원래 resolver동작)
		return "common/result";
	}
	
	//글 삭제 처리
	@RequestMapping(value="/board/delete.do")				//contextPath				데이터전달
	public String submitDelete(@RequestParam int board_num, HttpServletRequest request, Model model) {
		
		if(log.isDebugEnabled()){
			log.debug("<<글 삭제>> : " + board_num);
		}
		
		//글 삭제 - 이미 로그인된 상태로 삭제하기 때문에 별도의 유효성 검사 필요없음 (Interceptor들이 이미 체크했음)
		boardService.deleteBoard(board_num);
		
		//View에 표시할 메시지 (model은 view에 데이터 전달하기위해 사용, 컨테이너가 제공)
		model.addAttribute("message", "글 삭제 완료!");
		model.addAttribute("url", request.getContextPath()+ "/board/list.do");
		
		//Tiles 설정에 아래 뷰 이름이 없으면 단독으로 JSP 호출(우선순위1에 해당사항 없으므로 우선순위2인 원래 resolver동작)
		return "common/result";
	}
}
