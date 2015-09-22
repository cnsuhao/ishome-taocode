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
    <link href="/resources/css/wm/ht/bootstrap.min.css" rel="stylesheet" media="screen">
	<script src="/resources/js/wm/ht/jquery.js"></script>
    <script src="/resources/js/wm/ht/bootstrap.min.js"></script>

    <!-- 主要样式表 -->
	<link href="/resources/css/wm/ht/global.css" rel="stylesheet" media="screen">
	
    <!-- 左侧菜单js -->
    <script type="text/javascript" src="/resources/js/wm/ht/leftmenu.js"></script>

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
        showForm('blankForm','/WMGL/H.go');
        resizeIFrame();
    }
    
    function resizeIFrame(){
    	var screen_width = window.screen.width ;
    	var screen_heigh = window.screen.height ;
    	var cur_height = 260;
    	if(screen_width == 1024 && screen_heigh==768){
    		cur_height = 150;
    	}else if(screen_width == 1152 && screen_heigh==864){
    		cur_height = 150;
    	}else if(screen_width == 1280 && screen_heigh==720){
    		cur_height = 150;
    	}else if(screen_width == 1280 && screen_heigh==768){
    		cur_height = 150;
    	}else if(screen_width == 1280 && screen_heigh==800){
    		cur_height = 150;
    	}else if(screen_width == 1280 && screen_heigh==960){
    		cur_height = 150;
    	}else if(screen_width == 1280 && screen_heigh==1024){
    		cur_height = 150;
    	}else if(screen_width == 1360 && screen_heigh==768){
    		cur_height = 150;
    	}else if(screen_width == 1366 && screen_heigh==768){
    		cur_height = 150;
    	}else if(screen_width == 1600 && screen_heigh==900){
    		cur_height = 150;
    	}else if(screen_width == 1600 && screen_heigh==1024){
    		cur_height = 150;
    	}else if(screen_width == 1600 && screen_heigh==1200){
    		cur_height = 150;
    	}else if(screen_width == 1600 && screen_heigh==1050){
    		cur_height = 150;
    	}else if(screen_width == 1920 && screen_heigh==1080){
    		cur_height = 260;
    	}else if(screen_width == 1920 && screen_heigh==1200){
    		cur_height = 150;
    	}
    	main_iframe.height = screen_heigh - cur_height;
    }
    
	</script>

  </head>
  <body class="body" onload="body_onload()">
  <form action="" id="blankForm" name="blankForm" target="BizFrame" method="post">  												
	<input type="hidden" name="LoginUrl" id="LoginUrl" value="${LoginUrl}">
	<input type="hidden" name="eb5" id="eb5" value="${eb5}">
	<input type="hidden" name="pageid" id="pageid" value="">	
	<input type="hidden" name="listParams" id="listParams" value="">
  </form>
  
<table width="100%" border="0">
  <tr>
    <td><table width="100%" border="0">
      <tr>
        <td  width="200" valign="top" ><table width="100%" border="0">
          <tr>
            <td><div class="logo logo-h"><span>管理后台</span></div></td>
          </tr>
          <tr>
            <td valign="top" ><menus:menus menuVO="${myrool}" lgstyle="2" /></td>
          </tr>
        </table>
    </td>
    <td><table width="100%" border="0">
         <tr height="60"><td bgcolor="#4D4D4A">
         <table align="right"  width="100%" border="0">
         	<tr>
         		<td width="10"></td>
         		<td><a href="#">欢迎您 ${k01}</a></td>
         		<td><a href="javascript:showForm('blankForm','/WMGL/H.go')">首页</a> </td>
         		
         		<td><a href="/WMHOME/${k02}/HT.go" onclick="showForm('blankDiv','/WMHOME/${k02}/HT.go')">退出</a></td>
         	</tr>
         </table>
		  </td>
        </tr>
        <tr>
          <td valign="top" bgcolor="#FFFFFF" id="main_iframe" >
      		<iframe src="javascript:'';"  width="100%" height="100%" frameborder="no" border="0" marginwidth="0" marginheight="0" allowtransparency="yes" id="BizFrame" name="BizFrame"></iframe></td>
        </tr>
        </table></td>
      </tr>
    </table>
    </td>
  </tr>
  <!-- 
  <tr>
    <td align="center"><a href="http://www.daifuboo.com/" target="_blank">技术支持贷付宝</a></td>
  </tr>
   -->
</table>
  </body>
</html>
