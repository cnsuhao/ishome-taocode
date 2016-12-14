<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/lib/pagtag.tld" prefix="pagetag"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">
  <head>
  <base href="<%=basePath%>">    
    <title>登录日志</title>
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
		function sub(){
			document.form1.action = "/WMKZ05/A.go";
			form1.submit();
		}
	
	</script>
	<style type="text/css"> 
          th{
          	 text-align: center;
          }
       
    </style> 	
  </head>
  <body>
  <table align="center" width="100%" style="margin:0; padding:0;" border="0">
    <tr height="40" style="border:1px solid #DBDBDB; background:#f8f8f8;">
		<td><h4 style="margin-left:10px;">用户登录日志</h4></td>
	</tr>

	<tr height="40" style="border-left:1px solid #DBDBDB; border-right:1px solid #DBDBDB;">
		<td>
			<table align="center" width="100%" border="0">
				<tr>
					<td width="2%"></td>
					<td width="66%" align="left">
						<pagetag:pagtag  pageVo="${pageVO}"  url="/WMKZ05/F.go" style="4"/>
					</td>
					<td width="30%" align="right">
						<input type="button" class="btn btn-primary btn-sm" 
						onclick="parent.toTargetForm('/WMKZD1L/H.go','/WMKZ05/F.go')" value="检索">
					</td>
					<td width="2%"></td>
				</tr>
			</table>
		</td>
	</tr>
	<tr style="border-bottom:1px solid #DBDBDB; border-left:1px solid #DBDBDB; border-right:1px solid #DBDBDB;">
		<td>
			<table class="table table-hover">
                <tr>
                  <th><input type="hidden" id="puk" value="${csss01.puk}"></th>                  
                  	<th>系统授权编号</th>
                  	<th>授权联系人</th>
                  	<th>登录域名</th>                 	
                  	<th>用户名</th>
                  	<th>登录IP</th>
                  	<th>登录时间</th>	
                  	<th>客户端种类</th>             	              		  
                </tr>
                 <form action="" method="post" id="form2" name="form2">	
		             <c:forEach var="cssl11dbo" items="${pageVO.pageData}">	
		                <tr align="center">
		               	 <td><input type="hidden" id="puk" value="${cssl11dbo.puk}"></td>		                  
			                  <td>${cssl11dbo.k03}</td>
			                  <td>${cssl11dbo.f07}</td>
			                  <td>${cssl11dbo.k02}</td>
			                  <td>${cssl11dbo.k01}</td>
			                  <td>${cssl11dbo.fb2}</td>
			                  <td>${cssl11dbo.cc1}</td>
			                  <td>${cssl11dbo.eb2}</td>			                  
		                </tr>
	                </c:forEach>
	                </form> 
            </table>
		</td>
	</tr>
  </table>
</body>
</html>
