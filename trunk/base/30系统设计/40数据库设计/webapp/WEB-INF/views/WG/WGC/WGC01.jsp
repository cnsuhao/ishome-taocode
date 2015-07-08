<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<meta charset="utf-8" />
<title>发布公告</title>
<%@ include file="/resources/jsp/themeCSS.jsp"%>
<%@ include file="/resources/jsp/themeJS.jsp"%>
</head>
<body>
<div class="container-fluid">
	<form class="form-horizontal" role="form" action="/31408031"
		method="post" id="form1" enctype="multipart/form-data">
		<div class="form-group">
			<label class="col-sm-1 control-label no-padding-right"
				for="form-field-1">标 &nbsp;题 </label>
			<div class="col-sm-9">
				<input type="text" id="f01_bt"
					class="form-control col-xs-13 col-sm-12" name="f01_bt"
					check-type="required" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-1 control-label no-padding-right"
				for="form-field-1">重要度 </label>
			<div class="col-sm-9">
				<select class="form-control" name="f06_zyd">
					<option value="1">非常重要</option>
					<option value="2">重要</option>
					<option value="3">一般</option>
				</select>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-1 control-label no-padding-right"
				for="form-field-1">紧急度 </label>
			<div class="col-sm-9">
				<select class="form-control" name="f05_jjd">
					<option value="1">立即处理</option>
					<option value="2">紧急</option>
					<option value="3">一般</option>
				</select>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-1 control-label no-padding-right"
				for="form-field-1">开始时间</label>
			<div class="col-sm-9">
				<input type="text" id="f02_kssh"
					class="form-control col-xs-13 col-sm-12" name="f02_kssh"
					check-type="required" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-1 control-label no-padding-right"
				for="form-field-1">结束时间</label>
			<div class="col-sm-9">
				<input type="text" id="f03_zlsj"
					class="form-control col-xs-13 col-sm-12" name="f03_zlsj"
					check-type="required" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-1 control-label no-padding-right"
				for="form-field-1">公告内容</label>
			<div class="col-sm-9">
				<textarea class="form-control" rows="5" name="bbb" check-type="required"></textarea>
			</div>
		</div>

		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
				<button type="submit" id="submit1" class="btn btn-primary col-sm-8">
					<i class="icon-ok bigger-110"></i>发布
				</button>
			</div>
		</div>
	</form>
</div>
	<script type="text/javascript"
		src="/resources/bootstrap-3.2.0-dist/js/bootstrap3-validation.js"></script>
	<script type="text/javascript">
		function demo() {
			var txt = $("#editor1").html();
			var t = $("#editor1").text();
		}
		$(function() {
			$("#form1").validation();
			$("#submit1").on('click', function(event) {
				if ($("#form1").valid(this, "信息填写不完整！") == false) {
					return false;
				}
			});
		})
	</script>
</body>
</html>