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
  <table width="100%" border="0">
 <tr>
    <td><table  width="100%" border="0"> 
            <tr>
            <td>
            <div class="tab-main dark-gray">
       <h3 class="tab-main-title"><div style="font-size:14px; font-weight:bold; color:#000;">分配支付通道</div></h3>
      </div></td>
      
        </table>
        </td>
  </tr>

	<tr height="50" style="border-left:1px solid #DBDBDB; border-right:1px solid #DBDBDB;">
		<td>
			<table align="center" width="100%" style="margin:0; padding:0;" border="0">
				<tr>
					<td width="2%"></td>
					<td align="left">
					  <pagtag:pagtag  pageVo="${pageVO}"  url="F.go"  />
					</td>
					
				</tr>
			</table>
		</td>
	</tr>
<tr>
    <td> <div style="padding:0 20px;"><table width="100%" class="table table-hover">
                            <tr>
                              <!-- <th>会员名称</th> -->
                              <th>通道名称</th>
                               <!-- <th>原通道名称</th> -->
                              <th>通道说明</th>
					            <th>充值费率</th>
					            <th>提现费用<br>(标准)</th>
					            <th>提现费用<br>(休息日)</th>
					            <th>提现费用<br>(节假日)</th>
							  <th>单笔结算上限</th>
							  <th>操作</th>
							</tr>
		
	                    <c:forEach var="wmbm01dbo" items="${list}">
                            <tr >
                           <%--  <td>${userinfo.f04}</td> --%>
                             <td>${wmbm01dbo.f03}</td>
                             <%-- <td>${wmbm01dbo.fb1}</td> --%>
                             <td>${wmbm01dbo.f19}</td>
              <td align="center" valign="middle" style="color: #00F">${wmbm01dbo.f15}%</td>
              <td align="center" valign="middle" style="color:#F00;">${wmbm01dbo.f16}</td>
              <td align="center" valign="middle" style="color:#F00;">${wmbm01dbo.eb1}</td>
              <td align="center" valign="middle" style="color:#F00;">${wmbm01dbo.eb2}</td>
							 <td>${wmbm01dbo.f17}</td>
							<td>
							 <c:choose>
		                  		<c:when test="${wmbm01dbo.puk==''}">
		                  		<a href="/WMKLH1/${userinfo.puk}/${wmbm01dbo.k03}/P.go">分配通道</a>									 
		                  		</c:when>
		                  		<c:when test="${wmbm01dbo.puk!=''}">
									 已分配
		                  		</c:when>
		                  	 </c:choose>						
							 </td>													 
							  </tr>
                           </c:forEach>
    </table></div></td>
</tr> 




</table>

 </body>
</html>