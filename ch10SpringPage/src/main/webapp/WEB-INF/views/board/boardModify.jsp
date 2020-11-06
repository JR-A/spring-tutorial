<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" %>
<!-- jstl의 core 라이브러리 사용 -->	
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%-- form 스프링 커스텀태그 사용 --%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div class="page-main-style">
	<h2>글 수정</h2>
	<form:form action="update.do" commandName="boardVO" encType="multipart/form-data">
		<form:hidden path="board_num"/> <%-- 글 번호 서버에 전송하기위함 --%>
		<%-- 필드가 없는 에러 표시 --%>
		<form:errors element="div" cssClass="error-color"/>
		<ul>
			<li>
				<label for="title">제목</label>
				<form:input path="title"/>
				<form:errors path="title" cssClass="error-color"/>
			</li>
			<li>
				<label for="content">내용</label>
				<form:textarea path="content"/>
				<form:errors path="content" cssClass="error-color"/>
			</li>
			<li>
				<label for="upload">이미지 파일 업로드</label>	 <%-- 주의)유효성체크로는 사용할 수 없음! 모든파일 선택하여 아무 파일 올릴수도있음 --%>
				<input type="file" id="upload" name="upload" accept="image/gif, image/png, image/jpeg">
				<%-- 기존 파일이 있으면 메시지 표시 --%>
				<c:if test="${!empty boardVO.filename}">
				<br>
				<span>(${boardVO.filename})파일이 등록되어 있습니다.
				 다시 업로드하면 기존 파일은 삭제됩니다.</span>
				</c:if>
			</li>
		</ul>
		<div class="align-center">
			<input type="submit" value="수정">
			<input type="button" value="목록" onclick="location.href='list.do'">
		</div>
	</form:form>
</div>