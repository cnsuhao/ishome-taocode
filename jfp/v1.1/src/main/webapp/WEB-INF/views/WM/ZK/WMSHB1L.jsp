<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">
  <head>
  <base href="<%=basePath%>">    
    <title>search</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
   <!-- Bootstrap -->
    <link href="/resources/css/wm/zk/bootstrap.min.css" rel="stylesheet" media="screen">
	<link href="/resources/css/wm/zk/global.css" rel="stylesheet" media="screen">
	<link href="/resources/css/wm/zk/sticky-footer-navbar.css" rel="stylesheet">	
    
	<!-- Bootstrap js -->
	<script src="/resources/js/wm/zk/jquery.js"></script>
<!-- 	<script type="text/javascript" src="/resources/js/wm/jquery-1.8.3.js"></script> -->
    <script src="/resources/js/wm/zk/bootstrap.min.js"></script>
    <script src="/resources/js/wm/zk/topmenu.js"></script>	 	
	<script src="/resources/js/wm/zk/bootstrap.js"></script>
	<script type="text/javascript">
	
		
	</script>
  </head>
  <body>
  <table align="center" width="100%" style="margin:0; padding:0;" border="0">
    <tr height="40" style="border:1px solid #DBDBDB; background:#f8f8f8;">
		<td><h4 style="margin-left:10px;">黑名单查询</h4></td>
	</tr>
	<tr class="message-button">
      <td height="60"><table width="100%" border="0">
		  <tr>
          	<td width="2%"></td>
		    <td style="color:red; font-size:12px;">message</td>
		    <td align="right">
					  <button  class="btn btn-default btn-sm" onclick="">返回</button>
					  <button  class="btn btn-default btn-sm" onclick="">清空</button>
					  <button  class="btn btn-primary btn-sm" onclick="">查询</button>	
            </td>
            <td width="2%"></td>
	      </tr>
		  </table></td>
    </tr>
	<tr style="border:1px solid #DBDBDB;">
		<td>
			<table align="center" width="100%" style="margin:0; padding:0;" border="0">
				<tr height="40">
					<td width="20%" align="right">来自云端：</td>
					<td width="5"></td>
					<td>
					<input type="text"  class="form-control input-sm" id="eb4" name="eb4" value="${parambean1.eb4}"  >
<!-- 					<input type="text" class="form-control input-sm" id="exampleInputEmail1" placeholder="标题"> -->
					</td>
					<td width="5"></td>
					<td width="30%"></td>
				</tr>
				<tr height="40">
					<td width="20%" align="right">创建时间：</td>
					<td width="5"></td>
					<td><input type="text"  class="form-control input-sm" id="cc1" name="cc1" value="${parambean1.cc1}"  ></td>
					<td width="5"></td>
					<td width="30%"></td>
				</tr>
				<tr height="40">
					<td width="20%" align="right">创建者：</td>
					<td width="5"></td>
					<td><input type="text"  class="form-control input-sm" id="cc2" name="cc2" value="${parambean1.cc2}"  ></td>
					<td width="5"></td>
					<td width="30%"></td>
				</tr>	
			</table>
		</td>
	</tr>
  </table>
</body>
</html>
