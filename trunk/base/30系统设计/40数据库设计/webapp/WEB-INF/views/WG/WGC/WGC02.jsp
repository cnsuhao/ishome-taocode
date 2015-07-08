<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<!DOCTYPE html>
<html lang="zh-cn">
  <head>
    <meta charset="utf-8">
    <title>Model</title>
 
  <%@ include file="/resources/jsp/themeCSS.jsp" %> 
	<link rel="stylesheet" href="/resources/css/list.css">
  </head> 
  <body>
  		<div class="container-fluid">
		  <div class="row">
			<div class="col-sm-12">
				<div class="panel_div">
					<form class="form-inline" role="form" action="/31408032" method="get">
					  <div class="form-group">
						<label for="inputPassword2" class="sr-only">请选择日期</label>
						<input type="text" class="form-control" id="inputPassword2" placeholder="rili">
					  </div>
					  <div class="form-group">
						<label for="inputPassword2" class="sr-only">发起人</label>
						<input type="text" class="form-control" id="cc2" name="cc2" placeholder="发起人">
					  </div>
					  <button type="submit" class="btn btn-default">检索</button>
					  <button type="button" class="btn btn-default" onclick="parent.showPage('/31408030');">发布公告</button>
					  
					</form>
				</div>
				<div class="panel-body">
				  <div class="table-responsive">
					<table class="table table-bordered">
					  <thead>
						<tr>
						 	<th>标题</th>
							<th>重要度</th>
							<th>紧急度</th>

							<th><i class="icon-time bigger-110"></i> 开始时间</th>
							<th>结束时间</th>

							<th></th>
						</tr>
					  </thead>
					  <tbody>
						<c:forEach items="${dataList}" var="dbo">
							<tr>
								<!-- 								<td class="center"><label> <input type="checkbox"
										class="ace" /> <span class="lbl"></span>
								</label></td> -->

								<td>${dbo.f01_bt}</td>
								<td>${dbo.f06_zyd}</td>
								<td>${dbo.f05_jjd }</td>
								<td>${dbo.f02_kssh }</td>

								<td>${dbo.f03_zlsj }</td>

								<td>
									<div
										class="visible-md visible-lg hidden-sm hidden-xs action-buttons">
										<a class="blue" href="javascript:parent.showPage('/31408034?puk=${dbo.puk }')">
											<i class="glyphicon glyphicon-zoom-in">查看</i>
										</a>
										&nbsp;&nbsp;
										<a class="red" href="javascript:parent.showPage('/31408033?puk=${dbo.puk }')">
											<i class="glyphicon glyphicon-remove">删除</i>
										</a>
									</div>

								</td>
							</tr>
						</c:forEach>
					  </tbody>
					</table>
				  </div>
				</div>
				<ul class="pagination" style="margin-left:20px;">
					  <li class="disabled"><a href="#">&laquo;</a></li>
					  <li class="active"><a href="#">1 <span class="sr-only">(current)</span></a></li>
					  <li><a href="#">2 <span class="sr-only">(current)</span></a></li>
					  <li><a href="#">3 <span class="sr-only">(current)</span></a></li>
					  <li><a href="#">4 <span class="sr-only">(current)</span></a></li>
					  <li><a href="#">5 <span class="sr-only">(current)</span></a></li>
					</ul>

			  
			</div>
			
		  </div>
		</div>
		  
	
  <%@ include file="/resources/jsp/themeJS.jsp" %>
  <script type="text/javascript">
  	function publish(){
  		window.location.href="/31408030";
  	}
  </script>
  </body>
</html>