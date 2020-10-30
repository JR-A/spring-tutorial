package kr.spring.board.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.spring.board.dao.BoardDAO;
import kr.spring.board.vo.BoardCommand;

//@Service : Business Layer에서 Service. 트랜잭션 처리
			//빈이름
@Service("boardService")
public class BoardServiceImpl implements BoardService {
	/*
	 * DAO는 db에 접근해서 한 메서드에 하나의 sql문밖에 적용할 수 없으므로,
	 * DAO의 메서드 여러개(여러번의 sql문 실행)를 묶어 하나의 작업단위로 만든다(트랜잭션 처리)
	 */
	
	@Resource
	private BoardDAO boardDAO; //컨테이너에 정의되어있으므로 주입받음(root-context.xml에서 빈 자동스캔)
	
	
	@Override
	public void insertBoard(BoardCommand board) {
		boardDAO.insertBoard(board); //하나의 트랜잭션만 사용하므로 AutoCommit사용
	}

	@Override
	public int getBoardCount() {
		return boardDAO.getBoardCount();
	}

	@Override
	public List<BoardCommand> getBoardList(Map<String, Object> map) {
		return boardDAO.getBoardList(map);
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
