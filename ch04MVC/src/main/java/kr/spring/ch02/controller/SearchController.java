package kr.spring.ch02.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SearchController {
	/*
	 * @RequestParam 어노테이션은 HTTP 요청 파라미터를 메서드의 파라미터로 전달
	 * query는 필수적으로 전달해야하는 데이터이기 때문에
	 * query를 미전달시 오류 발생(null로 처리하는게 아니라) - required=true가 기본값
	 * 
	 * @RequestParam("query") String query
	 * :request.getParameter("query")하여 query 매개변수로 전달받은것과 같은 효과
	 * 
	 * @RequestParam(value="query", required=false)
	 * :query를 전송하지 않아도 오류 발생하지 않음
	 * 
	 * @RequestParam(value="query", defaultValue="")
	 * :query가 전송되지 않으면 디폴트값 ""사용
	 * 
	 * @RequestParam String query
	 * :파라미터 네임을 query로 간주
	 * 
	 * @RequestParam(value="p", defaultValue="1") int pageNumber
	 * :request.getParameter는 무조건 String으로 반환하는 것과 달리 어노테이션 사용하면 int형으로 인식하여 pageNumber 매개변수에 정상적으로 전달됨
	 * 
	 */
	@RequestMapping("/search/internal.do")
	public ModelAndView searchInternal(@RequestParam("query") String query,
									 @RequestParam(value="p", defaultValue="1") int pageNumber) {
		
		System.out.println("query = " + query + ", pageNumber = " + pageNumber);
								//view이름
		return new ModelAndView("search/internal");
		/* view resolver의
		 * prefix : /WEB-INF/views/
		 * suffix : .jsp
		 * 이므로 view이름 반환할때 search앞에 / 표기하지 않음 
		 */
	}
	
	/*
	 * @RequestParam 생략 가능! 전송된 파라미터 네임을 매개변수명(query)으로 간주.
	 * 파라미터 네임과 메서드의 매개변수명이 일치하면 전송된 데이터 받을 수 있음
	 * 어노테이션 생략시 query를 미전달시 오류 발생하지 않음(null로 처리)
	 * 
	 * 전달되는 파라미터 네임과 매개변수명 맞추면 int형으로 자동 변환
	 * 단, int형은 기본자료형이므로 미전달시 오류 발생 (null을 int형으로 변환시킬 수 없으므로)
	 * ->기본 자료형의 경우 @RequestParam어노테이션 사용하여 default값 설정해주기
	 */
	@RequestMapping("/search/external.do")
	public ModelAndView searchExternal(String query, 
									  @RequestParam(value="p", defaultValue="0") int pageNumber) {
		
		System.out.println("query = " + query + ", pageNumber = " + pageNumber);
								//view이름
		return new ModelAndView("search/external");
	}
}
