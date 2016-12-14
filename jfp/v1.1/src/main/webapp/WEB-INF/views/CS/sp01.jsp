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
    <title>系统画面菜单</title>
    
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
			document.form1.action="/CSSP01/R.go?puk="+puk;
			form1.submit();		
		}
		
		function xiugai(puk){
			document.form2.action="/CSSP01/R.go?puk="+puk;
			form2.submit();
		}
	</script>
  </head>
    <form action="/CSSP01/F.go" method="post" id="form1">
    	<fieldset>
    		<input type="text" placeholder="k01" id="k01" name="k01"value="">&nbsp;&nbsp;
    	     <input type="text" placeholder="puk" id="puk" name="puk"value="">&nbsp;&nbsp;
			 <input type="text"  placeholder="k02" id="k02" name="k02"value="">
			 <input type="text"  placeholder="k02" id="k02" name="k02"value="">
    		<br>
         <p style="margin-left: 180px">
         	<button class="btn" type="button" id="btn1" onclick="location.href=('/CSSP01/H.go')">添加</button>&nbsp;&nbsp;&nbsp;
         	<button class="btn" type="button" id="btn2" onclick="jiansuo('${sb01dbo.puk}')">检索</button>
         </p>
         </fieldset> 
    </form>
    
 <table width="673" border="1" cellspacing="1" cellpadding="1">

  <tr>
    <td width="47">人员编号</td>
    <td width="113">名称</td>
    <td width="94">性别</td>
    <td width="128">年龄</td>
    <td width="64">登记日期</td>
    <td width="213">身份证号</td>
	<td width="213">联系电话</td>
  </tr>
 <form action="" method="post" id="form2" name="form2">
  <c:forEach var="sp01dbo" items="${list}">

  <tr>
   
    <td>${sp01dbo.f01}</td>
    <td>${sp01dbo.f02}</td>
    <td>${sp01dbo.f03}</td>
    <td>${sp01dbo.f01} </td>
    <td>${sp01dbo.f01}</td>
    <td>${sp01dbo.f01} </td>
    <td>${sp01dbo.f01}</td>
  </tr>
   </c:forEach>
</form>

  
</table>