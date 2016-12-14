<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>服务-爱医康医用耗材信息服务平台</title>
<meta http-equiv="keywords" content="1" />
<meta http-equiv="description" content="1" />
<%@ include file="inc.jsp" %>
</head>

<body>
<jsp:include page="/resources/jsp/main/top.jsp">
  <jsp:param name="type" value="fw"/>
</jsp:include>
<div class="serives-banner"></div>
<div class="screen-width">
	<div class="serives-box">
		<span class="ser-icon consult"></span>
		<div class="serives-content">
			<h3>咨询服务</h3>
			<div class="serives-dl">
				<b>标准咨询：</b>了解产品、试用产品、费用咨询。<br />
				<b>医院客户：</b>产品演示及讲解、协助客户数据信息处理。
			</div>
		</div>
	</div>
	<div class="serives-box">
		<span class="ser-icon operation"></span>
		<div class="serives-content">
			<h3>运维服务</h3>
			<p><b>”值得信赖、善解人意“是我们的服务理念，提供满意的服务、关注客户价值的实现是我们服务的目标。根据与客户约定，我们提供：</b></p>
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
		<div class="serives-content">
			<h3>技术支持</h3>
			<div class="serives-dl">
				<b>产品讨论：</b>问题反馈、意见建议、常用技巧、常见问题。<br />
				<b>企业微信：</b>关注爱医康微信，及时了解产品最新动态。<br />
				<b>QQ在线：</b>客服QQ<a target="_blank" href="http://wpa.qq.com/msgrd?v=3&uin=2777618728&Site=杭州爱医康在线客服&Menu=yes">2777618728</a>，我们的技术人员会及时响应您提出的各种问题。
			</div>
		</div>
	</div>
</div>
 <%@ include file="bottom.jsp" %> 
</body>
</html>