<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>用户组权限</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="/resources/css/bootstrap.min.css" rel="stylesheet" media="screen">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="/resources/js/jquery.js"></script>
   	<!--
   <script src="/resources/js/bootstrap.js"></script>
   -->
	<script type="text/javascript">
		function xiugai(puk){
			document.form2.action="/CSSR04/R.go?puk="+puk;
			form2.submit();
		}
	</script>
  </head>

  
  <body  style="margin-left:50px; margin-top:80px;">
    <script src="http://code.jquery.com/jquery.js"></script>
    <script src="bootstrap.js"></script>
   
   
   <table width="673" border="1" cellspacing="0" cellpadding="0">
   <tr>
	<td>用户组编号</td>
	<td>用户组名称</td>
	<td>菜单权限定义</td>
   </tr>
   <form action="/CSSR04/F.go" method="post" id="form2" name="form2">
   <c:forEach var="sr04dbo" items="${list}">
   <tr>
	<td>${sr04dbo.k01}</td>
	<td>${sr04dbo.bbb}</td>
	<td>
	<button type="button" id="edit" class="button" onclick="xiugai('${sr04dbo.puk}')">编辑</button>
	</td>
   </tr>
   </c:forEach>
   </form>
   </table>
  </body>
</html>
