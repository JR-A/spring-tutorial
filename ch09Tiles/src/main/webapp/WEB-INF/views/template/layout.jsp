<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><tiles:getAsString name="title"/></title>
</head>
<body>
<table border="1" style="width:100%">
	<tr height="100">
		<td colspan="2">
			<tiles:insertAttribute name="header"/>
		</td>
	</tr>
	<tr height="600">
		<td width="15%">
			<tiles:insertAttribute name="menu"/>
		</td>
		<td width="85%">
			<tiles:insertAttribute name="body"/>
		</td>
	</tr>
	<tr>
		<td colspan="2">
			<tiles:insertAttribute name="footer"/>
		</td>
	</tr>
</table>
</body>
</html>