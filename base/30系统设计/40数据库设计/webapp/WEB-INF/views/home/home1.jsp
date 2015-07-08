<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>爱医康医用耗材信息服务平台</title>
<meta http-equiv="keywords" content="1" />
<meta http-equiv="description" content="1" />
<link rel="icon" href="../skin/logo.ico" type="image/x-icon" />
<link rel="shortcut icon" href="../skin/logo.ico" type="image/x-icon" />
<script type="text/javascript" src="../skin/default/js/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="../skin/default/js/jq.ui.js"></script>
<link rel="stylesheet" type="text/css" href="../skin/default/css/base.css">
<link rel="stylesheet" type="text/css" href="../skin/default/css/supplier.css">
<script type="text/javascript" src="../skin/default/js/function.js"></script>
<script type="text/javascript">
$(function(){
	$(".msg").uiSwitch();
	$("#siteNav").nav();
})
</script>
</head>

<body>
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
	</div>
</div>
<div class="site-header">
	<div class="screen-width">
		<ul class="ulMenu rFloat">
			<li><a href="?a=index" class="current">首页</a></li><li><a href="?a=account">账户</a></li><li><a href="?a=cert">证件维护</a></li><li><a href="?a=hospitallist">客户关系</a></li>		</ul>
		<a href="/" class="site-logo"><h1>爱医康医用耗材信息服务平台</h1></a>
	</div>
</div>
<div class="screen-width mTop30">
	<div class="site-main-left lFloat pos-relative">
		<div class="use-info">
			<div class="use-info-img lFloat"><img src="" /></div>
			<p>
				<b>fff</b><br /><br />
				<a href="/">账号信息</a><br /><a href="/">管理子帐号</a>
			</p>
			<p class="clearfix mTop10">上次登录：2014-08-12 12:32:01</p>
		</div>
		<div class="stm-app">
			<h3>
				<a href="/">添加应用</a>
				已添加应用
			</h3>
			<ul class="stm-app-list" id="myApp"></ul>
		</div>
		<div class="stm-app">
			<h3>增值应用</h3>
			<ul class="stm-app-list" id="systemApp"></ul>
		</div>
		<div class="pos-absolute" style="bottom:20px; left:20px;">
			<img src="/images/erweima.jpg" /><p align="center">扫描二维码下载</p>
		</div>
	</div>
	<div class="site-main-right rFloat">
		<div class="stm-article">
			<h4>系统公告</h4>
			<ul class="stm-article-list">
				<li><a href="?id=1">爱医康会员注册流程</a></li><li><a href="?id=2">会员注册常见问题</a></li><li><a href="?id=3">登录帐号有什么要求？</a></li><li><a href="?id=4">为什么注册不了会员帐户？</a></li>			</ul>
		</div>
		<div class="stm-article">
			<h4>客服中心</h4>
			<div class="stm-article-content">
				<a href="" class="kf-btn kf-online">咨询在线客服</a>
				<a href="" class="kf-btn kf-tutorial">视频教程</a>
				<a href="" class="kf-btn kf-problem">常见问题</a>
			</div>
		</div>
		<div class="stm-article">
			<h4>帮助中心</h4>
			<ul class="stm-article-list">
				<li><a href="?id=1">爱医康会员注册流程</a></li><li><a href="?id=2">会员注册常见问题</a></li><li><a href="?id=3">登录帐号有什么要求？</a></li><li><a href="?id=4">为什么注册不了会员帐户？</a></li>			</ul>
		</div>
		<div class="stm-article artNoBottom" style="height:345px;">
			<h4>意见反馈</h4>
			<div class="stm-article-content">
				欢迎您给我们提出宝贵的意见：<br /><a href="">点此反馈建议</a>
			</div>
		</div>
	</div>
	<div class="site-main">
		<div class="stm-title mTop30">
			<h2>证件维护</h2>
		</div>
		<ul class="stm-cert mTop20">
			<li>
				<span class="rFloat gray">有 <u class="red">0</u> 本证过期</span>
				生产厂家营业执照
			</li>
			<li>
				<span class="rFloat gray">有 <u class="red">1</u> 本证过期</span>
				生产厂家营业执照
			</li>
			<li>
				<span class="rFloat gray">有 <u class="red">4</u> 本证过期</span>
				生产厂家营业执照
			</li>
		</ul>
		<div class="stm-title mTop30"><h2>消息通知</h2></div>
		<div class="msgBox mTop20">
  		<div class="msg {movetag:'span',showtag:'.msgnBox .tagNav',addsclass:'msgnSelect',addtclass:'msgSelect',time:9999999}"> 
			<span>证件维护消息</span>
			<span>医院通知</span>
        </div>
        <div class="msgnBox">
			<div class="tagNav">
				<ul class="stm-msg-list">					<li class=" noline">
						<div class="safe-logo lFloat"><img src="" /></div>
						<div class="stm-msg-text">
							<p>你司医疗器械注册证：国食药监械(准)字2014第3630253号已过期，请及时处理。</p>
							<div class="stm-msg-act">
								<span class="rFloat"><a href="#">立即处理&nbsp;&nbsp;&nbsp;&nbsp;</a><a href="#">查看详细(1212)</a>&nbsp;&nbsp;&nbsp;&nbsp;仍有过期内容</span>
								42分钟前&nbsp;&nbsp;&nbsp;&nbsp;来自<a href="#">浙二医院</a>
							</div>
						</div>
					</li>
										<li class="bg">
						<div class="safe-logo lFloat"><img src="" /></div>
						<div class="stm-msg-text">
							<p>你司医疗器械注册证：国食药监械(准)字2014第3630253号已过期，请及时处理。</p>
							<div class="stm-msg-act">
								<span class="rFloat"><a href="#">立即处理&nbsp;&nbsp;&nbsp;&nbsp;</a><a href="#">查看详细(1212)</a>&nbsp;&nbsp;&nbsp;&nbsp;仍有过期内容</span>
								42分钟前&nbsp;&nbsp;&nbsp;&nbsp;来自<a href="#">浙二医院</a>
							</div>
						</div>
					</li>
										<li class="">
						<div class="safe-logo lFloat"><img src="" /></div>
						<div class="stm-msg-text">
							<p>你司医疗器械注册证：国食药监械(准)字2014第3630253号已过期，请及时处理。</p>
							<div class="stm-msg-act">
								<span class="rFloat"><a href="#">立即处理&nbsp;&nbsp;&nbsp;&nbsp;</a><a href="#">查看详细(1212)</a>&nbsp;&nbsp;&nbsp;&nbsp;仍有过期内容</span>
								42分钟前&nbsp;&nbsp;&nbsp;&nbsp;来自<a href="#">浙二医院</a>
							</div>
						</div>
					</li>
									</ul>
			</div>
			<div class="tagNav">
				<ul class="stm-msg-list">
										<li>
						<div class="safe-logo lFloat"><img src="" /></div>
						<div class="stm-msg-text">
							<p>关于医药领域商业贿赂新规通知：各供应商：为配合浙江省卫生和计划生育委员会关于落实 医药购销领域商业贿赂...</p>
							<div class="stm-msg-act">
								<span class="rFloat"><a href="#">查看详细(1212)</a></span>
								42分钟前&nbsp;&nbsp;&nbsp;&nbsp;来自<a href="#">浙二医院</a>
							</div>
						</div>
					</li>
				</ul>
			</div>

        </div>
    </div>
	</div>
</div>
<div class="clear"></div>
<div class="screen-width">
	<div class="site-footer">
		<a href="#">法律声明</a>&nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;<a href="#">服务条款</a>&nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;<a href="#">隐私声明</a>&nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;<a href="#">关于我们</a>&nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;<a href="#">新闻动态</a>&nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;<a href="#">人才招聘</a>
		<p>Copyright &copy; 2011-2014 aek56.com 版权所有 ICP证：浙ICP备13028265号</p>
	</div>
</div>
</body>
</html>