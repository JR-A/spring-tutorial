<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" %>
<%-- form 스프링 커스텀태그 사용 --%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>계정 생성</title>
</head>
<body>
<%-- 
	자바스크립트로 유효성 체크하는 것은 서버에 보내기 전에 이루어짐
	 form 커스텀태그 사용한 유효성 체크는 서버에 전송 후 서버단에서 이루어짐
 --%>

<%-- 
	스프링 form 커스텀태그는 method명시하지 않으면 기본값은 post방식 ( cf.html태그는 기본값이 get )
	commandName을 생략하면 기본값은 "command"
--%>
						<%-- request에 저장된 command(자바빈)가져옴 --%>
<form:form action="create.do" commandName="command">
				<%-- path를 자바빈의 프로퍼티(field)명과 같게 쓰면 바인딩시켜줌 --%>
	아이디 : <form:input path="id"/>   <%-- type="text"임 --%>
	<form:errors path="id"/> <%-- 유효성 체크 --%> <%-- 에러있으면 span태그 만들어짐 --%>
	<br>
	이름 : <form:input path="name"/>
	<form:errors path="name"/>
	<br>
	우편번호 : <form:input path="zipcode"/>
	<form:errors path="zipcode"/>
	<br>
	주소1 : <form:input path="address1"/>
	<form:errors path="address1"/>
	<br>
	주소2 : <form:input path="address2"/>	
	<form:errors path="address2"/>
	<br>
	<input type="submit" value="전송"> <%-- submit은 커스텀태그 없음 --%>
</form:form>
</body>
</html>