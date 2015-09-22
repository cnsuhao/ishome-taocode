<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/taglib/pagetag.tld" prefix="p" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="utf-8">
	<title>比较一览</title>
	<%@ include file="/resources/jsp/yw/inc.jsp" %>
	<link href="/resources/bootstrap-3.2.0-dist/css/bootstrap-datetimepicker.min.css" rel="stylesheet">
	<script src="/resources/bootstrap-3.2.0-dist/js/bootstrap-datetimepicker.min.js"></script>
<body>
	<div class="container-fluid">
		<div class="panel panel-default mt20">
			<!-- 导航按钮 -->
			<div class="panel-heading tc">
				<a type="button" class="btn btn-default fl-l btn-xs" href="javascript:;">
					<span class="glyphicon glyphicon-arrow-left"></span>
				</a>
				品牌-比较
			</div>
			<!-- 主体 -->
			<div class="panel-body">
				<form class="form-horizontal form-edit form-read submit-form" role="form" action="/31701002" method="post" id="myform">
					<div class="form-group table-title">
						<label class="col-sm-2 control-label label-title">&nbsp;</label>
						<label class="col-sm-4 control-label label-content">修改前内容</label>
						<label class="col-sm-4 control-label label-content">修改后内容</label>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label label-title">品牌全称：</label>
						<label class="col-sm-4 control-label label-content">2</label>
						<label class="col-sm-4 control-label label-content">2</label>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label label-title">生产厂商：</label>
						<label class="col-sm-4 control-label label-content"></label>
						<label class="col-sm-4 control-label label-content"></label>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label label-title">所在国家：</label>
						<label class="col-sm-4 control-label label-content"></label>
						<label class="col-sm-4 control-label label-content"></label>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label label-title">厂家地址：</label>
						<label class="col-sm-4 control-label label-content"></label>
						<label class="col-sm-4 control-label label-content"></label>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label label-title">厂家负责人：</label>
						<label class="col-sm-4 control-label label-content"></label>
						<label class="col-sm-4 control-label label-content"></label>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label label-title">售后服务热线：</label>
						<label class="col-sm-4 control-label label-content"></label>
						<label class="col-sm-4 control-label label-content"></label>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label label-title">付款方式：</label>
						<label class="col-sm-4 control-label label-content"></label>
						<label class="col-sm-4 control-label label-content"></label>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label label-title">品牌种类：</label>
						<label class="col-sm-4 control-label label-content"></label>
						<label class="col-sm-4 control-label label-content"></label>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label label-title">品牌知名度：</label>
						<label class="col-sm-4 control-label label-content"></label>
						<label class="col-sm-4 control-label label-content"></label>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label label-title">品牌所属环节</label>
						<label class="col-sm-4 control-label label-content"></label>
						<label class="col-sm-4 control-label label-content"></label>
					</div>
					
					<div class="form-group">
						<label class="col-sm-2 control-label label-title">品牌来源：</label>
						<label class="col-sm-4 control-label label-content"></label>
						<label class="col-sm-4 control-label label-content"></label>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label label-title">品牌生命周期：</label>
						<label class="col-sm-4 control-label label-content"></label>
						<label class="col-sm-4 control-label label-content"></label>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label label-title">品牌内外销</label>
						<label class="col-sm-4 control-label label-content"></label>
						<label class="col-sm-4 control-label label-content"></label>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label label-title">品牌所属行业：</label>
						<label class="col-sm-4 control-label label-content"></label>
						<label class="col-sm-4 control-label label-content"></label>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label label-title">品牌负责人：</label>
						<label class="col-sm-4 control-label label-content"></label>
						<label class="col-sm-4 control-label label-content"></label>
					</div>
					
					<div class="form-group btn-form-group">
						<label class="col-sm-2 control-label"></label>
						<div class="col-sm-4">
							<button type="submit" class="btn btn-primary" >通过</button>
							<button type="reset" class="btn btn-warning" >拒绝</button>
						</div>
					</div>
				</form>
			</div><!-- end-body -->
		</div>
	</div>
</body>
</html>