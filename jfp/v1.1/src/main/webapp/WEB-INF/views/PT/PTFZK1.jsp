<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/lib/pagtag.tld" prefix="pagtag" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>系统画面菜单</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link href="/resources/css/pt/bootstrap.min.css" rel="stylesheet" media="screen">
<!-- 	<link href="/resources/css/pt/global.css" rel="stylesheet" media="screen"> -->
	<script type="text/javascript" src="/resources/js/pt/jquery.js"></script>
	<script src="/resources/js/pt/bootstrap.min.js"></script>
	<script type="text/javascript">
	function xiugai(puk,uu1) {
		if(uu1 == ""){
			document.form2.action="/PTFZ1/R.go?puk="+puk;
			form2.submit();	
		}else{	
			document.form2.action="/PTFZ1/R.go?puk="+puk+"&uu1="+uu1;
			form2.submit();	
		}	
	}
	function del(puk,uu1){
		if(uu1 == ""){
			document.form2.action="/PTFZ1/D.go?puk="+puk;	
			form2.submit();	
		}else{
			document.form2.action="/PTFZ1/D.go?puk="+puk+"&uu1="+uu1;
			form2.submit();	
		}
	}
	function search(){
			document.form1.action = "/PTFZ1/S.go";
			form1.submit();
		}
	function add(){
			document.form1.action = "/PTFZ1/C.go";
			form1.submit();
		}
</script>
<body>
	<table align="center" width="100%" style="margin:0; padding:0;" border="0">
    <tr height="40" style="border:1px solid #DBDBDB; background:#f8f8f8;">
		<td><h4 style="margin-left:10px;">客户系统管理</h4></td>
	</tr>
	<tr height="50" style="border-left:1px solid #DBDBDB; border-right:1px solid #DBDBDB;">
		<td><table style="width:100%; color:red; font-size:12px; margin:0; padding-top:10px;">
		  <tr>
		    <td align="right">提示信息</td>
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
					<!-- 
					<td align="left">
					 -->
        				<pagtag:pagtag   pageVo="${pageVO}"  url="/PTFZ1/F.go" style="2"/>
   					<!-- 
   					</td>
   					 -->
					<td width="30%" align="right">
						<form action="" method="post" id="form1" name="form1">
							<button type="submit" class="btn btn-default btn-sm" onclick="add()">添加</button>
							<button type="submit" class="btn btn-primary btn-sm" onclick="search()">检索</button>
						</form>
					</td>
					<td width="2%"></td>
				</tr>
			</table>
		</td>
	</tr>
	<tr style="border-bottom:1px solid #DBDBDB; border-left:1px solid #DBDBDB; border-right:1px solid #DBDBDB;">
    <td><table class="table table-hover">
                    	<tr>
                            <th>绑定域名</th>
		               		<th>系统分类</th>
		               		<th>系统版本</th>
		               		<th>系统密钥</th>
		 	              		<th>联系电话</th>
		               		<th>授权终了日期</th>
							<th class="table-th-do">操作</th>
							<th class="table-th-do"></th>
                      	</tr>
           		<form action="" method="post" id="form2" name="form2">
					<c:forEach var="paramPageModel" items="${pageVO.pageData}">
                 		<tr>
                 			<td>${paramPageModel.k02}</td>
		                  	<td>${paramPageModel.f09}</td>
		                  	<td>${paramPageModel.f10}</td>
		                  	<td>${paramPageModel.f11}</td>
		                  	<td>${paramPageModel.f08}</td>
		                  	<td>${paramPageModel.f12}</td>
							<td><a>
							   <button type="button" id="edit" class="btn btn-default btn-sm main-table-btn" onclick="xiugai('${paramPageModel.puk}','${paramPageModel.uu1}')">编辑</button>
							</a></td>
							<td><a>
							   <button type="button" id="edit" class="btn btn-default btn-sm main-table-btn" onclick="del('${paramPageModel.puk}','${paramPageModel.uu1}')">删除</button>
							</a></td>
							<td><input type="hidden" id="puk" value="${paramPageModel.puk}"></td>
							<td><input type="hidden" id="uu1" value="${paramPageModel.uu1}"></td>
						</tr>
                   </c:forEach>
            	</form>
     		</table></td>
		</tr>
  </table>
</body>
</html>