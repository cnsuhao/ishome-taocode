<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/taglib/pagetag.tld"  prefix="p" %>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
	<meta charset="utf-8">
	<title>添加经销授权书</title>
<%@ include file="/resources/jsp/gys/inc.jsp" %>
<link rel="stylesheet" type="text/css" href="/resources/css/base.css">
<link rel="stylesheet" type="text/css" href="/resources/css/jquery.mCustomScrollbar.css">
<link rel="stylesheet" type="text/css" href="/resources/css/hospital_cert.css">
<script src="/resources/js/jquery.js"></script>
<script src="/resources/js/jquery.mousewheel.js"></script>
<script src="/resources/js/jquery.mCustomScrollbar.js"></script>
<script src="/resources/js/jq.ui.js"></script>
<script type="text/javascript">
$(function(){
	$(".control").jdMarquee({
		deriction: "up",
		width: 220,
		height: 385,
		step: 4,
		speed: 8,
		delay: 10,
		control: true,
		_front: ".yuiNext",
		_back: ".yuiPrve"
	});
});

function leftRotate(rot){
	rot -= 90;
	if(rot === -90) rot = 270;
	$("#yui-max-img").rotate(rot);
}

function rightRotate(rot){
	rot += 90;
	$("#yui-max-img").rotate(rot);
	if(rot === 270) rot = -90;
}

function zoomMax(){
	var img = $("#yui-max-img"), w = img.width()<=100?100:img.width()+100, h = img.height() <=100 ? 100 : img.height()+100;
	img.width(w).height(h);
}

function zoomMin(){
	var img = $("#yui-max-img"), w = img.width()<=100?100:img.width()-100, h = img.height() <=100 ? 100 : img.height()-100;
	img.width(w).height(h);
}

function zoom(){
	$("#yui-max-img").width(800).height(500);
}
</script>
</head>
<body>
<div class="certPicBox">
	<div class="certPicNavBox">
		<a href="javascript:zoomMax();" class="certPicBtn zoomMax">放大</a>
		<a href="javascript:zoomMin();" class="certPicBtn zoomMin">放小</a>
		<a href="javascript:zoom();" class="certPicBtn zoom">原始大小</a>
		<a href="javascript:leftRotate(0);" class="certPicBtn leftRotate">左转</a>
		<a href="javascript:rightRotate(0);" class="certPicBtn rightRotate">右转</a>
	</div>
	<div class="certPicContentBox">
		<div class="certPicRight rFloat">
			<div class="contnet"><h3 class="font14">${gysxx }</h3>
			<p class="gray font14" align="center">${typeName }</p></div>
			<div class="control">
				<a href="javascript:void(0);" class="yuiPrve"></a>
				<a href="javascript:void(0);" class="yuiNext"></a>
				<ul class="clearfix">
					<c:forEach items="${imgpaths}" var="dbo" varStatus="status">
                    <li><img src="<%=ImagePath %>/00003030/${dbo.bbb }/${loginer.token}2323" data-ext="${dbo.eb1 }" data-time="${dbo.f04_yxksrq }至${dbo.f05_yxzzrq }" /></li>
                    </c:forEach>
				</ul>
			</div>
		</div>
		<div class="certPicLeft relative">
			<div id="yui-max-pic" class="switchImg"><img alt="" id="yui-max-img" width="800" height="500" src="/resources/images/noImg.png" onerror="/resources/images/noImg.png" /></div>
			<div class="switch">
				<a id="foward">&lt;</a>
				<a id="next">&gt;</a>
			</div>
		</div>
	</div>
	<div class="certPicNavBox">
		有效期：<span id="yui-cert-time"></span>
	</div>
</div>
</body>
</html>