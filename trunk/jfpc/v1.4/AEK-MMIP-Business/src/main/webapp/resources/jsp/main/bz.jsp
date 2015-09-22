<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>帮助中心 - 爱医康医用耗材信息服务平台</title>
<meta http-equiv="keywords" content="爱医康,供应商登录,医院登录" />
<meta http-equiv="description" content="爱医康平台账户登录页面。" />

<%@ include file="inc.jsp" %>
<link rel="stylesheet" type="text/css" href="/resources/skin/main/css/help.css?t=1409546990"></link>
<script type="text/javascript" src="/resources/skin/main/js/function.js"></script>
<script type="text/javascript" src="/resources/skin/main/js/placeholder.js"></script>
</head>

<body>
<jsp:include page="/resources/jsp/main/top.jsp">
  <jsp:param name="type" value="bz"/>
</jsp:include>
<div class="main">
	<div class="screen-width">
		<h3 class="t-box-hd">帮助中心</h3>
		<p class="f14">
			如果您使用过程中遇到问题,立即需要帮助,请联系<a target="_blank" href="http://wpa.qq.com/msgrd?v=3&uin=2777618728&Site=杭州爱医康在线客服&Menu=yes">在线客服</a><br /><br />
			<span class="f18">爱医康客服热线：<b class="blue">400-052-5256</b></span>
		</p>
		<div class="help-box mTop15">
			<div class="help-left">
				<h4>常见问题分类</h4>
				<dl class="help-category">
					<dt>+会员账号&密码</dt>
					<dd>
						<a href="">-注册</a>
						<a href="">-验证</a>
						<a href="">-登录</a>
						<a href="">-密码</a>
						<a href="">-会员资料</a>
					</dd>
				</dl>
			</div>
			<div class="help-right">
				<div class="help-search">
					<input type="text" name="keywords" id="keywords" value="" placeholder="输入问题关键字，如：注册" />
					<input type="submit" name="submitSearch" id="submitSearch" value="搜索" />
				</div>
				<ul class="essay-list">
					<li>1、爱医康会员注册平台</li>
					<li>1、爱医康会员注册平台</li>
					<li>1、爱医康会员注册平台</li>
					<li>1、爱医康会员注册平台</li>
					<li>1、爱医康会员注册平台</li>
					<li>1、爱医康会员注册平台</li>
					<li>1、爱医康会员注册平台</li>
					<li>1、爱医康会员注册平台</li>
					<li>1、爱医康会员注册平台</li>
					<li>1、爱医康会员注册平台</li>
					<li>1、爱医康会员注册平台</li>
					<li>1、爱医康会员注册平台</li>
					<li>1、爱医康会员注册平台</li>
				</ul>
				<div class="pages mTop15">
					<p:page pageVo="${page}"/>
				</div>
			</div>
			<div class="clear"></div>
		</div>
	</div>
</div>
 <%@ include file="bottom.jsp" %> 
</body>
</html>