<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>注册 - 爱医康医用耗材信息服务平台</title>
<meta http-equiv="keywords" content="爱医康,供应商登录,医院登录" />
<meta http-equiv="description" content="爱医康平台账户登录页面。" />
<link rel="icon" href="skin/logo.ico" type="image/x-icon" />
<link rel="shortcut icon" href="skin/logo.ico" type="image/x-icon" />
<link rel="stylesheet" type="text/css" href="skin/default/css/base.css">
<link rel="stylesheet" type="text/css" href="skin/default/css/reg.css">
<script type="text/javascript" src="skin/default/js/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="skin/default/js/jq.ui.js"></script>
<script type="text/javascript" src="skin/default/js/jquery.autocompleter.js"></script>
<script type="text/javascript" src="skin/default/js/placeholder.js"></script>
<script type="text/javascript" src="skin/default/js/reg.js"></script>
</head>
<body>
	<div class="content-bd">
		<div class="t-box">
			<div class="t-box-hd">爱医康注册</div>
			<div class="t-box-bd t-a-c f14">
				<div class="flowPath-mod w710 m0a">
					<ul class="flowPath">
						<li class="flowPath-item">
							<div class="flowPath-item-img img1"></div>
							<div class="flowPath-item-text">验证身份信息</div>
						</li>
						<li class="flowPath-item flowPath-line"></li>
						<li  class="flowPath-item cur">
							<div class="flowPath-item-img img2"></div>
							<div class="flowPath-item-text">验证账号信息</div>
						</li>
						<li class="flowPath-item flowPath-line"></li>
						<li  class="flowPath-item">
							<div class="flowPath-item-img img-suc"></div>
							<div class="flowPath-item-text">注册成功</div>
						</li>
					</ul>
				</div>
				<div class="tabs-mod tabs-max-2 w710 m0a">
					<div class="tabs-hd">
						<ul>
							<li class="selected">医院注册<div class="arrow"></div></li>
							<li><a href="/resources/jsp/main/gys.jsp">供应商注册<div class="arrow"></div></a></li>
						</ul>
					</div>
					<div class="tabs-bd">
						<ul>
							<li>
								<form id="reg-form" action="/31401043" method="post">
									<div class="form-item">
										<div class="form-item-left"><em>*</em>登录账号：</div>
										<div class="form-item-right">
											<div class="input-text">
												<input type="text" name="fb5" id="fb5" class="text w230" />
											</div>
										</div>
									</div>
									<div class="form-item">
										<div class="form-item-left"><em>*</em>登录密码：</div>
										<div class="form-item-right">
											<div class="input-text">
												<input type="password" name="fb4" id="fb4" class="text w230" />
											</div>
										</div>
										<div class="qzr-mod clear inline-block" data-type="r">
											<div class="qzr-r">弱</div>
											<div class="qzr-z">中</div>
											<div class="qzr-q">强</div>
										</div>
									</div>
									<div class="form-item">
										<div class="form-item-left"><em>*</em>再次确认：</div>
										<div class="form-item-right">
											<div class="input-text">
												<input type="password" name="refb4" id="refb4" class="text w230" />
											</div>
										</div>
									</div>
									<div class="form-item">
										<div class="form-item-left"><em>*</em>真实姓名：</div>
										<div class="form-item-right">
											<div class="input-text">
												<input type="text" name="fb1" id="fb1" class="text w230" />
											</div>
										</div>
									</div>
									<div class="form-item">
										<div class="form-item-left"><em>*</em>您的手机号码：</div>
										<div class="form-item-right">
											<div class="input-text fl-l">
												<input type="text" name="fb3" id="fb3" class="text w230" />
											</div>
											<div class="inline-block fl-l ml10">
											<input type="button" style="width:135px;" class="button gray-button vm" value="免费获取验证码(60)" />
											<span class="f12">您还可以：<a href="javascript:;" class="blurHoverLine">使用邮箱验证》</a></span>
											</div>
										</div>
									</div>
									<div class="form-item">
										<div class="form-item-left"><em>*</em>验证码：</div>
										<div class="form-item-right">
											<div class="input-text">
												<input type="text" name="yzm" id="yzm" class="text w230" />
											</div>
										</div>
									</div>
									<div class="form-bottom">
										<input type="submit" value="下一步" class="button blue-buttom" />
									</div>
									<input type="hidden" name="f02_qyqc" id="f02_qyqc" value="${UserData.f02_qyqc}"/>
									<input type="hidden" name="f01_qyjc" id="f01_qyjc" value="${UserData.f01_qyjc}"/>
									<input type="hidden" name="f13_szs" id="f13_szs" value="${UserData.f13_szs}"/>
									<input type="hidden" name="f14_szs" id="f14_szs" value="${UserData.f14_szs}"/>
									<input type="hidden" name="f15_szq" id="f15_szq" value="${UserData.f15_szq}"/>
									<input type="hidden" name="f16_szxxdz" id="f16_szxxdz" value="${UserData.f16_szxxdz}"/>
									
									<input type="hidden" name="f08_frxm" id="f08_frxm" value="${UserData.f08_frxm}"/>
									<input type="hidden" name="f18_lxdh" id="f18_lxdh" value="${UserData.f18_lxdh}"/>
									
									<input type="hidden" name="eb5" id="eb5" value="${UserData.eb5}"/>
									<input type="hidden" name="puk" id="puk" value="${UserData.puk}"/>
								</form>
							</li>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>