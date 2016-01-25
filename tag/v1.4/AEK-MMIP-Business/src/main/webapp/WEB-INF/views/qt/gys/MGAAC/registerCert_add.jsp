<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/taglib/pagetag.tld"  prefix="p" %>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<title>添加注册证</title>
<link rel="stylesheet" type="text/css" href="/resources/css/base.css">
<link rel="stylesheet" type="text/css" href="/resources/css/supplier_cert.css">
<link rel="stylesheet" type="text/css" href="/resources/css/uploadify.css" />
<link rel="stylesheet" href="/resources/css/jquery.mCustomScrollbar.css">
<link rel="stylesheet" type="text/css" href="/resources/css/jquery.datetimepicker.css">
<script src="/resources/js/jquery.js"></script>
<script src="/resources/js/jquery.datetimepicker.js"></script>
<script src="/resources/js/jquery.mousewheel.js"></script>
<script src="/resources/js/jquery.mCustomScrollbar.js"></script>
<script src="/resources/js/jq.ui.js"></script>
<script language="javascript" type="text/javascript">  
    //防止客户端缓存文件，造成uploadify.js不更新，而引起的“喔唷，崩溃啦”  
    document.write("<script type='text/javascript' "  
            + "src='/resources/js/jquery.uploadify.js?" + new Date() + "'></s" + "cript>");  
</script>
<script src="/resources/js/override.uploadify.js"></script>
<script src="/resources/js/function.js"></script>
<script src="/resources/js/validate.js"></script>
<%@ include file="/resources/jsp/ImagePath.jsp" %>
<script>
//获取url中的参数
$(function(){
	resetText();
	$(".textTips").input();
	$(".select").uiSelect();
	loadUpload({
        id:'#tag_0',
        multi:true,
        buttonText:'',
        buttonClass:'',
        width:50,
        height:50,
        type: '*.gif; *.jpg; *.png; *.pdf;',
        simUploadLimit:99,
        queueSizeLimit:99,
        success:uploadSuccess_1
    });
	<c:if test="${UserData.puk != '' && UserData.puk != null }">foreachImages();</c:if>
	//纠错
	//自动补全并填充
	new inputTips("#certNumber","/2001030/30",2,"#zczId", 360, function(data){
			if(data.length <=0){
				setTimeout(function(){
					$(".dataError").html("没有找到，请<a href=\"javascript:resetInput(0);\">点击添加</a>");
				},200);
				
			}else{
				$(".dataError").html("");
				$("#productName").val(data.f03_cpzwmc).attr("readonly","readonly").addClass("readonly");
				$("#productor").val(data.f32_scqyzwmc).attr("readonly","readonly").addClass("readonly");
				$("#startDate").val(data.f09_yxksrq).attr("readonly","readonly").addClass("readonly");
				$("#endDate").val(data.f10_yxzzrq).attr("readonly","readonly").addClass("readonly");
			}
	},true,true,true);
});

function uploadSuccess_1(file, data, response) {
	var json = data.toJSON(), img = json.result;
	
	$("#yui-reg-img").prepend("<li data-img=\""+img+"\"><div class=\"uploadBox uploadW100\" style=\"height:100px;\">\n\
	<div class=\"uploadImg\">\n\
		<a href=\""+cateImage(img, json.message)+"\" target=\"_blank\"><img src=\""+cateImage(img, json.message)+"\"></a>\n\
		<input type=\"hidden\" id=\"\" name=\"\" value=\""+img+"\" />\n\
	</div>\n\
	<a href='javascript:void(0)' class='imgDel' onclick=\"del(this)\"></a></div></li>");
}

function del(obj){
	$(obj).parent().parent().remove();
}

function onSubmit(){
	//得到图片ids
    var url=$("#form").attr("action");
	var parm=$("#form").serializeJSON();
	parm.bbb = getImgs();
	$("#zczIds").val(parm.bbb);
	var vData = $("#form").validateForm({
		id : '#yui-error-msg'
	});
	if (!vData.allSuccess) {
		return false;
	}

	if($("#startDate").val()>$("#endDate").val()){
		 top.msgText("开始时间应该小于结束时间！",true);
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

function getImgs(){
	var imgs = [];
	$("#yui-reg-img>li").each(function(){
		imgs.push($(this).data("img"));
	});
	return imgs.join(',');
}
<c:if test="${UserData.puk != '' && UserData.puk != null }">
function foreachImages() {
	var images="${UserData.bbb}";
	if(images.length > 0){
		var imagesIds=images.split(',');
		for(var i=0;i<imagesIds.length;i++){
			$("#yui-reg-img").prepend("<li data-img=\""+imagesIds[i]+"\"><div class=\"uploadBox uploadW100\" style=\"height:100px;\">\n\
					<div class=\"uploadImg\">\n\
						<a href=\""+cateImage(imagesIds[i])+"\" target=\"_blank\"><img src=\""+cateImage(imagesIds[i])+"\"></a>\n\
						<input type=\"hidden\" id=\"\" name=\"\" value=\""+imagesIds[i]+"\" />\n\
					</div>\n\
			<a href='javascript:void(0)' class='imgDel' onclick=\"del(this)\"></a></div></li>");
		}
	}
	
}
</c:if>
</script>
</head>
<body class="whiteBg">
<form id="form" action="/311100002" method="post" onsubmit="return onSubmit()">
	<input type="hidden" name="isErrorCov" value="0" />
	<input type="hidden" name="puk" value="${UserData.puk }" id="returnId">
	<input type="hidden" name="zczIds" id="zczIds" vd-key="nonempty" data-error="请上传注册证"/>
	<div class="contnet">
	  <div class="formTitle">
	   <c:if test="${UserData.puk == '' || UserData.puk == null }">添加医疗器械注册证</c:if>
	   <c:if test="${UserData.puk != '' && UserData.puk != null }">编辑医疗器械注册证</c:if>
	   </div>
		<ul class="ulFrm">
		<li>
			<span class="label labelW120 labelRight"><span class="red">*</span> 注册证号：</span>
			<input type="text" id="certNumber" class="text textTips {text:'输入注册证号，如：国食药监械（准）字第2012第3401296号'}"  arrow="true" name="f01_zczzwmc" value="${UserData.f01_zczzwmc }" size="54"  vd-key="nonempty" data-error="请填写注册证">&nbsp;&nbsp;
			<input type="hidden" id="zczId" value="${UserData.puk }" name="puk">
			<span class="dataError"></span>
		</li>
		<li>
			<span class="label labelW120 labelRight"><span class="red">*</span> 注册证产品名称：</span>
			<input type="text" vd-key="nonempty" data-error="注册证产品名称不能为空！" class="text" name="f03_cpzwmc" value="${UserData.f03_cpzwmc }" size="49" readonly="readonly" id="productName" >
		</li>
		<li>
			<span class="label labelW120 labelRight">生产者名称：</span>
			<input type="text" class="text" name="f32_scqyzwmc" value="${UserData.f32_scqyzwmc }" size="49" readonly="readonly" id="productor">
		</li>
		<li>
			<span class="label labelW120 labelRight"><span class="red">*</span> 有效期：</span>

			<input type="text" class="text" vd-key="date" name="f09_yxksrq" value="${UserData.f09_yxksrq }" wdate="true" 
 readonly="readonly" id="startDate">
  至
   <input type="text" class="text" name="f10_yxzzrq"  vd-key="date" value="${UserData.f10_yxzzrq }" wdate="true"  readonly="readonly" id="endDate">
		</li>
		<li>
			<span class="label labelW120 labelRight" style="vertical-align:top;"><span class="red">*</span>&nbsp;&nbsp;证件图片：</span>
			<ul class="inlineBlock regImgList" id="yui-reg-img">
				<li>
					<div class="uploadBox uploadW100" style="height:100px;">
						<div class="uploadBtn">
							<div class="uploadBtnBox"><div class="btnUp" ><span id="tag_0"></span></div></div>
						</div>
					</div>
				</li>
			</ul>
			
		</li>
		<li class="p125">
			<div class="errorMsg" id="yui-error-msg" style="width: 315px;"></div>
		</li>
		<li class="p125"><input type="submit" class="btn" value="保存"></li>
	</ul>
	</div>
</form>
<%@ include file="/resources/jsp/formJS.jsp" %>
</body>
</html>