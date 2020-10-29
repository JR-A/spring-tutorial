package kr.spring.board.vo;

import java.sql.Date;	//DB연동용이므로 java.util이 아닌 java.sql

public class BoardCommand {
	//프로퍼티
	private int num;
	private String writer;
	private String title;
	private String passwd;
	private String content;
	private Date reg_date;
	
	//Getters and Setters
	public int getNum() {
		return num;
	}
	public String getWriter() {
		return writer;
	}
	public String getTitle() {
		return title;
	}
	public String getPasswd() {
		return passwd;
	}
	public String getContent() {
		return content;
	}
	public Date getReg_date() {
		return reg_date;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}
	
}
