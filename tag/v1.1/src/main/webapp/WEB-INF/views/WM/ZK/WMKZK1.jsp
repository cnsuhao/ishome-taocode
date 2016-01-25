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
    <title>列表</title>
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
		function add(){
			document.form1.action = "/WMKZ01/H.go";
			form1.submit();
		}
		function sub(){
			document.form1.action = "/WMKZ01/B.go";
			form1.submit();
		}
		function edit(puk,uu1){
			if(uu1 == ""){
				document.form2.action="/WMKZ01/R.go?puk="+puk;	
				form2.submit();	
			}else{	
				document.form2.action="/WMKZ01/R.go?puk="+puk+"&uu1="+uu1;		
				form2.submit();	
			}	
		}
		function del(puk,uu1){
			if(uu1 == ""){
				document.form2.action="/WMKZ01/D.go?puk="+puk;	
				form2.submit();	
			}else{
				document.form2.action="/WMKZ01/D.go?puk="+puk+"&uu1="+uu1;		
				form2.submit();	
			}		
		}
		function dis(eb5){
			document.form2.action="/WMJG01/P.go?eb5="+eb5;	
			form2.submit();	
		}
		function hy(){
			document.form2.action = "/WMKZ06/F.go";
			form2.submit();
		}
		function vip(){
			document.form2.action = "/WMKZ07/F.go";
			form2.submit();
		}
		function tx(id){
			document.form2.action = "/WMKZ01/TRX.go?puk="+id;
			form2.submit();
		}
		function add1(puk,k03){
			document.form2.action = "/WMKZ01/CG.go?eb5="+puk+"&k03="+k03;
			form2.submit();
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
		<td><h4 style="margin-left:10px;">客户系统管理</h4></td>
	</tr>
	<tr height="40" style="border-left:1px solid #DBDBDB; border-right:1px solid #DBDBDB;">
		<td>
			<table align="center" width="100%" border="0">
				<tr>
					<td width="2%"></td>
					<td width="66%" align="left">
						<pagetag:pagtag  pageVo="${pageVO}"  url="/WMKZ01/F.go" style="4"/>
					</td>
					<td>${message}</td>
					<td align="right">
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
		               		<th>授权域名</th>
		               		<th>系统版本号</th>
		               		<th>客户简称</th>
		               		<th>联系人名称</th>
		               		<th>联系人电话</th>
		               		<th>超级管理员帐号</th>
		               		<th>授权终了日期</th>
<!-- 		               		<th>更新时间uu1</th> -->
		               		<th class="table-th-do" style="text-align: center;">操作</th>
                </tr>
                 <form action="" method="post" id="form2" name="form2">	
		                <c:forEach var="csss01" items="${pageVO.pageData}">	
		                <tr align="center">
		               	 <td><input type="hidden" id="puk" value="${csss01.puk}"></td>
		                  <td>${csss01.k02}</td>
		                  <td>${csss01.f10}</td>
		                  <td>${csss01.f01}</td>
		                  <td>${csss01.f07}</td>
		                  <td>${csss01.f08}</td>
		                  <td>${csss01.k03}</td>
		                  <td>${csss01.f12}</td>
		                  <td>
		                  	<button  class="btn btn-link" onclick="parent.toTargetForm('/WMKZ01/R.go?puk=${csss01.puk}&uu1=${csss01.uu1}','')" >资料修改</button>
							<button  class="btn btn-link" onclick="parent.toTargetForm('/WMKZ01/TRX.go?puk=${csss01.puk}','')">授权期限</button>
 							<button  class="btn btn-link" onclick="">功能选定</button>
							<button  class="btn btn-link" onclick="parent.toTargetForm('/WMKZ01/P.go?puk=${csss01.puk}','')" >分配通道</button>
<!--    
 							<button  class="btn btn-link" onclick="hy()">用户组</button>
							<button  class="btn btn-link" onclick="vip()">会员组</button>
							
 -->							
							<button  class="btn btn-link" onclick="parent.toTargetForm('/WMKZ01/CG.go?eb5=${csss01.puk}&k03=${csss01.k03}','')">超级管理员密码</button>
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
