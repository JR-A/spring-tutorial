package kr.spring.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/*
 *	Interceptor : 컨트롤러에 들어오는 요청 HttpRequest와 컨트롤러가 응답하는 HttpResponse를 가로채는 역할
 *	인터셉터는 관리자만 접근할 수 있는 관리자 페이지에 접근하기 전에 관리자 인증을 하는 용도로 활용될 수 있음
 *  또는 마이페이지 등 로그인 선행되어야하는 페이지에서 페이지 호출 "전" 로그인 여부 체크하는 역할
 */
public class LoginCheckInterceptor extends HandlerInterceptorAdapter {

	//로그 처리(로그 대상 지정)
	private Logger log = Logger.getLogger(this.getClass());
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
		
		//로그 처리
		if(log.isDebugEnabled()) {
			log.debug("==== LoginCheckInterceptor 진입 ====");
		}
		
		//로그인 여부 체크
		HttpSession session = request.getSession();
		if(session.getAttribute("user") == null) {
			//로그인 되어있지 않은 경우
			response.sendRedirect(request.getContextPath() + "/member/login.do"); //로그인 페이지로 redirect
			
			return false;
		}
		
		//로그인되어있는 경우
		return true;
	}
}
