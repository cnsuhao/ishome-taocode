<!DOCTYPE html>
<html lang="zh-cn">
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<meta name="viewport" content="width=device-width, initial-scale=1" />
<title></title>
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

function changCode(obj){
	$("img", obj).attr("src","/00003031/68/24?t="+Math.random());
}

function saveInfo(){
	var data = $("#hform1").serializeJSON(),
	vData = $("#hform1").validateForm({id:'#yui-error-msg'});
	
	if(!vData.allSuccess){
		return false;
	}
	
	ajax("/31402052",data,function(json){
		if(json.code == 0){
			alert(json.message);
			$("#div1").hide();
			$("#div2").show();
/* 			post('/3140205?index=0');
			top.hideWindow(); */
		}else{
			alert(json.message);
		}
	})
	return false;
}

function saveInfo2(){
	var data = $("#hform2").serializeJSON(),
		vData = $("#hform2").validateForm({id:'#yui-error-msg1'});
	
	if(!vData.allSuccess){
		return false;
	}
/* 	if(data['supplierName'].isEmpty()){
		alert("aaa");
		return false;
	} */
	ajax("/31402053",data,function(json){
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
<div id="div1">
	<form id="hform1" name="hform1" onsubmit="return saveInfo();">
		<ul class="ulFrm">			
			<li>
				<span class="label labelW80 labelRight">原手机号码：</span>
				<input type="text" name="mobile" vd-key="phone" id="mobile" size="28" value="" class="text" />
			</li>
			<li>
				<span class="label labelW80 labelRight">验证码：</span>
				<input type="text" name="verCode" vd-key="vcode" id="verCode" size="4" maxlength="4" value="" class="text" />
				<!-- <input type="button" name="getCode" id="getCode" class="btn dislabel minBtn" value="获取验证码" /> -->
				<a href="javascript:void(0);" onclick="changCode(this);" title="看不清，点击换一张？"><img src="/00003031/68/24?t=130701" style="height:28px; width:68px; vertical-align:middle;" /></a>
			</li>
			<li class="p85">
				<div class="errorMsg" id="yui-error-msg" style="width:197px;">
					
				</div>
			</li>
			<li class="p85">
				<input type="submit" name="submit" id="submit" class="btn" value="验证" />
			</li>
		</ul>
	</form>
</div>
<div id="div2" style="display: none;">
	<form id="hform2" name="hform2" onsubmit="return saveInfo2();">
		<ul class="ulFrm">			
			<li>
				<span class="label labelW80 labelRight">新手机号码：</span>
				<input type="text" vd-key="phone" name="fb3" id="fb3" size="28" value="" class="text" />
			</li>
			<li class="p85">
				<div class="errorMsg" id="yui-error-msg1" style="width:197px;">
					
				</div>
			</li>
			<li class="p85">
				<input type="submit" name="submit" id="submit" class="btn" value="提交" />
			</li>
		</ul>
	</form>
</div>
</body>
<%@ include file="/resources/jsp/formJS.jsp" %>
</html>