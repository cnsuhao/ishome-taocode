<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" import="org.jfpc.framework.helper.*" %>
<%@ taglib uri="/WEB-INF/taglib/pagetag.tld"  prefix="p" %>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<title></title>
<meta name="description" content="">
<meta name="keywords" content="">
<link rel="stylesheet" type="text/css" href="/resources/css/base.css">
<style type="text/css">html, body{overflow: hidden;}</style>
<script src="/resources/js/jquery.js"></script>
<script src="/resources/js/jq.ui.js"></script>
<script src="/resources/js/function.js?t=<%=DateHelper.currentTime1()%>"></script>
<script src="/resources/js/jquery.mousewheel.js"></script>
<script src="/resources/js/jquery.mCustomScrollbar.js"></script>
<%@ include file="/resources/jsp/ImagePath.jsp" %>
<script>
var isClick = false, token="${loginer.token}";
$(function(){
	$("#yui-siteNav").nav();
	getHeight();
	$(window).resize(function() {
	  	getHeight();
	});
	getMenu();
	getUserInfo();
});
</script>
</head>
<body>
<div class="header" id="yui-siteNav">
	<ul class="nav rFloat">
		<!-- <li><a href="javascript:firstMenuClick('/3140205','13600')" target="iframe" class="topApp">添加新应用</a></li> -->
		<li><a href="javascript:void(0);" onclick="post('/3000200?index=0');" target="iframe" class="topMsg"><span id="yui-msg">0</span></a></li>
		<li>
			<div class="menu-hd topArrow">
				<span class="mlogo" id="yui-hosName"></span> <span class="userName"></span>
				<i></i>
			</div>
			<div class="menu-bd" style="width:200px; right: 5px;">
				<div class="topInfoTitle">帐户设置</div>
				<ul class="topUserInfo">
					<li>
						<div class="mlogo inlineBlock" id="yui-name"></div>
						<p class="inlineBlock w100"><span class="userName"></span><br /><span class="gray hospitalName"></span></p>
					</li>
					<li>
					    <!-- /users_list.jsp?index=0,/users_list.jsp?index=0 -->
						<a href="javascript:void(0);" onclick="post('/3140205?index=0');" target="iframe" class="topUicon">帐户设置</a>
						<c:if test="${loginer.isAdmin=='0' }">
							<a href="javascript:post('/3140205?index=1');" target="iframe" class="topCicon">成员管理</a>
						</c:if>
					</li>
					<li class="noLine">
						<a href="" class="topHicon">帮助中心</a>
						<a href="" class="topBicon">用户社区</a>
					</li>
				</ul>
				<div class="toplogout"><a href="/00099000">退出</a></div>
			</div>
		</li>
	</ul>
	<a href="/" class="logo"><h1></h1></a>
</div>
<div class="main">
	<div class="left lFloat" id="yui-left-box">
		<ul class="menu" id="yui-menu">
		</ul>
		<a href="javascript:void(0);" onclick="clickZoom(this);" class="zoomOn"></a>
	</div>
	<div class="right" id="yui-right-box">
		<div class="menuTitle">
			<ul class="subNav rFloat" id="yui-subNav"></ul>
			<img id="yui-iframeIcon" src=""> <span id="yui-navName"></span>
		</div>
		<iframe id="yui-iframe" name="iframe" frameborder="0" scrolling="no" src="" width="100%"></iframe>
	</div>
</div>
<%@ include file="/resources/jsp/formJS.jsp" %>
</body>
</html>