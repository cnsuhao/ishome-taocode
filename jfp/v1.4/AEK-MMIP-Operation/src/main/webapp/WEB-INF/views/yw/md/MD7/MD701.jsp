<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/taglib/pagetag.tld"  prefix="p" %>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
	<meta charset="utf-8">
	<title>规格列表</title>
	<%@ include file="/resources/jsp/yw/inc.jsp" %>
<body>
    <div class="container-fluid">
        <div class="row">
            <div class="col-sm-12" style="margin-top:20px;"> 
                <!-- 筛选 -->
                <form class="form-inline form-search" role="form" action="/3910800" method="post">
                    <div class="form-group">
                        <input type="text" class="form-control" placeholder="产品主编号" name="f03_cpzbh" value="${search.f03_cpzbh }">
                    </div>
                    <div class="form-group">
                        <input type="text" class="form-control" placeholder="产品型号" name="f05_cpxh" value="${search.f05_cpxh }">
                    </div>
                    <div class="form-group">
                        <input type="text" class="form-control" placeholder="材质" name="f08_cl" value="${search.f08_cl }">
                    </div>
                    <div class="form-group">
                        <button type="submit" class="btn btn-default">检索</button>
                    </div>
                    <div class="tools-group">
                        <a type="submit" href="javascript:post('/39108001?k01_cpid=${search.k01_cpid }');" class="btn btn-primary">添加</a>
                    </div>
                </form>
                <!-- 表格 -->
                <div class="table-responsive" style="margin-top:20px;">
                    <table class="table table-bordered table-hover table-striped">
                        <thead>
                            <tr>
                                <th>产品编号(主)</th>
                                <th>产品型号</th>
                                <th>中文描述</th>
                                <th>材质</th>
                                <th>外观</th>
                                <th>适用范围</th>
                                <th>操作</th>
                            </tr>
                        </thead>
                        <tbody id="tableList">
                            <c:forEach items="${page.pageListData}" var="dbo" varStatus="status">
                            <tr>
                                <td>${dbo.f03_cpzbh }</td>
                                <td>${dbo.f05_cpxh }</td>
                                <td>${dbo.f06_zwggms }</td>
                                <td>${dbo.f08_cl }</td>
                                <td>${dbo.f09_ys }</td>
                                <td>${dbo.f22_syfw }</td>
                                <td>
                                    <a href="javascript:post('/39108001?pukid=${dbo.puk }');" type="button" class="btn btn-primary btn-xs">编辑</a>
                                    <a href="javascript:post('/39108003/${dbo.puk }');" type="button" class="btn btn-danger btn-xs">删除</a>
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