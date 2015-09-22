<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="zh-cn">
  <head>
    <meta charset="utf-8">
    <title>首页</title>

    <%@ include file="/resources/jsp/themeCSS.jsp" %> 
    <link href="/resources/css/home0.css" rel="stylesheet">
	<style>
		.color_navbar{background:rgba(4, 125, 173, 1);}
		.navbar_fontcolor{color:#fff;}
		.navbar_fontcolor:hover{color:#fff;}
		.navbar_bg{color:#fff;}
		.icon_1{float:left; width:25%; line-height:40px; text-align:center; cursor:pointer; background:#ddd;}
		.icon_1:hover{background:#fff;}
		.icon_totle{width:100%; height:40px; margin-bottom:10px;}
		.panel_none{border-radius:0px;}
		.panel_p{background:rgba(218, 255, 233, 1); padding:10px; }
	</style>
  </head>
  <body>
	<!-- 头部 -->
	<div class="navbar color_navbar navbar-fixed-top" role="navigation">
      <div class="container-fluid">
        <div class="navbar-header">
          <a class="navbar-brand navbar_fontcolor" href="#">中国医疗材料信息平台</a>
        </div>
        <div class="navbar-collapse collapse">
          <ul class="nav navbar-nav navbar-right">
            <li><a href="#" class="navbar_bg"><span class="glyphicon glyphicon-th-list"></span></a></li>
            <li><a href="#" class="navbar_bg"><span class="glyphicon glyphicon-bell"></a></li>
            <li><a href="#" class="navbar_bg"><span class="glyphicon glyphicon-envelope"></a></li>
          </ul>
        </div>
      </div>
    </div>
    
	<div class="container-fluid">
      <div class="row">
        <div class="col-sm-3 col-md-2 sidebar">
		  <div class="icon_totle">
			<div class="icon_1 icon_active"><span class="glyphicon glyphicon-signal"></span></div>
			<div class="icon_1"><span class="glyphicon glyphicon-pencil"></span></div>
			<div class="icon_1"><span class="glyphicon glyphicon-user"></span></div>
			<div class="icon_1"><span class="glyphicon glyphicon-cog"></span></div>
		  </div>
          <ul class="nav nav-sidebar">
            <li class="active"><a href="#">三证管理</a></li>
            <li><a href="#">文字排版</a></li>
            <li><a href="#">ui组件</a></li>
            <li><a href="#">表格</a></li>
          </ul>
          <ul class="nav nav-sidebar">
            <li class="active"><a href="#">三证管理</a></li>
            <li><a href="#">文字排版</a></li>
            <li><a href="#">ui组件</a></li>
            <li><a href="#">表格</a></li>
          </ul>
          <ul class="nav nav-sidebar">
            <li class="active"><a href="#">三证管理</a></li>
            <li><a href="#">文字排版</a></li>
            <li><a href="#">ui组件</a></li>
            <li><a href="#">表格</a></li>
          </ul>
        </div>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
          <div class="col-xs-6 col-md-10 ">
	          <h5 class="page-header"><span class="glyphicon glyphicon-home"></span>&nbsp;首页&nbsp;>&nbsp;三证管理</h5>
	          
         </div>
	          <div class="alert alert-success" role="alert">
				  <a href="#" class="alert-link">...</a>
			  </div>
		 <div class="embed-responsive embed-responsive-4by3" style="height:525px" >
		  <iframe class="embed-responsive-item" style="height:525px" src="http://www.163.com"></iframe>
		 </div>
        </div>
      </div>
    </div>
	
	
    <%@ include file="/resources/jsp/themeJS.jsp" %>
  </body>
</html>