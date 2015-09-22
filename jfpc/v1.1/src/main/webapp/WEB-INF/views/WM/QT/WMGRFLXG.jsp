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
       <h3 class="tab-main-title"><div style="font-size:14px; font-weight:bold; color:#000;">会员费率修改</div></h3>
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
					<td>${message}</td>
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
    <td><table width="100%" class="table table-hover" border="0">
                            <tr>
                              <th>通道名称</th>
                              <th>通道说明</th>
                              <th>充值费率</th>
            <th align="center" valign="middle">提现费用<br>(标准)</th>
            <th align="center" valign="middle">提现费用<br>(休息日)</th>
            <th align="center" valign="middle">提现费用<br>(节假日)</th>
							  <th>提现上限</th>
							  <th>操作</th>
							</tr>
	                    <c:forEach var="wmbm01dbo" items="${pageVO.pageData}">
                            <tr>
                             <td>${wmbm01dbo.fb1}</td>
                             <td>${wmbm01dbo.f19}</td>
              <td align="center" valign="middle" style="color: #00F">${wmbm01dbo.f15}%</td>
              <td align="center" valign="middle" style="color:#F00;">${wmbm01dbo.f16}</td>
              <td align="center" valign="middle" style="color:#F00;">${wmbm01dbo.eb3}</td>
              <td align="center" valign="middle" style="color:#F00;">${wmbm01dbo.eb4}</td>
							 <td>${wmbm01dbo.f17}</td>
							<td>
						
									<input type="button" class="btn btn-info" value="费率修改" onclick="parent.toTargetForm('/WMKHTD/${wmbm01dbo.puk}/R.go','/WMKHTD/C.go')" />
                                    
							 </td>													 
							  </tr>
                           </c:forEach>
    </table></td>
</tr> 




</table>

 </body>
</html>