package kr.spring.board.dao;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import kr.spring.board.vo.BoardCommand;

//@Repository : Persistence Layer에서 DAO. 영속성을 가지는 속성(파일, 데이터베이스 등)
			//빈이름
@Repository("boardDAO")
public class BoardDAOImpl implements BoardDAO {	
	
	@Resource
	private SqlSessionTemplate sqlSession;
	
	@Override
	public void insertBoard(BoardCommand board) {
		sqlSession.insert("insertBoard", board);
	}

	@Override
	public int getBoardCount() {
		Integer count = sqlSession.selectOne("getBoardCount");
		
		return count;
	}

	@Override
	public List<BoardCommand> getBoardList(Map<String, Object> map) {
		List<BoardCommand> list = sqlSession.selectList("getBoardList", map);
		
		return list;
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
