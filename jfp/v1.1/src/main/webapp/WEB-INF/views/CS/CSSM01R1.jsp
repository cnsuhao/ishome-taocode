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
    <title>系统公告一览</title>
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
	function edit(puk,uu1){
		if(uu1==""){
			document.form2.action="/CSSM01/R.go?fb1=${fb1}&puk="+puk;
			form2.submit();
		}else{
			document.form2.action="/CSSM01/R.go?fb1=${fb1}&puk="+puk+"&uu1="+uu1;
			form2.submit();
		}		
	}
	function del(puk,uu1){
		if(uu1==""){
			document.form2.action="/CSSM01/D.go?fb1=${fb1}&puk="+puk;
			form2.submit();
		}else{
			document.form2.action="/CSSM01/D.go?fb1=${fb1}&puk="+puk+"&uu1="+uu1;
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
  <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" style="margin:0; padding:0;">
    <tr height="40" >
		<td style="background:#f8f8f8; border-bottom:1px solid #dbdbdb;"><h4 style="margin-left:10px;"><span style="font-size:14px; font-weight:bold; color:#666666;">公告管理</span></h4></td>
	</tr>
	</tr>
	<tr height="40" style="border-left:1px solid #DBDBDB; border-right:1px solid #DBDBDB;">
		<td>
			<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
				<tr>
					<td width="2%"></td>
					<td width="66%" align="left">
						<pagetag:pagtag  pageVo="${pageVO}"  url="/CSSM01A/F.go" style="4"/>
					</td>
					<td width="30%" align="right" style="padding:20px;">
					<c:if test="${fb1!='3'}">
						<input type="button" value="添加" class="btn btn-primary btn-sm"
						 onclick="parent.toTargetForm('/CSSM01/H.go?fb1=${fb1}','')">
					</c:if>	
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
                            <th>开始时间</th>  		
		               		<th>标题</th>
		               		<th>公告内容</th>	               		
<!-- 		               	<th>终了时间</th> -->
		               		<th>级别</th>
		               		<c:if test="${fb1!=3}">
		               		<th>操作</th>
		               		</c:if>
                </tr>
                
	                <form action="" method="post" id="form2" name="form2">	
		                <c:forEach var="cssm01dbo" items="${pageVO.pageData}">	
		                <tr align="center">
		                  <td title="${cssm01dbo.bbb}">${cssm01dbo.f02}</td>
		                  <td title="${cssm01dbo.bbb}">${cssm01dbo.f01}</td>
		                   <td title="${cssm01dbo.bbb}">${cssm01dbo.bbb}</td>
<!-- 		                  <td title="${cssm01dbo.bbb}">${cssm01dbo.f03}</td>		                   -->
							<td title="${cssm01dbo.bbb}">
								<c:choose>
									<c:when test="${cssm01dbo.fb1}==1">
									云端
									</c:when>
									<c:when test="${cssm01dbo.fb1}==2">
									中心
									</c:when>
									<c:otherwise>
									系统
									</c:otherwise>
								</c:choose>
							</td>
							<c:if test="${fb1!=3}">
		                  <td>
		                  	<input type="button" value="修改"  class="btn btn-link" onclick="edit('${cssm01dbo.puk}','${cssm01dbo.uu1}')">
		                  	<input type="button" value="删除"  class="btn btn-link" onclick="del('${cssm01dbo.puk}','${cssm01dbo.uu1}')">
		                  </td>	
		                  </c:if>	                  
		                </tr>
	                </c:forEach>
		                
	                </form> 
            </table>
		</td>
	</tr>
  </table>
</body>
</html>

                 