package kr.spring.ch03.service;

import kr.spring.ch03.model.NewArticleCommand;

//트랜잭션 처리하기 목적
/*여러 sql문 사용하려면 DAO의 여러 메소드 호출해야하므로 그 메소드들을 묶어줌
추후 myBatis사용하면 DAO클래스 메소드 하나당 하나의 sql문만 사용가능*/
public class ArticleService {
	public void writeArticle(NewArticleCommand command) {
		System.out.println("게시글 처리 완료 : " + command);
	}
}
