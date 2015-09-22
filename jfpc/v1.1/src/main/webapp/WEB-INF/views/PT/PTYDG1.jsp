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
    <title>系统公告</title>
    
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
			document.form2.action="/PTYDG/R.go?puk="+puk;
			form2.submit();	
		}else{	
			document.form2.action="/PTYDG/R.go?puk="+puk+"&uu1="+uu1;
			form2.submit();	
		}	
	}
	function del(puk,uu1){
		if(uu1 == ""){
			document.form2.action="/PTYDG/D.go?puk="+puk;	
			form2.submit();	
		}else{
			document.form2.action="/PTYDG/D.go?puk="+puk+"&uu1="+uu1;
			form2.submit();	
		}
	}
	function search(){
			document.form1.action = "/PTYDG/S.go";
			form1.submit();
		}
	function add(){
			document.form1.action = "/PTYDG/C.go";
			form1.submit();
		}
</script>
<body>
	<table align="center" width="100%" style="margin:0; padding:0;" border="0">
    <tr height="40" style="border:1px solid #DBDBDB; background:#f8f8f8;">
		<td><h4 style="margin-left:10px;">系统公告</h4></td>
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
	<!-- 分页 -->
    <tr><td align="left">
        <pagtag:pagtag   pageVo="${pageVO}"  url="PTYDG/F.go"/>
   	</td></tr> 
   	<tr height="40" style="border-left:1px solid #DBDBDB; border-right:1px solid #DBDBDB;">
		<td>
			<table align="center" width="100%" border="0">
				<tr>
					<td width="2%"></td>
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
                            <th>开始日期</th>
							<th>截止日期</th>
							<th>发布者</th>
							<th>公告标题</th>
							<th class="table-th-do">操作</th>
							<th class="table-th-do"></th>
                      	</tr>
           		<form action="" method="post" id="form2" name="form2">
					<c:forEach var="paramPageModel" items="${pageVO.pageData}">
                 		<tr>
							<td>${paramPageModel.f02}</td>
							<td>${paramPageModel.f03}</td>
							<td>${paramPageModel.uu2}</td>
							<td>${paramPageModel.f01}</td>
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