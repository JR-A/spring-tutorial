<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>리포트 등록 완료</title>
</head>
<body>
리포트 <b>${report.subject}</b>를 등록했습니다.<br>
업로드한 파일명 : ${report.reportFile.originalFilename} <%-- EL은 원칙적으로 .으로 프로퍼티에 접근 --%>
</body>
</html>