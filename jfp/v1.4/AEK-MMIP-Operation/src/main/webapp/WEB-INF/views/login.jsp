<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" import="org.jfpc.framework.helper.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>账号登录 - 爱医康医用耗材信息服务平台</title>
<meta http-equiv="keywords" content="爱医康,供应商登录,医院登录" />
<meta http-equiv="description" content="爱医康平台账户登录页面。" />
<meta name="copyright" content="© 爱医康医用耗材信息服务平台 v0.1.4.20141024-SNAPSHOT" />
<%@ include file="/resources/jsp/main/inc.jsp" %> 
<link rel="stylesheet" type="text/css" href="/resources/skin/main/css/login.css">
<script type="text/javascript">
$(function(){
	$(".login-tab>li").each(function(){
		$(this).click(function(){
			$(this).addClass("current").siblings().removeClass("current");
			$("userType").val($(this).index()+1);
		})
	});
	<c:if test="${page_message != null}">$("#errorMsg").errMsg('${page_message}', -1);</c:if>
});

function loginSubmit(){
	var loginData = $("#loginFrm").serializeJSON();
	// || (loginData['userName'].length < 3 || loginData['userName'] > 6)
	if(loginData['userName'].isEmpty()){
		$("#userName").errMsg();
		return false;
	}
	if(loginData['passWord'].isEmpty() || loginData['passWord'].length < 6){
		$("#passWord").errMsg();
		return false;
	}
	if (loginData['verCode'].isEmpty()) {
		$("#verCode").errMsg('请输入验证码！');
		return false;
	}
	return true;
}
function changCode(obj){
	$("img", obj).attr("src","/00003031/92/35?t="+Math.random());
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
				<form id="loginFrm" name="loginFrm" action="/00000000" method="post" onsubmit="return loginSubmit();">
				<h2>登录爱医康</h2>
				<div id="errorMsg"></div>
				<div class="mTop18">
					<label class="borderLabel user" type="icon">
						<input type="text" name="userName" id="userName" autocomplete="off" value="" placeholder="Email/手机/会员帐号" tips="请输入6-12位的登录用户名，以字母开头，包含数字。" />
					</label>
				</div>
				<div class="mTop18">
					<label class="borderLabel pass" type="icon">
						<input type="password" name="passWord" id="passWord" autocomplete="off" value="" placeholder="输入登录密码" tips="请输入6位以上的登录密码。" />
					</label>
				</div>
				<div class="mTop18">
					<label class="borderLabel code" type="icon">
						<input type="text" name="verCode" id="verCode" size="4" maxlength="4" autocomplete="off" placeholder="输入验证码" tips="请输入4位验证码。" />
					</label>
					<a href="javascript:void(0);" onclick="changCode(this);" title="看不清，点击换一张？"><img src="/00003031/92/35?t=<%=DateHelper.currentTime1()%>" style="height:40px; width:96px; vertical-align:middle;" /></a>
				</div>
				<div class="mTop18 pos-relative"><input type="submit" name="loginBtn" id="loginBtn" value="登录" class="login-btn" /></div>
				<div class="mTop18" align="right"><a href="/resources/jsp/main/zc.jsp"><u>免费注册</u></a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="/resources/jsp/main/mm.jsp">忘记登录密码？</a></div>

  <!-- 登录地址 -->
  <input type="hidden" name="loginUrl"  id="loginUrl" value="/3140000" />         
  <!-- 回调地址 -->
  <input type="hidden" name="callBackUrl"  id="callBackUrl" value="/3000000" />

  <!-- 产品ID -->
  <input type="hidden" name="productId" id="productId"/>
  <input type="hidden" name="companyId" id="companyId"/>
  <input type="hidden" name="userType" id="userType"/>
  <input type="hidden" name="token" id="token" />

				</form>
			</div>
		</div>
	</div>
	<div class="login-banner" id="banner">
		<!-- <div class="screen-width"><a href="#">爱医康服务协议</a>&nbsp;&nbsp;|&nbsp;&nbsp;<a href="#">隐私权规则</a></div> -->
	</div>
</div>
 <%@ include file="/resources/jsp/main/bottom.jsp" %> 
</body>
</html>