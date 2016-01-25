<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html lang="en">
  <head>
    <title>列表</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- Bootstrap -->
    <link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
	<!-- Bootstrap js -->
	<script src="js/jquery.js"></script>
	<script src="js/bootstrap.min.js"></script>
  </head>
<!--   画面url:/WMKZ01/E.go -->
  <body>  
  <table align="center" width="100%" style="margin:0; padding:0;" border="0">
   	<tr height="100" style="border:1px solid #DBDBDB; background:#f8f8f8;">
   		<td>
   			<table>
   				<tr>
   					<td>&nbsp;</td>
   					<td>&nbsp;</td>
   					<td>&nbsp;</td>
   					<td>&nbsp;</td>
   					<td>&nbsp;</td>
   					<td>&nbsp;</td>  					
   				</tr>
   				<tr>
   					<td >系统版本</td>
   					<td>&nbsp;</td>
   					<td>授权终了日期</td>
   					<td>&nbsp;</td>
   					<td>&nbsp;</td>
   					<td>&nbsp;</td>  					
   				</tr>  			
   			</table>
   		</td>
   	</tr>
   	<tr>
   		<td>系统动态</td>
   	</tr>
   	<tr>
   		<td>系统公告	</td>
   	</tr>
  </table>
</body>
</html>

