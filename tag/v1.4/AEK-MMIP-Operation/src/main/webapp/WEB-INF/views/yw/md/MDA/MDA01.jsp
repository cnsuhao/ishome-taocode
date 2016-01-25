<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/taglib/pagetag.tld"  prefix="p" %>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
	<meta charset="utf-8">
	<title>产品线列表</title>
	<%@ include file="/resources/jsp/yw/inc.jsp" %>
<body>
    <div class="container-fluid">
        <div class="row">
            <div class="col-sm-12" style="margin-top:20px;"> 
                <!-- 筛选 -->
                <form class="form-inline" role="form" action="/3911100" method="post">
                    <div class="form-group">
                        <input type="text" class="form-control" placeholder="厂商全称" name="f11" value="${search.f11 }">
                    </div>
                    <div class="form-group">
                        <input type="text" class="form-control" placeholder="产品线中文名称" name="f01_zwmc" value="${search.f01_zwmc }">
                    </div>
                    <div class="form-group">
                        <button type="submit" class="btn btn-default">检索</button>
                    </div>
                    <div class="tools-group">
                        <a type="submit" href="javascript:post('/39111001')" class="btn btn-primary">添加</a>
                    </div>
                </form>
                <!-- 表格 -->
                <div class="table-responsive" style="margin-top:20px;">
                    <table class="table table-bordered table-hover table-striped">
                        <thead>
                            <tr>
                                <th>产品线中文全称</th>
                                <th>产品线英文名称</th>
                                <th>厂商</th>
                                <th>操作</th>
                            </tr>
                        </thead>
                        <tbody id="tableList">
                            <c:forEach items="${page.pageListData}" var="dbo" varStatus="status">
                            <tr>
                                <td>${dbo.f01_zwmc }</td>
                                <td>${dbo.f02_ywmc }</td>
                                <td>${dbo.f11 }</td>
                                <td>
                                    <a href="javascript:post('/39111001?pukid=${dbo.puk }');" type="button" class="btn btn-primary btn-xs">编辑</a>
                                    <a href="javascript:post('/39111003/${dbo.puk }')" type="button" class="btn btn-danger btn-xs">删除</a>
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