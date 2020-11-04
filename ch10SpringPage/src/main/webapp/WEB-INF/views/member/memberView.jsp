<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> <!-- jstl의 core 라이브러리 사용 -->
<div class="page-main-style">
	<h2>프로필 사진</h2>
	<ul>
		<li>
			<%-- 이미지를 등록하지 않았으면 기본이미지 표시 --%>
			<c:if test="${empty member.photoname}">
			<img src="${pageContext.request.contextPath}/resources/images/blank.jpg" width="100" height="100" class="my-photo">
			</c:if>
			<%-- 이미지를 등록했으면 이미지 표시 --%>
			<c:if test="${!empty member.photoname}">
			<img src="${pageContext.request.contextPath}/member/photoView.do" width="100" height="100" class="my-photo">
			</c:if>
		</li>
		<li>
			<div class="align-center">
				<input type="button" value="수정" id="photo_btn">
			</div>
			<%-- ajax방식으로 수정버튼 누르면 보이도록 --%>
			<div id="photo_choice" style="display:none;">
				<input type="file" id="upload" accept="image/gif,image/png,img/jpeg">
				<input type="button" value="전송" id="photo_submit">
				<input type="button" value="취소" id="photo_reset">
			</div>
		</li>
	</ul>
	<h2>회원 상세 정보</h2>
	<ul>
		<li>이름 : ${member.name}</li>
		<li>전화번호 : ${member.phone}</li>
		<li>이메일 : ${member.email}</li>
		<li>우편번호 : ${member.zipcode}</li>
		<li>주소 : ${member.address1}</li>
		<li>상세주소 : ${member.address2}</li>
		<li>가입일 : ${member.reg_date}</li>
		<li>정보 수정일 : ${member.modify_date}</li>
	</ul>
	<hr size="1" width="100%">
	<p class="align-right">
		<input type="button" value="수정" onclick="location.href='update.do'">
		<input type="button" value="비밀번호변경" onclick="location.href='changePassword.do'">
		<input type="button" value="회원탈퇴" onclick="location.href='delete.do'">
	</p>
</div>