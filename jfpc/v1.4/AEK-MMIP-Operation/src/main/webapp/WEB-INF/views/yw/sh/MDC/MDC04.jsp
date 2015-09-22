<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/taglib/pagetag.tld" prefix="p" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="utf-8">
	<title>品牌审核主页</title>
	<%@ include file="/resources/jsp/yw/inc.jsp" %>
	<link href="/resources/bootstrap-3.2.0-dist/css/bootstrap-datetimepicker.min.css" rel="stylesheet">
	<script src="/resources/bootstrap-3.2.0-dist/js/bootstrap-datetimepicker.min.js"></script>
<body>
	<div class="container-fluid">
		<div class="row">
			<div class="col-sm-12" style="margin-top:20px;"> 
				<!-- 筛选 -->
				<form class="form-inline form-search" role="form" action="/31701000" method="post">
					<div class="form-group">
						<select class="form-control">
						  <option>状态</option>
						  <option>2</option>
						  <option>3</option>
						  <option>4</option>
						  <option>5</option>
						</select>
						
					</div>
					<div class="form-group">
						<label for="">变更日期：</label>
						<input type="text" class="form-control startDate"> 至 
						<input type="text" class="form-control endDate">
					</div>
					<div class="form-group">
						<button type="submit" class="btn btn-default">检索</button>
					</div>
					
				</form>
				<!-- 表格 -->
				<div class="table-responsive" style="margin-top:20px;">
					<table class="table table-bordered table-hover table-striped">
						<thead>
							<tr>
								<th>品牌全称</th>
								<th>状态</th>
								<th>变更日期</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody id="tableList">
							<c:forEach items="${page.pageListData }" var="item" varStatus="status">
								<tr>
									<td></td>
									<td></td>
									<td></td>
									<td>
										<a href="品牌-比较.html" type="button" class="btn btn-primary btn-xs">比较</a>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>	
				<!-- 分页 -->
				<p:page pageVo="${page}"/>
			</div>
		</div>
	</div>
    
</body>
</html>