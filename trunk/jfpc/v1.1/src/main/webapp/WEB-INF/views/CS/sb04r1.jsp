<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">    
    <title>系统提示信息</title>   
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
	<script type="text/javascript" src="/resources/js/jquery-1.8.3.js"></script>
	<script src="/resources/js/bootstrap.js"></script>
	<script type="text/javascript">
		$(function(){
			alert("this is sb04r1 一览页面");			
		});
	</script>
  </head>
  
  <body  style="margin-left:50px; margin-top:80px;">
  	  系统提示信息<button type="button" id="test" value="">test</button><br>
   		<table class="table table-condensed">
   			<tbody>
   			<tr><td>
   				<form action="" method="post" id="form1">
   					<input type="hidden" id="puk" name="puk" class="requriedId" value="">
   					<label>uu2</label>
   					<input type="text" placeholder="uu2" id="uu2" name="bbb" style="width: 180; height: 30">
   					<select id="fb1"  style="width: 200px">
<!--    					multiple="multiple" -->
				        <option>1</option>
				        <option>2</option>
				        <option>3</option>
				        <option>4</option>
				        <option>5</option>
				    </select><br><br>
				    <button type="button" class="btn">添加</button>
				    <button type="button" class="btn">检索</button>
   				</form> 				
   			</td></tr>
   			</tbody>
   		</table>
  </body>
</html>
