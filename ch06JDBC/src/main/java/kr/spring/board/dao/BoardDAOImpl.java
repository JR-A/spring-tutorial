package kr.spring.board.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.spring.board.vo.BoardCommand;

//@Repository : Persistence Layer에서 DAO. 영속성을 가지는 속성(파일, 데이터베이스 등)
			//빈이름
@Repository("boardDAO")
public class BoardDAOImpl implements BoardDAO{
	
	//sql문 정의
	private static final String INSERT_SQL = "INSERT INTO zboard (num, writer, title, passwd, content, reg_date) VALUES (zboard_seq.nextval, ?, ?, ?, ?, SYSDATE)";
	
	@Resource
	private JdbcTemplate jdbcTemplate;	//컨테이너에 정의되어있으므로 주입받음(root-context.xml참고)
	
	//글 등록
	@Override
	public void insertBoard(BoardCommand board) {
		jdbcTemplate.update(INSERT_SQL, new Object[] {board.getWriter(), board.getTitle(), board.getPasswd(), board.getContent()});
	}

	@Override
	public int getBoardCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<BoardCommand> getBoardList(int startRow, int endRow) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BoardCommand getBoard(int num) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateBoard(BoardCommand board) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteBoard(int num) {
		// TODO Auto-generated method stub
		
	}

}
