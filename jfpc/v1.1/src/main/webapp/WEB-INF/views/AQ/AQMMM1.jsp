<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/lib/pagtag.tld" prefix="pagetag" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">
  <head>
  <base href="<%=basePath%>">    
    <title>密码管理</title>
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
	<style type="text/css"> 
          th{
          	 text-align: center;
          }       
    </style> 	
  </head>
  <body>
  <table align="center" width="100%" style="margin:0; padding:0;" border="0">
    <tr height="40" style="border:1px solid #DBDBDB; background:#f8f8f8;">
		<td><h4 style="margin-left:10px;">密码管理</h4></td>
	</tr>
	<tr height="50" style="border-left:1px solid #DBDBDB; border-right:1px solid #DBDBDB;">
		<td><table style="width:100%; color:red; font-size:12px; margin:0; padding-top:10px;">
		  <tr>
		    <td align="right"></td>
		    <td width="2%">&nbsp;</td>
	      </tr>
		  </table>
	    </td>
	</tr>
	<tr height="40" style="border-left:1px solid #DBDBDB; border-right:1px solid #DBDBDB;">
		<td>
			<table align="center" width="100%" border="0">
				<tr>
					<td width="2%"></td>
					<td width="66%" align="left">
						<pagetag:pagtag  pageVo="${pageVO}"  url="/WMKZ01/F.go" style="4"/>
					</td>
					<td width="30%" align="right">
					<form id="form1" name="form1" action="" method="post">
						<button  class="btn btn-default btn-sm" onclick="add()">添加</button>						
						<input type="button" value="检索" class="btn btn-primary btn-sm"
						 onclick="parent.toTargetForm('/WMKZD1L/H.go','/WMKZ01/F.go')">
					</form>
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
                  <th><input type="hidden" id="puk" value="${csss01.eb5}"></th>              		
		               		<th>绑定域名</th>	               		
		               		<th>系统分类</th>
		               		<th>系统版本</th>
		               		<th>系统密钥</th>
		               		<th>联系电话</th>
		               		<th>授权终了日期</th>
<!-- 		               		<th>更新时间uu1</th> -->
		               		<th class="table-th-do" style="text-align: center;">操作</th>
                </tr>
                 <form action="" method="post" id="form2" name="form2">	
		                <c:forEach var="csss01" items="${pageVO.pageData}">	
		                <tr align="center">
		               	 <td><input type="hidden" id="puk" value="${csss01.puk}"></td>
		                  <td>${csss01.k02}</td>
		                  <td>${csss01.f09}</td>
		                  <td>${csss01.f10}</td>
		                  <td>${csss01.f11}</td>
		                  <td>${csss01.f08}</td>
		                  <td>${csss01.f12}</td>
<!-- 		                  <td>${csss01.uu1}</td> -->
		                  <td>
		                  	<button  class="btn btn-link" onclick="edit('${csss01.puk}','${csss01.uu1}')" >修改</button>
		                  	<button  class="btn btn-link" onclick="del('${csss01.puk}','${csss01.uu1}')" >删除</button>		                  
							<button  class="btn btn-link" onclick="dis('${csss01.puk}')" >通道</button>
<!-- 							<button  class="btn btn-link" onclick="hy()">用户组</button> -->
							<button  class="btn btn-link" onclick="vip()">会员组</button>
							<button  class="btn btn-link" onclick="tx('${csss01.puk}')">授权</button>
							<button   class="btn btn-link" onclick="add1('${csss01.puk}')">创建超级管理员</button>
<!-- 			          
						        <a href="/WMKZ01/R.go?puk=${csss01.puk}&uu1=${csss01.uu1}">修改</a>&nbsp; -->
<!-- 			                  <a href="/WMKZ01/D.go?puk=${csss01.puk}&uu1=${csss01.uu1}">删除</a> -->
		                  </td>		                  
		                </tr>
	                </c:forEach>
	                </form> 
            </table>
		</td>
	</tr>
  </table>
</body>
</html>
