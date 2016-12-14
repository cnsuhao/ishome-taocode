<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/taglib/pagetag.tld" prefix="p" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="utf-8">
	<title>品牌一览</title>
	<%@ include file="/resources/jsp/yw/inc.jsp" %>
<body>
	<div class="container-fluid">
		<div class="row">
			<div class="col-sm-12" style="margin-top:20px;"> 
				<!-- 筛选 -->
				<form class="form-inline form-search" role="form" action="/39113001" method="post">
					<div class="form-group">
						<input type="text" class="form-control" placeholder="厂商名称" id="f14_csqm" name="f14_csqm">
					</div>
					<div class="form-group">
						<input type="text" class="form-control" placeholder="品牌名称" id="f01_ppqc" name="f01_ppqc">
					</div>
					<div class="form-group">
						<button type="submit" class="btn btn-default">检索</button>
					</div>
					
				</form>
				<!-- 工具 -->
				<div class="tools-group">
					<a href="javascript:post('/39113002')" class="btn btn-primary">新增品牌</a>
				</div>
				<!-- 表格 -->
				<div class="table-responsive" style="margin-top:20px;">
					<table class="table table-bordered table-hover table-striped">
						<thead>
							<tr>
								<th>品牌名称</th>
								<th>厂家全称</th>
								<th>厂家地址</th>
								<th>售后服务电话</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody id="tableList">
							<c:forEach items="${page.pageListData }" var="item" varStatus="status">
								<tr>
									<td>${item.f01_ppqc}</td>
									<td>${item.f14_csqm}</td>
									<td>${item.f16}</td>
									<td>${item.f11_shfwrx}</td>
									<td>
										<a href="javascript:;" onclick="post('/39113002?puk=${item.puk}')" type="button" class="btn btn-primary btn-xs">编辑</a>
										<a href="javascript:post('/39113006?puk=${item.puk}');" onclick="return confirm('你确定要删除吗?');" type="button" class="btn btn-primary btn-xs">删除</a>
										<a href="javascript:;" onclick="post('/39114001')" type="button" class="btn btn-primary btn-xs">供应商一览</a>
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