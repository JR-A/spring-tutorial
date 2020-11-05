package kr.spring.board.vo;

import java.io.IOException;
import java.sql.Date;

import org.springframework.web.multipart.MultipartFile;

public class BoardVO {
	//프로퍼티
	private int board_num;		//게시판번호
	private String title;		//제목
	private String content; 	//내용
	private int hit;			//조회수
	private Date reg_date;		//등록일
	private Date modify_date;	//수정일
	private byte[] uploadFile;	//이미지 파일
	private String filename;	//파일명
	private String ip;			//ip주소
	private int mem_num;		//회원번호
	
	private int id;				//회원아이디 - 컬럼에는 없지만 게시글 표시할때 자주 쓰이므로 프로퍼티에 추가
	
	/*========= 이미지 BLOB 처리 ===========*/
	//setter
	public void setUpload(MultipartFile upload) throws IOException{
		//uploadFile세팅 (MultipartFile -> byte[])
		setUploadFile(upload.getBytes());
		
		//filename세팅 (파일명)
		setFilename(upload.getOriginalFilename());
	}
	
	//Getters and Setters
	public int getBoard_num() {
		return board_num;
	}

	public String getTitle() {
		return title;
	}

	public String getContent() {
		return content;
	}

	public int getHit() {
		return hit;
	}

	public Date getReg_date() {
		return reg_date;
	}

	public Date getModify_date() {
		return modify_date;
	}

	public byte[] getUploadFile() {
		return uploadFile;
	}

	public String getFilename() {
		return filename;
	}

	public String getIp() {
		return ip;
	}

	public int getMem_num() {
		return mem_num;
	}

	public int getId() {
		return id;
	}

	public void setBoard_num(int board_num) {
		this.board_num = board_num;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setHit(int hit) {
		this.hit = hit;
	}

	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}

	public void setModify_date(Date modify_date) {
		this.modify_date = modify_date;
	}

	public void setUploadFile(byte[] uploadFile) {
		this.uploadFile = uploadFile;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public void setMem_num(int mem_num) {
		this.mem_num = mem_num;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	//byte[] uploadFile 제외하고 출력
	@Override
	public String toString() {
		return "BoardVO [board_num=" + board_num + ", title=" + title + ", content=" + content + ", hit=" + hit
				+ ", reg_date=" + reg_date + ", modify_date=" + modify_date + ", filename=" + filename + ", ip=" + ip
				+ ", mem_num=" + mem_num + ", id=" + id + "]";
	}
	
}
