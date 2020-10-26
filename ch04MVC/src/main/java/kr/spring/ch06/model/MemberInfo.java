package kr.spring.ch06.model;

public class MemberInfo {
	private String id;
	private String name;
	private String zipcode;
	private String address1;
	private String address2;
	
	//Getters and Setters
	public String getId() {
		return id;
	}
	public String getName() {
		return name;
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
	public void setId(String id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
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
	
	@Override
	public String toString() {
		return "MemberInfo [id=" + id + ", name=" + name + ", zipcode=" + zipcode + ", address1=" + address1
				+ ", address2=" + address2 + "]";
	}
	
}
