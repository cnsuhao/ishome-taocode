<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>账号登录 - 爱医康医用耗材信息服务平台</title>
<meta http-equiv="keywords" content="爱医康,供应商登录,医院登录" />
<meta http-equiv="description" content="爱医康平台账户登录页面。" />
<%@ include file="/resources/jsp/main/inc.jsp" %> 
<link rel="stylesheet" type="text/css" href="/resources/skin/main/css/login.css">
<script type="text/javascript">
$(function(){
	$(".login-tab>li").each(function(){
		$(this).click(function(){
			$(this).addClass("current").siblings().removeClass("current");
			if($(this).index() == 0) $("#banner").attr("class","login-banner"); else $("#banner").attr("class","login-banner2");
		})
	});
	$("#loginFrm").formTips();
})
function loginSubmit(){
	var loginData = $("#loginFrm").serializeJSON();
	if(loginData['userName'].isEmpty() || !/^[a-zA-Z0-9]{3,12}/g.test(loginData['userName'])){
		$("#userName").errMsg();
		return false;
	}
	if(loginData['passWord'].isEmpty() || loginData['passWord'].length < 6){
		$("#passWord").errMsg();
		return false;
	}
	loginFrm.submit();
/*
	loginData['passWord'] = $.md5(loginData['passWord']);
	$("#loginBtn").attr("disabled",true);
	ajax("/ajax/ajax_login.php?t="+Math.random(),loginData,function(json){
		if(json.code == 0){
			location.href = json.url;
		}else{
			$("#loginBtn").removeAttr('disabled');
			if(json.code == 1)
				$("#userName").errMsg(json.message);
			else if(json.code == 2)
				$("#passWord").errMsg(json.message);
			else
				$("#errorMsg").errMsg(json.message, -1);
		}
	});
	return false;
*/
}
function changCode(){
	$('#vcode').attr('src','?t='+Math.random());
} 
</script>
</head>

<body>
<%@ include file="/resources/jsp/main/top.jsp" %> 
<div class="login-main">
	<div class="screen-width pos-relative">
		<ul class="pos-absolute login-tab">
			<li class="current">供应商</li>
			<li>医院</li>
		</ul>
		<div class="login pos-absolute">
			<div class="pos-relative">
				<form id="loginFrm" name="loginFrm" action="/00000000" method="post" >
				<div class="pos-relative" id="errorMsg"></div>
				<h2>登录爱医康</h2>
				<div class="mTop18">
					<label class="borderLabel" type="icon">
						<input type="text" name="userName" id="userName" class="user" value="admin" placeholder="Email/手机/会员帐号" tips="请输入6-12位的登录用户名，以字母开头，包含数字。" />
					</label>
				</div>
				<div class="mTop18">
					<label class="borderLabel" type="icon">
						<input type="password" name="passWord" id="passWord" class="pass" value="123456" placeholder="输入登录密码" tips="请输入6位以上的登录密码。" />
					</label>
				</div>
				<div class="mTop18">
					<label class="borderLabel" type="icon">
						<input type="text" name="code" id="code" size="4" class="code" placeholder="输入验证码" tips="请输入4位验证码。" />
					</label>
					<!-- <img valign="middle" src="/?t=1409547111" onclick="changCode();" id="vcode"> -->
				</div>
				<div class="mTop18 pos-relative"><input type="button" name="loginBtn" id="loginBtn" onclick="loginSubmit();" value="登录" class="login-btn" /></div>
				<div class="mTop18" align="right"><a href="/resources/jsp/main/zc.jsp"><u>免费注册</u></a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="/resources/jsp/main/mm.jsp">忘记登录密码？</a></div>

  <!-- 登录地址 -->
  <input type="hidden" name="loginUrl"  id="loginUrl" value="/3140000" />         
  <!-- 回调地址 -->
  <input type="hidden" name="callBackUrl"  id="callBackUrl" value="/3140314" />

  <!-- 产品ID -->
  <input type="hidden" name="productId" id="productId"/>
  <input type="hidden" name="companyId" id="companyId"/>
  <input type="hidden" name="token" id="token" />

				</form>
			</div>
		</div>
	</div>
	<div class="login-banner" id="banner">
		<div class="screen-width"><a href="#">爱医康服务收费规则</a>&nbsp;&nbsp;|&nbsp;&nbsp;<a href="#">爱医康服务协议</a>&nbsp;&nbsp;|&nbsp;&nbsp;<a href="#">隐私权规则</a></div>
	</div>
</div>
 <%@ include file="/resources/jsp/main/bottom.jsp" %> 
</body>
</html>