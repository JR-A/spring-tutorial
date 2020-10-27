package kr.spring.ch08.controller;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.spring.ch08.model.SubmitReportVO;
import kr.spring.ch08.validator.SubmitReportValidator;

@Controller
@RequestMapping("/report/submitReport.do")
public class SubmitReportController {
	
	/* 모든 properties파일을 검사하므로 다른 properties파일이라도 같은 key값 쓰면 결과 제대로 나오지 않음 */
	//파일 업로드 경로 읽기
	@Value("${file_path}") //EL표기법으로 값 읽어 프로퍼티에 저장(properties파일에서 key로 접근)
	private String path;
	
	//자바빈(VO) 초기화후 request에 등록
	@ModelAttribute("report")
	public SubmitReportVO initCommand() {
		return new SubmitReportVO();
	}
	
	//GET방식으로 전송시 form으로 진입
	@RequestMapping(method = RequestMethod.GET)
	public String form() {
		return "report/submitReportForm";
	}
	
	//POST방식으로 전송시 유효성 체크
	@RequestMapping(method = RequestMethod.POST)
	public String submit(@ModelAttribute("report") SubmitReportVO vo, BindingResult result) throws IOException {
		
		//전송된 데이터 유효성 체크
		new SubmitReportValidator().validate(vo, result);
		
		/*
		 * BindingResult에 유효성 체크 결과 오류에 대한 내용이 저장돼있으면 form을 호출
		 */
		if(result.hasErrors()) {
			return form();
		}
		
		//업로드 경로 정보를 갖는 File 객체 생성
		File file = new File(path + "/" + vo.getReportFile().getOriginalFilename());	//업로드경로/파일명
		
		vo.getReportFile().transferTo(file); //메모리에 올라간 파일을 실제 업로드 경로에 업로드
		
		System.out.println("제목 : " + vo.getSubject());
		System.out.println("업로드한 파일명 : " + vo.getReportFile().getOriginalFilename());
		System.out.println("소스 코드 크기 : " + vo.getReportFile().getSize()); //byte단위
		
		return "report/submittedReport";
	}
}
