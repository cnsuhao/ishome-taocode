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

<%@ include file="/resources/jsp/main/inc.jsp" %> 
<script type="text/javascript">
	$(function(){
		$('#slide').smallslider({
			onImageStop:true,
			textPosition:'bottom',
			buttonPosition:'centerBottom',
			buttonSpace : 10,
			buttonOffsetY : 20,
			showText: false
		});
	})
</script>
</head>

<body>
<%@ include file="/resources/jsp/main/top.jsp" %> 
<div class="slide" id="slide">
	<ul>
		<li><div class="img" style="background: url(/resources/skin/main/images/home/slider_1.jpg) center top no-repeat;">
			<div class="screen-width pos-relative"><a href="/account/register.php" class="pos-absolute" style="width:112px; height:34px; top:330px; left:20px; display:block;"></a></div>
		</div></li>
		<li><div class="img" style="background: url(/resources/skin/main/images/home/slider_2.jpg) center top no-repeat;">
			<div class="screen-width pos-relative"><a href="#more" class="pos-absolute" style="width:112px; height:34px; top:330px; left:20px; display:block;"></a></div>
		</div></li>
		<li><div class="img" style="background: url(/resources/skin/main/images/home/slider_3.jpg) center top no-repeat;"></div></li>
		<li><div class="img" style="background: url(/resources/skin/main/images/home/slider_4.jpg) center top no-repeat;"></div></li>
	</ul>
</div>
<div class="main screen-width">
	<div class="hos-app">
		<span class="hos-app-icon"></span>
		通过爱医康强大的信息数据中心，将医院三证现有纸质资料全部转化为电子资料；
	</div>
	<div class="sup-app">
		<span class="sup-app-icon"></span>
		提供快速、便捷的三证信息短信预警；
	</div>
	<div class="public-mobile">
		<span class="public-mobile-icon"></span>
		提供按耗材名称、供应商名称、证件名称等检索服务；
	</div>
	<div class="public-yun">
		<span class="public-yun-icon"></span>
		云服务数据处理中心，安全稳定，解决您的后顾之忧；
	</div>
	<div class="clear"></div>
</div>
 <%@ include file="/resources/jsp/main/bottom.jsp" %> 
</body>
</html>