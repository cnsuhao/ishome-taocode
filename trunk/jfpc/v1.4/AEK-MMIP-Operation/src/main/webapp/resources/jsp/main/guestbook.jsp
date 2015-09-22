<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>意见反馈-爱医康医用耗材信息服务平台</title>
<meta http-equiv="keywords" content="1" />
<meta http-equiv="description" content="1" />
<%@ include file="inc.jsp" %>
<link rel="stylesheet" type="text/css" href="/resources/skin/main/css/help.css"></link>
<script type="text/javascript" src="/resources/xheditor/xheditor-1.2.1.min.js"></script>
<script type="text/javascript" src="/resources/xheditor/xheditor_lang/zh-cn.js"></script>
<script type="text/javascript">
$(function(){
	$("#bbb").clickClear();
	//$('#elm2').xheditor({tools:'mini',skin:'nostyle',upImgUrl:"/00003010/${loginer.token}",upImgExt:"jpg,jpeg,gif,png"});
})

function fSubmit(){
	var bookFrm = $("#bookFrm").serializeJSON();
	if(bookFrm.title.isEmpty()){
		$("#title").errMsgTip();
		return false;
	}
	return true;
}
</script>
<style type="text/css">
.borderRadius{position: relative;}
.borderRadius p{text-align:left;}
</style>
</head>

<body>
<%@ include file="top.jsp" %>
<div class="screen-width">
	<h3 class="t-box-hd mTop30">意见反馈</h3>
	<p class="f14">
		如果您使用过程中遇到问题,立即需要帮助,请联系<a target="_blank" href="http://wpa.qq.com/msgrd?v=3&uin=2777618728&Site=杭州爱医康在线客服&Menu=yes">在线客服</a><br /><br />
		<span class="f18">爱医康客服热线：<b class="blue">400-052-5256</b></span>
	</p>
	<h5 class="t-box-td">请把您的建议告诉我们</h5>
	<form name="bookFrm" id="bookFrm" method="post" action="/31408021" onsubmit="return fSubmit();">
	<ul class="advice mBottom30">
		<li><span>建议标题：</span> <input type="text" name="fb1" id="fb1" size="60" value="" tips="请输入建议标题。" class="text inputText" /></li>
		<li>
			<span style="vertical-align:top;">描述：</span>
			<textarea id="bbb" name="bbb" cols="80" rows="12">
亲爱的用户：
欢迎您提供使用产品的感受和建议。
我们会参考您的建议，不断优化产品，为您提供更好的服务。</textarea>
		</li>
		<li class="p70"><input type="submit" class="submit" name="submitBtn" id="submitBtn" value="提交"/></li>
	</ul>
	</form>
</div>
 <%@ include file="bottom.jsp" %> 
</body>
</html>