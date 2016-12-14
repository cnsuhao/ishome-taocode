<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" import="org.jfpc.framework.helper.*" %>
<div class="site-nav">
	<div class="site-nav-bd" id="siteNav">
		<ul class="site-nav-bd-r">
			<li>
				<div class="menu-hd">
					<a href="javascript:void(0);">您好，${company.f02_qyqc}&nbsp;－&nbsp;${loginer.userNameCN}</a>
					<i>
						<em></em>
						<span></span>
					</i>
				</div>
				<div class="menu-bd">
					<div class="menu-bd-panel" style="width: 200px; padding:8px;">
						<img src="<%=ImagePath %>/00003030/${company.f20_logo_url}/${loginer.token}?t=<%=DateHelper.currentTime1()%>" align="left" width="45" height="45" border="0" />
						<div style="margin-left:55px; line-list:26px;">
							<p>${loginer.userNameCN}</p>
							<p class="mTop10">
							<c:choose>
							<c:when test="${loginer.userType ==1}">
							<a href="javascript:firstMenuClick('/3140205','13600')">账号信息</a>  
							</c:when>
							<c:when test="${loginer.userType ==2}">
							<a href="javascript:firstMenuClick('/3140205','23700')">账号信息</a>  
							</c:when>
							</c:choose>

							<c:if test="${loginer.isAdmin ==0}">
								<c:choose>
								<c:when test="${loginer.userType ==1}">
								 | <a href="javascript:firstMenuClick('/31402045','13600')">管理子账号</a>
								</c:when>
								<c:when test="${loginer.userType ==2}">
								 | <a href="javascript:firstMenuClick('/31402045','23700')">管理子账号</a>
								</c:when>
								</c:choose>
							</c:if>
							</p>
						</div>
					</div>
				</div>
			</li>
			<li>
				<div class="menu-hd">
				<c:choose>
					<c:when test="${loginer.userType ==1}">
					<a href="javascript:firstMenuClick('/31408041','13900')">消息<span id="msgNum" class="msg">${NBXXNUM}</span></a>
					</c:when>
					<c:when test="${loginer.userType ==2}">
					<a href="javascript:firstMenuClick('/31408041','23930')">消息<span id="msgNum" class="msg">${NBXXNUM}</span></a>
					</c:when>
					</c:choose>
				</div>
				<c:if test="${NBXXNUM > 0 }">
				<div class="menu-bd">
					<div class="menu-bd-panel" style="width: 200px;">
						<ul class="top-msg-list">
						<c:forEach var="item" items="${NBXX}" varStatus="s">
							<li<c:if test="${s.index == 0}"> class="noline"</c:if>><a href="javascript:showPage('/31408042/${item.puk}')">${item.f03_bt}</a></li>
						</c:forEach>
						</ul>
					</div>
				</div>
				</c:if>
			</li>
			<li>
				<div class="menu-hd">
					<a>客服</a>
					<i>
						<em></em>
						<span></span>
					</i>
				</div>
				<div class="menu-bd">
					<div class="menu-bd-panel" style="width: 200px; padding:8px;">
						<h3>400-052-5256</h3>
					</div>
				</div>
			</li>
			<li><div class="menu-hd"><a href="/resources/jsp/main/guestbook.jsp" target="_blank">意见反馈</a></div></li>
			<li class="noBg"><div class="menu-hd-no"><a href="javascript:showPage('/00099000')">退出</a></div></li>
		</ul>
	</div>
</div>