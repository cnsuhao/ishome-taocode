<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/taglib/pagetag.tld" prefix="p" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="utf-8">
	<title>规格选择</title>
	<%@ include file="/resources/jsp/yw/inc.jsp" %>
<body>
	<div class="container-fluid">
		<div class="row">
			<div class="col-sm-12" style="margin-top:20px;"> 
				<!-- 筛选 -->
				<form class="form-inline form-search" role="form" action="/31701000" method="post">
					<div class="form-group">
						<input type="text" class="form-control" placeholder="产品编号">
					</div>
					<div class="form-group">
						<input type="text" class="form-control" placeholder="产品型号">
					</div>
					<div class="form-group">
						<input type="text" class="form-control" placeholder="材质">
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
								<th><label><input class="allCheck" type="checkbox">全选</label></th>
								<th>产品编号(主)</th>
								<th>产品型号</th>
								<th>中文描述</th>
								<th>材质</th>
								<th>外观</th>
								<th>适用范围</th>
							</tr>
						</thead>
						<tbody id="tableList">
							<tr>
								<td><input class="checkItem" type="checkbox"></td>
								<td>产品编号(主)</td>
								<td>产品型号</td>
								<td>中文描述</td>
								<td>材质</td>
								<td>外观</td>
								<td>适用范围</td>
							</tr>
						</tbody>
					</table>
				</div>	
				<!-- 分页 -->
				<p:page pageVo="${page}"/>
			</div>
		</div>
		<div class="row tr">
			<div class="col-sm-12" >
				<button class="btn btn-primary">选择并返回</button>
			</div>
		</div>
	</div>
	<script>

	</script>
</body>
</html>