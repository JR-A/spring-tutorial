package kr.spring.board.service;

import java.util.List;
import java.util.Map;

import kr.spring.board.vo.BoardCommand;

//interface이므로 구현체 {} 없음
public interface BoardService {
	public void insertBoard(BoardCommand board);
	public int getBoardCount();
	public List<BoardCommand> getBoardList(Map<String, Object> map);
	public BoardCommand getBoard(int num);
	public void updateBoard(BoardCommand board);
	public void deleteBoard(int num);
}
