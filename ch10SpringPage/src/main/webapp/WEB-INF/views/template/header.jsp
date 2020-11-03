<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> <!-- jstl의 core 라이브러리 사용 -->

<h2 class="align-center">회원제 게시판</h2>
<div class="align-right">
	<c:if test="${!empty user && !empty user.photoname}"> <!-- 회원사진이 있으면 표시 -->
	<img src="${pageContext.request.contextPath}/upload/member/${user.photo}" class="my-photo">
	</c:if>
	<c:if test="${!empty user && empty user.photoname}"> <!-- 회원사진이 없으면 blank 이미지 표시 -->
	<img src="${pageContext.request.contextPath}/resources/images/blank.jpg" width="25" height="25" class="my-photo">
	</c:if>
	<c:if test="${!empty user}"> <!-- 로그인된 상태이면 -->
	[<span>${user.id}</span>]
	<a href="${pageContext.request.contextPath}/member/logout.do">로그아웃</a>
	<a href="${pageContext.request.contextPath}/member/myPage.do">My페이지</a>
	</c:if>
	<c:if test="${empty user}"> <!--  로그인되지 않은 상태이면 -->
	<a href="${pageContext.request.contextPath}/member/registerUser.do">회원가입</a>
	<a href="${pageContext.request.contextPath}/member/login.do">로그인</a>
	</c:if>
	<!-- 로그인 여부와 관계없이 항상 노출 -->
	<a href="${pageContext.request.contextPath}/main/main.do">홈으로</a> 
</div>