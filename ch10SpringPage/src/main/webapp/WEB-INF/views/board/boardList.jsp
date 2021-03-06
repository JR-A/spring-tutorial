<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> <!-- jstl의 core 라이브러리 사용 -->

<div class="page-main-style">
	<h2>게시판 목록</h2>
	<form id="search_form" action="list.do" method="get"> <!-- 검색처리하기 위해 get방식으로 전송 -->
		<ul class="search">
			<li>
				<select name="keyfield" id="keyfield">	<!-- 콤보박스(검색항목) -->
					<option value="title">제목</option>
					<option value="id">아이디</option>
					<option value="content">내용</option>
					<option value="all">전체</option>
				</select>
			</li>
			<li>
				<input type="text" name="keyword" id="keyword"> <!-- 검색내용 -->
			</li>
			<li>
				<input type="submit" value="찾기">
				<input type="button" value="목록" onclick="location.href='list.do'">
			</li>
		</ul>
	</form>
	<div class="align-right">
	<c:if test="${!empty user}"> <!-- 로그인된 상태이면 -->
	<input type="button" value="글쓰기" onclick="location.href='write.do'">
	</c:if>
	</div>
	
	<c:if test="${count == 0}"> <%-- count(전체 글 개수)가 0이면 메시지 표시 --%>
	<div class="align-center">
		등록된 게시물이 없습니다.
	</div>
	</c:if>
	
	<c:if test="${count > 0}"> <%-- count가 0보다 크면(게시글이 존재하면) 게시글목록 테이블 표시 --%>
	<table>
		<tr>
			<th>글 번호</th>
			<th width="400">제 목</th>
			<th>작성자</th>
			<th>최근 수정일</th>
			<th>조회수</th>
		</tr>
		<c:forEach var="board" items="${list}">	<%--list의 board객체정보를 차례로 출력 --%>
		<tr>
			<td>${board.board_num}</td>
			<td><a href="detail.do?board_num=${board.board_num}">${board.title}</a></td>  <%-- get방식으로 primary key넘겨줌 --%>
			<td>${board.id}</td>
			<td>${board.modify_date}</td>
			<td>${board.hit}</td>
		</tr>
		</c:forEach>
	</table>
	
	<div class="align-center">
		${pagingHtml} 	<%-- 페이지 표시 문자열 --%>
	</div>
	</c:if>
	
</div>