<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ taglib uri="/WEB-INF/lib/pagtag.tld" prefix="pagtag" %>

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
  <table width="100%"  align="center" cellpadding="0" cellspacing="0" style="margin:0; padding:0;  border:1px solid #DBDBDB;">
    <tr height="40" >
		<td style="background:#f8f8f8; border-bottom:1px solid #dbdbdb;"><h4 style="margin-left:10px;">会员一览</h4></td>
	</tr>
	<tr height="50" style="border-left:1px solid #DBDBDB; border-right:1px solid #DBDBDB;">
		<td>
			<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" style="margin:0; padding:0;">
				<tr>
				<td width="9"></td>
				<td width="1076" align="left">
                	<pagtag:pagtag  pageVo="${pageVO}"  url="F.go" />
                </td>
					<td width="482" align="right">
					<input type="button" class="btn btn-info" value="添加会员" onclick="parent.toTargetForm('/WMBM09/H.go','/WMBM09/H.go')" />
						 <input type="button" class="btn btn-default btn-sm" value="检索" onclick="parent.toTargetForm('/WMQTJYCX/H.go','/WMBM09/F.go')" />
					</td>
					<td width="100"></td>
				</tr>
			</table>
		</td>
	</tr>	
	<tr style="border-bottom:1px solid #DBDBDB; border-left:1px solid #DBDBDB; border-right:1px solid #DBDBDB;">
    	<td><table class="table table-hover">
                 <tr>
                    <th>编号</th>
                    <th>会员名称</th>
                    <th>会员姓名</th>
					<th>用户类型</th>
					<th>审核状态</th>
                </tr>
           <c:forEach var="wmui01dbo" items="${list}">
               <tr>
                 <td>${wmui01dbo.puk}</td>
                 <td><a href="javascript:parent.toTargetForm('/WMBM09/${wmui01dbo.puk}/R.go','')">${wmui01dbo.f03}</a></td>  
                 <td>${wmui01dbo.f04}</td>                            
			  	<td>${wmui01dbo.f01}</td>	
			  	<td>
			  	<c:choose>
          		<c:when test="${wmui01dbo.fb4==0}">
		                 未审核			
		        </c:when>
		        <c:when test="${wmui01dbo.fb4==1}">
		                 审核通过     			
		        </c:when>
		        <c:when test="${wmui01dbo.fb4==2}">
		                 审核失败      			
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
