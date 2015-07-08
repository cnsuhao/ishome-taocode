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
						<li  class="flowPath-item cur">
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
				<form id="reg-form" action="/31401034" method="post">
					<div class="form-item">
						<div class="form-item-left">新密码：</div>
						<div class="form-item-right">
							<div class="input-text fl-l">
								<input class="text w120" type="password" name="k04_dlmm" id="k04_dlmm"/>
							</div>
							<div class="qzr-mod clear inline-block" data-type="r">
								<div class="qzr-r">弱</div>
								<div class="qzr-z">中</div>
								<div class="qzr-q">强</div>
							</div>
							<br/>
							<span class="f12 inline-block" style="line-height:16px; margin-top:5px;">
								1、为了您的账号安全，新密码必须与旧密码不同。<br>
								2、密码由6-16位（字母、数字、符号）组成，不能使用同一种字符、区分大小写
							</span>
						</div>
					</div>
					<div class="form-item">
						<div class="form-item-left">再次输入：</div>
						<div class="form-item-right">
							<div class="input-text">
								<input class="text w120" type="password" name="rek04_dlmm" id="rek04_dlmm"/>
							</div>
						</div>
					</div>
					<div class="form-bottom">
						<input type="submit" value="下一步" class="button blue-buttom" />
					</div>
					<input type="hidden" name="puk" id="puk" value="${UserData.puk}"/>
				</form>
			</div>
		</div>
	</div>
</body>
</html>