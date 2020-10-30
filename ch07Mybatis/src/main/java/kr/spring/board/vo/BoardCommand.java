package kr.spring.board.vo;

import java.sql.Date;

import javax.validation.constraints.NotEmpty;

/*
 * 어노테이션을 이용한 유효성 체크
 */
//어노테이션으로 유효성체크하려면 hibernate라이브러리 필요
//자바빈의 프로퍼티명이 Database의 컬럼명과 동일해야함
public class BoardCommand {
	//프로퍼티
	private int num;
	@NotEmpty
	private String writer;
	@NotEmpty
	private String title;
	@NotEmpty
	private String passwd;
	@NotEmpty
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
	
	
	@Override
	public String toString() {
		return "BoardCommand [num=" + num + ", writer=" + writer + ", title=" + title + ", passwd=" + passwd
				+ ", content=" + content + ", reg_date=" + reg_date + "]";
	}
	
}
