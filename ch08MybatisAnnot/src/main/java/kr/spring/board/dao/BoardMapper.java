package kr.spring.board.dao;

import java.util.List;
import java.util.Map;

import kr.spring.board.vo.BoardCommand;

//xml파일과 명칭 똑같아야 자동매핑 (BoardMapper)
//BoardDAO -> BoardMapper로 명칭 변경
public interface BoardMapper {
	public void insertBoard(BoardCommand board);
	public int getBoardCount();
	public List<BoardCommand> getBoardList(Map<String, Object> map);
	public BoardCommand getBoard(int num);
	public void updateBoard(BoardCommand board);
	public void deleteBoard(int num);
}
