<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/taglib/pagetag.tld"  prefix="p" %>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
	<meta charset="utf-8">
	<title>注册证列表</title>
	<%@ include file="/resources/jsp/yw/inc.jsp" %>
<body>
    <div class="container-fluid">
        <div class="row">
            <div class="col-sm-12" style="margin-top:20px;"> 
                <!-- 筛选 -->
                <form class="form-inline form-search" role="form" action="/39118001" method="post">
                    <label>
                        排序：
                    </label>
                    <div class="form-group">
                        <div class="btn-group" data-toggle="buttons">
                            <label class="btn btn-default <c:if test="${search.eb5 == 0 }">active</c:if>">
                                <input type="radio" name="eb5" id="option1" autocomplete="off" value="0" <c:if test="${search.eb5 == 0 }">checked</c:if>>正常</label>
                            <label class="btn btn-default <c:if test="${search.eb5 == 1 }">active</c:if>">
                                <input type="radio" name="eb5" id="option2" value="1" autocomplete="off" <c:if test="${search.eb5 == 1 }">checked</c:if>>过期</label>
                            <label class="btn btn-default <c:if test="${search.eb5 == 2 }">active</c:if>">
                                <input type="radio" name="eb5" id="option3" value="2" autocomplete="off" <c:if test="${search.eb5 == 2 }">checked</c:if>>3个月内过期</label>
                        </div>
                    </div>
                    <div class="form-group">
                        <input type="text" class="form-control" name="f01_zczzwmc" placeholder="注册号" value="${search.f01_zczzwmc }">
                    </div>
                    <div class="form-group">
                        <button type="submit" class="btn btn-default">检索</button>
                    </div>
                    <div class="tools-group">
                        <a type="submit" href="javascript:post('/391180002')" class="btn btn-primary">添加注册证</a>
                    </div>
                <!-- 表格 -->
                <div class="table-responsive" style="margin-top:20px;">
                    <table class="table table-bordered table-hover table-striped">
                        <thead>
                            <tr>
                                <th>序号</th>
                                <th>注册证号</th>
                                <th>注册产品名称</th>
                                <th>生产厂家</th>
                                <th>操作</th>
                            </tr>
                        </thead>
                        <tbody id="tableList">
                            <c:forEach items="${page.pageListData}" var="dbo" varStatus="status">
                            <tr>
                                <td>${status.index + 1}</td>
                                <td>${dbo.f01_zczzwmc }</td>
                                <td>${dbo.f03_cpzwmc }</td>
                                <td>${dbo.f32_scqyzwmc }</td>
                                <td>
                                    <a href="javascript:post('/391180002?pukid=${dbo.puk }')" type="button" class="btn btn-primary btn-xs">更新</a>
                                    <a href="javascript:post('/391180006/${dbo.puk }')" type="button" class="btn btn-danger btn-xs">删除</a>
                                    <!-- <a href="javascript:;" type="button" class="btn btn-info btn-xs">维护规格</a> -->
                                </td>
                            </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>  
                <!-- 分页 -->
                <p:page pageVo="${page}"/>
            </form>
            </div>
        </div>
    </div>
</body>
<%@ include file="/resources/jsp/formJS.jsp" %>
</html>