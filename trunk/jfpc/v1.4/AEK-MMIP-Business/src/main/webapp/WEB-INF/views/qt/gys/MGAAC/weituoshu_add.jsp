<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="/WEB-INF/taglib/pagetag.tld"  prefix="p" %>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
	<meta charset="utf-8">
	<title>注册证列表</title>
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
<script src="/resources/js/function.js"></script>
<script src="/resources/js/validate.js"></script>
<script src="/resources/js/jquery.datetimepicker.js"></script>
<%@ include file="/resources/jsp/gys/inc.jsp" %>
<script>
//获取url中的参数
//console.log(getUrlParam("id"));
$(function(){
    resetText();
    $(".textTips").input();
    $("body").mCustomScrollbar({theme:"minimal"});
    $(".select").uiSelect();
    loadUpload({
        id:'#tag_1',
        success:uploadSuccess_1
    });
    loadUpload({
        id:'#tag_2',
        success:uploadSuccess_2
    });
    
    $(".Wdate").datetimepicker({timepicker:false,format:'Y-m-d'});
    
    new inputTips("#hospitalName","/31105004",2,"#hospitalId", 425, null,true);
    // 开始结束时间
});

function onSubmit(){
    var url=$("#form").attr("action");
	var  parm=$("#form").serializeJSON();
	var vData = $("#form").validateForm({
		id : '#yui-error-msg'
	});
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
			 window.top.iframe.getEntrustList();
		 },1000);
	 },load:false});
	 return false;
}

function uploadSuccess_1(file, data, response){
	var json = data.toJSON();
	var img = json.result;
	$("#zjPhoto1_url").val(img);
	$("#zjPhoto1").attr("src",cateImage(img, json.message));
	$("#zjPhoto1Link").attr("href",cateImage(img, json.message));
}
function uploadSuccess_2(file, data, response){
	var json = data.toJSON();
	var img = json.result;
	$("#zjPhoto2_url").val(img);
	$("#zjPhoto2").attr("src",cateImage(img, json.message));
	$("#zjPhoto2Link").attr("href",cateImage(img, json.message));
}

</script>
</head>
<body class="whiteBg">
<form id="form" action="/31105002" method="post" onsubmit="return onSubmit()">
    <input type="hidden" name="puk" value="${UserData.puk }">
    <input type="hidden" name="f11" value="${UserData.f11 }" id="hospitalId">
    <div class="contnet">
      <div class="formTitle">添加法人委托书</div>
        <ul class="ulFrm">
        <c:if test="${fn:length(page) == 0}">
	        <li>
	            <span class="label labelW100 labelRight"></span>
	            你还没有添加业务员，只有添加了业务员才能维护委托书，赶紧去<a>添加</a>吧！
	        </li>
        </c:if>
        <li>
            <span class="label labelW100 labelRight"><span class="red">*</span>医院名称：</span>
            <input type="text" id="hospitalName" class="text textTips {text:'请输入医院名称'}" arrow="true" name="f12" value="${UserData.f12 }" size="54" vd-key="nonempty" data-error="请选择医院名称!">
        </li>
        <li>
            <span class="label labelW100 labelRight"><span class="red">*</span>员工真实姓名：</span>
            <div class="select">
	            <select name="f10" vd-key="nonempty" data-error="请选择员工真实姓名">
	               <c:forEach items="${page}" var="dbo" varStatus="status">
	                   <option value="${dbo.puk }@${dbo.f01_yhxm }" <c:if test="${dbo.puk == UserData.f09 }">selected</c:if>>${dbo.f01_yhxm }</option>	                
	               </c:forEach>
	            </select>  
            </div>
            
        </li>
        <li>
            <span class="label labelW100 labelRight"><span class="red">*</span>手机号码：</span>
            <input type="text" vd-key="phone" class="text" name="f07" value="${UserData.f07 }" size="20" >
        </li>
        <li>
            <span class="label labelW100 labelRight"><span class="red">*</span>证件信息：</span>
            <div class="uploadBox uploadW200">
                <a href="#" class="showGlass" style="display: none;"></a>
				<div class="uploadImg"><a id="zjPhoto1Link" href="<%=ImagePath %>/00003030/${UserData.bbb }/${loginer.token}" target="_bank"><img src="<%=ImagePath %>/00003030/${UserData.bbb }/${loginer.token}" id="zjPhoto1"></a></div>
                <div class="uploadBoxText"><span id="tag_1"></span>法人委托书</div>
                 <input type="hidden" name="bbb" value="${UserData.bbb }" id="zjPhoto1_url" vd-key="nonempty" data-error="请上传法人委托书">
            </div>
            <div class="uploadBox uploadW200" style="margin-left:20px;">
                <a href="#" class="showGlass" style="display: none;"></a>
				<div class="uploadImg"><a id="zjPhoto2Link" href="<%=ImagePath %>/00003030/${UserData.f13 }/${loginer.token}" target="_bank"><img src="<%=ImagePath %>/00003030/${UserData.f13 }/${loginer.token}" id="zjPhoto2"></a></div>
                <div class="uploadBoxText"><span id="tag_2"></span>员工身份证（正反面扫描件）</div>
                <input type="hidden" name="f13" value="${UserData.f13 }" id="zjPhoto2_url" vd-key="nonempty" data-error="请上传员工身份证">
            </div>
        </li>
         
        <li class="p105">
        	<span class="label  labelRight"><span class="red">*</span> 开始日期：</span>
            <input type="text" wdate="true" class="text startDate" name="f04_yxksrq" value="${UserData.f04_yxksrq }" size="15" vd-key="date" id="startDate"><br>&nbsp;<br>
            <span class="label  labelRight"><span class="red">*</span> 结束日期：</span>
            <input type="text" wdate="true" class="text endDate" name="f05_yxzzrq" value="${UserData.f05_yxzzrq }" size="15" vd-key="date" id="endDate">
           
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