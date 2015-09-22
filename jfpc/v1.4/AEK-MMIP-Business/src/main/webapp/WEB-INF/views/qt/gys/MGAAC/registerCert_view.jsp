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
<link rel="stylesheet" type="text/css" href="/resources/css/jquery.datetimepicker.css">
<link rel="stylesheet" type="text/css" href="/resources/css/swfupload.css" />
<link rel="stylesheet" href="/resources/css/jquery.mCustomScrollbar.css">
<style type="text/css">
.textareaRadius{width: 268px;}
</style>
<script src="/resources/js/jquery.js"></script>
<script src="/resources/js/swfupload.js"></script>
<script src="/resources/js/swfupload.queue.js"></script>
<script src="/resources/js/fileprogress.js"></script>
<script src="/resources/js/handlers.js"></script>
<script src="/resources/js/jquery.mousewheel.js"></script>
<script src="/resources/js/function.js"></script>
<script src="/resources/js/jquery.mCustomScrollbar.js"></script>
<script src="/resources/js/jq.ui.js"></script>
<script src="/resources/js/jquery.datetimepicker.js"></script>
<script src="/resources/js/validate.js"></script>
<script>
//获取url中的参数
$(function(){
	resetText();
});
function connection(){
	resetInput(0);
	$(".contentTxt").show();
}


function onSubmit(){
    var url=$("#form").attr("action");
	var  parm=$("#form").serializeJSON();
	var vData = $("#form").validateForm({
		id : '#yui-error-msg'
	});
	if (!vData.allSuccess) {
		return false;
	}
	ajax({url:url,data:parm,callBack:function(result){
		 top.msgText(result.message,true);
		 setTimeout(function(){
			 parent.closeLayer();
			 window.top.iframe.getRegisterCert();
		 },1000);
	 },load:false});
	 return false;
}
</script>
</head>
<body class="whiteBg viewRegisterCert ">
<form id="form" action="/311100002" method="post" onsubmit="return onSubmit()">
<input type="hidden" name="isErrorCov" value="0">
<input type="hidden" name="puk" value=${UserData.puk }>
	<div class="contnet">
	  <div class="formTitle" align="center">
		
	  ${UserData.f01_zczzwmc }&nbsp;&nbsp;&nbsp;&nbsp;<span class="gray titleMark">${UserData.f02_zczywmc }</span> <a class="correction font12 rFloat" href="javascript:connection()">纠错</a></div>
		<ul class="ulFrmTwo">
			<li>
				<span class="label labelW140 labelRight"> <span class="red">*</span>&nbsp;&nbsp;产品名称：</span>
				
				<input type="text" class="text WT300" name="f03_cpzwmc" value="${UserData.f03_cpzwmc }" readonly="readonly"  id="productName"  vd-key="nonempty" data-error="产品名称不能为空！" >&nbsp;&nbsp;
				
			</li>
			<li>
				<span class="label labelW140 labelRight"><span class="red">*</span>&nbsp;&nbsp;有效期：</span>
				<input type="text" class="text startDate" name="f09_yxksrq" value="${UserData.f09_yxksrq }" wdate="true" readonly="readonly" 
size="12" id="startDate" vd-key="date" > 至 <input type="text" class="text endDate" name="f10_yxzzrq" readonly="readonly" value="${UserData.f10_yxzzrq }" wdate="true"  readonly="readonly"  size="12" id="endDate" vd-key="date" >
			</li>
			<li>
				<span class="label labelW140 labelRight"><span class="red">*</span>&nbsp;&nbsp;商品英文名称：</span>
                <input type="text" class="text WT300" name="f04_cpywmc" value="${UserData.f04_cpywmc }" readonly="readonly"  id="productForEnglish"  vd-key="nonempty" data-error="产品英文名称不能为空！" >
			</li>
			<li>
				<span class="label labelW140 labelRight"><span class="red">*</span>&nbsp;&nbsp;生产者名称：</span>
				<input type="text" class="text WT300" name="f32_scqyzwmc" value="${UserData.f32_scqyzwmc }" readonly="readonly"  id="productor"  vd-key="nonempty" data-error="生产者名称不能为空！" >
			</li>
			<li>
				<span class="label labelW140 labelRight"><span class="red">*</span>&nbsp;&nbsp;生产者地址：</span>
				<input type="text" class="text WT300" name="f33_scqydz" value="${UserData.f33_scqydz }" readonly="readonly"  id="productorAddress"  vd-key="nonempty" data-error="生产者地址不能为空！" >
			</li>
			<li>
				<span class="label labelW140 labelRight"><span class="red">*</span>&nbsp;&nbsp;产品执行标准编号：</span>
				<input type="text" class="text WT300" name="f17_cpzxbzbh" value="${UserData.f17_cpzxbzbh} " readonly="readonly"  id="biaozhun"  vd-key="nonempty" data-error="产品执行标准编号不能为空！" >
			</li>
			<li>
				<span class="label labelW140 labelRight" style="vertical-align:top"><span class="red">*</span>&nbsp;&nbsp;产品适用范围：</span>
				<textarea class="textarea" rows="3"  readonly="readonly"  id="fanwei" name="f19_cpsyfw" vd-key="nonempty" data-error="产品适用范围不能为空！" >${UserData.f19_cpsyfw }</textarea>
			</li>
			<li>
				<span class="label labelW140 labelRight" style="vertical-align:top"><span class="red">*</span>&nbsp;&nbsp;产品性能结构及组成：</span>
				<textarea  name="f18_cpxnjgjzc" class="textarea" rows="3"  readonly="readonly"  id="jiegou" vd-key="nonempty" data-error="产品性能结构及组成不能为空！">${UserData.f18_cpxnjgjzc }</textarea>
			</li>
			<li>
				<span class="label labelW140 labelRight"><span class="red">*</span>&nbsp;&nbsp;注册代理机构名称：</span>
				<input type="text" class="text WT300" name="f23_zcdljgmc" value="${UserData.f23_zcdljgmc }"  readonly="readonly" id="productor" id="daili" vd-key="nonempty" data-error="注册代理机构名称不能为空！">
			</li>
			<li>
				<span class="label labelW140 labelRight"><span class="red">*</span>&nbsp;&nbsp;售后服务机构：</span>
				<input type="text" class="text WT300" name="f25_shfwjgmc" value="${UserData.f25_shfwjgmc }"  readonly="readonly" id="productor" id="jigou" vd-key="nonempty" data-error="售后服务机构名称不能为空！">
			</li>
			<li>
				<span class="label labelW140 labelRight" id="remark" style="vertical-align:top"><span class="red">*</span>&nbsp;&nbsp;备注：</span>
				<textarea  class="textarea" name="f30_bz" rows="3" readonly="readonly"  vd-key="nonempty" data-error="备注不能为空！">${UserData.f30_bz }</textarea>
			</li>
			<li  style="margin-left:145px">
			<div class="errorMsg" id="yui-error-msg" style="width: 315px;"></div>
		   </li>
			<li class="contentTxt p145">
				<input type="submit" value="保存" class="btn"/>
			</li>
		</ul>
	</div>
</form>
</body>
<%@ include file="/resources/jsp/formJS.jsp" %>
</html>