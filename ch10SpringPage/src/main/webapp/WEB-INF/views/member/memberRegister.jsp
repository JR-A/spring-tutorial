<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" %>
<%-- form 스프링 커스텀태그 사용 --%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--jQuery 라이브러리 사용 --%>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.5.1.min.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		//아이디 중복체크 여부 플래그
		var checkId = 0;
		
		//ID중복체크 버튼 클릭이벤트 연결
		$('#confirmId').click(function(){
			//클라이언트 사이드에서 유효성체크(annotation이용한것은 서버단에서 하는 유효성체크)
			//아이디 공란인경우
			if($('#id').val()==''){
				$('#message_id').css('color', 'red').text('아이디를 입력하세요!');
				$('#id').focus();
				return; 	//클릭 이벤트 함수 종료 위해 return  cf)submit이면 return false해야 submit안되게 할수있음
			}
			
			//아이디가 4자이상 10자이하가 아닌경우
			if($('#id').val().length<4 || $('#id').val().length>10){
				$('#message_id').css('color', 'red').text('아이디는 4자이상 10자이하');
				$('#id').focus();
				return;
			}
			
			$('#message_id').text(); //로딩체크여부 메시지 초기화
			$('#loading').show();	//로딩이미지 노출
			
			//서버와 ajax비동기 통신
			$.ajax({
				url: 'confirmId.do',		//요청URL
				type: 'post',				//전송방식
				data: {id: $('#id').val()},	//서버에 전송할 데이터
				dataType: 'json',			//서버가 전송하는 데이터 형식
				cache: false,				//예전 데이터 저장해 사용하지 않도록 제한
				timeout: 30000,				//ms(1/1000초).  3초 지나도 정보 도착하지 않으면 포기
				success: function(data){	//서버로부터 데이터가 성공적으로 도착하면 호출되는 함수
											//data: ajax메소드가 서버로부터 전송받은 데이터(매개변수명이므로 변경가능)
					$('#loading').hide();	//로딩이미지 숨김
						//.key로 value 접근
					if(data.result == 'idNotFound'){			//아이디 미중복
						$('#message_id').css('color', '#000').text('등록가능 ID');
						checkId = 1;
					}else if(data.result == 'idDuplicated'){	//아이디 중복
						$('#message_id').css('color', 'red').text('중복된 ID');
						$('#id').val('').focus();
						checkId = 0;
					}else{					//result값이 idNotFound도 아니고 idDuplicated도 아닌경우(에러)
						alert('ID중복체크 오류 발생!');
						checkId = 0;
					}
				},
				error: function(){			//서버에 데이터를 요청했으나 실패한 경우 호출되는 함수	
					$('#loading').hide();	//로딩이미지 숨김
					alert('네트워크 오류 발생!');
					checkId = 0;			//중복체크 플래그 0
				}
			});
		});
		
		//(아이디 중복체크 한 후)아이디 입력창에 키 입력되는 경우
		$('#register_form #id').keydown(function(){
			checkId = 0;				// 아이디 중복값 초기화
			$('#message_id').text('');	//아이디 중복 안내 메시지 초기화
		});
		
		//submit 이벤트 발생시 아이디 중복체크여부 확인
		$('#register_form').submit(function(){
			if(checkId == 0){
				$('#message_id').css('color', 'red').text('ID중복체크 필수');
				$('#id').focus();
				return false;
			}
		});
	});
</script>
<div class="page-main-style">
	<h2>회원가입</h2>
	<form:form id="register_form" action="registerUser.do" commandName="memberVO">
		<%-- 필드가 없는 에러 표시 --%>
		<form:errors element="div" cssClass="error-color"/>
		<ul>
			<li>
				<label for="id">아이디</label>
				<form:input path="id"/>
				<input type="button" id="confirmId" value="ID중복체크">
				<%-- 로딩gif --%>
				<img id="loading" src="${pageContext.request.contextPath}/resources/images/ajax-loader.gif" width="16" height="16" style="display:none;">
				<span id="message_id"></span> <%-- 아이디 중복체크 메세지 --%>
				<form:errors path="id" cssClass="error-color"/> <%-- 아이디 유효성체크(입력여부) --%>
			</li>
			<li>
				<label for="name">이름</label>
				<form:input path="name"/>
				<form:errors path="name" cssClass="error-color"/>
			</li>
			<li>
				<label for="passwd">비밀번호</label>
				<form:password path="passwd"/>
				<form:errors path="passwd" cssClass="error-color"/>
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
			<input type="submit" value="전송">
			<input type="button" value="홈으로 " onclick="location.href='${pageContext.request.contextPath}/main/main.do'">
		</div>
	</form:form>
</div>