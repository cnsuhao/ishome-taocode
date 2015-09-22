<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/taglib/pagetag.tld" prefix="p" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="utf-8">
	<title>供货商选择</title>
	<%@ include file="/resources/jsp/yw/inc.jsp" %>
<body>
	<div class="container-fluid">
		<div class="row">
			<div class="col-sm-12" style="margin-top:20px;"> 
				<!-- 筛选 -->
				<!-- <form class="form-inline form-search" role="form" action="/39107004" method="post">
					<div class="form-group">
						<input type="text" class="form-control" placeholder="经销商名称" id="f01_qyqc" name="f01_qyqc">
					</div>
					<div class="form-group">
						<button type="submit" class="btn btn-default">检索</button>
					</div>
				</form> -->
				<!-- 表格 -->
				<div class="table-responsive" style="margin-top:20px;">
					<table class="table table-bordered table-hover table-striped">
						<thead>
							<tr>
								<th><label><input type="checkbox">全选</label></th>
								<th>全称</th>
								<th>负责人</th>
								<th>联系电话</th>
								<th>公司地址</th>
							</tr>
						</thead>
						<tbody id="tableList">
						   <c:forEach items="${page.pageListData }" var="item" varStatus="status">
							<tr>
								<td><input type="checkbox"></td>
								<td>${item.f01_qyqc}</td>
								<td>${item.f06_frxm}</td>
								<td>${item.f16_lxdh}</td>
								<td>${item.f11_szs}${item.f12_szds}${f13_szqx}${f14_szxxdz}</td>
							</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>	
				<!-- 分页 -->
				<p:page pageVo="${page}"/>
			</div>
		</div>
		<div class="row tr">
			<div class="col-sm-12" >
				<!-- <button class="btn btn-primary">选择并返回</button> -->
				<a href="javascript:post('/39107004')" id="selSup" class="btn btn-primary">保存</a>
			</div>
		</div>
	</div>
</body>
<%@ include file="/resources/jsp/formJS.jsp" %>
</html>