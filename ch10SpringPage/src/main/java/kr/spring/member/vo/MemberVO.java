package kr.spring.member.vo;

import java.io.IOException;
import java.sql.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;

public class MemberVO {
	//프로퍼티
	//spmember 테이블 컬럼
	private int mem_num;
	@NotEmpty
	private String id;
	private int auth;	/*0:탈퇴회원, 1:정지회원, 2:일반회원, 3:관리자*/ //default값 : 2
	//spmember_detail 테이블 컬럼
	@NotEmpty
	private String name;
	@Size(min=4,max=10)
	private String passwd;
	@NotEmpty
	private String phone;
	@NotEmpty
	@Email
	private String email;
	@Size(min=5,max=5)
	private String zipcode;
	@NotEmpty
	private String address1;
	@NotEmpty
	private String address2;
	private byte[] photo;
	private String photoname;
	private Date reg_date;
	private Date modify_date;
	
	//비밀번호 변경시 현재 비밀번호를 저장하는 용도
	@Size(min=4,max=10)
	private String now_passwd;
	
	/*========= 비밀번호 일치 여부 체크 ===========*/
	public boolean isCheckedPasswd(String userPasswd) {
		//일반회원 or 관리자의 경우 && 비밀번호 일치시 
		if(auth > 1 && passwd.equals(userPasswd)) { /*0:탈퇴회원, 1:정지회원, 2:일반회원, 3:관리자*/
			return true;
		}
		return false;
	}
	
	/*========= 이미지 BLOB 처리 ===========*/
	public void setUpload(MultipartFile upload) throws IOException {
		//photo세팅 (MultipartFile -> byte[])
		setPhoto(upload.getBytes());
		
		//filename세팅 (파일명)
		setPhotoname(upload.getOriginalFilename());
	}
	
	//Getters and Setters
	public int getMem_num() {
		return mem_num;
	}

	public String getId() {
		return id;
	}

	public int getAuth() {
		return auth;
	}

	public String getName() {
		return name;
	}

	public String getPasswd() {
		return passwd;
	}

	public String getPhone() {
		return phone;
	}

	public String getEmail() {
		return email;
	}

	public String getZipcode() {
		return zipcode;
	}

	public String getAddress1() {
		return address1;
	}

	public String getAddress2() {
		return address2;
	}

	public byte[] getPhoto() {
		return photo;
	}

	public String getPhotoname() {
		return photoname;
	}

	public Date getReg_date() {
		return reg_date;
	}

	public Date getModify_date() {
		return modify_date;
	}

	public String getNow_passwd() {
		return now_passwd;
	}

	public void setMem_num(int mem_num) {
		this.mem_num = mem_num;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setAuth(int auth) {
		this.auth = auth;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	public void setPhotoname(String photoname) {
		this.photoname = photoname;
	}

	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}

	public void setModify_date(Date modify_date) {
		this.modify_date = modify_date;
	}

	public void setNow_passwd(String now_passwd) {
		this.now_passwd = now_passwd;
	}

	
	//byte[] photo 제외하고 출력
	@Override
	public String toString() {
		return "MemberVO [mem_num=" + mem_num + ", id=" + id + ", auth=" + auth + ", name=" + name + ", passwd="
				+ passwd + ", phone=" + phone + ", email=" + email + ", zipcode=" + zipcode + ", address1=" + address1
				+ ", address2=" + address2 + ", photoname=" + photoname + ", reg_date=" + reg_date + ", modify_date="
				+ modify_date + ", now_passwd=" + now_passwd + "]";
	}
	
	
}
