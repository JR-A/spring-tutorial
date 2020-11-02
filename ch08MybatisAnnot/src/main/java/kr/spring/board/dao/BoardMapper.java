package kr.spring.board.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.spring.board.vo.BoardCommand;

//sql문이 길거나, 동적sql문의 경우 BoardMapper.xml에 명시! Annotation은 짧은 sql문 명시하는 데 사용!

//xml파일과 명칭 똑같아야 자동매핑 (BoardMapper)
//BoardDAO -> BoardMapper로 명칭 변경
public interface BoardMapper {
	public void insertBoard(BoardCommand board);
	@Select("SELECT COUNT(*) FROM zboard")		//어노테이션 방식 적용
	public int getBoardCount();
	public List<BoardCommand> getBoardList(Map<String, Object> map);
	@Select("SELECT * FROM zboard WHERE num=#{num}")
	public BoardCommand getBoard(int num);
	@Update("UPDATE zboard SET writer=#{writer}, title=#{title}, content=#{content} WHERE num=#{num}")
	public void updateBoard(BoardCommand board);
	public void deleteBoard(int num);
}
