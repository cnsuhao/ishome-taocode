<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>业务运行日志</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="/resources/js/jquery.js"></script>
	<script src="/resources/js/bootstrap.js"></script>
	<script type="text/javascript">
	function jiansuo(aa){	
// 			alert(cc1);	
 			document.form1.action="/CSSL01/R.go?cc1=" + aa;
			form1.submit();
		}
	</script>
  </head>
  	<form action="/CSSL01/H.go" method="post" id="form1" name="form1">
    	<fieldset>
    		<br>
    		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<label>日期</label>
    		<input type="text" id="aa" name="aa"value="">&nbsp;&nbsp;
    		<br>
         <p style="margin-left: 880px">
         	<button class="btn" type="button" id="btn1" onclick="jiansuo(document.getElementById('aa').value)">检索</button>
         </p>
         </fieldset> 
    </form>
	<table width="1083" border="1" cellspacing="0" cellpadding="0">
  	<tr>
   		 <td width="247">日期</td>
   		 <td width="213">业务ID</td>
   		 <td width="213">操作者</td>
   		 <td width="413">业务内容</td>
   		 <td width="313">分类</td>
   		 <td width="113">结果</td>
  	</tr>
  <form action="" method="post" id="form2" name="form2">
   <c:forEach var="sl02dbo" items="${list}">
    <tr>
    <td id="td1">${sl02dbo.cc1}</td>
    <td>${sl02dbo.k01}</td>
    <td>${sl02dbo.cc2}</td>
    <td>${sl02dbo.bbb}</td>
    <td>${sl02dbo.k03}</td>
    <td>${sl02dbo.fb1}</td>
  </tr>
  </c:forEach>
 </form>
</table>
  