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
	
	//글 등록
	@Override
	public void insertBoard(BoardCommand board) {
		sqlSession.insert("insertBoard", board);
	}

	//총 게시글 수(레코드 수) 반환
	@Override
	public int getBoardCount() {
		Integer count = sqlSession.selectOne("getBoardCount");
		
		return count;
	}

	//게시글 목록 반환(데이터 베이스에 저장된 데이터(글)의 행번호 startRow~endRow까지)
	@Override
	public List<BoardCommand> getBoardList(Map<String, Object> map) {
		List<BoardCommand> list = sqlSession.selectList("getBoardList", map);
		
		return list;
	}

	//글 번호로 게시글 반환
	@Override
	public BoardCommand getBoard(int num) {
		BoardCommand board = sqlSession.selectOne("getBoard", num);
		
		return board;
	}
	
	//글 수정
	@Override
	public void updateBoard(BoardCommand board) {
		sqlSession.update("updateBoard", board);
	}
	
	//글 삭제
	@Override
	public void deleteBoard(int num) {
		sqlSession.delete("deleteBoard", num);
	}

}
