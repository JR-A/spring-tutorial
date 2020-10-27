package kr.spring.ch08.model;

import org.springframework.web.multipart.MultipartFile;

public class SubmitReportVO {
	private String subject;
	private MultipartFile reportFile;
	
	//Getters and Setters
	public String getSubject() {
		return subject;
	}
	public MultipartFile getReportFile() {
		return reportFile;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public void setReportFile(MultipartFile reportFile) {
		this.reportFile = reportFile;
	}
	
	
}
