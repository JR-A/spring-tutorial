<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" %>
<%-- form 스프링 커스텀태그 사용 --%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%-- 메시지 읽어오기. 웹 요청과 관련된 언어 정보를 이용해서 알맞은 언어의 메시지를 출력 --%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%-- spring접두사 이용해 label.properties에 명시한 key 써줌! --%>
<title><spring:message code="login.form.title"/></title>
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
<form:form action="login.do" commandName="login">
	<form:errors element="div"/> <%-- 필드가 없는 에러메세지는 모두 이곳에 표시 --%>
	<p>
		<label for="id"><spring:message code="login.form.id"/></label>
		<form:input path="id"/> <%-- path를 자바빈의 프로퍼티(field)명과 같게 쓰면 바인딩시켜줌 --%>
		<form:errors path="id"/> <%-- 유효성 체크 --%> <%-- 에러있으면 span태그 만들어짐 --%>
	</p>
	<p>
		<label for="password"><spring:message code="login.form.password"/></label>
		<form:password path="password"/>
		<form:errors path="password"/>
	</p>
	<p>
		<input type="submit" value="<spring:message code="login.form.submit"/>"> <%-- submit은 커스텀태그 없음 --%>
	</p>
</form:form>
</body>
</html>