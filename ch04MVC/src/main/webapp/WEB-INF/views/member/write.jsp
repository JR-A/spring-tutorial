<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" %>
<%-- form 스프링 커스텀태그 사용 --%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 가입</title>
</head>
<body>
<form:form action="write.do" commandName="command">
	아이디 : <form:input path="id"/>
	<form:errors path="id"/>
	<br>
	비밀번호 : <form:password path="password"/>
	<form:errors path="password"/>
	<br>
	이름 : <form:input path="name"/>
	<form:errors path="name"/>
	<br>
	생년월일 : <input type="date" name="birth" id="birth" value="${command.birth}"> <%-- date 커스텀태그 없음 --%>
	<form:errors path="birth"/>
	<br>
	나이 : <input type="number" name="age" id="age" value="${command.age}"><%-- number 커스텀 태그 없음 --%>
	<form:errors path="age"/>
	<br>
	이메일 : <form:input path="email"/> <%-- 어노테이션 유효성 테스트 위해 email html태그가 아닌 커스텀태그 사용(태그에서 유효성 체크 하지 않고 서버단에서 체크하는것 테스트) --%>
	<form:errors path="email" />
	<br>
	<input type="submit" value="전송">
</form:form>
</body>
</html>