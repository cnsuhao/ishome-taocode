<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>产品-爱医康医用耗材信息服务平台</title>
<meta http-equiv="keywords" content="1" />
<meta http-equiv="description" content="1" />
<%@ include file="inc.jsp" %>
</head>

<body>
<%@ include file="top.jsp" %> 
<div class="screen-width">
	<div class="serives-box">
		<span class="ser-icon consult"></span>
		<div class="serives-content pos-relative">
			<div class="pos-absolute" style="right:20px; top:-20px;"><img src="skin/default/images/serives_01.jpg" align="right" /></div>
			<h3>咨询服务</h3>
			<dl class="serives-dl">
				<dt>标准咨询</dt>
				<dd class="mBottom30">了解产品、试用产品、费用咨询。</dd>
				<dt>医院客户</dt>
				<dd>产品演示及讲解、协助客户数据信息处理。</dd>
			</dl>
		</div>
	</div>
	<div class="serives-box">
		<span class="ser-icon operation"></span>
		<div class="serives-content pos-relative">
			<div class="pos-absolute" style="right:20px; top:80px;"><img src="skin/default/images/serives_02.jpg" /></div>
			<h3>运维服务</h3>
			<p>”值得信赖、善解人意“是我们的服务理念，提供满意的服务、关注客户价值的实现是我们服务的目标。根据与客户约定，我们提供：</p>
			<ul class="serives-ul">
				<li>程序故障及时处理；</li>
				<li>协助服务器、数据库优化；</li>
				<li>数据备份方案的制定及实施；</li>
				<li>分布式部署；</li>
				<li>数据转移；</li>
				<li>小需求响应和处理。</li>
			</ul>
		</div>
	</div>
	<div class="serives-box ser-noline">
		<span class="ser-icon technology"></span>
		<div class="serives-content pos-relative">
			<div class="pos-absolute" style="right:20px; top:30px;"><img src="skin/default/images/serives_03.jpg" /></div>
			<h3>技术支持</h3>
			<dl class="serives-dl">
				<dt>产品讨论</dt>
				<dd class="mBottom30">问题反馈、意见建议、常用技巧、常见问题。</dd>
				<dt>企业微信</dt>
				<dd class="mBottom30">关注爱医康微信，及时了解产品最新动态。</dd>
				<dt>QQ</dt>
				<dd>在线客服：2777618728，我们的技术人员会及时响应您提出的各种问题。</dd>
			</dl>
		</div>
	</div>
</div>
 <%@ include file="bottom.jsp" %> 
</body>
</html>