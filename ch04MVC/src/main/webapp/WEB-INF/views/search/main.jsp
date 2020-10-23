<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게임 검색 메인</title>
</head>
<body>
<!-- 전송하면 ~/search/game.do?type=1&query=어쩌구 형식으로 전달 -->
<form action="game.do" method="get">
	<select name="type">
		<option value="1">전체</option>
		<option value="2">아이템</option>
		<option value="3">캐릭터</option>
	</select>
	<input type="text" name="query">
	<input type="submit" value="검색">
</form>
</body>
</html>