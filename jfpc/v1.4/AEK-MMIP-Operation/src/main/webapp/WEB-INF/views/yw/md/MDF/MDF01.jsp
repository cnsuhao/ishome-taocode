<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/taglib/pagetag.tld"  prefix="p" %>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
	<meta charset="utf-8">
	<title>患者列表</title>
	<%@ include file="/resources/jsp/yw/inc.jsp" %>
<body>
	<div class="container-fluid">
		<div class="row">
			<div class="col-sm-12" style="margin-top:20px;"> 
				<!-- 筛选 -->
				<form class="form-inline form-search" role="form" action="/391160001" method="post">
					<div class="form-group">
						<input type="text" class="form-control" placeholder="患者姓名" name="f01_hzxm" value="${search.f01_hzxm }">
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
								<th>患者姓名</th>
								<th>出生日期</th>
								<th>血型</th>
								<th>性别</th>
							</tr>
						</thead>
						<tbody id="tableList">
							<c:forEach items="${page.pageListData }" var="item" varStatus="status">
								<tr>
									<td>${item.f01_hzxm }</td>
									<td>${item.f03_csnyr }</td>
									<td>${item.f04_xx }</td>
									<td>${item.f05 }</td>
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