<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> <!-- jstl의 core 라이브러리 사용 -->
	
<div class="page-main-style">
	<h2>${board.title}</h2>
	<ul>
		<li>번호 : ${board.board_num}</li>
		<li>작성자 : ${board.id}</li>
		<li>조회수 : ${board.hit}</li>
		<li>작성일 : ${board.reg_date}</li>
		<li>최근 수정일 : ${board.modify_date}</li>
	</ul>
	<hr size="1" width="100%">
	<c:if test="${!empty board.filename}">	<%-- 이미지가 있으면(filename이 비어있지않으면) 표시 --%>
	<div class="align-center">
		<img src="imageView.do?board_num=${board.board_num}" style="max-width: 500px;">
	</div>
	</c:if>
	<p>
		${board.content}
	</p>
	<hr size="1" width="100%">
	<div class="align-right">
		<%-- 수정, 삭제 버튼은 '로그인된 상태이고, 로그인한 회원번호와 작성자 회원번호가 일치할 때' 표시 --%>
		<c:if test="${!empty user && user.mem_num == board.mem_num }">
			<input type="button" value="수정" onclick="location.href='update.do?board_num=${board.board_num}'">
			<input type="button" value="삭제" onclick="location.href='delete.do?board_num=${board.board_num}'">
		</c:if>
		<input type="button" value="목록" onclick="location.href='list.do'">
	</div>
</div>