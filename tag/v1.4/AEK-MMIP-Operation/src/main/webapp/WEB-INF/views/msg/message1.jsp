<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>爱医康医用耗材信息服务平台</title>
<meta http-equiv="keywords" content="1" />
<meta http-equiv="description" content="1" />
<%@ include file="/resources/jsp/gys/inc.jsp" %>

</head>

<body>
<%@ include file="/resources/jsp/gys/title.jsp" %> 
<div class="screen-width mTop30 overflow">
	<h3 class="stm-page-title">${message}</h3>
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
		<p class="mTop10 gray">
			网页会在 <span id="m"></span> 秒后自动跳转到相关页面。<br /><br /><br />
			<input type="button" class="forword" name="submitBtn" id="submitBtn" value="返回"  onclick="forword()">
		</p>
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
	forwordForm.submit();
}
</script>
<form id="forwordForm" name="forwordForm" method="post" action="${forwordURL}"></form>
<%@ include file="/resources/jsp/gys/bottom.jsp" %>
</body>
</html>