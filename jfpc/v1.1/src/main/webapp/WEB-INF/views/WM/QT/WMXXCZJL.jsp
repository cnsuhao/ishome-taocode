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
<script type="text/javascript">
	function xiugai(puk){
			document.form2.action="/WMBM02/R.go?puk="+puk;
			form2.submit();
		}
	</script>
  </head>
  <body>
  <table width="100%" align="center" cellpadding="0" cellspacing="0" style="margin:0; padding:0;  border:1px solid #DBDBDB;">
    <tr height="40" >
		<td style="background:#f8f8f8; border-bottom:1px solid #dbdbdb;"><h4 style="margin-left:10px;">会员充值记录</h4></td>
	</tr>
	<tr height="50" style="border-left:1px solid #DBDBDB; border-right:1px solid #DBDBDB;">
		<td>
			<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" style="margin:0; padding:0;">
				<tr>
					<td width="9"></td>
					<td width="1075" align="left">
					  
					  <pagtag:pagtag  pageVo="${pageVO}"  url="T.go"  />
					</td>
					<td width="483" align="right">
						<input type="button" class="btn btn-info" value="检索" onclick="parent.toTargetForm('/WMQTJYCX/H.go','/WMBM02/T.go')" />
					</td>
					<td width="100"></td>
				</tr>
			</table>
		</td>
	</tr>
	<tr style="border-bottom:1px solid #DBDBDB; border-left:1px solid #DBDBDB; border-right:1px solid #DBDBDB;">
		<td>
			<table width="100%" class="table table-hover">
                <tr>
                                <th align="center">订单号</th>
                                <th align="center">会员名称</th>
                               <th align="center">通道名称</th>
							  <th align="center">充值金额</th>
							  <th align="center">充值时间</th>
							   <th align="center">充值状态</th>
							   <th align="center">账户余额</th>
<!-- 							 <th align="center">帐记余额</th> -->
<!--                               <th align="center" class="table-th-do">操作</th> -->
                </tr>
				  <form id="form2" name="form2" method="post" >

               <c:forEach var="wmbm02dbo" items="${list}">
                            <tr>
                            <td align="center" valign="middle">${wmbm02dbo.puk}</td>
                            <td align="center" valign="middle">${wmbm02dbo.f03}</td>
                                <td align="center" valign="middle">${wmbm02dbo.fb1}</td>
                              <td align="center" valign="middle">${wmbm02dbo.f07}</td>
							     <td align="center" valign="middle">${wmbm02dbo.cc1}</td>
							     <td align="center" valign="middle">
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
                             <td align="center" valign="middle">${wmbm02dbo.f18}</td>
							 
							  
							 
<!-- 							  <td align="center" valign="middle">${wmui01dbo.f02}</td> -->
<!-- 							  <td align="center" valign="middle"><a> -->

  
<!-- 							   <button type="button" id="edit" class="btn btn-info" onclick="xiugai('${wmbm02dbo.puk}')">浏览</button> -->

   
<!-- 							</a></td> -->
							  </tr>
                            </c:forEach>

							</form>
            </table>
	  </td>
	</tr>
  </table>
</body>
</html>
