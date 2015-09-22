<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <title>贷付宝</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- Bootstrap -->
	<link href="/resources/css/wm/qt/global.css" rel="stylesheet" media="screen">	
	<script src="/resources/js/wm/qt/jquery.js"></script>
    <script src="/resources/js/wm/qt/bootstrap.min.js"></script>
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
  <table align="center" width="100%" border="0">
		<tr>
			<td height="20"  style="background-color:#F8F8F8;" >
  			</td>
		</tr>
  		<tr>
  			<td>
  	<table align="center" width="100%" border="0">
		<tr height="80" style="background-color:#F8F8F8; border-bottom: 1px solid #bbb; margin-bottom: 20px;">
			<td>
				<table width="95%" border="0">
					<tr>
						<td height="80" width="80">&nbsp;</td>
						<td>
							<img src="/resources/img/logo.png" class="logo-img"/>
						</td>
					</tr>
					<tr>
						<td colspan="2">&nbsp;</td>
					</tr>
				</table>
			</td>
		</tr>
  		<tr>						
		<tr style="background:url(/resources/img/bg.jpg);">
			<td valign="top">
				<table align="right" border="0" style="margin-top:10px;">
					<tr>
						<td width="360" valign="top" style="padding-bottom:10px;">
							<div class="content-login login-div">
								<p class="content-login-p">登录到您的帐户</p>
								<div class="content-login-content">
																		
									<form  action="/WMLOGIN/HOME.go" method="post" id="form2" name="form2">
									
									<table width="100%" border="0">
									 <c:if test="${message!=null}">
									  <tr height="28">
									    <td><span class="help-block">${message} </span></td>
									  </tr>
									  </c:if>
									  <tr height="28">
									    <td>用户名：</td>
									  </tr>
									  <tr height="28">
									    <td><input type="text" class="input-large" name="f03" placeholder="请输入用户名…"></td>
									  </tr>
									  <tr height="28">
									    <td>密码：</td>
									  </tr>
									  <tr height="28">
									    <td><input type="password" class="input-large" name="f04" placeholder="请输入密码…"></td>
									  </tr>
									  <tr height="28">
									    <td><button type="submit" class="btn btn-large btn-info">登陆</button></td>
									  </tr>
									</table>
										
										<input type="hidden" name="eb5" id="eb5" value="${usertoken}"> 
										<input type="hidden" name="f02" id="f02" value="WM.QT">
										<input type="hidden" name="fb2" id="fb2" value="<%=ip%>"> 
									</form>
								</div>
							</div>
						</td>
						<td width="100">
							&nbsp;
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
	</table></td>
					</tr>
				</table>
  </body>
</html>