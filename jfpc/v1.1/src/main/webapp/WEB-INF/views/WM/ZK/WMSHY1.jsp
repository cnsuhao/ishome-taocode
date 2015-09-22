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
    <title>云端黑名单</title>
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
			document.form1.action = "/WMSH02/H.go";
			form1.submit();
		}
		function back(){
			document.form1.action = "/WMSH02/H.go";
			form1.submit();
		}
		function search(){
			document.form1.action = "/WMSH02/B.go";
			form1.submit();
		}
		function check(){
			document.form1.action = "/WMSH02/F.go";
			form1.submit();
		}
		function del(puk,uu1){
			if(uu1 == ""){
				document.form2.action="/WMSH02/D.go?puk="+puk;	
				form2.submit();	
			}else{
				document.form2.action="/WMSH02/D.go?puk="+puk+"&uu1="+uu1;		
				form2.submit();	
			}
		
		}
		function edit(puk,uu1){
			if(uu1 == ""){
				document.form2.action="/WMSH02/R.go?puk="+puk;	
				form2.submit();	
			}else{	
				document.form2.action="/WMSH02/R.go?puk="+puk+"&uu1="+uu1;		
				form2.submit();	
			}	
		}		
		function sub(){
			document.form1.action = "/WMSH02/F.go";
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
  <table ="center" width="100%" style="margin:0; padding:0;" border="0">
    <tr height="40" style="border:1px solid #DBDBDB; background:#f8f8f8;">
		<td><h4 style="margin-left:10px;">云端黑名单一览</h4></td>
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
					<pagetag:pagtag  pageVo="${pageVO}"  url="/WMSH01/F.go" style="4"/>
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
<!-- 						<button  class="btn btn-default btn-sm">添加</button> -->
						<button  class="btn btn-primary btn-sm" onclick="sub()">检索</button>
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
			            <th>业务系统ID</th>	
	               		<th>来自云端</th>	 
	               		<th>创建时间</th>
	               		<th>创建者</th>   
	               		<th>更新时间</th>           				               				               		
	               		<th>更新者</th>     	            			               		
<!-- 	               		<th class="table-th-do" style="text-align: center;">操作</th> -->
                </tr>
                 <form action="" method="post" id="form2" name="form2">	
		                <c:forEach var="csss01" items="${pageVO.pageData}">	
		                <tr align="center">
		               	 <td><input type="hidden" id="puk" value="${csss01.puk}"></td>
		                  	  <td>${csspb1dbo.k01}</td>
			                  <td>${wmui01dbo.eb4}</td>
			                  <td>${wmui01dbo.cc1}</td>
			                  <td>${wmui01dbo.cc2}</td>
			                  <td>${wmui01dbo.uu1}</td>
			                  <td>${wmui01dbo.uu2}</td>
<!-- 		                  <td>${csss01.uu1}</td> -->		                           
		                </tr>
	                   </c:forEach>
	             </form> 
            </table>
		</td>
	</tr>
  </table>
</body>
</html>
