<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false" %>
<html>
<head>
	<title>页面跳转中</title>
</head>
<body>
${message}

<br>
<input type="button" value="确定返回" onclick="forword()">

<script>
function forword(){
	forwordForm.submit();
}
</script>
<form id="forwordForm" name="forwordForm" method="post" action="${forwordURL}">   
  
</form>
</body>
</html>