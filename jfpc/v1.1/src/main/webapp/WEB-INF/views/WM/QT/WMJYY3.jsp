<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/lib/pagtag.tld"  prefix="pagtag"%>

<!DOCTYPE html>
<html lang="en">
  <head>
    <title>${userInfo.f04}的佣金记录</title>
 
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
			document.form1.action="/WMBM04/R.go?puk="+puk;
			form1.submit();
		}
	</script>
  </head>
  <body>
  <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" style="margin:0; padding:0;">
    <tr height="40" style="border:1px solid #DBDBDB; background:#f8f8f8;">
		<td><h4 style="margin-left:10px;">${userInfo.f04}的佣金记录</h3></td>
	</tr>
	<tr height="50" style="border-left:1px solid #DBDBDB; border-right:1px solid #DBDBDB;">
		<td>
			<table align="center" width="100%" style="margin:0; padding:0;" border="0">
				<tr>
					<td width="2%"></td>
					<td align="left">

					<pagtag:pagtag  pageVo="${pageVO}"  url="F.go" /> 
					</td>
					<td width="30%" align="right">
						
<!-- 					<form action="" method="post" id="form1" name="form1"> -->
<!-- 						<button type="submit" class="btn"  onclick="tianjia()">添加</button> -->
<!-- 						<button type="submit" class="btn btn-primary">检索</button> -->
<!-- 					</form> -->
					</td>
					<td width="2%"></td>
				</tr>
			</table>
		</td>
	</tr>
	<tr style="border-bottom:1px solid #DBDBDB; border-left:1px solid #DBDBDB; border-right:1px solid #DBDBDB;">
		<td align="left">
			<table width="100%" border="0" cellpadding="0" cellspacing="0" class="table table-hover">
                <tr>
                               <th align="center">结算编号</th>
                              <th align="center">开户行名称</th>                             
							  <th align="center">银行账号</th>
							  <th align="center">佣金金额</th>
							 <th align="center">交易类型</th>
							  <th align="center">申请时间</th>
							  <th align="center">结算状态</th>
							  <th align="center">结算日期</th>
<!--                               <th align="center" class="table-th-do">操作</th> -->
                </tr>
 				   <form action="" method="post" id="form1" name="form1"> 
                            <c:forEach var="wmbm04dbo" items="${list}">
                            <tr>
                              <td align="center">${wmbm04dbo.puk}</td> 
                              <td align="center">${wmbm04dbo.f03}</td>
                             <td align="center">${wmbm04dbo.k02}</td>
                              <td align="center">${wmbm04dbo.f08}</td>
                              <td align="center">
                              <c:choose>
		                  		<c:when test="${wmbm04dbo.k01==0}">
		                  			 充值     			
		                  		</c:when>
		                  		<c:when test="${wmbm04dbo.k01==1}">
		                  			 提现       			
		                  		</c:when>
		                  		<c:when test="${wmbm04dbo.k01==2}">
		                  			 转账      			
		                  		</c:when>
		                  	 </c:choose>
                              </td>
							 <td align="center">${wmbm04dbo.cc1}</td>
						       <td align="center">
						        <c:choose>
		                  		<c:when test="${wmbm04dbo.f02==0}">
		                  			未结算  			
		                  		</c:when>
		                  		<c:when test="${wmbm04dbo.f02==1}">
		                  			已结算       			
		                  		</c:when>
		                  	 </c:choose>
						       </td>
							  <td align="center">${wmbm04dbo.uu1}</td>
							  
						 
<!--                               <td align="center"><a> -->
                   
						
<!-- 							   <button type="button" id="edit" class="btn btn-primary " onclick="xiugai('${wmbm04dbo.puk}')">浏览</button> -->
							
						
<!-- 							 </a></td> -->
						
							 
							
                            </tr>
                        	</c:forEach>
                           </form>
							
            </table>
		</td>
	</tr>
  </table>
</body>
</html>
