package kr.spring.ch14;

//자동(ByType)
public class SystemMonitor {
	//프로퍼티
	private PhoneCall call;

	//Setter. 객체를 주입받는 메서드
	public void setCall(PhoneCall call) {
		this.call = call;
	}

	@Override
	public String toString() {
		return "SystemMonitor [call=" + call + "]";
	}
}
