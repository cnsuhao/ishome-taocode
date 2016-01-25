<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/lib/menutag.tld" prefix="menus" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <title>贷付宝</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- Bootstrap -->
	<link href="/resources/css/wm/qt/bootstrap.min.css" rel="stylesheet" media="screen">
	<link href="/resources/css/wm/qt/global.css" rel="stylesheet" media="screen">	
	<script src="/resources/js/wm/qt/jquery.js"></script>
    <script src="/resources/js/wm/qt/bootstrap.min.js"></script>
    <script type="text/javascript">
	function showForm(ulid,urlpath){
		if(urlpath=='') return;
		document.blankForm.action=urlpath;
		blankForm.submit();
		onout(ulid);
	}

	//画面给予系统框架迁移
	//actionpath 当前操作跳转地址
	//returnpath 跳转后页面回调地址
	function toTargetForm(actionpath,returnpath){
		document.blankForm.pageid.value=returnpath;
		document.blankForm.action=actionpath;
		blankForm.submit();
	}	
	
	function onover(obj){
       document.getElementById(obj).style.display='block';
    }

    function onout(obj){
        document.getElementById(obj).style.display='none';
    }
    
    function body_onload(){
        showForm('blankForm','/WMMN/F.go');
        resizeIFrame();
    }
    
    function resizeIFrame(){
    	var screen_width = window.screen.width ;
    	var screen_heigh = window.screen.height ;
    	var cur_height = 436;
    	if(screen_width == 1024 && screen_heigh==768){
    		cur_height = 436;
    	}else if(screen_width == 1152 && screen_heigh==864){
    		cur_height = 516;
    	}else if(screen_width == 1280 && screen_heigh==720){
    		cur_height = 380;
    	}else if(screen_width == 1280 && screen_heigh==768){
    		cur_height = 436;
    	}else if(screen_width == 1280 && screen_heigh==800){
    		cur_height = 460;
    	}else if(screen_width == 1280 && screen_heigh==960){
    		cur_height = 610;
    	}else if(screen_width == 1280 && screen_heigh==1024){
    		cur_height = 690;
    	}else if(screen_width == 1360 && screen_heigh==768){
    		cur_height = 436;
    	}else if(screen_width == 1366 && screen_heigh==768){
    		cur_height = 436;
    	}else if(screen_width == 1600 && screen_heigh==900){
    		cur_height = 560;
    	}else if(screen_width == 1600 && screen_heigh==1024){
    		cur_height = 690;
    	}else if(screen_width == 1600 && screen_heigh==1200){
    		cur_height = 740;
    	}else if(screen_width == 1600 && screen_heigh==1050){
    		cur_height = 718;
    	}else if(screen_width == 1920 && screen_heigh==1080){
    		cur_height = 708;
    	}else if(screen_width == 1920 && screen_heigh==1200){
    		cur_height = 708;
    	}
    	main_iframe.height = cur_height;
    }
    
	</script>

  </head>
  <body onload="body_onload()">
  <form action="" id="blankForm" name="blankForm" target="BizFrame" method="post">  												
	<input type="hidden" name="LoginUrl" id="LoginUrl" value="${LoginUrl}">
	<input type="hidden" name="eb5" id="eb5" value="${eb5}">
	<input type="hidden" name="pageid" id="pageid" value="WM.QT">	
	<input type="hidden" name="listParams" id="listParams" value="">	
  </form>
	<table width="100%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0">
		<tr height="29" style="background-color:#009CCE;">
			<td>
				<table align="center" width="92%" border="0">
					<tr>
						<td>&nbsp;<div id="blankDiv" name="blankDiv">&nbsp;</div></td>
						<td width="120"><a style="color:#fff;" href="#">欢迎您 ${k01}</a></td>
						<td align="center" width="120"><a style="color:#fff;" href="javascript:showForm('blankDiv','/CSSM01/F.go')"">公告</a></td>
						<td align="right" width="60"><a style="color:#fff;" href="/WMHOME/${k02}/QT.go" onclick="showForm('blankDiv','/WMHOME/${k02}/QT.go')">退出</a></td>
					</tr>
				</table>
			</td>
		</tr>
		<tr height="100" style="background-color:#F8F8F8; border-bottom: 1px solid #bbb; ">
			<td>
				<table align="center" width="100%" border="0">
					<tr>
						<td width="4%" align="left"></td>
						<td width="380">
							<img src="/resources/img/logo.png" class="logo" />
						</td>
						<td align="right">
							<div class="mainnav">
								<div class="mainnav-wrap">
									<nav>
										<ul class="mainnav-link">
											<li><a class="nav-main" href="#" onclick="showForm('blankDiv','/WMMN/F.go')">首页</a></li>
										 	<li><a class="nav-youhui" href="#" onclick="showForm('blankDiv','/WMZHM1/F.go')">通道</a></li>
										 	<c:if test="${vip=='DFB_QT_VIP_1'}">
										 	<li><a class="nav-youhui" href="#" onclick="showForm('blankDiv','/WMBM09/H.go')">开户</a></li>
										 	</c:if>
										 	<li><a class="nav-safe" href="#" title ="即将上线">余额宝</a></li>
										 	<li><a class="nav-safe" href="#" title ="即将上线">积分商城</a></li>
									 		<li><a class="nav-bank" href="#" title ="即将上线">公益捐款</a></li>
										</ul>
									</nav>
								</div>
							</div>
						</td>
						<td width="4%" align="left"></td>
					</tr>
				</table>
			</td>
		</tr>
		<tr height="8">
			<td>
			</td>
		</tr>
		<tr>
			<td valign="top">
				<table align="center" width="100%" border="0">
					<tr>
					<td width="2%" align="left"></td>
						<td width="150" valign="top" style="background:#F8F8F8; border-top:1px solid #D8D8D8; border-left:1px solid #D8D8D8; border-right:1px solid #D8D8D8; border-bottom:1px solid #D8D8D8;">	                
	                  <menus:menus menuVO="${myrool}" lgstyle="1" />
						</td>
						<td width="10" >&nbsp;</td>
						<td id="main_iframe" valign="top" bgcolor="#FFFFFF" >
<iframe src="javascript:'';"  width="100%" height="100%" frameborder="no" border="0" marginwidth="0" marginheight="0" allowtransparency="yes" id="BizFrame" name="BizFrame"></iframe>
						</td>
						<td width="2%" align="left"></td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td valign="middle">
				<table align="center" width="100%" border="0" >
					<tr>
						<td align="center" valign="middle">
							<a href="http://www.daifuboo.com/" target="_blank">技术支持贷付宝</a> 
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
</body>
</html>
