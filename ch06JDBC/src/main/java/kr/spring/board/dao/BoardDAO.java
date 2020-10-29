package kr.spring.board.dao;

import java.util.List;

import kr.spring.board.vo.BoardCommand;

//interface이므로 구현체 {} 없음
/*
 * 프로젝트가 커질수록 유용. 인터페이스를 implements하는 클래스가 반드시 추상메소드 구현하도록 강요
 * 설계자의 의도대로 구현하도록 함
 */
public interface BoardDAO {
	public void insertBoard(BoardCommand board);
	public int getBoardCount();	//페이징처리
	
	public List<BoardCommand> getBoardList(int startRow, int endRow);
	public BoardCommand getBoard(int num);
	public void updateBoard(BoardCommand board);
	public void deleteBoard(int num);
	
}
