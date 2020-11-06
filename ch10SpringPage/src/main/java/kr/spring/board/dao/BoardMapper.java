package kr.spring.board.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;

import kr.spring.board.vo.BoardVO;

//동적 sql, 복잡한 sql문은 어노테이션 방식으로 하지 않고 xml파일에 명시함
public interface BoardMapper {
	public List<BoardVO> selectList(Map<String, Object> map);
	public int selectRowCount(Map<String, Object> map);
	@Insert("INSERT INTO spboard (board_num, title, content, uploadfile, filename, ip, mem_num) VALUES (board_seq.nextval, #{title}, #{content}, #{uploadfile}, #{filename}, #{ip}, #{mem_num})")
	public void insertBoard(BoardVO board);
	public BoardVO selectBoard(Integer board_num);
	public void updateHit(Integer board_num);
	public void updateBoard(BoardVO board);
	public void deleteBoard(Integer board_num);
}
