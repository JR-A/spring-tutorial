<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게임 검색 결과</title>
</head>
<body>
전송한 데이터 <br>
검색 항목 : ${command.type}<br>
검색어 : ${command.query}<br>
<br>
검색 결과 : ${searchResult}
</body>
</html>