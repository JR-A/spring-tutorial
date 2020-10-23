<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 쓰기</title>
</head>
<body>
게시글 쓰기 입력 폼 : 
	<!-- 같은 경로이므로 /article/ 필요없음 -->
	<!-- 같은 주소 호출해도 전송방식에 따라 다른 메서드 호출!
		  폼에서 submit시 post방식으로 전송하므로 NewArticleController의 submit메서드 실행 -->
<form action="newArticle.do" method="post">
	<input type="hidden" name="parentId" value="10">
	제목 : <input type="text" name="title"><br>
	내용 : <textarea rows="5" cols="30" name="content"></textarea><br>
	<input type="submit" value="전송">
</form>
</body>
</html>