<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" %>
<%-- form 스프링 커스텀태그 사용 --%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div class="page-main-style">
	<h2>회원 정보 수정</h2>
	<form:form action="update.do" commandName="member">
		<%-- hidden으로 mem_num넣어 가져다쓰거나, session에 저장된 user정보 가져다 쓰기  --%>
		<%-- primary key의 보안 위해 가급적 세션 통해 쓰는것이 좋음 --%>
		
		<%-- 필드가 없는 에러 표시 --%>
		<form:errors element="div" cssClass="error-color"/>
		<ul>
			<li>
				<label for="name">이름</label>
				<form:input path="name"/>
				<form:errors path="name" cssClass="error-color"/>
			</li>
			<li>
				<label for="phone">전화번호</label>
				<form:input path="phone"/>
				<form:errors path="phone" cssClass="error-color"/>
			</li>
			<li>
				<label for="email">이메일</label> <%-- 어노테이션 유효성 테스트 위해 email html태그가 아닌 커스텀태그 사용(태그에서 유효성 체크 하지 않고 서버단에서 체크하는것 테스트) --%>
				<form:input path="email"/>
				<form:errors path="email" cssClass="error-color"/>
			</li>
			<li>
				<label for="zipcode">우편번호</label>
				<form:input path="zipcode"/>
				<form:errors path="zipcode" cssClass="error-color"/>
			</li>
			<li>
				<label for="address1">주소</label>
				<form:input path="address1"/>
				<form:errors path="address1" cssClass="error-color"/>
			</li>
			<li>
				<label for="address2">상세주소</label>
				<form:input path="address2"/>
				<form:errors path="address2" cssClass="error-color"/>
			</li>
		</ul>
		<div class="align-center">
			<input type="submit" value="수정">
			<input type="button" value="홈으로 " onclick="location.href='${pageContext.request.contextPath}/main/main.do'">
		</div>
	</form:form>
</div>