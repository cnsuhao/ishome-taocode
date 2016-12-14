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
			document.form1.action = "/WMKZ04/H.go";
			form1.submit();
		}
		function check(){
			document.form1.action = "/WMKZ04/A.go";
			form1.submit();
		}
		function edit(puk,uu1){
			if(uu1 == ""){
				document.form2.action="/WMKZ04/R.go?puk="+puk;	
				form2.submit();	
			}else{	
				document.form2.action="/WMKZ04/R.go?puk="+puk+"&uu1="+uu1;		
				form2.submit();	
			}	
		}	
	/*	function del(puk,uu1){
			if(uu1 == ""){
				document.form2.action="/WMKZ04/D.go?puk="+puk;	
				form2.submit();	
			}else{
				document.form2.action="/WMKZ04/D.go?puk="+puk+"&uu1="+uu1;		
				form2.submit();	
			}		
		}	*/		
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
		<td><h4 style="margin-left:10px;">管理员明细</h4></td>
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

						<pagetag:pagtag  pageVo="${pageVO}"  url="/WMKZ04/F.go" style="4"/>
					</td>
					<td width="30%" align="right">
					<form id="form1" name="form1" action="" method="post">
						<input type="button" class="btn btn-primary btn-sm" 
						onclick="parent.toTargetForm('/WMKZD1L/H.go','/WMKZ04/F.go')" value="检索">
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
                  <th><input type="hidden" id="puk" value="${csss01.puk}"></th>              		
						<th>人员编号</th>
						<th>用户名</th>
						<th>用户角色等级</th>
	               		<th>最后登录时间</th>
						<th>最后登录IP</th>
	               		<th>操作</th>              			               		
                </tr>
                 <form action="" method="post" id="form2" name="form2">	
		                <c:forEach var="cssr01dbo" items="${pageVO.pageData}">	
		                <tr align="center">
		               	 <td><input type="hidden" id="puk" value="${cssr01dbo.puk}"></td>
		                  	  <td>${cssr01dbo.f01}</td>
			                  <td>${cssr01dbo.f03}</td>
			                  <td>${cssr01dbo.f06}</td>
			                  <td>${cssr01dbo.fb1}</td>
			                  <td>${cssr01dbo.fb2}</td>
		                  <td>
		                  <button  class="btn btn-link" onclick="edit('${cssr01dbo.puk}','${cssr01dbo.uu1}')" >修改</button>
<!-- 		                  <button  class="btn btn-link" onclick="del('${cssr01dbo.puk}','${cssr01dbo.uu1}')" >删除</button> -->
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
