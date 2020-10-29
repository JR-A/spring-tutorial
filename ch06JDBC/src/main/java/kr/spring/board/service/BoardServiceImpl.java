package kr.spring.board.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.spring.board.dao.BoardDAO;
import kr.spring.board.vo.BoardCommand;

//@Service : Business Layer에서 Service. 트랜잭션 처리
			//빈이름
@Service("boardService")
public class BoardServiceImpl implements BoardService {
	
	@Resource
	private BoardDAO boardDAO; //컨테이너에 정의되어있으므로 주입받음(root-context.xml에서 빈 자동스캔)
	
	
	@Override
	public void insertBoard(BoardCommand board) {
		boardDAO.insertBoard(board); //하나의 트랜잭션만 사용하므로 AutoCommit사용
	}

	@Override
	public int getBoardCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<BoardCommand> getBoardList(int startRow, int endRow) {
		// TODO Auto-generated method stub
		return null;
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
