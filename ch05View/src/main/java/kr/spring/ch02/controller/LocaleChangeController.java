package kr.spring.ch02.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.LocaleResolver;

@Controller
public class LocaleChangeController {
	//프로퍼티
	@Autowired
	private LocaleResolver localeResolver;	//Locale 변경 위함

	//GET방식으로 lang 받아옴
	@RequestMapping("/changeLanguage.do")
	public String change(@RequestParam("lang") String language, HttpServletRequest request, 
																HttpServletResponse response) {
		
		Locale locale = new Locale(language);	//지정한 언어로 Locale 객체 생성
		
		//세션에 locale정보 저장. 세션 만료되면 원래 locale로 초기화
		localeResolver.setLocale(request, response, locale); //LocaleResolver객체 이용해 Locale 설정
		
		return "redirect:/index.jsp";
	}
}
