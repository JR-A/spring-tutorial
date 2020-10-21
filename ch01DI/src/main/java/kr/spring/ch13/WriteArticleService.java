package kr.spring.ch13;

//자동(ByName)
public class WriteArticleService {
	//프로퍼티
	private WriteArticleDAO writeArticleDAO;
	
	//Setter. 객체를 주입받는 메서드
	public void setWriteArticleDAO(WriteArticleDAO writeArticleDAO) {
		this.writeArticleDAO = writeArticleDAO;
	}
	
	public void write() {
		System.out.println("WriteArticleService의 write() 메서드 실행");
		
		writeArticleDAO.insert();
	}
}
