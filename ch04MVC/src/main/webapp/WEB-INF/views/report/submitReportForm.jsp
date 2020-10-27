<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" %>
<%-- form 스프링 커스텀태그 사용 --%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>리포트 등록 폼</title>
</head>
<body>
<%-- 파일 업로드 위해 인코딩타입 지정 --%>
<form:form action="submitReport.do" enctype="multipart/form-data" commandName="report">
	<p>
		<label for="subject">제목</label>
		<form:input path="subject"/>
		<form:errors path="subject"/>
	</p>
	<p>
		<label for="reportFile">리포트 파일</label>
		<input type="file" id="reportFile" name="reportFile"> <%-- file타입 input은 커스텀태그 없음. html태그사용 --%>
		<form:errors path="reportFile"/>
	</p>
	<p>
		<input type="submit" value="리포트 전송"> <%-- submit은 커스텀태그 없음 --%>
	</p>
</form:form>
</body>
</html>