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
<link rel="stylesheet" href="/resources/css/jquery.mCustomScrollbar.css">
<script src="/resources/js/jquery.js"></script>
<script language="javascript" type="text/javascript">  
    //防止客户端缓存文件，造成uploadify.js不更新，而引起的“喔唷，崩溃啦”  
    document.write("<script type='text/javascript' "  
            + "src='/resources/js/jquery.uploadify.js?" + new Date() + "'></s" + "cript>");  
</script>
<script src="/resources/js/override.uploadify.js"></script>
<script src="/resources/js/jquery.mousewheel.js"></script>
<script src="/resources/js/function.js"></script>
<script src="/resources/js/jquery.mCustomScrollbar.js"></script>
<script src="/resources/js/jq.ui.js"></script>
<script src="/resources/js/jquery.datetimepicker.js"></script>
<script src="/resources/js/validate.js"></script>
<%@ include file="/resources/jsp/ImagePath.jsp" %>
<script>
//获取url中的参数
//console.log(getUrlParam("id"));
$(function(){
    resetText();
    $(".textTips").input();
    $(".select").uiSelect();
    $(".radio").radio();
    $(".checkbox").checkbox();
    $(".Wdate").datetimepicker({timepicker:false,format:'Y-m-d',enabled:false});
	loadUpload({
        id:'#tag_0',
        multi:true,
        buttonText:'',
        buttonClass:'',
        type: '*.gif; *.jpg; *.png; *.pdf;',
        simUploadLimit:99,
        queueSizeLimit:99,
        success:uploadSuccess_1
    });
	
	//自动补全并填充
	new inputTips("#certNumber","/2001030/30",2,"#zczId", 425, function(data){
		console.log(data);
			if(data.length <= 1){
				$("#hiddenText").html("没有找到您搜索的证件，请<a href=\"javascript:resetInput();\">点击添加</a>证件。");
			}else{
				$("#hiddenText").html("");
				$("#productName").val();
				$("#productor").val();
				$("#startDate").val();
				$("#endDate").val();
				$("#zjPhoto").html("<img src='' />")
			}
	},true);
});

function uploadSuccess_1(file, data, response) {
	var json = data.toJSON(), img = json.result;
	
	$("#yui-reg-img").prepend("<li data-img=\""+img+"\"><div class=\"uploadBox uploadW100\" style=\"height:100px;\">\n\
	<div class=\"uploadImg\">\n\
		<a href=\""+cateImage(img, json.message)+"\" target=\"_blank\"><img src=\""+cateImage(img,json.message)+"\"></a>\n\
		<input type=\"hidden\" id=\"\" name=\"\" value=\""+img+"\" />\n\
	</div>\n\
	<a href='javascript:void(0)' class='imgDel' onclick=\"del(this)\"></a></div></li>");
	
}

function del(obj){
	$(obj).parent().parent().remove();
}

function onSubmit(){
	var  parm=$("#form").serializeJSON();
	ajax({url:"/311100002",data:parm,callBack:function(result){
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
<body class="whiteBg">
<form id="form" onsubmit="return onSubmit()">
    <input type="hidden" name="oldPuk" value="${UserData.puk }">
    <div class="contnet">
      <div class="formTitle">医疗器械注册证换证</div>
        <ul class="ulFrm">
        <li class="p125">
            <input type="radio" id="attr1" value="2" name="p02_zjbs" checked class="radio" /><label class="radiobox-check">变更</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <!-- <input type="radio" id="attr2" value="1" name="p02_zjbs" class="radio" /><label>延期</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; -->
            <input type="radio" id="attr3" value="3" name="p02_zjbs" class="radio" /><label>替换</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        </li>
        <li>
            <span class="label labelW120 labelRight">原注册证号：</span>
            <input type="text" class="text" name="" value="${UserData.f01_zczzwmc }" size="45" disabled="true" id="certName">
        </li>
        <li>
            <span class="label labelW120 labelRight"><span class="red">*</span> 注册证号：</span>
            <input type="text" id="certNumber" name="f01_zczzwmc" class="text textTips {text:'输入注册证号，如：国食药监械（准）字第2012第3401296号'}"  arrow="true" name="f01_zczzwmc" value="" size="54">&nbsp;&nbsp;
        	<input type="hidden" id="zczId" name="puk">
        </li>
        <li>
            <span class="label labelW120 labelRight"><span class="red">*</span> 注册证产品名称：</span>
            <input type="text" class="text" name="f03_cpzwmc" value="" size="45" id="certName">
        </li>
        <li>
            <span class="label labelW120 labelRight"><span class="red">*</span> 生产者名称：</span>
            <input type="text" class="text" name="f32_scqyzwmc" value="" size="45"  id="certName">
        </li>
        <li>
            <span class="label labelW120 labelRight"><span class="red">*</span> 有效期：</span>
            <input type="text" class="text" name="f09_yxksrq" value="" wdate="true" enabled=false size="17"> 至 <input type="text" class="text" name="f10_yxzzrq"  value="" wdate="true"  size="17">
            <input type="checkbox" id="attr1" name="f12_yqbz" value="0" class="checkbox" /><label>是否延期</label>
        </li>
        <li>
            <span class="label labelW120 labelRight" style="vertical-align:top;"><span class="red">*</span> 证件图片：</span>
           <ul class="inlineBlock regImgList" id="yui-reg-img" style="width:330px">
				<li>
					<div class="uploadBox uploadW100" style="height:100px;">
						<div class="uploadBtn">
							<div class="uploadBtnBox" style="display:block;"><div class="btnUp" ><span id="tag_0"></span></div></div>
						</div>
					</div>
				</li>
			</ul>
        </li>
        <li class="p125"><input type="submit" class="btn" value="保存"></li>
    </ul>
    </div>
</form>
</body>
<%@ include file="/resources/jsp/formJS.jsp" %>
</html>