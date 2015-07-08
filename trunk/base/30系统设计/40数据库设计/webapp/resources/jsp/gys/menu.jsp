<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ include file="user.jsp" %>
<div class="site-header">
	<div class="screen-width">
		<ul class="ulMenu rFloat">
			<li><a href="?a=index" class="current">首页</a></li><li><a href="?a=account">账户</a></li><li><a href="?a=cert">三证管理</a></li><li><a href="?a=hospitallist">耗材采购</a></li>		</ul>
		<a href="/" class="site-logo"><h1>爱医康医用耗材信息服务平台</h1></a>
	</div>
</div>
${menu}