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
<%@ include file="inc.jsp" %>
</head>

<body>
	<div class="content-bd">
		<div class="t-box">
			<div class="t-box-hd">爱医康注册</div>
			<div class="t-box-bd t-a-c f14">
				<div class="flowPath-mod w710 m0a">
					<ul class="flowPath">
						<li class="flowPath-item cur">
							<div class="flowPath-item-img img1"></div>
							<div class="flowPath-item-text">验证身份信息</div>
						</li>
						<li class="flowPath-item flowPath-line"></li>
						<li  class="flowPath-item">
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
							<li><a href="gys.jsp">供应商注册<div class="arrow"></div></a></li>
						</ul>
					</div>
					<div class="tabs-bd">
						<ul>
							<li>
								<!-- form -->
								<form id="reg-form" action="/31401041" method="post">
									<div class="form-item">
										<div class="form-item-left"><em>*</em>医院全称：</div>
										<div class="form-item-right">
											<div class="preloader-mod input-text arrow">
												<input name="f02_qyqc" id="f02_qyqc" class="text" style="width:275px;" type="text" placeholder="请输入供医院全称"/>
												<span class="arrow-ico"></span>
											</div>
										</div>
									</div>
									<input type="hidden" name="eb5" id="eb5" value="2"/>
									<input type="hidden" name="puk" id="puk" value=""/>
									<div class="form-bottom">
										<input type="submit" value="下一步" class="button blue-buttom" />
									</div>
								</form>
								<!-- end form -->
							</li>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>