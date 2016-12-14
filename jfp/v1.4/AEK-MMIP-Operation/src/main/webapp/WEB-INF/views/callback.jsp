<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>页面跳转中</title>
</head>
<%
session.setAttribute("appServer", "http://192.168.1.200:8888");
%>
<body>
	<c:choose>
		<c:when test="${loginer.userType=='0'}">
	<form id="tokenForm" name="tokenForm" method="post" action="${loginer.callBackUrl}">
		</c:when>
		<c:when test="${loginer.userType=='1'}">
	<form id="tokenForm" name="tokenForm" method="post" action="${appServer}${loginer.callBackUrl}">
		</c:when>
		<c:when test="${loginer.userType=='2'}">
	<form id="tokenForm" name="tokenForm" method="post" action="${appServer}${loginer.callBackUrl}">
		</c:when>
		<c:when test="${loginer.userType=='3'}">
	<form id="tokenForm" name="tokenForm" method="post" action="${appServer}${loginer.callBackUrl}">
		</c:when>
		<c:when test="${loginer.userType=='4'}">
	<form id="tokenForm" name="tokenForm" method="post" action="${appServer}${loginer.callBackUrl}">
		</c:when>
		<c:when test="${loginer.userType=='9'}">
	<form id="tokenForm" name="tokenForm" method="post" action="${loginer.callBackUrl}">
		</c:when>
		<c:otherwise>
	<form id="tokenForm" name="tokenForm" method="post" action="/">
		</c:otherwise>
	</c:choose>
		<input type="hidden" name="message" id="message" value="${page_message}" /> 
		<input type="hidden" name="token" id="token" value="${loginer.token}" /> 
		<input type="hidden" name="securityCode" id="securityCode" value="${loginer.securityCode}" />
		<input type="hidden" name="loginUrl" id="loginUrl" value="${loginer.loginUrl}" /> 
		<input type="hidden" name="userType" id="userType" value="${loginer.userType}" /> 
		<input type="hidden" name="menuType" id="menuType" value="MS0A1" />
	</form>
	<%
		System.out.println("////////////////////页面跳转中///////////////////////");
	%>
	<script language="javascript" type="text/javascript">
		tokenForm.submit();
	</script>

</body>
</html>
