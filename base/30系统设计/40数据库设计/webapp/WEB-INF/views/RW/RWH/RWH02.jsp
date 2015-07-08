<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<!DOCTYPE html>
<html lang="zh-cn">
  <head>
    <meta charset="utf-8">
    <title>字典一览</title>
 
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

						<select class="form-control" id="dfl">
							<option selected="selected" value="">选择大分类</option>
						</select>
					  </div>
					  <div class="form-group">

						<select class="form-control" id="zfl">
							<option selected="selected" value="">选择中分类</option>
						</select>
					  </div>
					  <div class="form-group">

						<select class="form-control" id="xfl">
							<option selected="selected" value="">选择小分类</option>
						</select>
					  </div>					  
					  <div class="form-group">
					  <button type="submit" class="btn btn-default">检索</button>
					  </div>
					  <div class="form-group">
					  <button type="button" class="btn btn-default" onclick="parent.showPage('/31608000');">添加字典</button>
					  </div>
					</form>
				</div>
				<div class="panel-body">
				  <div class="table-responsive">
					<table class="table table-bordered">
					  <thead>
						<tr>
						 	<th>字典名称</th>
							<th>大分类编号</th>
							<th>大分类名称</th>

							<th>中分类编号</th>
							<th>中分类名称</th>
							<th>小分类编号</th>
							<th>小分类名称</th>
							<th>排列顺序</th>							
							<th></th>
						</tr>
					  </thead>
					  <tbody>
						<c:forEach items="${dataList}" var="dbo">
							<tr>
								<td>${dbo.f01_zdmc }</td>
								<td>${dbo.k01_dflid }</td>
								<td>${dbo.n01_dflmc }</td>
								<td>${dbo.k02_zflid }</td>
								<td>${dbo.n02_zflmc }</td>
								<td>${dbo.k03_xflid }</td>
								<td>${dbo.n03_xflmc }</td>
								<td>${dbo.f02_pxwz }</td>
								<td>
									<div
										class="visible-md visible-lg hidden-sm hidden-xs action-buttons">
										<a class="blue" href="javascript:parent.showPage('/31608003?puk=${dbo.puk }')">
											<i class="glyphicon glyphicon-pencil">编辑</i>
										</a>
										&nbsp;&nbsp;
										<a class="red" href="javascript:parent.showPage('/31608005?puk=${dbo.puk }')">
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