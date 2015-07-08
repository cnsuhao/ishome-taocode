<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<div class="site-nav">
	<div class="site-nav-bd" id="siteNav">
		<ul class="site-nav-bd-r">
			<li>
				<div class="menu-hd">
					<a href="/">您好，2222</a>
					<i>
						<em></em>
						<span></span>
					</i>
				</div>
				<div class="menu-bd">
					<div class="menu-bd-panel" style="width: 200px; padding:8px;">
						<img src="" align="left" width="45" height="45" border="0" />
						<div style="margin-left:55px; line-list:26px;">
							<p>fff(<span class="gray">2222</span>)</p>
							<p class="mTop10"><a href="?a=account">账号信息</a> |  <a href="?a=account_users">管理子账号</a></p>
						</div>
					</div>
				</div>
			</li>
			<li>
				<div class="menu-hd">
					<a href="#">消息<span id="msgNum" class="msg">3</span></a>
				</div>
				<div class="menu-bd">
					<div class="menu-bd-panel" style="width: 140px;">
						<ul class="top-msg-list">
							<li class="noline"><a href="#">XXX三证信息即将过期</a></li>
							<li><a href="#">XXX三证信息即将过期</a></li>
							<li><a href="#">XXX三证信息即将过期</a></li>
						</ul>
					</div>
				</div>
			</li>
			<li>
				<div class="menu-hd">
					<a href="#">客服</a>
					<i>
						<em></em>
						<span></span>
					</i>
				</div>
			</li>
			<li class="noBg"><div class="menu-hd-no"><a href="/account/logout.php">退出</a></div></li>
		</ul>
	</div>
</div>