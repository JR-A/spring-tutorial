package kr.spring.ch09.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Range;	//Hibernate Validator 라이브러리

/*
 * 어노테이션을 이용한 유효성 체크
 */
public class MemberCommand {
	//프로퍼티
	@NotEmpty
	private String id;
	@Size(min=4,max=10)		//Size는 문자열의 개수 검사하므로 int형에는 사용할수 없음
	private String password;
	@NotEmpty
	private String name;
	@NotEmpty
	private String birth;
	@Range(min=1,max=200)	//Hibernate라이브러리 - 숫자 최소,최댓값 검사
	private int age;
	@Email
	@NotEmpty
	private String email;
	
	//Getters and Setters
	public String getId() {
		return id;
	}
	public String getPassword() {
		return password;
	}
	public String getName() {
		return name;
	}
	public String getBirth() {
		return birth;
	}
	public int getAge() {
		return age;
	}
	public String getEmail() {
		return email;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public void setEmail(String email) {
		this.email = email;
	}
		
}
