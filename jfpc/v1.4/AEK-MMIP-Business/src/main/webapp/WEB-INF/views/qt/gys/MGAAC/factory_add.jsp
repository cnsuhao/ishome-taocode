<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/taglib/pagetag.tld"  prefix="p" %>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
	<meta charset="utf-8">
	<title>添加厂商/经销商</title>
<link rel="stylesheet" type="text/css" href="/resources/css/base.css">
<link rel="stylesheet" type="text/css" href="/resources/css/supplier_cert.css">
<link rel="stylesheet" type="text/css" href="/resources/css/jquery.datetimepicker.css">
<link rel="stylesheet" type="text/css" href="/resources/css/uploadify.css" />
<link rel="stylesheet" href="/resources/css/jquery.mCustomScrollbar.css">
<script src="/resources/js/jquery.js"></script>
<script src="/resources/js/jquery.uploadify.js"></script>
<script src="/resources/js/override.uploadify.js"></script>
<script src="/resources/js/jquery.mousewheel.js"></script>
<script src="/resources/js/function.js"></script>
<script src="/resources/js/jquery.mCustomScrollbar.js"></script>
<script src="/resources/js/jq.ui.js"></script>
<script src="/resources/js/jquery.datetimepicker.js"></script>
<script src="/resources/js/validate.js"></script>
<%@ include file="/resources/jsp/gys/inc.jsp" %>
<script>
//获取url中的参数
//console.log(getUrlParam("id"));
$(function(){
    resetText();
    $(".textTips").input();
    $(".select").uiSelect();
    $(".radio").radio();
    $(".Wdate").datetimepicker({timepicker:false,format:'Y-m-d',enabled:false});
    if ($('#eb1flag').val() != '1') {
	    $(".hidePart").hide();
    }
    new inputTips("#facName","/2001030/13?t=" + Math.random(), 2, "#returnId", 430, null,true);
    new inputTips("#eb2","/2001030/13?t=" + Math.random(), 2, "#eb1", 430, null,true);
    loadUpload({
        id:'#tag_1',
        success:uploadSuccess_1
    });
    loadUpload({
        id:'#tag_2',
        success:uploadSuccess_2
    });
});

function onSubmit(){
    var url=$("#form").attr("action");
	var  parm=$("#form").serializeJSON();
	var vData = $("#form li:not('.hidePart')").validateForm({id : '#yui-error-msg'});
	if(!$(".hidePart").is(':hidden')){
		vData = $("#form").validateForm({id : '#yui-error-msg'});
	}
	

	if (!vData.allSuccess) {
		return false;
	}
	
	if($("#startDate1").val()>$("#endDate1").val()){
		 top.msgText("营业执照开始时间应该小于等于结束时间！",true);
		 return false;
	}
	if($("#startDate2").val()>$("#endDate2").val()){
		 top.msgText("生产许可证开始时间应该小于等于结束时间！",true);
		 return false;
	}
	 ajax({url:url,data:parm,callBack:function(result){
		 top.msgText(result.message,true);
		 setTimeout(function(){
			 parent.closeLayer();
			 window.top.iframe.getFactoryList();
		 },1000);
	 },load:false});
	 return false;
}

function changeType(type){
	if(type==0){
		$(".hidePart").hide();
	}else{
		$(".hidePart").show();
	}
}

function uploadSuccess_1(file, data, response){
	var json = data.toJSON();
	var img = json.result;
	$("#yyzzImgUrl").val(img);
	$("#zjPhoto1").attr("src",cateImage(img,json.message));
	$("#zjPhoto1Link").attr("href",cateImage(img,json.message));
}

function uploadSuccess_2(file, data, response){
	var json = data.toJSON();
	var img = json.result;
	$("#scxkzImgUrl").val(img);
	$("#zjPhoto2").attr("src",cateImage(img,json.message));
	$("#zjPhoto2Link").attr("href",cateImage(img,json.message));
}
</script>
</head>
<body class="whiteBg">
<form id="form" action="/33010002" method="post" onsubmit="return onSubmit()">
	<!-- 厂家ID -->
	<input type="hidden" name="puk" value="${sccj.puk }" id="returnId">
	<!-- 营业执照ID -->
	<input type="hidden" name="fb1" value="${yyzz.puk }">
	<!-- 总代ID -->
	<input type="hidden" name="eb1" id="eb1" value="${sccj.eb1 }">
	<!-- 生产许可证 -->
	<input type="hidden" name="fb2" value="${scxkz.puk }">
	
	
	<input type="hidden" name="eb1flag" value="${sccj.eb1 }" id="eb1flag">
    <div class="contnet">
      <div class="formTitle">添加生产厂家/总经销商</div>
        <ul class="ulFrm">
        <li>
            <span class="label labelW100 labelRight"><span class="red">*</span> 生产厂家类型：</span>
            <input type="radio" id="attr1" value="0" class="radio" name="eb1"/><label onclick="changeType(0)" <c:if test="${sccj.eb1 == '0' || sccj.eb1 == '' || sccj.eb1 == null }">class="radiobox-check"</c:if>>非进口</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <input type="radio" id="attr2" value="1" class="radio" name="eb1"/><label onclick="changeType(1)" <c:if test="${sccj.eb1 == '1' }">class="radiobox-check"</c:if>>进口</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        </li>
        <li>
            <span class="label labelW100 labelRight"><span class="red">*</span> 生产厂家：</span>
            <input type="text" vd-key="nonempty" data-error="请输入生产厂家全称！" id="facName" class="text textTips {text:'请输入生产厂家全称'}"  name="f01_qyqc" value="${sccj.f01_qyqc }" size="65" arrow="true" >
        </li>
        <li class="hidePart">
            <span class="label labelW100 labelRight"><span class="red">*</span> 全国总代理：</span>
            <input type="text" vd-key="nonempty" data-error="请输入全国总代理全称！"  class="text textTips {text:'请输入全国总代理全称'}" id="eb2" name="eb2" value="${sccj.eb2 }" size="65" arrow="true">
        </li>
        <li>
            <span class="label labelW100 labelRight" style="vertical-align:top;"><span class="red">*</span> 证件信息：</span>
            <div class="uploadBox uploadW200">
				<div class="uploadImg"><a id="zjPhoto1Link" href="<%=ImagePath %>/00003030/${yyzz.bbb }/${loginer.token}2323" target="_bank"><img src="<%=ImagePath %>/00003030/${yyzz.bbb }/${loginer.token}2323" id="zjPhoto1"></a></div>
                <div class="uploadBoxText"><span id="tag_1"></span>营业执照</div>
                <input type="hidden" name="f33_qyjyxkz" value="${yyzz.bbb }" id="yyzzImgUrl" vd-key="nonempty" data-error="请上传营业执照" />
            </div>
            <div class="uploadBox uploadW200" style="margin-left:20px;">
				<div class="uploadImg"><a id="zjPhoto2Link" href="<%=ImagePath %>/00003030/${scxkz.bbb }/${loginer.token}2323" target="_bank"><img src="<%=ImagePath %>/00003030/${scxkz.bbb }/${loginer.token}2323" id="zjPhoto2"></a></div>
                <div class="uploadBoxText"><span id="tag_2"></span>生产许可证</div>
                <input type="hidden" name="f35_qyyyzz" value="${scxkz.bbb }" id="scxkzImgUrl" vd-key="nonempty" data-error="请上传生产许可证" />
            </div>
        </li>
        <li class="p105">
            <div class="lFloat">
                <span class="label  labelRight"><span class="red">*</span> 开始日期：</span>
                <input type="text" wdate="true" vd-key="date" class="text startDate" name="f25_qyyyzzksrq" value="${yyzz.f04_yxksrq }" size="15" id="startDate1"><br>&nbsp;<br>
                <span class="label  labelRight"><span class="red">*</span> 结束日期：</span>
                <input type="text" wdate="true" vd-key="date" class="text endDate" name="f26_qyyyzzzlrq" value="${yyzz.f05_yxzzrq }" size="15" id="endDate1"> 
            </div>
            <div class="lFloat" style="margin-left:22px;">
                <span class="label  labelRight"><span class="red">*</span> 开始日期：</span>
                <input type="text" wdate="true" vd-key="date" class="text startDate" name="f21_qyjyxkzksrq" value="${scxkz.f04_yxksrq }" size="15" id="startDate2"><br>&nbsp;<br>
                <span class="label  labelRight"><span class="red">*</span> 结束日期：</span>
                <input type="text" wdate="true" vd-key="date" class="text endDate" name="f22_qyjyxkzzlrq" value="${scxkz.f05_yxzzrq }" size="15" id="endDate2"> 
            </div>
            <div class="clear"></div>
        </li>
        <li class="p105">
			<div class="errorMsg" id="yui-error-msg" style="width: 315px;"></div>
		</li>
        <li class="p105"><input type="submit" class="btn" value="保存"></li>
    </ul>
    </div>
</form>
</body>
<%@ include file="/resources/jsp/formJS.jsp" %>
</html>