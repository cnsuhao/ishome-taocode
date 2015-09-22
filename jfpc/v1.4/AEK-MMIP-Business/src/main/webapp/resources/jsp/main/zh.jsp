<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" import="org.jfpc.framework.helper.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>找回密码 - 爱医康医用耗材信息服务平台</title>
<meta http-equiv="keywords" content="爱医康,供应商登录,医院登录" />
<meta http-equiv="description" content="爱医康平台账户登录页面。" />
<link rel="icon" href="skin/logo.ico" type="image/x-icon" />
<%@ include file="inc.jsp" %>

<link rel="stylesheet" type="text/css" href="/resources/skin/main/css/reg.css">
<script type="text/javascript">
$(function(){
	$(".text").input();
})
function changCode(){
	$("#vcode").attr("src","/00003031/92/35?t="+Math.random());
} 
function fSubmit(){
	var myForm = $("#reg-form").serializeJSON();
	if (myForm['username'].isEmpty()) {
		$("#username").errMsg('登陆账号不能为空！');
		return false;
	}
	if (myForm['yzm'].isEmpty()) {
		$("#yzm").errMsg('请输入验证码！');
		return false;
	}
	return true;
}
</script>
</head>

<body>
	<div class="content-bd">
		<div class="t-box find-pwd">
			<div class="t-box-hd">密码找回</div>
			<div class="t-box-bd t-a-c f14">
				<div class="flowPath-mod m0a" style="width:736px;">
					<ul class="flowPath">
						<li class="flowPath-item cur">
							<div class="flowPath-item-img img1"></div>
							<div class="flowPath-item-text">输入账号</div>
						</li>
						<li class="flowPath-line"></li>
						<li  class="flowPath-item">
							<div class="flowPath-item-img img2"></div>
							<div class="flowPath-item-text">验证身份</div>
						</li>
						<li class="flowPath-line"></li>
						<li  class="flowPath-item">
							<div class="flowPath-item-img img3"></div>
							<div class="flowPath-item-text">重置密码</div>
						</li>
						<li class="flowPath-line"></li>
						<li  class="flowPath-item">
							<div class="flowPath-item-img img-suc"></div>
							<div class="flowPath-item-text">找回成功</div>
						</li>
					</ul>
				</div>
				<div class="w710 m0a">
				<!-- form -->
				<form id="reg-form" action="/31401031" method="post" onsubmit="return fSubmit();">
					<div class="form-item">
						<div class="form-item-left">登陆账号：</div>
						<div class="form-item-right">
							<input type="text" name="username" id="username" class="text {text:'输入登录用户名'}" size="30" />
						</div>
					</div>
					<div class="form-item">
						<div class="form-item-left">验证码：</div>
						<div class="form-item-right">
							<div class="input-text">
								<input type="text" name="yzm" id="yzm" class="text {text:'输入验证码'}" size="12" maxlength="4" />
							</div>
						</div>
						<div class="fl-l ml10">
							<a href="javascript:changCode();" title="看不清？换一张"><img id="vcode" src="/00003031/92/35?t=<%=DateHelper.currentTime1()%>" height="32" align="mddile" /></a>
						</div>
					</div>
					<div class="form-bottom">
						<input type="submit" value="下一步" class="submit" />
					</div>
					
				</form>
				<!-- end form -->
				</div>
			</div>
		</div>
	</div>
	<!-- end foot -->
</body>
</html>