<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false" %>
<html>
<head>
	<title>页面跳转中</title>
</head>
<body>
<form id="tokenForm" name="tokenForm" method="post" action="${loginer.callBackUrl}">
  <input type="hidden" name="message"  id="message" value="${loginer.verCode}" />
  <input type="hidden" name="token"  id="token" value="${loginer.token}" />
  <input type="hidden" name="securityCode"  id="securityCode" value="${loginer.securityCode}" />
  <input type="hidden" name="loginUrl"  id="loginUrl" value="${loginer.loginUrl}" />
</form>
<%
System.out.println("////////////////////页面跳转中///////////////////////");
%>
<script language="javascript" type="text/javascript">
tokenForm.submit();
</script>
</body>
</html>
