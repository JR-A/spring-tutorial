<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" %>
<%-- form 스프링 커스텀태그 사용 --%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 삭제 폼</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css" type="text/css">
</head>
<body>
<div class="page-main-style">
	<h2>글 삭제</h2>
	<form:form action="delete.do" commandName="boardCommand">
		<form:hidden path="num"/>	<%-- hidden으로 Primary key가짐. 삭제시 넘겨줌 --%>
		<ul>
			<li>
				<label for="passwd">비밀번호</label>
				<form:password path="passwd"/>
				<form:errors path="passwd" cssClass="error-color"/>
			</li>
		</ul>
		<div class="align-center">
			<input type="submit" value="삭제">
			<input type="button" value="홈으로" onclick="location.href='list.do'">
		</div>
	</form:form>
</div>
</body>
</html>