<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Spring</title>
</head>
<body>
<a href="${pageContext.request.contextPath}/hello.do">HelloController</a><br> <!-- 클라이언트의 요청 -->
<!-- get방식으로 데이터 전달 : ?query=Seoul -->
<a href="${pageContext.request.contextPath}/search/internal.do?query=Seoul&p=10">SearchController - internal.do</a><br>
<a href="${pageContext.request.contextPath}/search/external.do?query=Busan&p=20">SearchController - external.do</a><br>
<!-- post방식으로 데이터 전달 -->
<!-- 같은 주소 호출해도 전송방식에 따라 다른 메서드 호출!
	 get방식으로 전송하므로 NewArticleController의 form메서드 실행 -->
<a href="${pageContext.request.contextPath}/article/newArticle.do">NewArticleController</a><br>
<a href="${pageContext.request.contextPath}/cookie/make.do">CookieController - make.do</a><br>
<a href="${pageContext.request.contextPath}/cookie/view.do">CookieController - view.do</a><br>
<a href="${pageContext.request.contextPath}/search/main.do">GameSearchController</a><br>
<a href="${pageContext.request.contextPath}/account/create.do">CreateAccountController</a><br>
<a href="${pageContext.request.contextPath}/login/login.do">LoginController</a><br>
<a href="${pageContext.request.contextPath}/report/submitReport.do">SubmitReportController</a><br>

</body>
</html>