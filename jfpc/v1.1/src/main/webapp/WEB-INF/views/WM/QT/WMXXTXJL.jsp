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
			document.form2.action="/WMBM03/R.go?puk="+puk;
			form2.submit();
		}
	</script>
  </head>
  <body>
  <table width="100%"  align="center" cellpadding="0" cellspacing="0" style="margin:0; padding:0;  border:1px solid #DBDBDB;">
    <tr height="40">
		<td style="background:#f8f8f8; border-bottom:1px solid #dbdbdb;"><h4 style="margin-left:10px;">会员提现记录</h4></td>
	</tr>
	<tr height="50" style="border-left:1px solid #DBDBDB; border-right:1px solid #DBDBDB;">
		<td>
			<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" style="margin:0; padding:0;">
				<tr>
					<td width="9"></td>
					<td width="1078" align="left">
					  
					  <pagtag:pagtag  pageVo="${pageVO}"  url="F.go"  />
					</td>
					<td width="482" align="right">
						<input type="button" class="btn btn-info" value="检索" onclick="parent.toTargetForm('/WMQTJYCX/H.go','/WMBM03/W.go')" />
					</td>
					<td width="100"></td>
				</tr>
			</table>
		</td>
	</tr>
	<tr style="border-bottom:1px solid #DBDBDB; border-left:1px solid #DBDBDB; border-right:1px solid #DBDBDB;">
		<td>
			<table class="table table-hover">
                <tr>
                               <th>结算编号</th>
                              <th>开户行名称</th>
                              <th>银行户名</th>
							  <th>所属通道</th>
							  <th>提现金额</th>
							  <th>申请时间</th>
							  <th>审核状态</th>
							  <th>结算时间</th>
							  
<!--                               <th class="table-th-do">操作</th> -->
                </tr>
				  <form id="form2" name="form2" method="post" >


				 <c:forEach var="wmbm03dbo" items="${list}">
                            <tr>
                              <td><a href="javascript:parent.toTargetForm('/WMBM02/${wmbm03dbo.k01}/${wmbm03dbo.k02}/${wmbm03dbo.puk}/Z.go','')">${wmbm03dbo.puk}</a></td>
                              <td>${wmbm03dbo.f04}</td>
                              <td>${wmbm03dbo.f03}</td>
							  <td>${wmbm03dbo.fb1}</td>
							  <td>${wmbm03dbo.f07}</td>
							  <td>${wmbm03dbo.cc1}</td>
							   <td>
							   <c:choose>
		                  		<c:when test="${wmbm03dbo.f06==0}">
		                  			 申请中     			
		                  		</c:when>
		                  		<c:when test="${wmbm03dbo.f06==1}">
		                  			 失败       			
		                  		</c:when>
		                  		<c:when test="${wmbm03dbo.f06==2}">
		                  			 成功       			
		                  		</c:when>
		                  	 </c:choose>
							   </td>
							  <td>${wmbm03dbo.uu1}</td>
							  
<!--                               <td><a> -->
<!-- 							   <button type="button" id="edit" class="btn btn-default btn-lg" onclick="xiugai('${wmbm03dbo.puk}')">浏览</button> -->
<!-- 							   </a></td> -->
                            </tr>
                            </c:forEach>

						</form>	
            </table>
		</td>
	</tr>
  </table>
</body>
</html>
