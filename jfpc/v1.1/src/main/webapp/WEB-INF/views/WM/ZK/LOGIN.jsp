<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>login</title>

    <!-- Bootstrap core CSS -->
    <link href="/resources/css/wm/zk/bootstrap.min.css" rel="stylesheet" media="screen">
	<script src="/resources/js/wm/zk/jquery.js"></script>
    <script src="/resources/js/wm/zk/bootstrap.min.js"></script>

    <!-- 主要样式表 -->
	<link href="/resources/css/wm/zk/global.css" rel="stylesheet" media="screen">
	<link href="/resources/css/wm/zk/signin.css" rel="stylesheet" media="screen">

	<script type="text/javascript">
    function body_onload(){
    	if(parent.blankForm == undefined){
    		//当前画面为父窗口
    	}else{
    		//当前画面为子窗口
    		parent.location="/WMHOME/"+ parent.blankForm.eb5.value +"/QT.go";
    	}
    }
	</script>
<%
	String ip = request.getHeader("x-forwarded-for");
	if (ip == null || ip.length() == 0
			|| "unknown".equalsIgnoreCase(ip)) {
		ip = request.getHeader("Proxy-Client-IP");
	}
	if (ip == null || ip.length() == 0
			|| "unknown".equalsIgnoreCase(ip)) {
		ip = request.getHeader("WL-Proxy-Client-IP");
	}
	if (ip == null || ip.length() == 0
			|| "unknown".equalsIgnoreCase(ip)) {
		ip = request.getRemoteAddr();
	}
%>
  </head>
  <body onload="body_onload()">
	<table align="center" width="100%" style="margin:0; padding:0;" border="0">
		<tr>
			<td valign="top">
				<table align="center" width="100%" border="0">
					<tr height="100" style="background:#0067a3;">
						<td width="100" valign="top" ></td>
						<td style="color:#fff; font-size:30px;">贷付宝</td>
					</tr>
				</table>
			</td>
		</tr>		
		<tr height="360" style="background:url(/resources/img/bg.jpg);">
			<td>
				<table align="right" width="260" border="0">
					<tr>
						<td>
							<div class="content">
								<div class="main">
									<div class="main-one">
										<div class="container">
											<form class="form-signin" action="/WMLOGIN/HOME.go" method="post" id="form2" name="form2">
												<h2 class="form-signin-heading">控制中心</h2>
												<span class="help-block">${message}</span>
												<input type="text" class="form-control" name="f03" placeholder="用户名" autofocus>
												<input type="password" class="form-control" name="f04" placeholder="密码">
												<!-- 
												<label class="checkbox">
													<input type="checkbox" value="remember-me">记住账号
												</label>
												 -->
												<button class="btn btn-lg btn-primary btn-block" type="submit">登陆</button>												
											<input type="hidden" name="eb5" id="eb5" value="${usertoken}"> 
											<input type="hidden" name="f02" id="f02" value="WM.ZK">
											<input type="hidden" name="fb2" id="fb2" value="<%=ip%>"> 
											</form>
										</div>
									</div>
								</div>
							</div>
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr><td></td></tr>
		<tr>
			<td>
				<table align="center" width="95%" border="0" style="height:50px; border-top:1px solid #DBDBDB;">
					<tr>
						<td align="center">
							<a href="http://www.daifuboo.com/" target="_blank">技术支持贷付宝</a> 
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>

  </body>
</html>
