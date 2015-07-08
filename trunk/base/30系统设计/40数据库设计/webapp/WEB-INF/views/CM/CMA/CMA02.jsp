<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<meta charset="utf-8" />
<title>企业名称审核</title>
<%@ include file="/resources/jsp/themeCSS.jsp"%>
<%@ include file="/resources/jsp/themeJS.jsp"%>
</head>
<body>
<div class="container-fluid">
	<form class="form-horizontal" role="form" action="/31408031"
		method="post" id="form1" enctype="multipart/form-data">
		<div class="form-group">
			<label class="col-sm-1 control-label no-padding-right"
				for="form-field-1">全 &nbsp;称 </label>
			<div class="col-sm-9">
				<input type="text" id="f01_bt"
					class="form-control col-xs-13 col-sm-12" disabled="disabled" name="f01_bt"
					check-type="required" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-1 control-label no-padding-right"
				for="form-field-1">地址</label>
			<div class="col-sm-9">
				<input type="text" id="f02_kssh"
					class="form-control col-xs-13 col-sm-12" name="f02_kssh" disabled="disabled"
					check-type="required" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-1 control-label no-padding-right"
				for="form-field-1">结束时间</label>
			<div class="col-sm-9">
				<input type="text" id="f03_zlsj"
					class="form-control col-xs-13 col-sm-12" name="f03_zlsj" value="${dbo.f03_zlsj }" disabled="disabled"
					check-type="required" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-1 control-label no-padding-right"
				for="form-field-1">公告内容</label>
			<div class="col-sm-9">
				<textarea class="form-control" rows="5" name="bbb" check-type="required" disabled="disabled">${dbo.bbb }</textarea>
			</div>
		</div>

		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
				<button type="button"  class="btn btn-primary col-sm-8" onclick="parent.showPage('/31408032')">
					<i class="icon-ok bigger-110"></i>返回
				</button>
			</div>
		</div>
	</form>
</div>
<script type="text/javascript">
	var zyd =${dbo.f06_zyd};
	var jjd =${dbo.f05_jjd};
	$(function(){
		$("#f06_zyd").val(zyd);
		$("#f05_jjd").val(jjd);
	})
</script>
</body>
</html>