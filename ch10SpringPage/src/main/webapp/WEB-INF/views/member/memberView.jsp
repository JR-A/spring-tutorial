<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> <!-- jstl의 core 라이브러리 사용 -->
<%--jQuery 라이브러리 사용 --%>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.5.1.min.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		
		//(프로필사진)수정버튼 클릭 이벤트 연결
		$('#photo_btn').click(function(){
			//이미지 파일 선택 태그 노출
			$('#photo_choice').show();
			//수정 버튼(#photo_btn)을 숨김
			$(this).hide();
		});
		
		var photo_path; //원래 이미지 src(소스) 보관용 변수
		var my_photo; 	//새로 선택된 이미지파일 보관용 변수
		
		$('#upload').change(function(){
			var upload = document.getElementById('upload');
			
			//새로 선택된 이미지파일 보관
			my_photo = upload.files[0];	//파일을 여러 개 업로드할 수 있으므로 배열에 보관되지만, 우리는 업로드 하나만 하므로 0번째 파일만존재
			if(my_photo){
				var reader = new FileReader();
				reader.readAsDataURL(my_photo);
				
				//사진 업로드 전 미리보기 처리
				reader.onload = function(){
					//원래 이미지 소스 보관
					photo_path = $('.my-photo').attr('src');
					//변경 이미지 미리보기 세팅
					$('.my-photo').attr('src', reader.result);
				};
			}
		});
		
		//취소 버튼 클릭이벤트 연결
		$('#photo_reset').click(function(){
			$('.my-photo').attr('src', photo_path); //이미지 초기화
			$('#upload').val('');
			$('#photo_choice').hide();	//파일선택, 전송, 취소버튼 숨김
			$('#photo_btn').show();		//수정 버튼 노출
		});
		
		//(이미지)전송버튼 클릭이벤트 연결 - submit이 아닌 일반 클릭이벤트임
		$('#photo_submit').click(function(){
			if($('#upload').val() == ''){
				alert('파일을 선택하세요');
				$('#upload').focus();
				return;				//클릭 이벤트 함수 종료
			}
			
			//binary data(이미지) 전송 위해 FormData객체 생성
			//FormData는 <form>태그가 전송된 것 같은 효과를 가져다주는 key/value 가 저장되는 객체
			var form_data = new FormData();
			form_data.append('upload', my_photo);	//setter찾아서 세팅
			
			//서버와 ajax비동기 통신
			$.ajax({
				url: 'updateMyPhoto.do',	//요청URL - MemberAjaxController.java참고
				type: 'POST',				//전송방식
				data: form_data,			//서버에 전송할 데이터 - setter찾아서 (MultipartFile -> byte[])파일, 파일명 세팅
				dataType: 'json',			//서버가 전송하는 데이터 형식
				cache: false,				//예전 데이터 사용하지 않도록 제한
				contentType: false,			//default값이 utf-8이므로 multipart/form-data 전송 위해 false로 처리
				enctype: 'multipart/form-data',	//인코딩타입
				processData: false,			//data 파라미터로 전달된 데이터를 jQuery 내부적으로 query string으로 만드는데, 파일 전송의 경우 이를 하지 않아야 하고 이를 설정하는 것이 processData: false
				success: function(data){	//서버로부터 데이터가 성공적으로 도착하면 호출되는 함수
											//data: ajax메소드가 서버로부터 전송받은 데이터(매개변수명이므로 변경가능)
					if(data.result == 'logout'){
						alert('로그인 후 사용하세요!');
					}else if(data.result == 'success'){
						alert('프로필 사진이 수정되었습니다.');
						//초기화
						$('#upload').val('');
						$('#photo_choice').hide();
						$('#photo_btn').show();
					}else{
						alert('파일 전송 오류 발생!');
					}
				},
				error: function(){			//서버에 데이터를 요청했으나 실패한 경우 호출되는 함수
					alert('네트워크 오류 발생!');
				}
			});
		});
		
		
	});
</script>
<div class="page-main-style">
	<h2>프로필 사진</h2>
	<ul>
		<li>
			<%-- 이미지를 등록하지 않았으면 기본이미지 표시 --%>
			<c:if test="${empty member.photoname}">
			<img class="my-photo" src="${pageContext.request.contextPath}/resources/images/blank.jpg" width="100" height="100">
			</c:if>
			<%-- 이미지를 등록했으면 이미지 표시 --%>
			<c:if test="${!empty member.photoname}">
			<img class="my-photo" src="${pageContext.request.contextPath}/member/photoView.do" width="100" height="100">
			</c:if>
		</li>
		<li>
			<div class="align-center">
				<input type="button" value="수정" id="photo_btn">
			</div>
			<div id="photo_choice" style="display:none;">
				<input type="file" id="upload" accept="image/gif,image/png,image/jpeg"> <%-- Ajax방식으로 처리하므로 name 생략가능 --%>
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