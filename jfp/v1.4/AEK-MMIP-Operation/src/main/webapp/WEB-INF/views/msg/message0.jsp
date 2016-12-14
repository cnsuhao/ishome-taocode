<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false" %>
<html>
<head>
	<title>页面跳转中</title>
	<%@ include file="/resources/jsp/main/inc.jsp" %> 
</head>
<body>
<div class="screen-width mTop30 overflow">
	<h3 class="stm-page-title">操作提醒</h3>
	<dl class="optionMessage">
	<c:choose>
	<c:when test="${error=='false'}">
		<dt><img src="/resources/skin/main/images/error_icon.png" /></dt>
		<dd>
		<span class="red font24">${message}！</span>
	</c:when>
	<c:otherwise>
		<dt><img src="/resources/skin/main/images/ok.png" /></dt>
		<dd>
		<span class="green font24">${message}！</span>
	</c:otherwise>
	</c:choose>
	<c:if test="${error=='false'}">
		<p class="mTop10 gray">
			网页会在 <span id="m"></span> 秒后自动跳转到相关页面。<br /><br /><br />
			<input type="button" class="forword" name="submitBtn" id="submitBtn" value="返回" onclick="forword()">
		</p>
	</c:if>		
		</dd>
	</dl>
</div>
<script>
$(function(){
	show();
});
var timeout = 5;
function show() {
    var showbox = $("#m");
    showbox.html(timeout);
    timeout--;
    if (timeout == 0) {
        window.opener = null;
        forword();
    }
    else {
        setTimeout("show()", 1000);
    }
}
function forword(){
	<c:if test="${error=='false'}">
		window.history.go(-1);
	</c:if>
}
</script>
</body>
</html>