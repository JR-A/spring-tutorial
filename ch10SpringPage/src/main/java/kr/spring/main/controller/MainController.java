package kr.spring.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
	
	@RequestMapping("/main/main.do")
	public String getMain() {
		//뷰 이름 : tiles의 설정파일인 main.xml에 명시한 definition name
		return "main";
	}
	
	
}
