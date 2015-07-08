<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>找回密码 - 爱医康医用耗材信息服务平台</title>
<meta http-equiv="keywords" content="爱医康,供应商登录,医院登录" />
<meta http-equiv="description" content="爱医康平台账户登录页面。" />
<link rel="icon" href="skin/logo.ico" type="image/x-icon" />
<%@ include file="inc.jsp" %>
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
				<form id="reg-form" action="/31401031" method="post">
					<div class="form-item">
						<div class="form-item-left">登陆账号：</div>
						<div class="form-item-right">
							<div class="input-text">
								<input type="text" name="username" id="username" class="text w210" />
							</div>
							<span class="gray f12 ml10">
								忘记登录帐号？忘记会员名？就使用手机号或邮箱吧
							</span>
						</div>
					</div>
					<div class="form-item">
						<div class="form-item-left">验证码：</div>
						<div class="form-item-right">
							<div class="input-text">
								<input type="text" name="yzm" id="yzm" class="text w210" />
							</div>
						</div>
						<div class="fl-l ml10">
							<img id="code" src="" width="52" height="25" />
						</div>
						<span class="gray f12 ml10">
							看不清？<a class="hoverLine" onclick="document.getElementById('code').src='{{url}}&r=' + Math.random()" href="javascript:;">换一张</a>
						</span>
					</div>
					<div class="form-bottom">
						<input type="submit" value="下一步" class="button blue-buttom" />
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