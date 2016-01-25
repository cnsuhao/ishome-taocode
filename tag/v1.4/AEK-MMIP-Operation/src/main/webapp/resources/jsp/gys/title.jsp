<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="user.jsp" %>
<div class="site-header">
	<div class="screen-width">
		<ul class="ulMenu rFloat">
		<li><a href="javascript:firstMenuClick('/3140314?menuType=MS0A1','USERMAIN')" <c:if test="${firstMenuid.equals('USERMAIN')}"> class="current"</c:if>>首页</a></li>
		<c:forEach var="item" items="${menu}">
			<li><a <c:if test="${firstMenuid.equals(item.puk)}">class="current"</c:if> href="javascript:firstMenuClick('${item.f04_cdurl}','${item.puk}')">${item.f02_cdmc}</a></li>
		</c:forEach>
		</ul>
		<a href="/" class="site-logo"><h1>爱医康医用耗材信息服务平台</h1></a>
	</div>
</div>
<script type="text/javascript">
function firstMenuClick(actionpath,firstMenuid){
	if(actionpath.indexOf("?")>0)
		document.blankForm.action=actionpath+"&firstMenuid="+firstMenuid;
	else
		document.blankForm.action=actionpath+"?firstMenuid="+firstMenuid;		
	blankForm.submit();
}
function secondMenuClick(actionpath,firstMenuid,secondMenuid){
	if(actionpath.indexOf("?")>0)
		document.blankForm.action=actionpath+"&firstMenuid="+firstMenuid+"&secondMenuid="+secondMenuid;
	else
		document.blankForm.action=actionpath+"?firstMenuid="+firstMenuid+"&secondMenuid="+secondMenuid;
	blankForm.submit();
}
</script>