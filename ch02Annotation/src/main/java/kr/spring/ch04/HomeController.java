package kr.spring.ch04;

import javax.annotation.Resource;

public class HomeController {
	//@Resource : 빈 '이름'과 프로퍼티명이 일치하면 의존 관계 설정
	@Resource
	private Camera camera2;
	//@Resource(name="빈 객체의 이름") : (프로퍼티명과 빈 객체 이름 다를경우) 빈 객체의 이름 지정
	@Resource(name="cameraZ")
	private Camera camera3;
	private Camera camera4;
	
	public void setCamera2(Camera camera2) {
		this.camera2 = camera2;
	}
	public void setCamera3(Camera camera3) {
		this.camera3 = camera3;
	}
	//메서드에도 명시 가능
	@Resource
	public void setCamera4(Camera camera4) {
		this.camera4 = camera4;
	}
	
	@Override
	public String toString() {
		return "HomeController [camera2=" + camera2 + ", camera3=" + camera3 + ", camera4=" + camera4 + "]";
	}
	
}
