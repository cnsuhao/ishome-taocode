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
	function xiugai(puk){
			document.form2.action="/WMBM10/R.go?puk="+puk;
			form2.submit();
		}
	</script>
  </head>
  <body>
  <table align="center" width="100%" style="margin:0; padding:0;" border="0">
    <tr height="40" style="border:1px solid #DBDBDB; background:#f8f8f8;">
		<td><h4 style="margin-left:10px;">充值记录</h4></td>
	</tr>

	<tr height="50" style="border-left:1px solid #DBDBDB; border-right:1px solid #DBDBDB;">
		<td>
			<table align="center" width="100%" style="margin:0; padding:0;" border="0">
				<tr>
					<td width="2%"></td>
					<td width="66%" align="left">
<%--
					<pagtag:pagtag  pageVo="${pageVO}"  url="F.go" />
--%>
						<!-- <div class="pagination" style="margin:0;">
							<ul>
								<li><a href="#">上一页</a></li>
								<li class="active"><a href="#">1</a></li>
								<li><a href="#">2</a></li>
								<li><a href="#">3</a></li>
								<li><a href="#">4</a></li>
								<li><a href="#">5</a></li>
								<li><a href="#">下一页</a></li>
                            </ul>
						</div> -->
					</td>
					<td width="30%" align="right">
					<form action="" method="post" id="form1" name="form1">
						<button type="submit" class="btn"  onclick="tianjia()">添加</button>
						<button type="submit" class="btn btn-primary">检索</button>
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
                     <th>银行账户</th>
                              <th>授权编号</th>
                              <th>通道名称</th>
							  <th>充值金额</th>
							  <th>实得金额</th>
                              <th class="table-th-do">操作</th>
                </tr>
				  <form id="form2" name="form2" method="post" >


				 <c:forEach var="wmbm03dbo" items="${pageVO.pageData}">
                            <tr>
                              <td>${wmbm03dbo.f01}</td>
                              <td>${wmbm03dbo.f02}</td>
                              <td>${wmbm03dbo.fb1}</td>
							  <td>${wmbm03dbo.f07}</td>
							  <td>${wmbm03dbo.f18}</td>
                              <td><a>
							   <button type="button" id="edit" class="btn btn-default btn-lg" onclick="xiugai('${wmbm03dbo.puk}')">浏览</button>
							   </a></td>
                            </tr>
                            </c:forEach>

						</form>	
            </table>
		</td>
	</tr>
  </table>
</body>
</html>
