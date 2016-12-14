<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/taglib/pagetag.tld" prefix="p" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="utf-8">
	<title>品牌设置</title>
	<%@ include file="/resources/jsp/yw/inc.jsp" %>
<body>
	<div class="container-fluid">
		<div class="row">
			<div class="col-sm-12" style="margin-top:20px;">
				<!-- 筛选 -->
				<form class="form-inline form-search" role="form" action="/39113008" method="post">
					<div class="form-group">
						<input type="text" class="form-control" placeholder="请输入供应商全称" id="f01_qyqc" name="f01_qyqc">
					</div>
					<div class="form-group">
						<button type="submit" class="btn btn-default">检索</button>
					</div>
				</form>
				<div class="table-responsive" style="margin-top:20px;">
					<table class="table table-bordered table-hover table-striped">
						<thead>
							<tr>
								<th>
									<label>
								      <input type="checkbox" class="allCheck">全选
								    </label>
							    </th>
								<th>供应商全称</th>
								<th>法人姓名</th>
								<th>地址</th>
							</tr>
						</thead>
						<tbody id="tableList">
							<c:forEach items="${page.pageListData }" var="item" varStatus="status">
								<tr>
									<td>
										<label>
									      <input type="checkbox" class="checkItem">
									    </label>
									</td>
									<td>${item.f01_qyqc}</td>
									<td>${item.f06_frxm}</td>
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
	</div>
</body>
</html>