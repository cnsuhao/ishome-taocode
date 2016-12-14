<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <title>列表</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- Bootstrap -->
    <link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
	<!-- Bootstrap js -->
<link href="/resources/css/wm/qt/bootstrap.min.css" rel="stylesheet" media="screen">
<link href="/resources/css/wm/qt/global.css" rel="stylesheet" media="screen">
<script type="text/javascript" src="/resources/js/wm/qt/jquery.js"></script>
<script src="/resources/js/wm/qt/bootstrap.js"></script>
  </head>
  <body>
  <table align="center" width="100%" style="margin:0; padding:0;  border:1px solid #DBDBDB;">
    <tr height="40" >
		<td style="background:#f8f8f8; border-bottom:1px solid #dbdbdb;"><h4 style="margin-left:10px;">佣金结算记录</h4></td>
	</tr>
	<tr height="40" style="border-left:1px solid #DBDBDB; border-right:1px solid #DBDBDB;">
		<td>
			<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
				<tr>
					<td width="1"></td>
					<td width="1560" align="right">
					 <input type="button" class="btn btn-default btn-sm" value="检索" onclick="parent.toTargetForm('/WMQTJYCX/H.go','/WMYJJSJL/F.go')" />
					</td>
					<td width="100">&nbsp;</td>
					
				</tr>
			</table>
		</td>
	</tr>
	<tr style="border-bottom:1px solid #DBDBDB; border-left:1px solid #DBDBDB; border-right:1px solid #DBDBDB;">
		<td>
			<table width="100%" border="0" cellpadding="0" cellspacing="0" class="table table-hover">
                <tr>
			            
					 <th align="center">结算编号</th>
			         <th align="center">银行名称</th>                             
				     <th align="center">银行账号</th>
					<th align="center">所属通道</th>
					<th align="center">实付金额</th>
					<th align="center">申请时间</th>
					 <th align="center">结算状态</th>			
                </tr>
                   <c:forEach var="wmbm05dbo" items="${list}">
                           <tr>
                             <td align="center"><a href="javascript:parent.toTargetForm('/WMBM04/F.go?f03=${wmbm05dbo.puk}&f02=1','')">${wmbm05dbo.puk}</a></td> 
                             <td align="center">${wmbm05dbo.f04}</td>
                             <td align="center">${wmbm05dbo.f01}</td>
                             <td align="center">${wmbm05dbo.fb1}</td>
					      <td align="center">${wmbm05dbo.f18}</td>
					      <td align="center">${wmbm05dbo.cc1}</td>
					       <td align="center">
					        <c:choose>
	                  		<c:when test="${wmbm05dbo.f06==0}">
	                  			  申请中
	                  		</c:when>
	                  		<c:when test="${wmbm05dbo.f06==1}">
	                  			失败 			
	                  		</c:when>
	                  		<c:when test="${wmbm05dbo.f06==2}">
	                  			成功 			
	                  		</c:when>
	                  	 </c:choose>
					       </td>							  
                </tr>
             </c:forEach>       
            </table>
		</td>
	</tr>
  </table>
</body>
</html>
