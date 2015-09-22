<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>logon</title>

    <!-- Bootstrap core CSS -->
    <!-- Bootstrap core CSS -->
	<script src="/resources/js/wm/ht/jquery.js"></script>
    <script src="/resources/js/wm/ht/bootstrap.min.js"></script>

    <!-- 主要样式表 -->
	<link href="/resources/css/wm/ht/global.css" rel="stylesheet" media="screen">
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

  <body class="body">
  <table align="center" width="100%"  border="0">
    <tr height="75">
      <td valign="top">
      </td>
    </tr>
    <tr height="400">
      <td valign="center">
        <table border="0" style="" align="center">
          <tr>
            <td>
              <table width="100%" border="0">
                <tr style="background:#eee;">
					<td width="400" ><div><img src="/resources/img/logo.png" style="padding-left:40px; width:400px;"/></div>
					</td>
					<td>
                        <div class="login-div-middle">
                            <p class="login-div-content"><b>管理后台</b></p>
						</div>  
					</td>
				</tr>
				<tr style="background:#eee; border-bottom:1px solid #ddd;">
					<td colspan="2" valign="top">
					  <form  action="/WMLOGIN/HOME.go" method="post" id="form2" name="form2">					
					    <table align="center" width="100%" border="0" style="margin-bottom:20px;">						
					      <tr height="50">
					        <td colspan="3"><span class="help-block">${message}</span></td>
					        </tr>
					      <tr height="50">
					        <td align="right"><label for="inputEmail3" class="login-label">用户名</label></td>
					        <td width="5"></td>
					        <td width="360"><input type="text" class="form-control" id="f03" name="f03" placeholder="username"></td>
					        </tr>
					      <tr height="50">
					        <td align="right"><label for="inputPassword3" class="login-label">密码</label></td>
					        <td></td>
					        <td><input type="password" class="form-control" id="f04" name="f04" placeholder="Password"></td>
					        </tr>
					      <tr height="50">
					        <td align="center" colspan="3">
					          <button type="submit" class="btn btn-primary btn-lg">登陆</button>
					          <button type="button" class="btn btn-link">注册</button>
					          </td>
					        </tr>
					      </table>
					    <input type="hidden" name="eb5" id="eb5" value="${usertoken}"> 
					    <input type="hidden" name="f02" id="f02" value="WM.HT"> 
					    <input type="hidden" name="fb2" id="fb2" value="<%=ip%>">
				      </form>
				    </td>
				</tr>
              </table>
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
