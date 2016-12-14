<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/lib/pagtag.tld" prefix="pagetag" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">
  <head>
  <base href="<%=basePath%>">    
    <title>通道</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
   <!-- Bootstrap -->
    <link href="/resources/css/wm/zk/bootstrap.min.css" rel="stylesheet" media="screen">
	<link href="/resources/css/wm/zk/global.css" rel="stylesheet" media="screen">
	<link href="/resources/css/wm/zk/sticky-footer-navbar.css" rel="stylesheet">	
    
	<!-- Bootstrap js -->
	<script src="/resources/js/wm/zk/jquery.js"></script>
    <script src="/resources/js/wm/zk/bootstrap.min.js"></script>
    <script src="/resources/js/wm/zk/topmenu.js"></script>	 	
	<script src="/resources/js/wm/zk/bootstrap.js"></script>
	<script type="text/javascript">
		/*$(function() {
 			if($('#btnhidd').val().toString()=="false"){
				$('#dis').hide();
 			}		
		});*/
		function distribution(){
			document.form1.action = "/WMJG01/B.go";
			form1.submit();
		}
		function sub(){
			document.form1.action = "/WMJG01/A.go";
			form1.submit();
		}
		function edit(puk,uu1){			
			if(uu1 == ""){
				document.form2.action="/WMJG01/R.go?puk="+puk;	
				form2.submit();	
			}else{	
				document.form2.action="/WMJG01/R.go?puk="+puk+"&uu1="+uu1;		
				form2.submit();	
			}	
		}
		function del(puk,uu1){		
			if(uu1 == ""){
				document.form2.action="/WMJG01/D.go?puk="+puk;	
				alert(document.form2.action);
				form2.submit();	
			}else{
				document.form2.action="/WMJG01/D.go?puk="+puk+"&uu1="+uu1;	
				alert(document.form2.action);	
				form2.submit();	
			}		
		}
	</script>
	<style type="text/css"> 
          th{
          	 text-align: center;
          }       
    </style> 		
  </head>
  <body>
  <table align="center" width="100%" style="margin:0; padding:0;" border="0">
    <tr height="40" style="border:1px solid #DBDBDB; background:#f8f8f8;">
		<td><h4 style="margin-left:10px;">客户通道设定</h4></td>
	</tr>
	<tr height="40" style="border-left:1px solid #DBDBDB; border-right:1px solid #DBDBDB;">
		<td>
			<table align="center" width="100%" border="0">
				<tr>
					<td width="2%"></td>
					<td align="left">
					</td>
					<td>${message}</td>
					<td width="30%" align="right">
					<input type="button" class="btn btn-info" value="返回" onclick="parent.toTargetForm('/WMKZ01/F.go','')">
					</td>
					<td width="2%"></td>
				</tr>
			</table>
		</td>
	</tr>
	<tr style="border-bottom:1px solid #DBDBDB; border-left:1px solid #DBDBDB; border-right:1px solid #DBDBDB;">
		<td>
			<table class="table table-hover">
                <tr>
                  	<th>系统<br>通道名称</th>
                  	<th>客户<br>通道名称</th>
                  	<th>充值费率<br>(%)</th>
                  	<th>提现费用<br>(标准)</th>
                  	<th>提现费用<br>(休息日)</th>
                  	<th>提现费用<br>(节假日)</th>
                	<th>单笔提现<br>上限</th> 
                	<th>通道说明</th> 
                	<th>操作</th>            		
                </tr>
		                <c:forEach var="wmbma1dbo" items="${pageVO.pageData}">	
		                <tr align="center">
							<td>${wmbma1dbo.fb1}</td>
							<td>${wmbma1dbo.f03}</td>
							<td>${wmbma1dbo.f15}</td>
							<td>${wmbma1dbo.f16}</td>
							<td>${wmbma1dbo.eb1}</td>
							<td>${wmbma1dbo.eb2}</td>
							<td>${wmbma1dbo.f17}</td>
							<td>${wmbma1dbo.f19}</td>		
							<td>
							<c:choose>
		                  		<c:when test="${wmbma1dbo.puk==''}">
									 <input type="button" class="btn btn-info" value="通道设定" onclick="parent.toTargetForm('/WMJG01/B.go?puk=${wmbma1dbo.puk}&eb5=${eb5}&fb3=${wmbma1dbo.fb3}','/')" />
		                  		</c:when>
		                  		<c:when test="${wmbma1dbo.puk!=''}">
									<input type="button" class="btn btn-info" value="取消分配" onclick="parent.toTargetForm('/WMJG01/D.go?k01=${wmbma1dbo.puk}&uu1=${wmbma1dbo.uu1}','/')" />
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
