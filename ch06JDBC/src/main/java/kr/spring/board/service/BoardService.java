package kr.spring.board.service;

import java.util.List;

import kr.spring.board.vo.BoardCommand;

public interface BoardService {
	public void insertBoard(BoardCommand board);
	public int getBoardCount();	//페이징처리
	
	public List<BoardCommand> getBoardList(int startRow, int endRow);
	public BoardCommand getBoard(int num);
	public void updateBoard(BoardCommand board);
	public void deleteBoard(int num);
}
