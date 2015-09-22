<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/taglib/pagetag.tld"  prefix="p" %>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
	<meta charset="utf-8">
	<title>规格列表</title>
	<%@ include file="/resources/jsp/yw/inc.jsp" %>
	<link rel="stylesheet" type="text/css" href="/resources/bootstrap-3.2.0-dist/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="/resources/bootstrap-3.2.0-dist/css/bootstrap-datetimepicker.min.css">
<script src="/resources/bootstrap-3.2.0-dist/js/bootstrap-datetimepicker.min.js"></script>
<script src="/resources/bootstrap-3.2.0-dist/js/bootstrap.min.js"></script>
<body>
    <div class="container-fluid">
        <div class="row">
            <div class="col-sm-12" style="margin-top:20px;"> 
                <!-- 筛选 -->
                <form class="form-inline form-search" role="form" action="/3920700" method="post">
                    <div class="form-group">
                        <label>状态：</label>
                        <select name="n03_shzt" id="" class="form-control">
                            <option value="2" <c:if test="${search.n03_shzt == '2' }">checked</c:if>>等待审核</option>
                            <option value="0" <c:if test="${search.n03_shzt == '0' }">checked</c:if>>审核通过</option>
                            <option value="1" <c:if test="${search.n03_shzt == '1' }">checked</c:if>>审核未通过</option>
                            <option value="3" <c:if test="${search.n03_shzt == '3' }">checked</c:if>>审核中</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="">变更日期：</label>
                        <input type="text" class="form-control startDate" name="eb1" value="${search.eb1 }"> 至 
                        <input type="text" class="form-control endDate" name="eb2" value="${search.eb2 }">
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
                                <th>注册证编号</th>
                                <th>变更时间</th>
                                <th>状态</th>
                                <th>操作</th>
                            </tr>
                        </thead>
                        <tbody id="tableList">
                            <c:forEach items="${page.pageListData}" var="dbo" varStatus="status">
                            <tr>
                                <td>${dbo.f01_zczzwmc }</td>
                                <td>${dbo.cc1 }</td>
                                <td>
                                    <c:choose>
                                        <c:when test="${dbo.n03_shzt == '0' }">审核通过</c:when>
                                        <c:when test="${dbo.n03_shzt == '1' }">审核未通过</c:when>
                                        <c:when test="${dbo.n03_shzt == '2' }">等待审核</c:when>
                                        <c:when test="${dbo.n03_shzt == '3' }">审核中</c:when>
                                    </c:choose>
                                </td>
                                <td>
                                    <a href="javascript:post('/39207001/${dbo.puk }');" type="button" class="btn btn-primary btn-xs">比较</a>
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