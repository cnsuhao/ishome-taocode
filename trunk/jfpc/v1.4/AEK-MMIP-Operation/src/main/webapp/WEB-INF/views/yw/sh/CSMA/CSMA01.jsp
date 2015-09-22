<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/taglib/pagetag.tld"  prefix="p" %>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
	<meta charset="utf-8">
	<title>品牌审核一览</title>
	<%@ include file="/resources/jsp/yw/inc.jsp" %>
	<link href="/resources/bootstrap-3.2.0-dist/css/bootstrap-datetimepicker.min.css" rel="stylesheet">
	<script src="/resources/bootstrap-3.2.0-dist/js/bootstrap-datetimepicker.min.js"></script>
<body>
	<div class="container-fluid">
		<div class="row">
			<div class="col-sm-12" style="margin-top:20px;"> 
				<!-- 筛选 -->
				<form class="form-inline form-search" role="form" action="/39211001" method="post">
					<div class="form-group">
					<label>状态：</label>
						<select class="form-control" name="n03_shzt" id="n03_shzt">
						  <option value="0">审核通过</option>
						  <option value="1">审核失败</option>
						  <option value="2">等待审核</option>
						  <option value="3">审核中</option>
						</select>
					</div>
					<div class="form-group">
						<label for="">变更日期：</label>
						<input type="text" class="form-control startDate" name="fb4" value="${search.fb4}"> 至 
						<input type="text" class="form-control endDate" name="fb5" value="${search.fb5}">
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
									<td>${item.f01_ppqc}</td>
									<td>
									<c:choose>
											<c:when test="${item.n03_shzt =='0' }">
												审核通过
											</c:when>										
											<c:when test="${item.n03_shzt =='1' }">
												审核失败
											</c:when>
											<c:when test="${item.n03_shzt =='2' }">
												等待审核
											</c:when>											
											<c:otherwise>
												审核中
											</c:otherwise>
										</c:choose>	
									</td>
									<td>${item.cc1}</td>
									<td>
									    <c:choose>
											<c:when test="${item.n03_shzt=='2' }">
												<a href="javascript:;" onclick="post('/39211002?puk=${item.puk}')" type="button" class="btn btn-primary btn-xs">比较</a>
											</c:when>
											<c:otherwise>
												<%-- <a href="javascript:;" onclick="post('/392020002?puk=${item.puk}')" type="button" class="btn btn-primary btn-xs">查看</a> --%>
											</c:otherwise>
										</c:choose>
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
<%@ include file="/resources/jsp/formJS.jsp" %>