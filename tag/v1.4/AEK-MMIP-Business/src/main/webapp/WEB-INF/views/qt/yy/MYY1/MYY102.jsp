<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" import="org.jfpc.framework.helper.*" %>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<title>医院添加供应商</title>
<meta name="description" content="">
<meta name="keywords" content="">
<link rel="stylesheet" type="text/css" href="/resources/css/base.css">
<script src="/resources/js/jquery.js"></script>
<script src="/resources/js/jquery.mousewheel.js"></script>
<script src="/resources/js/jquery.mCustomScrollbar.js"></script>
<script src="/resources/js/jq.ui.js"></script>
<script src="/resources/js/function.js"></script>
<script src="/resources/js/validate.js"></script>
<script>
$(function(){
	resetText();
	$(".textTips").input();
	new inputTips("#f01_qyqc","/2001030/11", 2, "#f01_qyqc_id", 300, function(data){
		if(data.length <= 0){
			$(".dataError").html("<a href=\"javascript:resetInput(0);\">我要添加</a>")
		}else{
			$(".dataError").html("");
			$("#f30_lxrxm, #f32_lxrdh").attr("readonly","readonly").addClass("readonly");
			$("#f30_lxrxm").val(data.f30_lxrxm);
			$("#f32_lxrdh").val(data.f32_lxrdh);
		}
	},true,true,true);
})

function saveInfo(){
	
	var data = $("#myform").serializeJSON(),
		vData = $("#myform").validateForm({id:'#yui-error-msg'});
	if(!vData.allSuccess){
		return false;
	}
/* 	if(data['supplierName'].isEmpty()){
		alert("aaa");
		return false;
	} */
	ajax("/32011003",data,function(json){
		if(json.code == 0){
			alert(json.message, 1, function(){
				
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
<div class="formTitle">添加供应商</div>
<form class="form-horizontal" role="form"  method="post" id="myform" onsubmit="return saveInfo();">
<ul class="ulFrm">
	<li>
		<span class="label labelW100 labelRight"><em class="red">*</em> 供应商名称：</span>
		<input type="text" name="f01_qyqc" vd-key="nonempty" data-error="请输入供应商名称" id="f01_qyqc" size="45" value="" arrow="true" class="text textTips {text:'输入供应商全称'}" />
		<input type="hidden" name="puk" id="f01_qyqc_id" value="" />
		&nbsp;&nbsp;<span class="dataError"></span>
	</li>
	<li>
		<span class="label labelW100 labelRight">联系人：</span>
		<input type="text" name="f30_lxrxm" id="f30_lxrxm" size="45" readonly="readonly" value="" class="text" />
	</li>
	<li>
		<span class="label labelW100 labelRight"><em class="red">*</em> 手机号码：</span>
		<input type="text" name="f32_lxrdh" vd-key="phone" id="f32_lxrdh" readonly="readonly" size="45" value="" class="text" />
	</li>
	<!-- <li>
		<span class="label labelW100 labelRight" style="vertical-align:top;">邀请内容：</span>
		<textarea class="textarea readonly" readonly="readonly" name="bbb" id="bbb" rows="4" cols="48">为便于管理我院证件，邀请你注册爱医康帐号后，将相关证件推送至杭州市第一人民医院！</textarea>
	</li> -->
	<li class="p105">
		<div class="errorMsg" id="yui-error-msg" style="display:none;width:310px;"></div>
	</li>
	<li class="p105">
		<input type="submit" id="submit" name="submit" value="添加保存" class="btn" />
	</li>
</ul>
</form>
</body>
</html>