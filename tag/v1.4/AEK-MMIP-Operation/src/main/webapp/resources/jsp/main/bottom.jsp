<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="clear"></div>
<div class="footer">
	<div class="link">
	<c:if test="${friends!=null && friends.size()!=0}">
		合作伙伴：  			
	</c:if>
	
	<c:forEach var="item" items="${friends}">
		<a href="${item.bbb}" target="_blank">${item.f01_zdmc}</a> 
	</c:forEach>
	</div>
	<p>
		AEK&专业医用耗材信息服务商&nbsp;&nbsp;&nbsp;&nbsp;联系我们：400-052-5256&nbsp;&nbsp;&nbsp;&nbsp;服务时间：周一至周五 8:30-18:00<br />
		<span class="copy"><a href="/resources/jsp/main/mianze.jsp" target="_blank">免责条款</a><!-- &nbsp;&nbsp;|&nbsp;&nbsp;<a href="/resources/jsp/main/guestbook.jsp" target="_blank">提建议</a> -->&nbsp;&nbsp;&copy;2015&nbsp;&nbsp;杭州爱医康科技有限公司&nbsp;&nbsp;浙ICP备13028265号</span>
	</p>
</div>
