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
    <title>子系统提现一览</title>
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
		function add(){
			document.form1.action = "/WMJG04/H.go";
			form1.submit();
		}
		function back(){
			document.form1.action = "/WMJG04/H.go";
			form1.submit();
		}
		function search(){
			document.form1.action = "/WMJG04/B.go";
			form1.submit();
		}
		function check(){
			document.form1.action = "/WMJG04/F.go";
			form1.submit();
		}
		function del(puk,uu1){
			if(uu1 == ""){
				document.form2.action="/WMJG04/D.go?puk="+puk;	
				form2.submit();	
			}else{
				document.form2.action="/WMJG04/D.go?puk="+puk+"&uu1="+uu1;		
				form2.submit();	
			}		
		}
		function edit(puk,uu1){
			if(uu1 == ""){
				document.form2.action="/WMJG04/R.go?puk="+puk;	
				form2.submit();	
			}else{	
				document.form2.action="/WMJG04/R.go?puk="+puk+"&uu1="+uu1;		
				form2.submit();	
			}	
		}		
		function sub(){
			document.form1.action = "/WMJG04/B.go";
			form1.submit();
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
		<td><h4 style="margin-left:10px;">提现一览统计</h4></td>
	</tr>
	<tr height="50" style="border-left:1px solid #DBDBDB; border-right:1px solid #DBDBDB;">
		<td><table style="width:100%; color:red; font-size:12px; margin:0; padding-top:10px;">
		  <tr>
		    <td align="right">message</td>
		    <td width="2%">&nbsp;</td>
	      </tr>
		  </table>
	    </td>
	</tr>
	<tr height="40" style="border-left:1px solid #DBDBDB; border-right:1px solid #DBDBDB;">
		<td>
			<table align="center" width="100%" border="0">
				<tr>
					<td width="2%"></td>
					<td width="66%" align="left">
					<pagetag:pagtag  pageVo="${pageVO}"  url="/WMJG04/F.go" style="4"/>
<!-- 						<ul class="pagination pagination-sm" style="margin:0;"> -->
<!-- 							<li><a href="#">上一页</a></li> -->
<!-- 							<li class="active"><a href="#">1</a></li> -->
<!-- 							<li><a href="#">2</a></li> -->
<!-- 							<li><a href="#">3</a></li> -->
<!-- 							<li><a href="#">4</a></li> -->
<!-- 							<li><a href="#">5</a></li> -->
<!-- 							<li><a href="#">下一页</a></li> -->
<!-- 						</ul> -->
					</td>
					<td width="30%" align="right">
					<form id="form1" name="form1" action="" method="post">
						<button  class="btn btn-default btn-sm" onclick="sub()">查询</button>
<!-- 						<button  class="btn btn-primary btn-sm" onclick="">重置</button> -->
						<button  class="btn btn-default btn-sm" onclick="">导出excel</button>
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
                  <th><input type="hidden" id="puk" value="${csss01.puk}"></th>  
                  		<th>序号</th>
                  		<th>授权编号</th>
                  		<th>授权域名</th>
                  		<th>授权联系人</th>
                  		<th>结算通道</th>
                  		<th>客户名称</th>
                  		<th>结算编号</th>
                  		<th>银行名称</th>
                  		<th>账号</th>
                  		<th>户名</th>
                  		<th>实付金额</th>
                  		<th>提现费用</th>
<!--                   		<th>申请时间</th>                 		 -->
<!--                   		<th>结算时间</th> -->
		               		<th class="table-th-do" style="text-align: center;">操作</th>
                </tr>
                 <form action="" method="post" id="form2" name="form2">	
		                <c:forEach var="wmbm03dbo" items="${pageVO.pageData}">	
		         <tr align="center">
		               	 <td><input type="hidden" id="puk" value="${wmbm03dbo.puk}"></td>
		               	 		<td>&nbsp;</td>
		                  	   <td>${wmbm03dbo.k01}</td>
		                  	   <td>${wmbm03dbo.k02}</td>
		                  	   <td>${wmbm03dbo.f07}</td>
		                  	   <td>${wmbm03dbo.fb1}</td>
		                  	   <td>${wmbm03dbo.f01}</td>
		                  	   <td>${wmbm03dbo.puk}</td>
		                  	   <td>${wmbm03dbo.f04}</td>
							   <td>${wmbm03dbo.f01}</td>
		                  	   <td>${wmbm03dbo.f03}</td>
		                  	   <td>${wmbm03dbo.f18}</td>
		                  	   <td>${wmbm03dbo.f15}</td>	                  	   
		                  <td>
<!-- 			                  <button  class="btn btn-link" onclick="edit('${wmbm03dbo.puk}','${wmbm03dbo.uu1}')" >修改</button> -->
			                  <button  class="btn btn-link" onclick="del('${wmbm03dbo.puk}','${wmbm03dbo.uu1}')" >删除</button>
<!-- 			                  <a href="/WMKZ01/R.go?puk=${wmbm03dbo.puk}&uu1=${wmbm03dbo.uu1}">修改</a>&nbsp; -->
<!-- 			                  <a href="/WMKZ01/D.go?puk=${wmbm03dbo.puk}&uu1=${wmbm03dbo.uu1}">删除</a> -->
		                  </td>		                  
		          </tr>
	                </c:forEach>
	                </form> 
	                <tr>
	                	<td colspan="12" align="center">实付总金额：【fddasfdf】</td>                
	                </tr>
            </table>
		</td>
	</tr>
  </table>
</body>
</html>
