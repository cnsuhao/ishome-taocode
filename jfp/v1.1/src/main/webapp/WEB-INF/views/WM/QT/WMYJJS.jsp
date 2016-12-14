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
			document.form2.action="/WMBM08/R.go?puk="+puk;	
			form2.submit();	
		}else{	
			document.form2.action="/WMBM08/R.go?puk="+puk+"&uu1="+uu1;		
			form2.submit();	
		}	
	}	
	function del(puk,uu1){
		if(uu1 == ""){
			document.form2.action="/WMBM08/D.go?puk="+puk;	
			form2.submit();	
		}else{
			document.form2.action="/WMBM08/D.go?puk="+puk+"&uu1="+uu1;		
			form2.submit();	
		}
	}
	
	//充值
	function chongzhi() {
		document.formsb01.action = "/WMBM06/H.go";
		formsb01.submit();
	}
	//提现
	function txin() {
		document.formsb01.action = "/WMBM07/H.go";
		formsb01.submit();
	}
	//	佣金结算
	// 	 function yongjinjesuan(){
	// 		document.form8.action = "/WMBM08/H.go";
	// 		form8.submit();
	// 	}
	</script>
  </head>
  <body>
  <table align="center" width="100%" style="margin:0; padding:0;" border="0">
    <tr height="40" style="border:1px solid #DBDBDB; background:#f8f8f8;">
		<td><div style="font-size:14px; font-weight:bold;margin-left:10px;">银行账户</div></td>
	</tr>
	<tr height="50" style="border-left:1px solid #DBDBDB; border-right:1px solid #DBDBDB;">
		<td>
			<table align="center" width="100%" style="margin:0; padding:0;" border="0">
				<tr>
<!-- 					<td> -->
<!-- 					 <div style="padding:15px 20px; _padding:0 20px ;"><div class="pagination"> -->
<!-- 					    <ul> -->
<!-- 					      <li><a href="#">上一页</a></li> -->
<!-- 					      <li class="active"><a href="#">1</a></li> -->
<!-- 					      <li><a href="#">2</a></li> -->
<!-- 					      <li><a href="#">3</a></li> -->
<!-- 					      <li><a href="#">4</a></li> -->
<!-- 					      <li><a href="#">5</a></li> -->
<!-- 					      <li><a href="#">下一页</a></li> -->
<!-- 					      </ul> -->
<!-- 					    </div>	</div>			    </td> -->
<pagtag:pagtag  pageVo="${pageVO}"  url="F.go" />
					<td width="30%" align="right">
 <input type="button" class="btn btn-default btn-sm" value="检索" onclick="parent.toTargetForm('/WMTJCX/H.go','/WMBM02/F.go')" />
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
                    <th align="center" valign="middle">用户名</th>
                              <th align="center" valign="middle">银行名称</th>
                              <th align="center" valign="middle">银行账号</th>
							  <th align="center" valign="middle">所在省市</th> 
							  <th align="center" valign="middle">银行详细地址</th> 
<!--                               <th align="center" valign="middle" class="table-th-do"><div style="padding-left:12px;">操作</div></th> -->
							  
                </tr>
					<form action="" method="post" id="form2" name="form2">
				
				  <c:forEach var="wmbs01dbo" items="${pageVO.pageData}">
                            <tr>
                              <td>${wmbs01dbo.f01}</td>
                              <td>${wmbs01dbo.f02}</td>
                              <td>${wmbs01dbo.k02}</td>
							  <td>${wmbs01dbo.f04}${wmbs01dbo.f05}</td>
							   <td>${wmbs01dbo.f03}</td>
<!-- 							  <td><a> -->
<!-- 							  <div class="control-group"> -->
<!-- 										<div class="controls"> -->
<!-- 							  <button type="button" id="edit" class="btn btn-info" -->
<!-- 							onclick="xiugai('${wmbs01dbo.puk}','${wmbs01dbo.uu1}')">编辑</button> -->
<!-- 							 </div> -->
<!-- 							 </div> -->
<!-- 							 </a> -->
<!-- 							  <a> -->
<!-- 							 <div class="control-group"> -->
<!-- 										<div class="controls"> -->
<!-- 							  	<button type="button" id="edit" class="btn btn-info" -->
<!-- 							onclick="del('${wmbs01dbo.puk}','${wmbs01dbo.uu1}')">删除</button> -->
<!-- 							 </div> -->
<!-- 							 </div> -->
<!-- 							 </a> -->
<!-- 							  </td> -->
                            </tr>
                            </c:forEach>
							</form>
							
            </table>
		</td>
	</tr>
  </table>
</body>
</html>
