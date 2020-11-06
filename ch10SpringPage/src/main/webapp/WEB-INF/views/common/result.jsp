<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" %>
<%-- 공통 안내 페이지 (controller의 각 메서드에서 message와 url 전달받음) --%>
<script type="text/javascript">
	alert('${message}');
	location.href='${url}';
</script>