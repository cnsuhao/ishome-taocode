<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" import="org.jfpc.framework.helper.*" %>
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
<link rel="stylesheet" type="text/css" href="/resources/css/jquery.mCustomScrollbar.css">
<link rel="stylesheet" type="text/css" href="/resources/css/hospital_cert.css">
<style type="text/css">
.lInput{text-align: left;}
.textareaRadius{width: 308px;}
</style>
<script src="/resources/js/jquery.js"></script>
<script src="/resources/js/jquery.mousewheel.js"></script>
<script src="/resources/js/jquery.mCustomScrollbar.js"></script>
<script src="/resources/js/function.js"></script>
<script src="/resources/js/jq.ui.js"></script>
<script>
$(function(){
	resetText();
});
</script>
</head>
<body class="whiteBg">
<div class="formTitle mTop10" align="center">
	${UserData.f01_zczzwmc }&nbsp;&nbsp;&nbsp;&nbsp;<span class="font12 gray">REG,NO：${UserData.f02_zczywmc }</span>
</div>
<ul class="ulFrmTwo">
	<li>
		<span class="label labelW100 labelRight">产品名称：</span>
		<input type="text" class="text WT300" disabled="disabled" value="${UserData.f03_cpzwmc }" />
	</li>
	<li>
		<span class="label labelW120 labelRight">有效期：</span>
		<input type="text" class="text" disabled="disabled" value="${UserData.f09_yxksrq }" size="14" wdate="true" /> 至 <input type="text" class="text" disabled="disabled" value="${UserData.f10_yxzzrq }" size="14" wdate="true" />
	</li>
	<li>
		<span class="label labelW100 labelRight">商品英文名称：</span>
		<input type="text" class="text WT300" disabled="disabled" value="${UserData.f04_cpywmc }" />
	</li>
	<li>
		<span class="label labelW120 labelRight">生产者名称：</span>
		<input type="text" class="text WT300" disabled="disabled" value="${UserData.f32_scqyzwmc }" />
	</li>
	<li>
		<span class="label labelW100 labelRight">生产者地址：</span>
		<input type="text" class="text WT300" disabled="disabled" value="${UserData.f33_scqydz }" />
	</li>
<%-- 	<li>
		<span class="label labelW120 labelRight">生产场所地址：</span>
		<input type="text" class="text WT300" disabled="disabled" value="${UserData.f32_scqyzwmc }" />
	</li> --%>
<!-- 	<li>
		<span class="label labelW100 labelRight">规格型号：</span>
		<input type="text" class="text WT300" disabled="disabled" value="见附页" />
	</li> -->
	<li>
		<span class="label labelW120 labelRight">产品标准：</span>
		<input type="text" class="text WT300" disabled="disabled" value="${UserData.f17_cpzxbzbh }" />
	</li>
	<li>
		<span class="label labelW100 labelRight" style="vertical-align:top;">产品适用范围：</span>
		<textarea class="textarea" disabled="disabled" rows="4" />${UserData.f19_cpsyfw }</textarea>
	</li>
	<li>
		<span class="label labelW120 labelRight" style="vertical-align:top;">产品性能结构及组成：</span>
		<textarea class="textarea" disabled="disabled" rows="4" />${UserData.f18_cpxnjgjzc }</textarea>
	</li>
	<li>
		<span class="label labelW100 labelRight">注册代理：</span>
		<input type="text" class="text WT300" disabled="disabled" value="${UserData.f23_zcdljgmc}" />
	</li>
	<li>
		<span class="label labelW120 labelRight">售后服务机构：</span>
		<input type="text" class="text WT300" disabled="disabled" value="${UserData.f25_shfwjgmc}" />
	</li>
	<li>
		<span class="label labelW100 labelRight" style="vertical-align:top;">备注：</span>
		<textarea class="textarea" disabled="disabled" rows="4" />${UserData.f30_bz}</textarea>
	</li>
</ul>
</body>
</html>