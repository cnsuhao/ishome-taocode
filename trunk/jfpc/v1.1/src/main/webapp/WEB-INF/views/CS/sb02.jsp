<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!doctype html>
<html lang="en">
 <head>
  <meta charset="UTF-8">
  <meta name="Generator" content="EditPlus®">
  <meta name="Author" content="">
  <meta name="Keywords" content="">
  <meta name="Description" content="">
  <title>系统配置</title>
    
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
	<script src="/resources/js/bootstrap.js"></script>
	<script type="text/javascript">
	function jiansuo(puk){		
			document.form1.action="/CSSB02/R.go?puk="+puk;
			form1.submit();		
		}
		
		function xiugai(puk){
			document.form2.action="/CSSB02/R.go?puk="+puk;
			form2.submit();
		}
	</script>
  </head>
  
  
    <form action="/CSSB02/F.go" method="post" id="form1">
    	<fieldset>
			 <input type="text" name="" value="关键字">
    		<br>
         <p style="margin-left: 180px">
         	<button class="btn" type="button" id="btn1" onclick="location.href=('/CSSB02/H.go')">添加</button>&nbsp;&nbsp;&nbsp;
         	<button class="btn" type="button" id="btn2" location.href="('http://localhost:8888/CSSB01/h')">检索</button>
         </p>
         </fieldset> 
    </form>
 <table width="673" border="1" cellspacing="0" cellpadding="0">
  <tr>
    <td width="47">关键字</td>
    <td width="113">内容</td>
    <td width="94">说明</td>
    <td width="64">&nbsp;</td>
    <td width="213">&nbsp;</td>
  </tr>
   <form action="" method="post" id="form2" name="form2">
   <c:forEach var="sb02dbo" items="${list}">
  <tr>
    <td>${sb02dbo.fb1}</td>
    <td>${sb02dbo.fb2}</td>
    <td>${sb02dbo.fb3}</td>
    <td>&nbsp;
    <button type="button" id="edit" class="button" onclick="xiugai('${sb02dbo.puk}')">编辑</button> 
    &nbsp;
    </td>
    <td>&nbsp;
    <a href="/CSSB02/D.go?puk=${sb02dbo.puk}">删除</a>
    &nbsp;
    </td>
  </tr>
  </c:forEach>
 </form>
</table>