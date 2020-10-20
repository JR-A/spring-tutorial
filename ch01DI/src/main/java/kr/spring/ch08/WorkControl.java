package kr.spring.ch08;

public class WorkControl {
	//프로퍼티
	private long periodTime;
	private EmailSender emailSender;
	
	//WorkControl는 EmailSender에 의존!
	//Setter. 객체를 주입받는 메서드 (DI 프로퍼티 방식으로 의존 관계 설정)
	public void setPeriodTime(long periodTime) {
		this.periodTime = periodTime;
	}
	public void setEmailSender(EmailSender emailSender) {
		this.emailSender = emailSender;
	}
	
	@Override
	public String toString() {
		return "WorkControl [periodTime=" + periodTime + ", emailSender=" + emailSender + "]";
	}
	
}
