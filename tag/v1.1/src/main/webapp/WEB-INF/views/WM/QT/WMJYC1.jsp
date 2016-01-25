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
 <script  type="text/javascript" src="/resources/js/My97DatePicker/WdatePicker.js"></script>

<script type="text/javascript">
	function xiugai(puk){
			document.form2.action="/WMBM02/R.go?puk="+puk;
			form2.submit();
		}
	</script>
  </head>
  <body>
  <form  action="/WMBM02/F.go" method="post">
  <table width="100%" align="center" cellpadding="0" cellspacing="0" style="margin:0; padding:0;  border:1px solid #DBDBDB;">
    <tr height="40" >
		<td style="background:#f8f8f8; border-bottom:1px solid #dbdbdb;"><h4 style="margin-left:10px;">充值记录</h4></td>
	</tr>
	<tr style="margin:10px; ">
		<td>
	
	   <table width="100%" border="0" cellpadding="0" cellspacing="0">
               <tr>
                <th width="192" height="40" align="right">订单号： </th>
                 <td align="left"><input type="text" name="puk" value="${ParamBean.puk}"></td>
                
                 <th width="192" height="40" align="right">通道名称： </th>
                 <td align="left"><input type="text" name="fb1" value="${ParamBean.fb1}"></td>
                
               </tr>
               <tr>
                 <th width="192" height="40" align="right">起始时间: </th>
                 <td align="left"><input type="text"  class="form-control input-sm" id="f02" name="f02" value="${ParamBean.f02}" size="" style="width: 200px;" onfocus="WdatePicker({dateFmt:'yyyy/MM/dd'})" onClick="WdatePicker()" ></td>
                 <th align="right">结束时间： </th>
                 <td align="left"><input type="text"  class="form-control input-sm" id="f03" name="f03" value="${ParamBean.f03}" size="" style="width: 200px;" onfocus="WdatePicker({dateFmt:'yyyy/MM/dd'})" onClick="WdatePicker()" ></td>
               </tr>
        
             </table>  
	
		</td>
	</tr>
	
	<tr height="50" style="border-left:1px solid #DBDBDB; border-right:1px solid #DBDBDB;">
		<td>
			<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" style="margin:0; padding:0;">
				<tr>
					<td width="9"></td>
					<td width="1079" align="left">

					<pagtag:pagtag  pageVo="${pageVO}"  url="F.go" /> 
					</td>
					<td width="479" align="right">
				 <input type="submit" class="btn btn-default btn-sm" value="检索" " />	 
					</td>
					<td width="100"></td>
				</tr>
			</table>
		</td>
	</tr>
	
	<tr style="border-bottom:1px solid #DBDBDB; border-left:1px solid #DBDBDB; border-right:1px solid #DBDBDB;">
		<td>
			<table width="100%" class="table table-hover" border="0">
                <tr>
                  <th align="center">订单号</th>
                     <th align="center">通道名称</th>
							  <th align="center">充值金额</th>
							  <th align="center">交易手续费</th>
							  <th align="center">实得金额</th>
							  <th align="center">充值时间</th>
							   <th align="center">备注说明</th>
 							   <th align="center">充值状态</th> 
<!--  							    <th align="center">交易前余额</th>  -->
                </tr>

               <c:forEach var="wmbm02dbo" items="${pageVO.pageData}">
                      <tr>
                            <td align="center" title="${wmbm02dbo.bbb}">${wmbm02dbo.puk}</td>
                                <td align="center">${wmbm02dbo.fb1}</td>
                              <td align="center">${wmbm02dbo.f07}</td>
							  <td align="center">${wmbm02dbo.eb2}</td>
							    <td align="center">${wmbm02dbo.f18}</td>
							     <td align="center">${wmbm02dbo.cc1}</td>
							       <td align="center">${wmbm02dbo.bbb}</td>
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
<!-- 							  <td align="center">${wmbm02dbo.fb2}</td> -->
                            </c:forEach>

            </table>
	  </td>
	</tr>
  </table>
  
	</form>
</body>
</html>
