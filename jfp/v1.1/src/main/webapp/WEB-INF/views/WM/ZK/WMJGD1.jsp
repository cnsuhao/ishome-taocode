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
<!-- 	<script type="text/javascript" src="/resources/js/wm/jquery-1.8.3.js"></script> -->
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
		<td><h4 style="margin-left:10px;">通道一览</h4></td>
	</tr>
	<tr height="40" style="border-left:1px solid #DBDBDB; border-right:1px solid #DBDBDB;">
		<td>
			<table align="center" width="100%" border="0">
				<tr>
					<td width="2%"></td>
					<td align="left">
						<ul class="pagination pagination-sm" style="margin:0;">
							<pagetag:pagtag  pageVo="${pageVO}"  url="/WMJG01/F.go" style="4"/>
						</ul>
					</td>
					<td>${message}</td>
					<td width="30%" align="right">
					<form id="form1" name="form1" action="" method="post">
					    <input type="hidden" id="btnhidd" name="btnhidd" value="${flag}">											
						<input class="btn btn-primary btn-sm"  type="button" id="dis" value="分配通道" onclick="distribution()">
						<input class="btn btn-primary btn-sm" type="button" value="检索" 						
						onclick="parent.toTargetForm('/WMJGY1L/H.go','/WMJG01/F.go')">
					</form>
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
                  	<th>授权编号</th>	 
                  	<th>授权域名</th>
                  	<th>分站管理员</th>
                  	<th>授权联系人</th>
                  	<th>通道名称</th>
                  	<th>充值手续费率(%)</th>
                  	<th>提现手续费(元)</th>
                  	<th>提现最大金额(元)</th>
                	<th>结算说明</th><!--   	T+0 -->   
                	<th>操作</th>            		
                </tr>
                 <form action="" method="post" id="form2" name="form2">	
		                <c:forEach var="wmbma1dbo" items="${pageVO.pageData}">	
		                <tr align="center">
							<td>${wmbma1dbo.k01}</td>
							<td>${wmbma1dbo.k02}</td>
							<td>${wmbma1dbo.k03}</td>
							<td>${wmbma1dbo.f07}</td>
							<td>${wmbma1dbo.f03}</td>
							<td>${wmbma1dbo.f15}</td>
							<td>${wmbma1dbo.f16}</td>
							<td>${wmbma1dbo.f17}</td>
							<td>${wmbma1dbo.f19}</td>		
							<td>
							<button  class="btn btn-link" onclick="edit('${wmbma1dbo.puk}','${wmbma1dbo.uu1}')">修改</button>						
<!-- 							<button  class="btn btn-link"  onclick="del('${wmbma1dbo.puk}','${wmbma1dbo.uu1}')">删除</button>						 -->
							</td>	                
		                </tr>
	                </c:forEach>
	                </form> 
            </table>
		</td>
	</tr>
  </table>
</body>
</html>
