<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" %>
<%-- jstl 라이브러리 사용 --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> <%-- JSTL의 core라이브러리 사용하기 위해 링크 추가 --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 목록</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css" type="text/css">
</head>
<body>
<div class="page-main-style">
	<h2>게시판 목록</h2>
	<div class="align-right">
		<input type="button" value="글쓰기" onclick="location.href='insert.do'">
	</div>
	<c:if test="${count == 0}"> <%-- count(전체 글 개수)가 0이면 메시지 표시 --%>
		<div class="result-display">출력할 내용이 없습니다.</div>
	</c:if>
	<c:if test="${count > 0}"> <%-- count(전체 글 개수)가 1개 이상이면 게시글목록 테이블 표시 --%>
		<table>
			<tr>
				<th>번 호</th>
				<th>제 목</th>
				<th>작성자</th>
				<th>작성일</th>
			</tr>
			<c:forEach var="board" items="${list}"><%-- list의 board객체정보를 loop돌며 출력 --%>
			<tr>
				<td>${board.num}</td>
				<td><a href="detail.do?num=${board.num}">${board.title}</a></td> <%-- get방식으로 primary key넘겨줌 --%>
				<td>${board.writer}</td>
				<td>${board.reg_date}</td>
			</tr>
			</c:forEach>
		</table>
		<div class="align-center">${pagingHtml}</div> <%-- 페이지 표시 문자열 --%>
	</c:if>
</div>
</body>
</html>