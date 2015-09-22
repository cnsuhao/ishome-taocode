<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" import="org.jfpc.framework.helper.*" %>
<c:choose>
<c:when test="${firstMenuid != null && firstMenuid != ''}">
<div class="stm-leftbox">
	<ul class="sub-menu-list">
		<c:forEach var="item" items="${menu2}">
			<c:choose>
			<c:when test="${secondMenuid.equals(item.puk)}">
				<li><a class="current" href="javascript:secondMenuClick('${item.f04_cdurl}','${item.k01_xsfcdid}','${item.puk}')">${item.f02_cdmc}</a></li>
			</c:when>
			<c:otherwise>
				<li><a href="javascript:secondMenuClick('${item.f04_cdurl}','${item.k01_xsfcdid}','${item.puk}')">${item.f02_cdmc}</a></li>
			</c:otherwise>
			</c:choose>
		</c:forEach>
	</ul>
	<!-- <div class="weixin">
		<a href="#">在线咨询</a><br />
		<a href="#">建议反馈</a><br />
		<a href="#">爱医康手机版</a><br />
		<img src="/images/erweima.jpg" /><p align="center">扫描二维码下载</p>
	</div> -->
</div>
</c:when>
<c:otherwise>
<div class="stm-leftbox" style="padding-top:0;">
	<div class="use-info" style="border-bottom:none;">
		<div class="use-info-img lFloat">
		<img src="<%=ImagePath %>/00003030/${company.f20_logo_url}/${loginer.token}?t=<%=DateHelper.currentTime1()%>" />
		</div>
		<p>
			<b>${loginer.userNameCN}</b><br/><br/>
		</p>
		<p class="clearfix mTop10">上次登录：<br/>${loginer.loginTime}</p>
	</div>
</div>
</c:otherwise>
</c:choose>