<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<title></title>
<meta name="description" content="">
<meta name="keywords" content="">
<link rel="stylesheet" type="text/css" href="/resources/css/base.css">
<link rel="stylesheet" href="/resources/css/jquery.mCustomScrollbar.css">
<script src="/resources/js/jquery.js"></script>
<script src="/resources/js/jquery.mousewheel.js"></script>
<script src="/resources/js/jquery.mCustomScrollbar.js"></script>
<script src="/resources/js/jq.ui.js"></script>
<script src="/resources/js/function.js"></script>
<%@ include file="/resources/jsp/ImagePath.jsp"%>
<script>
$(function(){
	$("body").mCustomScrollbar({theme:"minimal"});
	$('.checkbox').checkbox();
});
// 
function changePar(that, name){
	var data = {};
	data[name] = $(that).prev().prop('checked') ? 0 : 1;
	ajax({url:'/30001001', data:data, callBack:function(res){
		top.msgText(res.message,true);
	},load:false});
};
</script>
</head>
<body>
<div class="contnet">
    <div class="boxContent">
		<div class="formTitle">消息</div>
			<ul class="ulFrm">
				<li>
					<input class="checkbox" type="checkbox" 
					<c:if test="${UserData.f01 == '1'}">
					checked
					</c:if>
					/><label onclick="changePar(this, 'f01')">内部消息</label>
				</li>
				<li>
					<input class="checkbox" type="checkbox" 
					<c:if test="${UserData.f02 == '1'}">
					checked
					</c:if>
					/><label onclick="changePar(this, 'f02')">系统公告</label>
				</li>
				<li>
					<input class="checkbox" type="checkbox" 
					<c:if test="${UserData.f03 == '1'}">
					checked
					</c:if>
					/><label onclick="changePar(this, 'f03')">用户邀请 - 新用户添加自动审核</label>
				</li>
				<li>
					<input class="checkbox" type="checkbox" 
					<c:if test="${UserData.f04 == '1'}">
					checked
					</c:if>
					/><label onclick="changePar(this, 'f04')">订单消息</label>
				</li>
			</ul>
		<div class="formTitle mTop10">审核</div>
			<ul class="ulFrm">
				<li>
					<input class="checkbox" type="checkbox" 
					<c:if test="${UserData.f11 == '1'}">
					checked
					</c:if>
					/><label onclick="changePar(this, 'f11')">注册证更新 - 供应商更换注册证时候，系统自动审核</label>
				</li>
				<li>
					<input class="checkbox" type="checkbox" 
					<c:if test="${UserData.f12 == '1'}">
					checked
					</c:if>
					/><label onclick="changePar(this, 'f12')">主数据同步 - 平台数据字典更新时候，系统自动同步</label>
				</li>
			</ul>
		</div>
	</div>
</div>
</body>
</html>