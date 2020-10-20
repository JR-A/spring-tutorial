package kr.spring.ch04;

public class WriteArticleService {
	private WriteArticleDAO writeArticleDAO;
	
	//WriteArticleService는 WriteArticleDAO에 의존!
	//WriteArticleDAO 객체 먼저 생성되어 주어져야만 WriteArticleService 객체 생성 가능
	public WriteArticleService(WriteArticleDAO writeArticleDAO) {
		this.writeArticleDAO = writeArticleDAO;
	}
	
	public void write() {
		System.out.println("WriteArticleService의 write() 메서드 실행");
		
		writeArticleDAO.insert();
	}
}
