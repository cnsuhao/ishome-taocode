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
			<p class="font18">高值耗材精细化管理</p>
			<p class="mTop20">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>爱医康二级库房管理</b>就是在医疗器械供应链中应用标准化的信息编码技术，做到编码结构标准化；信息交换格式标准化；产品信息描述标准化。实现医疗器械供应链的信息共享，在供应链各环节点使用统一的标准和格式，保障信息的准确和渠道的畅通，确保患者安全。</p>
			<p class="mTop30">
				<b>其主要特点如下：</b><br />
				高值耗材条码智能识别及全流程条码管理；<br />
				让医护人员快速、便捷地完成高值耗材管理及病人收费<br />
				质管员可以全方位对高值耗材进行回溯<br />
				高值耗材零库存管理及精确采购与结算<br />
				让供应商一起参与高值耗材寄售库存管理；
			</p>
		</div>
	</div>
</div>
<%@ include file="/resources/jsp/gys/bottom.jsp" %> 
</body>
</html>