<!DOCTYPE html>
<html lang="zh-cn">
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<meta name="viewport" content="width=device-width, initial-scale=1" />
<title>修改邮箱</title>
<meta name="description" content="">
<meta name="keywords" content="">
<link rel="stylesheet" type="text/css" href="/resources/css/base.css">
<link rel="stylesheet" href="/resources/css/jquery.mCustomScrollbar.css">
<script src="/resources/js/jquery.js"></script>
<script src="/resources/js/jquery.mousewheel.js"></script>
<script src="/resources/js/jquery.mCustomScrollbar.js"></script>
<script src="/resources/js/jq.ui.js"></script>
<script src="/resources/js/function.js"></script>
<script src="/resources/js/validate.js"></script>
<script>
$(function(){
	resetText();
});

function saveInfo(){
	var data = $("#hform1").serializeJSON(),
		vData = $("#hform1").validateForm({id:'#yui-error-msg'});
	
	if(!vData.allSuccess){
		return false;
	}
	ajax("/31402055",data,function(json){
		if(json.code == 0){
			alert(json.message);
 			post('/3140205?index=0');
			top.hideWindow();
		}else{
			alert(json.message);
		}
	})
	return false;
}
</script>
</head>
<body class="whiteBg">
<form id="hform1" name="hform1" onsubmit="return saveInfo();">
<ul class="ulFrm">
	<li>
		<span class="label labelW80 labelRight">电子邮箱：</span>
		<input type="text" vd-key="email" name="fb1" id="fb1" size="28" value="" class="text" />
	</li>
	<li>
		<span class="label labelW80 labelRight">验证码：</span>
		<input type="text" vd-key="vcode"  name="verCode" id="verCode" size="4" maxlength="4" value="" class="text" />
		<!-- <input type="button" name="getCode" id="getCode" class="btn dislabel minBtn" value="邮箱验证码" /> -->
		<a href="javascript:void(0);" onclick="changCode(this);" title="看不清，点击换一张？"><img src="/00003031/68/24?t=130701" style="height:28px; width:68px; vertical-align:middle;" /></a>
	</li>
	<li class="p85">
		<div class="errorMsg" id="yui-error-msg" style="width:197px;">
			
		</div>
	</li>
	<li class="p85">
		<input type="submit" name="submit" id="submit" class="btn" value="保存" />
	</li>
</ul>
</form>
</body>
</html>