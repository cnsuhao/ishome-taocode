<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<!DOCTYPE html>
<html lang="zh-cn">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>index</title>

    <%@ include file="/resources/jsp/themeCSS.jsp" %> 
    
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
          <a class="navbar-brand navbar_fontcolor" href="#">Project name</a>
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
            <li class="active"><a href="#">控制台</a></li>
            <li><a href="#">文字排版</a></li>
            <li><a href="#">ui组件</a></li>
            <li><a href="#">表格</a></li>
          </ul>
          <ul class="nav nav-sidebar">
            <li><a href="#">文字排版</a></li>
            <li><a href="#">ui组件</a></li>
            <li><a href="#">表格</a></li>
          </ul>
          <ul class="nav nav-sidebar">
            <li><a href="#">文字排版</a></li>
            <li><a href="#">ui组件</a></li>
            <li><a href="#">表格</a></li>
          </ul>
        </div>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
          <h5 class="page-header"><span class="glyphicon glyphicon-home"></span>&nbsp;首页&nbsp;>&nbsp;控制台</h5>
		  <p class="page_p">Widget&nbsp;>>&nbsp;Daggabble Widget Boxes</p>
		  <div class="row">
			<div class="col-sm-6">
			  <div class="panel panel_none panel-default">
				<div class="panel-heading">
				  <h3 class="panel-title">Panel title</h3>
				</div>
				<div class="panel-body">
				  <p class="panel_p">This example is a quick exercise to illustrate how the default, static and fixed to top navbar work. This example is a quick exercise to illustrate how the default, static and fixed to top navbar work. </p>
				  <p class="panel_p">This example is a quick exercise to illustrate how the default, static and fixed to top navbar work. </p>
				</div>
			  </div>
			  <div class="panel panel_none panel-primary">
				<div class="panel-heading">
				  <h3 class="panel-title">Panel title</h3>
				</div>
				<div class="panel-body">
				  sss
				</div>
			  </div>
			</div>
			<div class="col-sm-6">
			  <div class="panel panel_none panel-success">
				<div class="panel-heading">
				  <h3 class="panel-title">Panel title</h3>
				</div>
				<div class="panel-body">
				  <div class="table-responsive">
					<table class="table table-striped">
					  <thead>
						<tr>
						  <th><span class="glyphicon glyphicon-user">user</th>
						  <th>@name</th>
						  <th>statue</th>
						</tr>
					  </thead>
					  <tbody>
						<tr>
						  <td>1,001</td>
						  <td>Lorem</td>
						  <td><span class="label label-default">Default</span></td>
						</tr>
						<tr>
						  <td>1,002</td>
						  <td>amet</td>
						  <td><span class="label label-primary">Primary</span></td>
						</tr>
						<tr>
						  <td>1,003</td>
						  <td>Integer</td>
						  <td><span class="label label-success">Success</span></td>
						</tr>
						<tr>
						  <td>1,003</td>
						  <td>libero</td>
						  <td><span class="label label-info">Info</span></td>
						</tr>
						<tr>
						  <td>1,003</td>
						  <td>libero</td>
						  <td><span class="label label-warning">Warning</span></td>
						</tr>
						<tr>
						  <td>1,003</td>
						  <td>libero</td>
						  <td><span class="label label-danger">Danger</span></td>
						</tr>
					  </tbody>
					</table>
				  </div>
				</div>
			  </div>
			  <div class="panel panel_none panel-info">
				<div class="panel-heading">
				  <h3 class="panel-title">Panel title</h3>
				</div>
				<div class="panel-body">
				  Panel content
				</div>
			  </div>
			</div>
			
		  </div>
		  
         </div>
        </div>
    </div>
	
	
    <%@ include file="/resources/jsp/themeJS.jsp" %>
  </body>
</html>