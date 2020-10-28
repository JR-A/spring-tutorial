package kr.spring.ch04.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.spring.ch04.model.PageRank;

@Controller
public class PageReportController {
	/*
	 * 메서드에 @ResponseBody를 명시하고 메서드내에서 List객체 또는 Map객체를 생성해서 반환하면 
	 * @ResponseBody가 JSON문자열이 포함된 View를 생성. 비동기처리하는 경우 ResponseBody, RequestBody 자주 사용함
	 * 전제조건 ) pom.xml에 dependency 설정
	 * 		   jackson library dependency(com.fasterxml.jackson.core)를 추가 (버전2.9.5)
	 */
	@RequestMapping("/pageJsonReport.do")
	@ResponseBody		//List를 json문자열로 만들고 view 자동 생성해줌 - view만들 필요가 없다
	public List<PageRank> jsonReport(){
		List<PageRank> pageRanks = new ArrayList<PageRank>();
		
		pageRanks.add(new PageRank(1, "/board/list.do"));
		pageRanks.add(new PageRank(2, "/board/detail.do"));
		pageRanks.add(new PageRank(3, "/board/write.do"));

		return pageRanks;
	}

}
