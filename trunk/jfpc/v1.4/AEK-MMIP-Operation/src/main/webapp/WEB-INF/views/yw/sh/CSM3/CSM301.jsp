<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/taglib/pagetag.tld"  prefix="p" %>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
	<meta charset="utf-8">
	<title>行政机构审核列表</title>
	<%@ include file="/resources/jsp/yw/inc.jsp" %>
	<link href="/resources/bootstrap-3.2.0-dist/css/bootstrap-datetimepicker.min.css" rel="stylesheet">
	<script src="/resources/bootstrap-3.2.0-dist/js/bootstrap-datetimepicker.min.js"></script>
<body>
	<script type="text/javascript">
		var shzt = ${search.n03_shzt};
		$(function(){
			$("#n03_shzt").val(shzt);
		})
	</script>
	<div class="container-fluid">
		<div class="row">
			<div class="col-sm-12" style="margin-top:20px;"> 
				<!-- 筛选 -->
				<form class="form-inline form-search" role="form" action="/392040001" method="post">
					<div class="form-group">
						<select class="form-control" name="n03_shzt" id="n03_shzt">
						  <option value="2">未审核</option>
						  <option value="0">审核通过</option>
						  <option value="1">审核失败</option>
						</select>
						
					</div>
					<div class="form-group">
						<input type="text" class="form-control" placeholder="请输入行政机构全称" name="f01_jgqc" value="${search.f01_jgqc }">
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
								<th>行政机构全称</th>
								<th>类别</th>
								<th>状态</th>
								<th>申请企业名称</th>
								<th>申请日期</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody id="tableList">
							<c:forEach items="${page.pageListData }" var="item" varStatus="status">
								<tr>
									<td>${item.f01_jgqc }</td>
									<td>
										<c:choose>
											<c:when test="${item.p02_sjlb=='1' }">
												新增
											</c:when>
											<c:otherwise>
												变更
											</c:otherwise>
										</c:choose>
									</td>
									<td>
										<c:choose>
											<c:when test="${item.n03_shzt =='0' }">
												审核通过
											</c:when>										
											<c:when test="${item.n03_shzt =='1' }">
												审核不通过
											</c:when>
											<c:when test="${item.n03_shzt =='3' }">
												审核中
											</c:when>											
											<c:otherwise>
												等待审核
											</c:otherwise>
										</c:choose>									
									</td>
									<td>${item.n05_qymc }</td>
									<td>${item.cc1}</td>
									<td>
										<c:choose>
											<c:when test="${item.n03_shzt=='2' }">
												<a href="javascript:;" onclick="post('/392040002?puk=${item.puk}')" type="button" class="btn btn-primary btn-xs">审核</a>
											</c:when>
											<c:otherwise>
												<%-- <a href="javascript:;" onclick="post('/392040002?puk=${item.puk}')" type="button" class="btn btn-primary btn-xs">查看</a> --%>
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
<%@ include file="/resources/jsp/formJS.jsp" %>

</html>