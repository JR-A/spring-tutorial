<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" %>
<%-- form 스프링 커스텀태그 사용 --%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--jQuery 라이브러리 사용 --%>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.5.1.min.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		//이벤트 연결 - 비밀번호 변경 체크
		$('#passwd').keyup(function(){						//$(this)는 $('#passwd')
			if($('#confirm_passwd').val()!='' && $('#confirm_passwd').val()!=$(this).val()){
				$('#message_id').text('비밀번호 불일치').css('color', 'red');
			}else if($('#confirm_passwd').val()!='' && $('#confirm_passwd').val()==$(this).val()){
				$('#message_id').text('비밀번호 일치').css('color', '#000');
			}
		});
		//이벤트 연결
		$('#confirm_passwd').keyup(function(){				//$(this)는 $('#confirm_passwd')
			if($('#passwd').val()!='' && $('#passwd').val()!=$(this).val()){
				$('#message_id').text('비밀번호 불일치').css('color', 'red');
			}else if($('#passwd').val()!='' && $('#passwd').val()==$(this).val()){
				$('#message_id').text('비밀번호 일치').css('color', '#000');
			}
		});
		
		//변경 버튼 클릭시
		$('#change_form').submit(function(){
			if($('#now_passwd').val()==''){
				alert('현재 비밀번호를 입력하세요!');
				$('#now_passwd').focus();
				return false;
			}
			if($('#passwd').val()==''){
				alert('변경 비밀번호를 입력하세요!');
				$('#passwd').focus();
				return false;
			}
			if($('#confirm_passwd').val()==''){
				alert('변경 비밀번호 확인을 입력하세요!');
				$('#confirm_passwd').focus();
				return false;
			}
			if($('#passwd').val()!=$('#confirm_passwd').val()){
				alert('변경 비밀번호와 변경 비밀번호 확인 불일치');
				return false;
			}
		});
	});
</script>
<div class="page-main-style">
	<h2>비밀번호 변경</h2>
	<form:form id="change_form" action="changePassword.do" commandName="memberVO">
		<%-- 필드가 없는 에러 표시 --%>
		<form:errors element="div" cssClass="error-color"/>
		<ul>
			<li>
				<label for="now_passwd">현재 비밀번호</label>
				<form:password path="now_passwd"/>
				<form:errors path="now_passwd" cssClass="error-color"/>
			</li>
			<li>
				<label for="passwd">변경 비밀번호</label>
				<form:password path="passwd"/>
				<form:errors path="passwd" cssClass="error-color"/>
			</li>
			<li>
				<label for="confirm_passwd">변경 비밀번호 확인</label>
				<input type="password" id="confirm_passwd">	<%-- 전송하지 않고(name필요없음) 자바스크립트로 체크만할 것임 --%>
				<span id="message_id" class="error-color"></span>
			</li>
		</ul>
		<div class="align-center">
			<input type="submit" value="변경">
			<input type="button" value="홈으로 " onclick="location.href='${pageContext.request.contextPath}/main/main.do'">
		</div>
	</form:form>
</div>