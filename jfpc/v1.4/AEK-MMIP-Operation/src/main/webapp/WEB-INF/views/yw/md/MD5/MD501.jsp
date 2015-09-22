<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/taglib/pagetag.tld"  prefix="p" %>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
	<meta charset="utf-8">
	<title>行政机构单位列表</title>
	<%@ include file="/resources/jsp/yw/inc.jsp" %>
<body>
	<div class="container-fluid">
		<div class="row">
			<div class="col-sm-12" style="margin-top:20px;"> 
				<!-- 筛选 -->
				<form class="form-inline form-search" role="form" action="/391060001" method="post">
					<div class="form-group">
						<select class="form-control" name="k02_jgxzlb" id="k02_jgxzlb">
							<option value="类别1">类别1</option>
						</select>
					</div>
					<div class="form-group">
						<select class="form-control" name="f03_szs" id="f03_szs">
						</select>
						
					</div>
					<div class="form-group">
						<select class="form-control" name="f04_szds" id="f04_szds">
						</select>
					</div>
					<div class="form-group">
						<select class="form-control" name="f05_szqx" id="f05_szqx">
						</select>
					</div>
					<div class="form-group">
						<input type="text" class="form-control" placeholder="请输入行政机构单位全称" name="f01_jgqc" value="${search.f01_jgqc }">

					</div>					
					<div class="form-group">
						<button type="submit" class="btn btn-default">检索</button>
					</div>
					
				</form>
				<!-- 工具 -->
				<div class="tools-group">
					<a href="javascript:post('/391060002')" class="btn btn-primary">新增行政机构单位</a>
				</div>
				<!-- 表格 -->
				<div class="table-responsive" style="margin-top:20px;">
					<table class="table table-bordered table-hover table-striped">
						<thead>
							<tr>
								<th>单位全称</th>
								<th>管理类别</th>
								<th>地址</th>
								<th>服务热线</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody id="tableList">
							<c:forEach items="${page.pageListData }" var="item" varStatus="status">
								<tr>
									<td>${item.f01_jgqc}</td>
									<td>${item.k02_jgxzlb }</td>
									<td>${item.f03_szs }${item.f04_szds }${item.f05_szqx }</td>
									<td>${item.f08_fwrx }</td>
									<td>
										<a href="javascript:;" onclick="post('/391060002?puk=${item.puk}')" type="button" class="btn btn-primary btn-xs">编辑</a>
										<a href="javascript:post('/391060004?puk=${item.puk}');" onclick="return confirm('你确定要删除吗?');" type="button" class="btn btn-primary btn-xs">删除</a>
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
<script src="/resources/js/address.js"></script>
<%@ include file="/resources/jsp/formJS.jsp" %>
<script type="text/javascript">
	addressInit("f03_szs","f04_szds","f05_szqx");
</script>
</html>