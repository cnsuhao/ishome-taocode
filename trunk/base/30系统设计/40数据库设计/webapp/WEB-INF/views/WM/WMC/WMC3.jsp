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
<%@ include file="/resources/jsp/main/inc.jsp" %>
</head>
<body>
	<div class="content-bd">
		<div class="t-box find-pwd">
			<div class="t-box-hd">密码找回</div>
			<div class="t-box-bd t-a-c f14">
				<div class="flowPath-mod m0a" style="width:736px;">
					<ul class="flowPath">
						<li class="flowPath-item">
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
						<li  class="flowPath-item cur">
							<div class="flowPath-item-img img-suc"></div>
							<div class="flowPath-item-text">找回成功</div>
						</li>
					</ul>
				</div>
				<div class="w710 m0a">
				<!-- form -->
				<form id="reg-form" action="忘记2.html">
					<div class="msg-mod">
						<div class="msg-hd">
							<span class="msg-ico success vb"></span>
							恭喜你，密码重置成功，新密码已发送至你的手机，请妥善保管。
						</div>
						<div class="msg-bd">
							<div>邮箱：未绑定 <a class="blue hoverLine">立即绑定</a></div>
							<div class="gray">绑定后可作为登录帐号使用 ，可使用邮箱进行找回密码</div>
						</div>
						<div class="msg-ft">
							<input type="button" class="button" value="进入平台"/>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
	<!-- end foot -->
</body>
</html>