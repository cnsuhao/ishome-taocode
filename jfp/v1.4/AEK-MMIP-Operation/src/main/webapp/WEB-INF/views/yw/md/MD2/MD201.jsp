<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/taglib/pagetag.tld"  prefix="p" %>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
	<meta charset="utf-8">
	<title>公告列表</title>
	<%@ include file="/resources/jsp/yw/inc.jsp" %>
<body>
	<div class="container-fluid">
		<div class="row">
			<div class="col-sm-12" style="margin-top:20px;"> 
				<!-- 筛选 -->
				<form class="form-inline form-search" role="form" action="/391020001" method="post">
					<div class="form-group">
						<input type="text" class="form-control" placeholder="请输入供应商全称" name="f01_qyqc" value="${search.f01_qyqc }">

					</div>
					<div class="form-group">
						<select class="form-control" name="f11_szs" id="f11_szs">
						</select>
						
					</div>
					<div class="form-group">
						<select class="form-control" name="f12_szds" id="f12_szds">
						</select>
					</div>
					<div class="form-group">
						<select class="form-control" name="f13_szqx" id="f13_szqx">
						</select>
					</div>
					<div class="form-group">
						<button type="submit" class="btn btn-default">检索</button>
					</div>
					
				</form>
				<!-- 工具 -->
				<div class="tools-group">
					<a href="javascript:post('/391020002')" class="btn btn-primary">新增供应商</a>
				</div>
				<!-- 表格 -->
				<div class="table-responsive" style="margin-top:20px;">
					<table class="table table-bordered table-hover table-striped">
						<thead>
							<tr>
								<th>供应商全称</th>
								<th>法人姓名</th>
								<th>地址</th>
								<th>联系电话</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody id="tableList">
							<c:forEach items="${page.pageListData }" var="item" varStatus="status">
								<tr>
									<td>${item.f01_qyqc}</td>
									<td>${item.f06_frxm }</td>
									<td>${item.f11_szs }${item.f12_szds }${item.f13_szqx }</td>
									<td>${item.f16_lxdh }</td>
									<td>
										<a href="javascript:;" onclick="post('/391020002?puk=${item.puk}')" type="button" class="btn btn-primary btn-xs">编辑</a>
										<a href="javascript:post('/391020004?puk=${item.puk}');" onclick="return confirm('你确定要删除吗?');" type="button" class="btn btn-primary btn-xs">删除</a>
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
	addressInit("f11_szs","f12_szds","f13_szqx");
</script>
</html>