<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/lib/pagtag.tld"  prefix="pagtag" %>
<!doctype html>
<html lang="en">
 <head>

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
	
	</script>
 </head>
 <body>
  <table width="100%" style="margin:0; padding:0;  border:1px solid #DBDBDB;">
 	<tr height="40" >
		<td style="background:#f8f8f8; border-bottom:1px solid #dbdbdb;"><h4 style="margin-left:10px;">我的支付通道</h3></td>
	</tr>
	<tr height="50" style="border-left:1px solid #DBDBDB; border-right:1px solid #DBDBDB;">
		<td>
			<table align="center" width="100%" border="0">
				<tr>
					
					<td align="left">
					 <div style="padding: 0 0 0 10px;"> <pagtag:pagtag  pageVo="${pageVO}"  url="F.go"  /></div>
					</td>
					
				</tr>
			</table>
		</td>
	</tr>
<tr style="border-bottom:1px solid #DBDBDB; border-left:1px solid #DBDBDB; border-right:1px solid #DBDBDB;">
    <td><table width="100%" class="table table-hover" border="0">
                            <tr>
                          
                              <th align="center">通道名称</th>
                              <th align="center">通道说明</th>
            <th >充值费率</th>
            <th >提现费用<br>(标准)</th>
            <th >提现费用<br>(休息日)</th>
            <th >提现费用<br>(节假日)</th>
							  <th align="center">通道余额</th>
							  	 <c:if test="${userinfo.k02=='DFB_QT_VIP_1'}">
							  <th align="center">通道佣金余额</th>
							  </c:if>
							  <th align="center">操作</th>
							</tr>
		
	                    <c:forEach var="wmbm01dbo" items="${pageVO.pageData}">
                            <tr >
                             <td align="center">${wmbm01dbo.fb1}</td>
                             <td align="center">${wmbm01dbo.f19}</td>
              <td align="center" valign="middle" style="color: #00F">${wmbm01dbo.f15}%</td>
              <td align="center" valign="middle" style="color:#F00;">${wmbm01dbo.f16}</td>
              <td align="center" valign="middle" style="color:#F00;">${wmbm01dbo.eb3}</td>
              <td align="center" valign="middle" style="color:#F00;">${wmbm01dbo.eb4}</td>
 
							  <td align="center">${wmbm01dbo.fb4}</td>
							  	 <c:if test="${userinfo.k02=='DFB_QT_VIP_1'}">
							   <td align="center">${wmbm01dbo.eb1}</td>
							   </c:if>
							 <td align="center">
							 <input type="button" class="btn btn-info" value="充值" onclick="parent.toTargetForm('/WMBM06/${wmbm01dbo.puk}/F.go','')" />
							 <input type="button" class="btn btn-info" value="提现" onclick="parent.toTargetForm('/WMBM07/F.go?puk=${wmbm01dbo.puk}','')" />
							 <c:if test="${userinfo.k02=='DFB_QT_VIP_1'}">
							 <input type="button" class="btn btn-info" value="佣金结算" onclick="parent.toTargetForm('/WMBM04/F.go?eb2=${wmbm01dbo.puk}&f02=0','')" />
							 </c:if>
							 </td>													 
							  </tr>
                           </c:forEach>
    </table></td>
</tr> 

</table>

 </body>
</html>