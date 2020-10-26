<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" %>
<%-- form 스프링 커스텀태그 사용 --%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
</head>
<body>
<%-- 
	자바스크립트로 유효성 체크하는 것은 서버에 보내기 전에 이루어짐
	 form 커스텀태그 사용한 유효성 체크는 서버에 전송 후 서버단에서 이루어짐
 --%>
<%-- 
	스프링 form 커스텀태그는 method명시하지 않으면 기본값은 post방식 ( cf.html태그는 기본값이 get )
	commandName을 생략하면 기본값은 "command"
--%>					<%-- request에 저장된 command(자바빈)가져옴 --%>
 <form:form action="login.do" commandName="loginCommand">
 	<%-- 기본적으로 에러메시지는 span태그에 표시. element="div"라고 명시하면 div태그에 에러메시지 표시됨(줄바꿈효과) --%>
	<%-- 필드가 없는 경우(reject로 에러생성시) path속성 사용할 수 없음. 필드없는 에러메시지가 표시됨 --%>
 	<form:errors element="div"/>	
 			 <%-- path를 자바빈의 프로퍼티(field)명과 같게 쓰면 바인딩시켜줌 --%>
 	아이디 : <form:input path="userId"/>
 	<form:errors path="userId"/>  <%-- 유효성 체크 --%> <%-- 에러있으면 span태그 만들어짐 --%>
 	<br>
 	비밀번호 : <form:password path="password"/>
 	<form:errors path="password"/>
 	<br>
 	<input type="submit" value="전송">
 </form:form>
</body>
</html>