<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<% 
	String type = request.getParameter("type");
	pageContext.setAttribute("type", type);
%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="header">
	<div class="screen-width pos-relative">
		
		<ul class="nav rFloat">
			<li><a href="/"<c:if test="${type == 'main'}"> class="current"</c:if>>首页</a></li>
			<li><a href="/resources/jsp/main/cp.jsp"<c:if test="${type == 'cp'}"> class="current"</c:if>>产品</a></li>
			<li><a href="/resources/jsp/main/fw.jsp"<c:if test="${type == 'fw'}"> class="current"</c:if>>服务</a></li>
			<li><a href="/31408061?token=SYSTEMBZ"<c:if test="${type == 'bz'}"> class="current"</c:if>>帮助</a></li>
			<li><a href="/resources/jsp/main/about.jsp"<c:if test="${type == 'about'}"> class="current"</c:if>>关于</a></li>
		</ul>
		<a href="/" class="logo"><h1>爱医康医用耗材信息服务平台</h1></a>
		<div class="pos-absolute login-info"><a href="/3140000" >登录</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="/resources/jsp/main/zc.jsp">注册</a></div>
	</div>
</div>
