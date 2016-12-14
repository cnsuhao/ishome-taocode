<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/lib/pagtag.tld" prefix="pagetag" %>

<!DOCTYPE html>
<html lang="en">
  <head>  
    <title>列表</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
   <!-- Bootstrap -->
    <link href="/resources/css/wm/zk/bootstrap.min.css" rel="stylesheet" media="screen">
	<link href="/resources/css/wm/zk/global.css" rel="stylesheet" media="screen">
	<link href="/resources/css/wm/zk/sticky-footer-navbar.css" rel="stylesheet">	
    
	<!-- Bootstrap js -->
	<script src="/resources/js/wm/zk/jquery.js"></script>
    <script src="/resources/js/wm/zk/bootstrap.min.js"></script>
    <script src="/resources/js/wm/zk/topmenu.js"></script>	 	
	<script src="/resources/js/wm/zk/bootstrap.js"></script>
		<style type="text/css"> 
          th{
          	 text-align: center;
          }       
    </style> 
  </head>
  <body>
  <table align="center" width="100%" style="margin:0; padding:0;" border="0">
    <tr height="40" style="border:1px solid #DBDBDB; background:#f8f8f8;">
		<td><h4 style="margin-left:10px;">提现管理</h4></td>
	</tr>
	<tr height="40" style="border-left:1px solid #DBDBDB; border-right:1px solid #DBDBDB;">
		<td>
			<table align="center" width="100%" border="0">
				<tr>
					<td width="2%"></td>
					<td align="left">
					<pagetag:pagtag  pageVo="${pageVO}"  url="/WMJG04/F.go" style="4"/>
					</td>
					<td align="right">${message}</td>
					<td width="30%" align="right">
						<input type="button" class="btn btn-primary btn-sm" value="检索" 
						onclick="parent.toTargetForm('/WMJGY1L/H.go','/WMJG04/F.go')">
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
                  <th><input type="hidden" id="puk" value="${wmbm02dbo.puk}"></th>   
                  	<th>流水号</th>
                  	<th>授权域名</th>
                  		<th>授权联系人</th>
                  			<th>客户名称</th>
                  	<th>开户名</th>
                  	<th>开户行名称</th>
                  	<th>银行账号</th>
                  	<th>实得金额(元)</th>
                  	<th>手续费用(元)</th>
                  	<th>操作状态</th>
                  	<th>操作时间</th>
                </tr>
		                <c:forEach var="wmbm02dbo" items="${pageVO.pageData}">	
		                <tr align="center">
		               	 <td><input type="hidden" id="puk" value="${wmbm02dbo.puk}"></td>
		                  	  <td>${wmbm02dbo.puk}</td>
		                  	   <td>${wmbm02dbo.k02}</td>
		                  	    <td>${wmbm02dbo.f02}</td>
		                  	       <td>${wmbm02dbo.f08}</td>
			                  <td>${wmbm02dbo.f03}</td>
			                  <td>${wmbm02dbo.f04}</td>
			                  <td>${wmbm02dbo.f01}</td>
			                  <td>${wmbm02dbo.f18}</td>
			                  <td>${wmbm02dbo.eb2}</td>
			                  <td>
			                  <c:choose>
		                  		<c:when test="${wmbm02dbo.f06==0}">
		                  			 申请中     			
		                  		</c:when>
		                  		<c:when test="${wmbm02dbo.f06==1}">
		                  			 失败       			
		                  		</c:when>
		                  		<c:when test="${wmbm02dbo.f06==2}">
		                  			 成功       			
		                  		</c:when>
		                  	 </c:choose>
		                  	 </td>	
			                  <td>${wmbm02dbo.cc1}</td>
		                </tr>
	                </c:forEach>
            </table>
		</td>
	</tr>
  </table>
</body>
</html>