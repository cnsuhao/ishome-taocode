<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/taglib/pagetag.tld"  prefix="p" %>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
	<meta charset="utf-8">
	<title>注册证列表</title>
<link rel="stylesheet" type="text/css" href="/resources/css/base.css">
<link rel="stylesheet" type="text/css" href="/resources/css/supplier_cert.css">
<link rel="stylesheet" href="/resources/css/jquery.mCustomScrollbar.css">
<script src="/resources/js/jquery.js"></script>
<script src="/resources/js/jquery.mousewheel.js"></script>
<script src="/resources/js/jquery.mCustomScrollbar.js"></script>
<script src="/resources/js/jq.ui.js"></script>
<script src="/resources/js/validate.js"></script>
<script src="/resources/js/function.js"></script>
<script>
//获取url中的参数
//console.log(getUrlParam("id"));
var isNew=false;
$(function(){
    resetText();
    $(".textTips").input();
    $(".select").uiSelect();
    new inputTips("#hospitalName","/2001030/12",2,"#hospitalId",275, function(data){
    	if(data.length <= 0){
    		setTimeout(function(){
    			isNew=true;
				$(".dataError").html("没有找到，请<a href=\"javascript:connection();\">点击添加</a>");
				$("#contacts").val("");
				$("#phone").val("");
			},200);
		}else{
			isNew=false;
			$(".dataError").html("");
			$("#contacts").val(data.f30_lxrxm).attr("readonly","readonly").addClass("readonly");
			$("#phone").val(data.f32_lxrdh).attr("readonly","readonly").addClass("readonly");
			$("#content").attr("readonly","readonly").addClass("readonly");
		}
    },true,true,false);
    $("body").mCustomScrollbar({theme:"minimal"});
});

function connection(){
	isNew=true;
	resetInput(0);
}

function onSubmit(){
	var url=$("#form").attr("action");
	var  parm=$("#form").serializeJSON();
	
	var vData = "";
	if(isNew){
		vData = $("#form").validateForm({id : '#yui-error-msg'});
		if (!vData.allSuccess) {
			return false;
		}
	}else{
		vData = $("#hospitalNameValidate").validateForm({id : '#yui-error-msg'});
		if (!vData.allSuccess) {
			return false;
		}
	}
	
	ajax({url:url,data:parm,callBack:function(result){
		 top.msgText(result.message,true);
		 setTimeout(function(){
			 parent.closeLayer();
			 window.top.iframe.getCustomerList();
		 },1000);
	 },load:false});
	 return false;
}
</script>
</head>
<body class="whiteBg">
<form id="form" action="/31022003" method="post" onsubmit="return onSubmit()">
    <input type="hidden" name="puk" id="hospitalId" value="">
    <div class="contnet">
      <div class="formTitle">添加医院</div>
        <ul class="ulFrm">
        <li id="hospitalNameValidate">
            <span class="label labelW100 labelRight"><span class="red">*</span> 医院名称：</span>
            <input type="text" id="hospitalName" name="f01_qyqc" class="text textTips {text:'输入医院名称'}" vd-key="nonempty" data-error="请输入医院名称！"  arrow="true" value="" size="40">&nbsp;&nbsp;
        	<span class="dataError"></span>
        </li>
        <li>
            <span class="label labelW100 labelRight"><span class="red">*</span> 联系人：</span>
            <input type="text" class="text" name="f30_lxrxm" value="" size="40" id="contacts" readonly="readonly"  vd-key="nonempty" data-error="请输入联系ren">
        </li>
        <!-- <li>
            <span class="label labelW100 labelRight">所属科室：</span>
            <div class="select">
            <select name="" id="">
                <option value="">请选择科室</option>
                <option value="">科室一</option>
                <option>科室二</option>
            </select>
            </div>
        </li> -->
        <li>
            <span class="label labelW100 labelRight"><span class="red">*</span> 手机号码：</span>
            <input type="text" vd-key="phone" class="text" name="f32_lxrdh" value="" size="40" id="phone" readonly="readonly">
        </li>
        <li>
            <span class="label labelW100 labelRight" style="vertical-align:top;"><span class="red">*</span> 邀请内容：</span>
            <textarea class="textarea"  readonly="readonly"  vd-key="nonempty" cols="43" rows="3" name="bbb" id="content"  vd-key="nonempty" data-error="请输入邀请内容！"></textarea>
        </li>
         <li class="p105">
			<div class="errorMsg" id="yui-error-msg" style="width: 315px;"></div>
		</li>
        <li class="p105"><input type="submit" class="btn" value="添加"></li>
    </ul>
    </div>
</form>
</body>
<%@ include file="/resources/jsp/formJS.jsp" %>
</html>