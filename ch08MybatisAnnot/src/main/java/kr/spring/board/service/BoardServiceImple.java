package kr.spring.board.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.spring.board.dao.BoardMapper;
import kr.spring.board.vo.BoardCommand;

			//빈이름
@Service("boardService")
public class BoardServiceImple implements BoardService {
	
	//프로퍼티
	@Resource
	BoardMapper boardMapper;	//DAO대신 Mapper라는 명칭 사용
	
	@Override
	public void insertBoard(BoardCommand board) {
		boardMapper.insertBoard(board);
	}

	@Override
	public int getBoardCount() {
		return boardMapper.getBoardCount();
	}

	@Override
	public List<BoardCommand> getBoardList(Map<String, Object> map) {
		return boardMapper.getBoardList(map);
	}

	@Override
	public BoardCommand getBoard(int num) {
		return boardMapper.getBoard(num);
	}

	@Override
	public void updateBoard(BoardCommand board) {
		boardMapper.updateBoard(board);
	}

	@Override
	public void deleteBoard(int num) {
		boardMapper.deleteBoard(num);
	}

}
