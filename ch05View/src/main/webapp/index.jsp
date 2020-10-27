<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Spring</title>
</head>
<body>
<%-- /login 은 view하위의 login폴더 생성여부와는 무관함. 그룹별로 의미있게 나누기 위해 임의로 지어준 이름! --%>
<a href="${pageContext.request.contextPath}/login/login.do">LoginController</a><br>
<a href="${pageContext.request.contextPath}/changeLanguage.do?lang=ko">LocaleChangeController - /changeLanguage.do?lang=ko</a><br>
<a href="${pageContext.request.contextPath}/changeLanguage.do?lang=en">LocaleChangeController - /changeLanguage.do?lang=en</a><br>

</body>
</html>