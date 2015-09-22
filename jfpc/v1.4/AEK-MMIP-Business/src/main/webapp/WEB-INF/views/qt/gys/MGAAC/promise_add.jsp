<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/taglib/pagetag.tld"  prefix="p" %>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
	<meta charset="utf-8">
	<title>添加承诺书</title>
<link rel="stylesheet" type="text/css" href="/resources/css/base.css">
<link rel="stylesheet" type="text/css" href="/resources/css/supplier_cert.css">
<link rel="stylesheet" href="/resources/css/jquery.mCustomScrollbar.css">
<link rel="stylesheet" type="text/css" href="/resources/css/jquery.datetimepicker.css">
<link rel="stylesheet" type="text/css" href="/resources/css/uploadify.css" />
<script src="/resources/js/jquery.js"></script>
<script src="/resources/js/jquery.uploadify.js"></script>
<script src="/resources/js/override.uploadify.js"></script>
<script src="/resources/js/jquery.mousewheel.js"></script>
<script src="/resources/js/jquery.mCustomScrollbar.js"></script>
<script src="/resources/js/jq.ui.js"></script>
<script src="/resources/js/validate.js"></script>
<script src="/resources/js/jquery.datetimepicker.js"></script>
<script src="/resources/js/handlers.js"></script>
<%@ include file="/resources/jsp/gys/inc.jsp" %>
<script>
//获取url中的参数
$(function(){
    resetText();
    $(".textTips").input();
    $("body").mCustomScrollbar({theme:"minimal"});
    // 开始结束时间
   $(".Wdate").datetimepicker({timepicker:false,format:'Y-m-d'});
   loadUpload({
       id:'#tag_1',
       success:uploadSuccess_1
   });
   new inputTips("#hospitalName","/31105004",2,"#hospitalId", 425, null,true);
});

function onSubmit(){
    var url=$("#form").attr("action");
	var  parm=$("#form").serializeJSON();
	var vData = $("#form").validateForm({id : '#yui-error-msg'});

	if (!vData.allSuccess) {
		return false;
	}
	if($("#startDate").val()>$("#endDate").val()){
		 top.msgText("开始时间应该小于等于结束时间！",true);
		 return false;
	}
	ajax({url:url,data:parm,callBack:function(result){
		 top.msgText(result.message,true);
		 setTimeout(function(){
			 parent.closeLayer();
			 window.top.iframe.getPromiseList();
		 },1000);
	 },load:false});
	 return false;
}

function uploadSuccess_1(file, data, response){
	var json = data.toJSON();
	var img = json.result;
	$("#zjPhoto_url").val(img);
	$("#zjPhoto").attr("src",cateImage(img, json.message));
	$("#zjPhoto1Link").attr("href",cateImage(img, json.message));
}


</script>
</head>
<body class="whiteBg">
<form id="form" action="/31106002" method="post" onsubmit="return onSubmit()">
    <input type="hidden" name="f10" value="${UserData.f10 }" id="hospitalId">
    <input type="hidden" name="puk" value="${UserData.puk }">
   
    <div class="contnet">
      <div class="formTitle">添加承诺书</div>
        <ul class="ulFrm">
	        <li><span class="label labelW80 labelRight"><span class="red">*</span>&nbsp;&nbsp;证书名称：</span>
	        <input type="text" vd-key="nonempty" data-error="请填写证书名称！" class="text" name="k02_zjbh" value="${UserData.k02_zjbh }" size="40"> </li>
	        <li><span class="label labelW80 labelRight"><span class="red">*</span>&nbsp;&nbsp;承诺医院：</span>
	        <input type="text" id="hospitalName" class="text textTips {text:'请输入医院名称'}"  arrow="true" name="f11" value="${UserData.f11 }" size="54" vd-key="nonempty" data-error="请选择承诺医院">
	        <li>
	        	<span class="label labelW80 labelRight" style="vertical-align:top;"><span class="red">*</span>&nbsp;&nbsp;证书图片：</span>
	            <div class="uploadBox uploadW200">
					<div class="uploadImg"><a id="zjPhoto1Link" href="<%=ImagePath %>/00003030/${UserData.bbb }/${loginer.token}" target="_bank"><img src="<%=ImagePath %>/00003030/${UserData.bbb }/${loginer.token}" id="zjPhoto"></a></div>
	                <div class="uploadBoxText"><span id="tag_1"></span>售后服务承诺书</div>
	                 <input type="hidden" name="bbb" value="${UserData.bbb }" id="zjPhoto_url" vd-key="nonempty" data-error="请上传售后服务承诺书！">
	            </div>
	        </li>
	        <li class="p85">
	        	<span class="red">*</span> 开始日期：
                <input type="text" vd-key="date" wdate="true" class="text startDate" name="f04_yxksrq" value="${UserData.f04_yxksrq }" size="15" id="startDate"><br><br>
                <span class="red">*</span> 结束日期：
                <input type="text" vd-key="date" wdate="true" class="text endDate" name="f05_yxzzrq" value="${UserData.f05_yxzzrq }" size="15" id="endDate">
	        </li>
	        <li class="p85">
				<div class="errorMsg" id="yui-error-msg" style="width: 315px;"></div>
			</li>
	        <li class="p85"><input type="submit" class="btn" value="保存"></li>
	    </ul>
    </div>
</form>
</body>
<%@ include file="/resources/jsp/formJS.jsp" %>
</html>