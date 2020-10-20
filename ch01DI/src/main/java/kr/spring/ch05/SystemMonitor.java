package kr.spring.ch05;

public class SystemMonitor {
	private long periodTime;
	private SmsSender sender;
	
	//SystemMonitor는 SmsSender에 의존!
	//SmsSender 객체 먼저 생성되어 주어져야만 SystemMonitor 객체 생성 가능
	public SystemMonitor(long periodTime, SmsSender sender) {
		this.periodTime = periodTime;
		this.sender = sender;
	}

	@Override
	public String toString() {
		return "SystemMonitor [periodTime=" + periodTime + ", sender=" + sender + "]";
	}
	
}
