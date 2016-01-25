<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>贷付宝</title>
    <!-- Bootstrap -->
    <link href="/resources/css/bootstrap.min.css" rel="stylesheet" media="screen">
	<link href="/resources/css/global.css" rel="stylesheet" media="screen">	
	<script src="/resources/js/jquery.js"></script>
    <script src="/resources/js/bootstrap.min.js"></script>
</head>

<body>
<table align="center" width="95%" height="480"  border="10">
	<tr>
		<td width="100">
			<div class="sidebar">
								<div class="sidebar-wrap">
									<ul class="nav">
										<li class="on dropdown-submenu">
											<a tabindex="-1" class="nav-main" href="#">我的账户</a>
											<ul class="dropdown-menu">
												<li><a class="nav-main-a" href="http://www.163.com" target="BizFrame">充值</a></li>
												<li><a class="nav-main-a" href="">提现</a></li>
												<li><a class="nav-main-a" href="">优惠券</a></li>
												<li><a class="nav-main-a" href="">银行账户</a></li>
											</ul>
										</li>
										<li class="on dropdown-submenu">
											<a class="nav-main" href="">交易查询</a>
											<ul class="dropdown-menu">
												<li><a class="nav-main-a" href="">交易管理</a></li>
												<li><a class="nav-main-a" href="">收支明细</a></li>
												<li><a class="nav-main-a" href="">充值记录</a></li>
												<li><a class="nav-main-a" href="">提现记录</a></li>
												<li><a class="nav-main-a" href="">佣金记录</a></li>
											</ul>
										</li>
										<li class="on dropdown-submenu">
											<a class="nav-main" href="">下线会员</a>
											<ul class="dropdown-menu">
												<li><a class="nav-main-a" href="">充值</a></li>
												<li><a class="nav-main-a" href="">提现</a></li>
												<li><a class="nav-main-a" href="">优惠券</a></li>
												<li><a class="nav-main-a" href="">银行账户</a></li>
											</ul>
										</li>
										<li class="on dropdown-submenu">
											<a class="nav-main" href="">安全中心</a>
											<ul class="dropdown-menu">
												<li><a class="nav-main-a" href="">充值</a></li>
												<li><a class="nav-main-a" href="">提现</a></li>
												<li><a class="nav-main-a" href="">优惠券</a></li>
												<li><a class="nav-main-a" href="">银行账户</a></li>
											</ul>
										</li>
									</ul>
								</div>
							</div>
		</td>
		<td width="10">&nbsp;</td>
		<td align="left" valign="top" >
<iframe src="javascript:'';"  width="100%" height="100%" frameborder="no" border="0" marginwidth="0" marginheight="0" scrolling="no" allowtransparency="yes" id="BizFrame" name="BizFrame"	></iframe></td>
	</tr>
</table>
</body>
</html>

<!-- 
  <frameset cols="0,*" border="10" framespacing="10">
		<frame src="/INDEX/BLANK.go" name="blankFrame" scrolling="no" noresize title="blankFrame">
		<frame src="/INDEX/BLANK.go" name="mainFrame" id="mainFrame" title="mainFrame" />
	</frameset><body>
</body></noframes>
</html>
 -->