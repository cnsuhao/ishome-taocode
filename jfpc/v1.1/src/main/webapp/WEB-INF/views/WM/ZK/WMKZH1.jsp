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
    <title>用户组一览</title>
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
			document.form1.action = "/WMKZ06/H.go";
			form1.submit();
		}
		function check(){
			document.form1.action = "/WMKZ06/A.go";
			form1.submit();
		}
		function edit(puk,uu1){
			if(uu1 == ""){
				document.form2.action="/WMKZ06/R.go?puk="+puk;	
				form2.submit();	
			}else{	
				document.form2.action="/WMKZ06/R.go?puk="+puk+"&uu1="+uu1;		
				form2.submit();	
			}	
		}	
		function del(puk,uu1){
			if(uu1 == ""){
				document.form2.action="/WMKZ06/D.go?puk="+puk;	
				form2.submit();	
			}else{
				document.form2.action="/WMKZ06/D.go?puk="+puk+"&uu1="+uu1;		
				form2.submit();	
			}		
		}		
		function adm(k01){
			document.form1.action = "/WMKZ06/E.go?k01="+k01;
			form1.submit();
		}	
		function update(puk,uu1){
			if(uu1 == ""){
				document.form2.action="/WMKZ06/R.go?puk="+puk;	
				form2.submit();	
			}else{
				document.form2.action="/WMKZ06/R.go?puk="+puk+"&uu1="+uu1;		
				form2.submit();	
			}		
		}
	</script>	
  </head>
  <body>
  <table align="center" width="100%" style="margin:0; padding:0;" border="0">
    <tr height="40" style="border:1px solid #DBDBDB; background:#f8f8f8;">
		<td><h4 style="margin-left:10px;">显示全部用户组</h4></td>
	</tr>
	<tr height="50" style="border-left:1px solid #DBDBDB; border-right:1px solid #DBDBDB;">
		<td><table style="width:100%; color:red; font-size:12px; margin:0; padding-top:10px;">
		  <tr>
		    <td align="right">message</td>
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
<!-- 						<ul class="pagination pagination-sm" style="margin:0;"> -->
<!-- 							<li><a href="#">上一页</a></li> -->
<!-- 							<li class="active"><a href="#">1</a></li> -->
<!-- 							<li><a href="#">2</a></li> -->
<!-- 							<li><a href="#">3</a></li> -->
<!-- 							<li><a href="#">4</a></li> -->
<!-- 							<li><a href="#">5</a></li> -->
<!-- 							<li><a href="#">下一页</a></li> -->
<!-- 						</ul> -->
							<pagetag:pagtag  pageVo="${pageVO}"  url="/WMKZ04/F.go" style="4"/>
					</td>
					<td width="30%" align="right">
					<form id="form1" name="form1" action="" method="post">
						<button type="button" class="btn btn-default btn-sm" onclick="">导入模板</button>
						<button type="button" class="btn btn-default btn-sm" onclick="adm('${cssr03dbo.f01}')">添加超级管理员</button>
						<button type="button" class="btn btn-primary btn-sm" onclick="add()">添加</button>
						<input type="hidden" id="eb5" name="eb5" value="${eb5}"  >
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
                  <th><input type="hidden" id="puk" value="${cssr03dbo.eb5}"></th>              				               
						<th>用户组编号</th>
						<th>用户组名称</th>
	               		<th>上级用户组编号</th>
	               		<th>上级用户组名称</th>
	               		<th>用户组分类</th>             			               		
		               	<th class="table-th-do" style="text-align: center;">操作</th>
                </tr>
                 <form action="" method="post" id="form2" name="form2">	
		                <c:forEach var="cssr03dbo" items="${pageVO.pageData}">	
		                <tr align="center">
		               	 <td><input type="hidden" id="puk" value="${cssr03dbo.puk}"></td>
		                  	  <td>${cssr03dbo.k01}</td>
			                  <td>${cssr03dbo.f01}</td>
			                  <td>${cssr03dbo.k03}</td>
			                  <td>${cssr03dbo.k02}</td>
			                  <td>${cssr03dbo.f02}</td>
<!-- 		                  <td>${csss01.uu1}</td> -->
		                  <td>
		                  <button  class="btn btn-link" onclick="edit('${cssr03dbo.puk}','${parambean.eb5}')" >权限</button>
		                  <button  class="btn btn-link" onclick="update('${cssr03dbo.puk}','${cssr03dbo.uu1}')" >修改</button>
		                  <button  class="btn btn-link" onclick="del('${cssr03dbo.puk}','${cssr03dbo.uu1}')" >删除</button>
<!-- 			                  <a href="/WMKZ01/R.go?puk=${csss01.puk}&uu1=${csss01.uu1}">修改</a>&nbsp; -->
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
