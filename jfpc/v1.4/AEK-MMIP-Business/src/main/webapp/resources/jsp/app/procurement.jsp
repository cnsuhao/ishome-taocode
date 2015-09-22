<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>爱医康医用耗材信息服务平台</title>
<meta http-equiv="keywords" content="1" />
<meta http-equiv="description" content="1" />
<%@ include file="/resources/jsp/yy/inc.jsp" %> 
<script type="text/javascript">
$(function(){
	$("#siteNav").nav();
})
</script>
</head>

<body>
<%@ include file="/resources/jsp/gys/title.jsp" %>
<div class="screen-width mTop30">
	<div class="site-main-left lFloat pos-relative">
		<div class="use-info">
			<div class="use-info-img lFloat">
			<img src="/resources/img/noImg.png" />
			</div>
			<p>
				<b>${loginer.userNameCN}</b><br/><br/>
				<a href="javascript:firstMenuClick('/3140205','13500')">账号信息</a><br />
				<a href="javascript:firstMenuClick('/31402045','13500')">管理子帐号</a>
			</p>
			<p class="clearfix mTop10">上次登录：${loginer.loginTime}</p>
		</div>
		<div class="stm-app">
			<h3>
				<!-- <a href="javascript:void(0);">添加应用</a> -->
				已添加应用
			</h3>
			<ul class="stm-app-list" id="myApp">
				<li><a href="javascript:firstMenuClick('/31403011','13200');"><img src="/resources/skin/icon/1.png" />证件管理</a></li>
				<li><a href="/resources/jsp/app/procurement.jsp"><img src="/resources/skin/icon/2.png" />耗材采购</a></li>
			</ul>
		</div>
		<div class="stm-app">
			<h3>增值应用</h3>
			<ul class="stm-app-list" id="systemApp">
				<li><a href="/resources/jsp/app/equipment.jsp"><img src="/resources/skin/icon/3.png" />设备管理</a></li>
			</ul>
		</div>
		<div class="pos-absolute" style="bottom:20px; left:20px;">
			<img src="/resources/skin/gys/images/erweima.jpg" /><p align="center">关注爱医康</p>
		</div>
	</div>
	<div class="site-main-temp">
		<h3 class="forward-title"><img src="/resources/skin/yy/images/forward.png" /> 敬请期待</h3>
		<div class="forward-content mTop30">
			<p class="font18">轻松完成采购，简单甩掉库存</p>
			<p class="mTop20">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;医疗器械供应链的效率高低和精准程度关乎到民众的切身利益，诸如患者安全、医疗成本、就医效率等。随着信息技术的不断提高，传统供应链模式已不能满足市场需求。</p>
			<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>爱医康请领采购</b>是以国家卫生行政部门相关政策法规为基础，结合市场需求，基于目前主流互联网应用模式进行开发，围绕医疗机构，通过对信息流、物流、资金流和服务流的控制，将医疗机构、供应商、制造商、分销商、零售商直到最终用户连成一个整体的功能网链结构模式。</p>
		</div>
	</div>
</div>
<%@ include file="/resources/jsp/gys/bottom.jsp" %> 
</body>
</html>