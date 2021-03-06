package kr.spring.tiles.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
	
	@RequestMapping("/index.do")
	public String getIndex() {
		//뷰 이름 : tiles의 설정파일인 tilesdef.xml에 명시한 definition name
		return "index";	//jsp파일 호출이 아님
	}
	
	@RequestMapping("/menu1.do")
	public String getMenu1() {
		return "menu1";
	}
	
	@RequestMapping("/menu2.do")
	public String getMenu2() {
		return "menu2";
	}
}
