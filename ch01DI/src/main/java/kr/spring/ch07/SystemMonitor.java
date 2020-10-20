package kr.spring.ch07;

public class SystemMonitor {
	//프로퍼티
	private long periodTime;
	private SmsSender smsSender;
	
	//SystemMonitor는 SmsSender에 의존!
	//Setter. 객체를 주입받는 메서드 (DI 프로퍼티 방식으로 의존 관계 설정)
	public void setPeriodTime(long periodTime) {
		this.periodTime = periodTime;
	}
	public void setSmsSender(SmsSender smsSender) {
		this.smsSender = smsSender;
	}
	
	@Override
	public String toString() {
		return "SystemMonitor [periodTime=" + periodTime + ", smsSender=" + smsSender + "]";
	}

}
