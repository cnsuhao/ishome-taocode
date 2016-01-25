<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/lib/menutag.tld" prefix="menus" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>index</title>

    <!-- Bootstrap core CSS -->
  
    <link href="/resources/css/wm/zk/bootstrap.css" rel="stylesheet">

    <!-- 主要样式表 -->
   
    <link href="/resources/css/wm/zk/global.css" rel="stylesheet">	 
    <link href="/resources/css/wm/zk/sticky-footer-navbar.css" rel="stylesheet">
    
	<script src="/resources/js/wm/zk/jquery.js"></script>
    <script src="/resources/js/wm/zk/bootstrap.min.js"></script>
	
	<!--一级菜单-->
	<script src="/resources/js/wm/zk/topmenu.js"></script>	 
     
  </head>
	<script type="text/javascript">
	function showForm(ulid,urlpath){
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
        showForm('blankForm','/WMKZ/H.go');
        resizeIFrame();
    }
    
    function resizeIFrame(){
    	var screen_width = window.screen.width ;
    	var screen_heigh = window.screen.height ;
    	var cur_height = 436;
    	if(screen_width == 1024 && screen_heigh==768){
    		cur_height = 466;
    	}else if(screen_width == 1152 && screen_heigh==864){
    		cur_height = 546;
    	}else if(screen_width == 1280 && screen_heigh==720){
    		cur_height = 410;
    	}else if(screen_width == 1280 && screen_heigh==768){
    		cur_height = 466;
    	}else if(screen_width == 1280 && screen_heigh==800){
    		cur_height = 490;
    	}else if(screen_width == 1280 && screen_heigh==960){
    		cur_height = 640;
    	}else if(screen_width == 1280 && screen_heigh==1024){
    		cur_height = 710;
    	}else if(screen_width == 1360 && screen_heigh==768){
    		cur_height = 466;
    	}else if(screen_width == 1366 && screen_heigh==768){
    		cur_height = 466;
    	}else if(screen_width == 1600 && screen_heigh==900){
    		cur_height = 590;
    	}else if(screen_width == 1600 && screen_heigh==1024){
    		cur_height = 720;
    	}else if(screen_width == 1600 && screen_heigh==1200){
    		cur_height = 770;
    	}else if(screen_width == 1600 && screen_heigh==1050){
    		cur_height = 748;
    	}else if(screen_width == 1920 && screen_heigh==1080){
    		cur_height = 738;
    	}else if(screen_width == 1920 && screen_heigh==1200){
    		cur_height = 738;
    	}
    	main_iframe.height = cur_height;
    }
    </script>
  <body class="body" onload="body_onload()">
  <form action="" id="blankForm" name="blankForm" target="BizFrame" method="post">  												
	<input type="hidden" name="LoginUrl" id="LoginUrl" value="${LoginUrl}">
	<input type="hidden" name="eb5" id="eb5" value="${eb5}">
	<input type="hidden" name="pageid" id="pageid" value="WM.ZK">	
	<input type="hidden" name="listParams" id="listParams" value="">
  </form>
	<table align="center" width="100%" style="margin:0; padding:0;" border="0">
		<tr height="110">
			<td valign="top">
				<table align="center" width="100%" border="0">
					<tr height="70" style="background:#0067a3;">
						<td width="10%" valign="top" ></td>
						<td width="80%" valign="top">
							<table align="center" width="100%" border="0">
								<tr height="70">
									<td width="30%" style="color:#fff; font-size:30px;">
										<!-- <img src="img/"/> -->财富管家<span style="color:#fff; font-size:14px;">会管理会生活</span>
									</td>
									<td >
										<table align="right" width="100%" border="0">
											<tr height="45">
												<td align="right">
													<div class="title-text"><a>欢迎您 ${k01}</a><a>消息（5条）</a><a href="/WMHOME/${k02}/ZK.go" onclick="showForm('blankDiv','/WMHOME/${k02}/ZK.go')">退出</a></div>
												</td>
											</tr>
											<tr height="30">
												<td>
                                             <menus:menus menuVO="${mytopmenu}"  lgstyle="4" />	
												<!--																										
													<div class="head">
														<div class="header_zzjs">
															<ul>
																<li onmouseover="javascript:toggle_nav(1)"><a href="">公益捐款</a></li>
																<li onmouseover="javascript:toggle_nav(2)"><a href="">云端应用</a></li>
																<li onmouseover="javascript:toggle_nav(3)"><a href="">商户黑名单</a></li>
																<li onmouseover="javascript:toggle_nav(4)"><a href="">系统监控</a></li>
																<li onmouseover="javascript:toggle_nav(5)"><a href="">分站管理</a></li>
																<li onmouseover="javascript:toggle_nav(6)"><a href="">安全中心</a></li>
																<li onmouseover="javascript:toggle_nav(7)"><a href="">首页</a></li>
															</ul>
														</div>
													</div>
													-->
												</td>
											</tr>
										</table>
									</td>
								</tr>
							</table>
						</td>
						<td width="10%" valign="top"></td>
					</tr>
					<tr height="40" style="border-bottom:1px solid #B9B9B9;">
						<td width="10%" valign="top"></td>
						<td width="80%" valign="top">
							<table align="center" width="100%" border="0">
								<tr height="40">
									<td width="30%">
										<span>位置：<a href="#">首页</a></span>
									</td>
									<td id="secondlevel">
									 <!--
										<div class="clear"></div>
										<div id="zzjs_nav0" class="headt" style="display:block">欢迎信息</div>
										<div id="zzjs_nav1" class="headt" style="display:none">
											<a href="">二级菜单</a> | <a href="">二级菜单</a> | <a href="">二级菜单</a> | <a href="">二级菜单</a>
										</div>
										<div id="zzjs_nav2" class="headt" style="display:none">
											<a href="">二级菜单</a> | <a href="">二级菜单</a> | <a href="">二级菜单</a> | <a href="">二级菜单</a>
										</div>
										<div id="zzjs_nav3" class="headt" style="display:none">
											<a href="">二级菜单</a> | <a href="">二级菜单</a> | <a href="">二级菜单</a>
										</div>
										<div id="zzjs_nav4" class="headt" style="display:none">
											<a href="">二级菜单</a> | <a href="">二级菜单</a>
										</div>
										<div id="zzjs_nav5" class="headt" style="display:none">
											<a href="">二级菜单</a> | <a href="">二级菜单</a> | <a href="">二级菜单</a> | <a href="">二级菜单</a>
										</div>
										<div id="zzjs_nav6" class="headt" style="display:none">
											<a href="">二级菜单</a> | <a href="">二级菜单</a> | <a href="">二级菜单</a> | <a href="">二级菜单</a> | <a href="">二级菜单</a> | <a href="">二级菜单</a> | <a href="">二级菜单</a>
										</div>
										<div id="zzjs_nav7" class="headt" style="display:none">
											<a href="">二级菜单</a> | <a href="">二级菜单</a> | <a href="">二级菜单</a> | <a href="">二级菜单</a>
										</div>
										-->
									</td>
								</tr>
							</table>
						</td>
						<td width="10%" valign="top"></td>
					</tr>
				</table>
			</td>
		</tr>
		
		<tr height="" style="background:#f6f6f6;">
		<td valign="top" id="main_iframe">
				<iframe src="javascript:'';"  width="100%" height="100%" frameborder="no" border="0" marginwidth="0" marginheight="0" allowtransparency="yes" id="BizFrame" name="BizFrame"></iframe>
			</td>
		</tr>
		
		<tr>
			<td>
				<table align="center" width="100%" border="0" height="50">
					<tr>
						<td width="10%" valign="top"></td>
						<td width="80%" align="center" style="color:#000; background:#fff;">
							<a class="footer-a" href="" target="_blank">关于财富管家</a>  |  <a class="footer-a" href="" target="_blank">导航</a>  |  <a class="footer-a" href="" target="_blank">诚征英才</a>  |  <a class="footer-a" href="" target="_blank">联系我们</a>  |  <a class="footer-a" href=" " target="_blank">International Business</a>  |  <a class="footer-a" href="" target="_blank">Copyright©2005-</a><span class="footer-a">2013</span><a class="footer-a" href="" target="_blank">***公司 版权所有</a>
						</td>
						<td width="10%" valign="top"></td>
					</tr>
				</table>
			</td>
		</tr>
	</table>

<script type="text/javascript">
       document.getElementById("secondlevel").innerHTML=levelhtml ;
       
</script>
    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
   
  </body>
</html>
