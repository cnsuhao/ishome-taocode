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

	function xiugai(puk,uu1) {
		if(uu1 == ""){
			document.form2.action="/WMHTFLMX/R.go?puk="+puk;	
			form2.submit();	
		}else{	
			document.form2.action="/WMHTFLMX/R.go?puk="+puk+"&uu1="+uu1;		
			form2.submit();	
		}	
	}	
	
	function del(puk,uu1)
	{

	if(uu1 == ""){
		document.form2.action="/WMHTFLMX/D.go?puk="+puk;	
		form2.submit();	
		}else{
		document.form2.action="/WMHTFLMX/D.go?puk="+puk+"&uu1="+uu1;
	    form2.submit();	
		}
     }
	</script>
  </head>
  <body>
 <form id="form2" name="form2" method="post" action="">
 </form>
  <table align="center" width="100%" style="margin:0; padding:0;" border="0">
    <tr height="40" style="border:1px solid #DBDBDB; background:#f8f8f8;">
		<td><h4 style="margin-left:10px;">动态费率设定</h4></td>
	</tr>
	<tr height="40" style="border-left:1px solid #DBDBDB; border-right:1px solid #DBDBDB;">
		<td>
			<table align="center" width="100%" style="margin:0; padding:0;" border="0">
				<tr>
					<td width="2%"></td>
					<td align="left">
					  
					  <pagtag:pagtag  pageVo="${pageVO}"  url="F.go"  />
					</td>
					<td width="30%" align="right">
						<c:choose>
		             		<c:when test="${ddd==1}">
							<a href="/WMSZT2/F.go">返回</a>
		             		</c:when>
		             		<c:when test="${ddd==0}">
							<a href="/">返回</a>
		             		</c:when>
		             	 </c:choose>
		             	 &nbsp;&nbsp;<a href="javascript:parent.toTargetForm('/WMHTFLMX/${roadid}/H.go','/WMHTFLMX/F.go')">添加</a>	
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
                  <th>日期分类</th>
				  <th>开始日期</th>
				  <th>终了日期</th>
				  <th>开始时间</th>
				  <th>终了时间</th>
				  <th>充值手续费率</th>
				  <th>提现手续费</th>
                  <th class="table-th-do">操作</th> 
                </tr>
	                 <c:forEach var="wmbma2dbo" items="${list}">
	             <tr>  
	                <td>
	                   <c:choose>
	             		<c:when test="${wmbma2dbo.k02==0}">
	             			星期    			
	             		</c:when>
	             		<c:when test="${wmbma2dbo.k02==1}">
	             			日期       			
	             		</c:when>
	             	 </c:choose>
	                  </td>
						 <td>${wmbma2dbo.f01}</td>
						 <td>${wmbma2dbo.f02}</td>
						 <td>${wmbma2dbo.f03}</td>
						 <td>${wmbma2dbo.f04}</td>
						 <td>${wmbma2dbo.f15}</td>	
						 <td>${wmbma2dbo.f16}</td>
				  	<td>
				  	<input type="button" class="btn btn-info" value="编辑" onclick="parent.toTargetForm('/WMHTFLMX/${wmbma2dbo.puk}/R.go','')" />
				  	<input type="button" class="btn btn-info" value="删除" onclick="del('${wmbma2dbo.puk}','${wmbma2dbo.uu1}')" />
			  		</td>     
                    </tr>
			 </c:forEach>
            </table>
		</td>
	</tr>
  </table>
</body>
</html>
