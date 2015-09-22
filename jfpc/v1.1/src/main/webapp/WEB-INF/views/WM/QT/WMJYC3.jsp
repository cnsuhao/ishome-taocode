<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/lib/pagtag.tld"  prefix="pagtag" %>

<!DOCTYPE html>
<html lang="en">
  <head>
    <title>${userInfo.f04}的充值记录</title>
 
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
<script type="text/javascript">
	function xiugai(puk){
			document.form2.action="/WMBM02/R.go?puk="+puk;
			form2.submit();
	}
    
	

	
</script>
  </head>
  <body>
  <table align="center" width="100%" style="margin:0; padding:0;" border="0">
    <tr height="40" style="border:1px solid #DBDBDB; background:#f8f8f8;">
		<td><h4 style="margin-left:10px;">${userInfo.f04}的充值记录</h4></td>
	</tr>
	<tr height="50" style="border-left:1px solid #DBDBDB; border-right:1px solid #DBDBDB;">
		<td>
			<table align="center" width="100%" style="margin:0; padding:0;" border="0">
				<tr>
					<td width="2%"></td>
					<td align="left">
					  <pagtag:pagtag  pageVo="${pageVO}"  url="X.go"  />
					</td>
					<td align="right">
					<c:if test="${ddd!=null||ddd!=''}">
					<input type="button" class="btn btn-info" value="返回" onclick="parent.toTargetForm('/WMBM09/${userInfo.puk}/R.go','')">
					</c:if>
					</td>
					<td width="2%"></td>
				</tr>
			</table>
		</td>
	</tr>
	<tr style="border-bottom:1px solid #DBDBDB; border-left:1px solid #DBDBDB; border-right:1px solid #DBDBDB;">
		<td>
			<table width="100%" class="table table-hover">
                <tr>
                  <th align="center">订单号</th>
                     <th align="center">通道名称</th>
<!--                               <th align="center">发生日期</th> -->
							  <th align="center">充值金额</th>
							  <th align="center">交易手续费</th>
							  <th align="center">实得金额</th>
							  <th align="center">充值时间</th>
 							   <th align="center">充值状态</th> 
 							 
                </tr>

               <c:forEach var="wmbm02dbo" items="${pageVO.pageData}">
                      <tr>
                            <td align="center">${wmbm02dbo.puk}</td>
                                <td align="center">${wmbm02dbo.fb1}</td>
                              <td align="center">${wmbm02dbo.f07}</td>
							  <td align="center">${wmbm02dbo.eb2}</td>
							    <td align="center">${wmbm02dbo.f18}</td>
							     <td align="center">${wmbm02dbo.cc1}</td>
 							  <td align="center">
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
 							
							  </tr>
                            </c:forEach>

            </table>
	  </td>
	</tr>
  </table>
</body>
</html>
