<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/lib/pagtag.tld"  prefix="pagtag" %>

<!DOCTYPE html>
<html lang="en">
  <head>
    <title>列表</title>
 
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="/resources/css/wm/qt/bootstrap.min.css" rel="stylesheet"
	media="screen">
<link href="/resources/css/wm/qt/global.css" rel="stylesheet" media="screen">
<script type="text/javascript" src="/resources/js/wm/qt/jquery.js"></script>
<script src="/resources/js/wm/qt/bootstrap.js"></script>
  </head>
  <body>
  <table align="center" width="100%" style="margin:0; padding:0;  border:1px solid #DBDBDB;">
    <tr height="40" >
		<td style="background:#f8f8f8; border-bottom:1px solid #dbdbdb;"><h4 style="margin-left:10px;">对账单</h4></td>
	</tr>
	<tr height="50" style="border-left:1px solid #DBDBDB; border-right:1px solid #DBDBDB;">
		<td>
			<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" style="margin:0; padding:0;">
				<tr>
					<td width="10"></td>
					<td width="737" align="left">

					<pagtag:pagtag  pageVo="${pageVO}"  url="F.go" /> 
					</td>
					<td width="258" align="right">${center}</td>
					<td width="552" align="right">
					</td>
					<td width="104"></td>
				</tr>
			</table>
		</td>
	</tr>
	<tr style="border-bottom:1px solid #DBDBDB; border-left:1px solid #DBDBDB; border-right:1px solid #DBDBDB;">
		<td>
			<table class="table table-hover" border="0">
                <tr>
                    <th>通道名称</th>
                    <th>发生日时</th>
                    <th>发生类别</th>
                    <th>发生金额</th>
                    <th>交易费</th>
                     <th>结算费</th>
					<th>账户余额</th>
                </tr>
                 <c:forEach var="wmbm02dbo" items="${pageVO.pageData}">
                <tr>
                    <td>${wmbm02dbo.fb1}</td>
                    <td>${wmbm02dbo.cc1}</td>
                    
						<c:choose>
	                  		<c:when test="${wmbm02dbo.bbb=='0'}">
	                  			 <td style="color: #00F">充值</td>   			
	                  		</c:when>
	                  		<c:when test="${wmbm02dbo.tablename=='1'}">
	                  			 <td style="color:#F00; text-align:right">提现</td>      			
	                  		</c:when>
	                  	 </c:choose>
                    
                    <td>${wmbm02dbo.f07}</td>
                    <td>${wmbm02dbo.eb2}</td>
                    <td>${wmbm02dbo.f15}</td>
                     <td>${wmbm02dbo.fb2}</td>
                    
                </tr>
                   </c:forEach>
            </table>
		</td>
	</tr>
  </table>
</body>
</html>
