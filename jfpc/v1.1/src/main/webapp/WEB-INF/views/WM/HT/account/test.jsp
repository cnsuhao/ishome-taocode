<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	request.setCharacterEncoding("gb2312");
%>
<%@ page
	import="java.util.*,java.lang.*,java.sql.*,javax.naming.*,javax.sql.*"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!doctype html>
<html lang="en">
 <head>
  <meta charset="UTF-8">
  <meta name="Generator" content="EditPlus®">
  <meta name="Author" content="">
  <meta name="Keywords" content="">
  <meta name="Description" content="">
  <title>Document</title>
  
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="/resources/css/bootstrap.min.css" rel="stylesheet"
	media="screen">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script type="text/javascript" src="/resources/js/jquery.js"></script>
<script src="/resources/js/bootstrap.js"></script>
<script type="text/javascript">
		
		function xiayibu(){

			document.formsb01.action="/WMBM06/C.go";
			formsb01.submit();
			}
	</script>

 </head>
 <body>
 <form action="" id="formsb01" name="formsb01">
  通道选择：<select name="">
	<option value="" selected>报复通道</option>
	<option value="">银联通道</option>
  </select><br><br>
 <textarea name="" rows="" cols="">T+0 周一到周五  9:00--17:00</textarea><br><br>
 充值金额： <input type="text" name="K01"id="k01" value="${parambean1.K01}">元<br><br>
 安全密码： <input type="text" name=""><br><br>
 <input type="button" value="下一步" onclick="xiayibu()">
 </form>
 </body>
</html>
