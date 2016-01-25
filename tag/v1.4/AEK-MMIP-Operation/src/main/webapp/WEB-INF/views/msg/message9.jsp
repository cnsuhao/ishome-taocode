<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false" %>
<html>
<head>
<title>页面跳转中</title>
<%@ include file="/resources/jsp/gys/inc.jsp" %>
</head>
<body>
<c:choose>
	<c:when test="${error=='false'}">
	<img src="/resources/skin/main/images/error_icon.png" align="middle" /> ${message}！
</c:when>
	<c:otherwise>
	<img src="/resources/skin/main/images/ok.png" align="middle" />${message}！
</c:otherwise>
</c:choose>
<br /><br />
<p>网页会在 <span id="m"></span> 秒后自动跳转到相关页面。</p>
<script>
$(function(){
	show();
});
var timeout = 3;
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
</body>
</html>