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
		function sub(){
			parent.blankForm.pageid.value="WMJGF1";
			parent.blankForm.listParams.value="JSON Object";
			parent.toTargetForm("/WMZKGGJS/F.go");
		}
		function account(puk){
// 			alert(puk);
			document.form2.action = "/WMJG02/A.go?puk="+puk;
// 			alert(puk);
// 			alert(document.form2.action);
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
		<td><h4 style="margin-left:10px;">费率管理</h4></td>
	</tr>
	<tr height="40" style="border-left:1px solid #DBDBDB; border-right:1px solid #DBDBDB;">
		<td>
			<table align="center" width="100%" border="0">
				<tr>
					<td width="2%"></td>
					<td width="66%" align="left">
					<pagetag:pagtag  pageVo="${pageVO}"  url="/WMJG02/F.go" style="4"/>
					</td>
					<td width="30%" align="right">
					<form id="form1" name="form1" action="" method="post">
						<input class="btn btn-primary btn-sm" type="button" value="检索" 
						onclick="parent.toTargetForm('/WMJGY1L/H.go','/WMJG02/F.go')"/>
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
                  		<th>授权域名</th>
                  		<th>会员名称</th>
                  		<th>支付通道</th>
                  		<th>结算说明</th>    		               			               		
                  		<th>充值手续费率(%)</th>
                  		<th>提现手续费(元)</th>
                  		<th>提现最大金额(元)</th>
		               		<th class="table-th-do" style="text-align: center;">操作</th>
                </tr>
		                <c:forEach var="item" items="${pageVO.pageData}">	
		                <tr align="center">
							<td>${item.k02}</td>
							<td>${item.f04}</td>
							<td>${item.fb1}</td>
							<td>${item.f19}</td>
							<td>${item.f15}</td>
							<td>${item.f16}</td>
							<td>${item.f17}</td>
			                  <td >
			                	 <button  class="btn btn-link" onclick="account('${item.puk}')" >对账单</button>
			                </td>
		                </tr>
	                </c:forEach>
            </table>
		</td>
	</tr>
  </table>
</body>
</html>
