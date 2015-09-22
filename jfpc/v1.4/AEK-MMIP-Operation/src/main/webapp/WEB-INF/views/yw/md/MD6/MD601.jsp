<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/taglib/pagetag.tld" prefix="p" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="utf-8">
	<title>产品一览</title>
	<%@ include file="/resources/jsp/yw/inc.jsp" %>
<body>
	<div class="container-fluid">
		<div class="row">
			<div class="col-sm-12" style="margin-top:20px;"> 
				<!-- 筛选 -->
				<form class="form-inline form-search" role="form" action="/39107001" method="post">
					<div class="form-group">
						<input type="text" class="form-control" placeholder="生产厂家" id="f11_sccjmc" name="f11_sccjmc">
					</div>
					<div class="form-group">
						<input type="text" class="form-control" placeholder="总代理" id="f06_qgzdlgysmc" name="f06_qgzdlgysmc">
					</div>
					<div class="form-group">
						<input type="text" class="form-control" placeholder="品牌名称" id="f05_ppqc" name="f05_ppqc">
					</div>
					<div class="form-group">
						<input type="text" class="form-control" placeholder="注册证编号" id="f07_zczbh" name="f07_zczbh">
					</div>
					<div class="form-group">
						<input type="text" class="form-control" placeholder="物资名称" id="f01_cpqc" name="f01_cpqc">
					</div>
					<div class="form-group">
						<button type="submit" class="btn btn-default">检索</button>
					</div>
					<div class="tools-group">
						<a href="javascript:post('/39107002')" class="btn btn-primary">添加</a>
					</div>
				</form>
				<!-- 表格 -->
				<div class="table-responsive" style="margin-top:20px;">
					<table class="table table-bordered table-hover table-striped">
						<thead>
							<tr>
								<th>物质名称</th>
								<th>品牌名称</th>
								<th>注册证编号</th>
								<th>生产厂家</th>
								<th>总代理</th>
								<th>客服热线</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody id="tableList">
						    <c:forEach items="${page.pageListData }" var="item" varStatus="status">
							<tr>
								<th>${item.f01_cpqc}</th>
								<th>${item.f05_ppqc}</th>
								<th>${item.f07_zczbh}</th>
								<th>${item.f11_sccjmc}</th>
								<th>${item.f06_qgzdlgysmc}</th>
								<th>${item.f14_fwrx}</th>
								<td>
									<a href="javascript:;" onclick="post('/39107002?puk=${item.puk}')" type="button" class="btn btn-primary btn-xs">编辑</a>
									<a href="javascript:post('/39107006?puk=${item.puk}');" onclick="return confirm('你确定要删除吗?');" type="button" class="btn btn-danger btn-xs">删除</a>
								    <a href="javascript:;" onclick="post('/39107008')" type="button" class="btn btn-primary btn-xs">规格选择</a>
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
	<script>

	</script>
</body>
<%@ include file="/resources/jsp/formJS.jsp" %>
</html>