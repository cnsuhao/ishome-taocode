<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html lang="zh-cn">
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<title>修改密码</title>
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
	var data = $("#hform1").serializeJSON();
	vData = $("#hform1").validateForm({id:'#yui-error-msg'});

	if(!vData.allSuccess){
		return false;
	}
	
	ajax("/31402054",data,function(json){
		if(json.code == 0){
			alert(json.message, 1, function(){
				post('/3140205?index=0');
				top.hideWindow();
			});
		}else{
			alert(json.message);
		}
	})
	return false;
}
</script>
</head>
<body class="whiteBg">
<form id="hform1" name="hform1" onsubmit="return saveInfo(); return false;">
<ul class="ulFrm">
	<li>
		<span class="label labelW80 labelRight"><em class="red">*</em> 当前密码：</span>
		<input type="password" vd-key="oldpassword" name="oldpassword" id="oldpassword" size="30" value="" class="text" />
	</li>
	<li>
		<span class="label labelW80 labelRight"><em class="red">*</em> 新密码：</span>
		<input type="password" vd-key="password" name="k04_dlmm" id="k04_dlmm" size="30" onkeyup="strength(this.value);" onBlur="strength(this.value);" value="" class="text" />
		<div class="strength" style="margin-left:85px; width:190px;"><span class="s0"></span></div>
	</li>
	<li>
		<span class="label labelW80 labelRight"><em class="red">*</em> 确认密码：</span>
		<input type="password" vd-key="repassword" name="cpassword" id="cpassword" size="30" value="" class="text" />
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
<%@ include file="/resources/jsp/formJS.jsp" %>
</html>