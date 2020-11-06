<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" %>
<%-- form 스프링 커스텀태그 사용 --%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div class="page-main-style">
	<h2>글쓰기</h2>
	<form:form action="write.do" commandName="boardVO" encType="multipart/form-data">
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
			</li>
		</ul>
		<div class="align-center">
			<input type="submit" value="등록">
			<input type="button" value="목록" onclick="location.href='list.do'">
		</div>
	</form:form>
</div>