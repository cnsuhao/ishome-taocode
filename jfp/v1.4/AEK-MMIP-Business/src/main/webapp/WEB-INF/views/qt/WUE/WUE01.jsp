<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html lang="zh-cn">
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<title></title>
<meta name="description" content="">
<meta name="keywords" content="">
<link rel="stylesheet" type="text/css" href="/resources/css/base.css">
<script src="/resources/js/jquery.js"></script>
<script src="/resources/js/jquery.mousewheel.js"></script>
<script src="/resources/js/jquery.mCustomScrollbar.js"></script>
<script src="/resources/js/jq.ui.js"></script>
<script src="/resources/js/function.js"></script>
<script src="/resources/js/validate.js"></script>
<%@ include file="/resources/jsp/yy/inc.jsp" %>
<script>
$(function(){
	resetText();
});
	
getRole(function(data){
	var html = "";
	$.each(data,function(index, item){
		html +="<input type=\"checkbox\" value=\""+item.puk+"\" class=\"checkbox\" name=\"roleName\" /><label>"+item.f01_jsmc+"</label> ";
	});
	$("#yui-role").html(html);
	$(".checkbox").checkbox();
});


function saveInfo(){
	var data = $("#myform").serializeJSON(),
		vData = $("#myform").validateForm({id:'#yui-error-msg'});
	
	if(!vData.allSuccess){
		return false;
	}
	var arr = [];
	$('.roles2 input:checked').each(function(i, item){
		arr.push($(item).val());
	});
	data.roles = arr.join(',');
	
	ajax("/31402041",data,function(json){
		top.msgText(json.message,true);
		if(json.code == 0){
			window.top.iframe.getMembers(false);
			parent.closeLayer();
		}
	})
	return false;
}
</script>
</head>
<body class="whiteBg">
<div class="formTitle">添加成员账号</div>
<form class="form-horizontal" role="form"  method="post" id="myform" onsubmit="return saveInfo();">
<ul class="ulFrm">
	<li>
		<span class="label labelW100 labelRight"><em class="red">*</em> 登录账户：</span>
		<input type="text" vd-key="username" name="k03_dlyhm" id="k03_dlyhm" size="50" value="" class="text" />
	</li>
	<li>
		<span class="label labelW100 labelRight"><em class="red">*</em> 登录密码：</span>
		<input type="password" vd-key="password" name="k04_dlmm" id="k04_dlmm" size="50" onkeyup="strength(this.value);" onBlur="strength(this.value);" value="" class="text" />
		<div class="strength" style="margin-left:105px; width:280px;"><span class="s0"></span></div>
	</li>
	<li>
		<span class="label labelW100 labelRight"><em class="red">*</em> 确认密码：</span>
		<input type="password" vd-key="repassword" name="cpassword" id="cpassword" size="50" value="" class="text" />
	</li>
	<li>
		<span class="label labelW100 labelRight"><em class="red">*</em> 真实姓名：</span>
		<input type="text" vd-key="realname" name="f01_yhxm" id="f01_yhxm" size="50" value="" class="text" />
	</li>
	<li>
		<span class="label labelW100 labelRight"><em class="red">*</em> 手机号码：</span>
		<input type="text" vd-key="phone" name="fb3" id="fb3" size="50" value="" class="text" />
	</li>
	<li>
		<span class="label labelW100 labelRight" style="vertical-align:top;">角色权限：</span>
		<div class="inlineBlock roles2" style="width:320px;" id="yui-role">
			<c:forEach var="item" items="${roles }">
				<input type="checkbox" class="checkbox" value="${item.puk }" /><label>${item.f01_jsmc }</label>
			</c:forEach>
		</div>
	</li>
	<li class="p105">
		<div class="errorMsg" id="yui-error-msg" style="width:315px;"></div>
	</li>
	<li class="p105">
		<input type="submit" id="submit" name="submit" value="添加保存" class="btn" />
	</li>
</ul>
</form>
</body>
<%@ include file="/resources/jsp/formJS.jsp" %>
</html>