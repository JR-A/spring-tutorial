package kr.spring.board.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import kr.spring.board.vo.BoardCommand;

/*
 *	JDBC Template 사용법
 *	1. qeuryForObject
 *		1) Querying for an Integer
 *		2) Querying for an String
 *		3) Querying and returning an object(하나의 객체)
 *			Integer, String은 컴파일러가 해당 타입에 맞게 매핑해주지만, 우리가 만든 객체는 직접 Mapping Logic을 구현해야함 
 *			(RowMapper<T> 인터페이스 implements하는 구현객체 필요)
 *  2. query
 *  	:Querying and returning multiple objects(여러 개 객체)
 *  3. update
 *  	1) Inserting a row into the table
 *  	2) Updating a row into the table
 *  	3) Deleting a row into the table
 *  
 */

//@Repository : Persistence Layer에서 DAO. 영속성을 가지는 속성(파일, 데이터베이스 등)
			//빈이름
@Repository("boardDAO")
public class BoardDAOImpl implements BoardDAO{
	
	//sql문 정의
	private static final String INSERT_SQL = 
			"INSERT INTO zboard (num, writer, title, passwd, content, reg_date) VALUES (zboard_seq.nextval, ?, ?, ?, ?, SYSDATE)";
	private static final String SELECT_COUNT_SQL = 
			"SELECT COUNT(*) FROM zboard";
	private static final String SELECT_LIST_SQL = 
			"SELECT * FROM "
			+ 		"(SELECT a.*, ROWNUM rnum FROM "
			+ 				"(SELECT * FROM zboard ORDER BY reg_date DESC) a) "
			+ "WHERE rnum >= ? AND rnum <= ?";
	/*
	 *	ROWNUM은 행번호. subquery의 질의결과(등록일 기준 내림차순정렬된 전체 게시글)를 테이블처럼 인식하므로 a라고 테이블 알리아스 지정. a.*로 테이블의 모든 컬럼 출력
	 *	ROWNUM에 rnum 알리아스 지정하고 WHERE절에서 rnum 조건 걸어 페이징 처리 (rnum의 범위를 지정하여 해당 행번호인 레코드 반환)
	 */
	
	@Resource
	private JdbcTemplate jdbcTemplate;	//컨테이너에 정의되어있으므로 주입받음(root-context.xml참고)

	//한 개의 레코드 정보를 처리하는 익명구현객체 - 재활용성 높임(글 목록, 상세페이지에 활용)
												//RowMapper<BoardCommand>인터페이스를 implements하는 익명구현객체
	private RowMapper<BoardCommand> rowMapper = new RowMapper<BoardCommand>(){
		@Override
		public BoardCommand mapRow(ResultSet rs, int rowNum) throws SQLException {
			
			BoardCommand board = new BoardCommand();
			board.setNum(rs.getInt("num"));
			board.setWriter(rs.getString("writer"));
			board.setTitle(rs.getString("title"));
			board.setPasswd(rs.getString("passwd"));
			board.setContent(rs.getString("content"));
			board.setReg_date(rs.getDate("reg_date"));
			
			return board;
		}
	};
	
		
	//글 등록
	@Override
	public void insertBoard(BoardCommand board) {	
		jdbcTemplate.update(INSERT_SQL, new Object[] {board.getWriter(), board.getTitle(), board.getPasswd(), board.getContent()});
		//update메서드 하나로 INSERT, UPDATE, DELETE query 모두 수행
	}
	
	//총 게시글 수(레코드 수) 반환
	@Override
	public int getBoardCount() {
		return jdbcTemplate.queryForObject(SELECT_COUNT_SQL, Integer.class);	//하나의 데이터 반환할 때는 queryForObject 함수 사용
	}
	
	//게시글 목록 반환(데이터 베이스에 저장된 데이터(글)의 행번호 startRow~endRow까지)
	@Override
	public List<BoardCommand> getBoardList(int startRow, int endRow) {
		List<BoardCommand> list = jdbcTemplate.query(SELECT_LIST_SQL, new Object[] {startRow, endRow}, rowMapper);
		
		return list;
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
