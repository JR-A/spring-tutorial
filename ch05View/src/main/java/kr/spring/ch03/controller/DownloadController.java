package kr.spring.ch03.controller;

import java.io.File;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DownloadController implements ApplicationContextAware {

	//프로퍼티
	private WebApplicationContext context;
	
	@RequestMapping("/file.do")
	public ModelAndView download() throws Exception {
		//절대경로 구하기											//상대경로
		String path = context.getServletContext().getRealPath("/WEB-INF/file.txt");
		
		//Creates a new File instance by converting the given pathname string into an abstract pathname.
		File downloadFile = new File(path);
							//(String viewName, String modelName, Object modelObject)
		return new ModelAndView("download", "downloadFile", downloadFile);
		/*	viewName을 "download"라고 하면 기존 viewResolver에 의해 접미사,접두사 붙어서 /WEB-INF/views/download.jsp 호출됨 
		 * 	-> viewResolver를 하나 더 만들어서 "DownloadView.java" 호출하도록 함 - viewName(download)과 bean명이 일치하면 호출
		 *	viewResolver가 2개 있으면 충돌 발생 -> 우선순위 지정해야한다
		 */
	}
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		
		this.context = (WebApplicationContext)applicationContext;	//다운캐스팅(ApplicationContext->WebApplicationContext)
	}
	
}
