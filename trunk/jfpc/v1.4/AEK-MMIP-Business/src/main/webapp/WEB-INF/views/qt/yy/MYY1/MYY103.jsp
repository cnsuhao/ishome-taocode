<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" import="org.jfpc.framework.helper.*" %>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<title>公告添加</title>
<meta name="description" content="">
<meta name="keywords" content="">
<link rel="stylesheet" type="text/css" href="/resources/css/base.css">
<link rel="stylesheet" type="text/css" href="/resources/css/jquery.datetimepicker.css">
<script src="/resources/js/jquery.js"></script>
<script src="/resources/js/jquery.mousewheel.js"></script>
<script src="/resources/js/jquery.mCustomScrollbar.js"></script>
<script src="/resources/js/jq.ui.js"></script>
<script src="/resources/js/jquery.datetimepicker.js"></script>
<script>
$(function(){
	resetText();
	$(".select").uiSelect();
	$(".Wdate").datetimepicker({timepicker:false,format:"Y-m-d"});
})
</script>
</head>
<body class="whiteBg">
<div class="formTitle">添加公告</div>
<ul class="ulFrm">
	<li>
		<span class="label labelW100 labelRight"><em class="red">*</em> 公告标题：</span>
		<input type="text" name="supplierName" id="supplierName" size="50" value="" class="text" />
	</li>
	<li>
		<span class="label labelW100 labelRight"><em class="red">*</em> 公告等级：</span>
		<div class="select">
			<select id="" name="">
				<option value="">请选择重要性</option>
			</select>
		</div>
		<div class="select">
			<select id="" name="">
				<option value="">请选择紧急度</option>
			</select>
		</div>
	</li>
	<li>
		<span class="label labelW100 labelRight"><em class="red">*</em> 截止日期：</span>
		<input type="text" name="endTime" id="endTime" size="50" value="" class="text" wdate="true" />
	</li>
	<li>
		<span class="label labelW100 labelRight" style="vertical-align:top;"><em class="red">*</em> 公告内容：</span>
		<textarea class="textarea" rows="8" cols="48"></textarea>
	</li>
	<li class="p105">
		<input type="submit" id="submit" name="submit" value="添加保存" class="btn" />
	</li>
</ul>
</body>
</html>