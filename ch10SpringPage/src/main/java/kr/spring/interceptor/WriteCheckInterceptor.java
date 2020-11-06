package kr.spring.interceptor;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import kr.spring.board.service.BoardService;
import kr.spring.board.vo.BoardVO;
import kr.spring.member.vo.MemberVO;

//Interceptor : 컨트롤러에 들어오는 요청 HttpRequest와 컨트롤러가 응답하는 HttpResponse를 가로채는 역할
public class WriteCheckInterceptor extends HandlerInterceptorAdapter {
	
	//프로퍼티
	@Resource
	private BoardService boardService;	//작성자 회원번호
	
	//로그 처리(로그 대상 지정)
	private Logger log = Logger.getLogger(this.getClass());
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
		
		if(log.isDebugEnabled()) {
			log.debug("==== WriteCheckInterceptor 진입 - 로그인 아이디와 작성자 아이디 일치 여부 체크 ====");
		}
		
		//글작성자 회원번호 구하기
		int board_num = Integer.parseInt(request.getParameter("board_num"));
		BoardVO board = boardService.selectBoard(board_num); //게시글 번호로 게시글 정보 반환(작성자 회원번호 정보 포함된)
		
		//로그인 회원번호 구하기
		HttpSession session = request.getSession();
		MemberVO user = (MemberVO)session.getAttribute("user");
		
		if(log.isDebugEnabled()) {
			log.debug("<<로그인 회원번호>> : " + user.getMem_num());
			log.debug("<<작성자 회원번호>> : " + board.getMem_num());
		}
		
		//로그인 회원번호와 작성자 회원번호 일치 여부 체크
		//로그인된 상태가 아니거나, 로그인한 회원번호와 작성자 회원번호가 다를 경우
		if(user == null || user.getMem_num() != board.getMem_num()) {
			if(log.isDebugEnabled()) {
				log.debug("<<로그인 아이디와 작성자 아이디 불일치>>");
			}
			
			//잘못된 접속임을 알려줌 - 경고페이지 호출
			/*Interceptor는 Controller호출 전 실행됨
			  controller처럼 resolver에서 경로, 확장자 붙여주지 않으므로 경로,확장자 모두 직접 명시하여 forward방식으로 호출하기 */
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/common/notice.jsp");	
		
			dispatcher.forward(request, response);
			
			return false;	//요청페이지 호출되지 않음
		}
		
		//에러가 없는경우(작성자 회원번호==로그인 회원번호)
		if(log.isDebugEnabled()) {
			log.debug("<<로그인 아이디와 작성자 아이디 일치>>");
		}
		
		return true;	//요청페이지 호출
	}
}
