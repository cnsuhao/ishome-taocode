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
			document.form1.action = "/WMKZ03/H.go";
			form1.submit();
		}
		function check(){
			document.form1.action = "/WMKZ03/A.go";
			form1.submit();
		}
		function edit(puk,uu1){
			if(uu1 == ""){
				document.form2.action="/WMKZ03/R.go?puk="+puk;	
				form2.submit();	
			}else{	
				document.form2.action="/WMKZ03/R.go?puk="+puk+"&uu1="+uu1;		
				form2.submit();	
			}	
		}	
		function del(puk,uu1){
			if(uu1 == ""){
				document.form2.action="/WMKZ03/D.go?puk="+puk;					
				form2.submit();	
			}else{
				document.form2.action="/WMKZ03/D.go?puk="+puk+"&uu1="+uu1;		
				form2.submit();	
			}		
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
		<td><h4 style="margin-left:10px;">授权期限一览</h4></td>
	</tr>
	<tr height="40" style="border-left:1px solid #DBDBDB; border-right:1px solid #DBDBDB;">
		<td>
			<table align="center" width="100%" border="0">
				<tr>
					<td width="2%"></td>
					<td align="left">
						<pagetag:pagtag  pageVo="${pageVO}"  url="/WMKZ03/F.go" style="4"/>
					</td>
					<td >${message}</td>
					<td align="right">
						<input type="button" class="btn btn-primary btn-sm" 
						onclick="parent.toTargetForm('/WMKZD1L/H.go','/WMKZ03/F.go')" value="检索">
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
               		<th>授权编号</th>	               		
               		<th>授权域名</th>
               		<th>授权联系人</th>
               		<th>手机号</th>
               		<th>到期日期</th>
               		<th>网站名称</th>
               		<th>系统版本</th>
               		<th>客户经理</th>
               		<th class="table-th-do" style="text-align: center;">操作</th>
                </tr>
                 <form id="form2" name="form2" action="" method="post">
		                <c:forEach var="csss01" items="${pageVO.pageData}">	
		                <tr align="center">
		                  <td>${csss01.k01}</td>
		                  <td>${csss01.k02}</td>
		                  <td>${csss01.f07}</td>
		                  <td>${csss01.f08}</td>
		                  <td>${csss01.f12}</td>
		                  <td>${csss01.f01}</td>
		                  <td>${csss01.f10}</td>
		                  <td>${csss01.eb1}</td>
		                  <td>
		                  <button  class="btn btn-link" onclick="edit('${csss01.puk}','${csss01.uu1}')" >修改期限</button>
<!-- 		                  <button  class="btn btn-link" onclick="del('${csss01.puk}','${csss01.uu1}')" >删除</button> -->
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
