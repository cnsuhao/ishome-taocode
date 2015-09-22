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
    <title>列表</title>
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
		<style type="text/css"> 
          th{
          	 text-align: center;
          }       
    </style> 
	<script type="text/javascript">
		function add(){
			document.form1.action = "/WMJG08/H.go";
			form1.submit();
		}
		/*function back(){
			document.form1.action = "/WMJG08/H.go";
			form1.submit();
		}
		function search(){
			document.form1.action = "/WMJG08/B.go";
			form1.submit();
		}
		function check(){
			document.form1.action = "/WMJG08/F.go";
			form1.submit();
		}*/		
		function edit(puk,uu1){
			if(uu1 == ""){
				document.form2.action="/WMJG08/R.go?puk="+puk;	
				form2.submit();	
			}else{	
				document.form2.action="/WMJG08/R.go?puk="+puk+"&uu1="+uu1;		
				form2.submit();	
			}	
		}		
		function sub(){
			document.form1.action = "/WMJG08/A.go";
			form1.submit();
		}
		function del(puk,uu1){
			if(uu1 == ""){
				document.form2.action="/WMJG08/D.go?puk="+puk;	
				form2.submit();	
			}else{
				document.form2.action="/WMJG08/D.go?puk="+puk+"&uu1="+uu1;		
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
		<td><h4 style="margin-left:10px;">会员一览与删除</h4></td>
	</tr>
	<tr height="40" style="border-left:1px solid #DBDBDB; border-right:1px solid #DBDBDB;">
		<td>
			<table align="center" width="100%" border="0">
				<tr>
					<td width="2%"></td>
					<td align="left">
						
						<pagetag:pagtag  pageVo="${pageVO}"  url="/WMJG08/F.go" style="4"/>

					</td>
					<td >${message}</td>
					<td width="30%" align="right">
						<input type="button" class="btn btn-primary btn-sm" 
						onclick="parent.toTargetForm('/WMJGY1L/H.go','/WMJG08/F.go')" value="检索">
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
                           <th>授权域名</th> 
             			   <th>会员编号</th>  
             			   <th>会员名称</th>  
             			  <th>上级会员名称</th>	  
             			  <th>账户种类</th>	  
             			  <th>提成种类</th>
             			   <th>充值次数</th> 
             			   <th>银行卡数目</th>
             			   <th>提现次数</th>
             			   <th>用户信用等级</th>      
                </tr>
		                <c:forEach var="wmui01dbo" items="${pageVO.pageData}">	
		                <tr align="center">
		                      <td>${wmui01dbo.f11}</td>
		               	 	   <td>${wmui01dbo.puk}</td>		               	 	   
		               	 	   <td>${wmui01dbo.f04}</td>		               	 	   
		               	 	    <td>${wmui01dbo.k01}</td>
		               	 	     <td>${wmui01dbo.k02}</td>
		               	 	     <td>${wmui01dbo.k03}</td>
		                  	  <td>${wmui01dbo.f08}</td>
		                  	  <td>${wmui01dbo.f09}</td>
		                  	  <td>${wmui01dbo.f10}</td>
			                   <td>${wmui01dbo.fb1}</td>             
		                </tr>
	                </c:forEach>
	                </form> 
            </table>
		</td>
	</tr>
  </table>
</body>
</html>
