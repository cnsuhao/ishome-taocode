<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>找回密码 - 爱医康医用耗材信息服务平台</title>
<meta http-equiv="keywords" content="爱医康,供应商登录,医院登录" />
<meta http-equiv="description" content="爱医康平台账户登录页面。" />
<%@ include file="/resources/jsp/main/inc.jsp" %>
</head>

<body>
	<div class="content-bd">
		<div class="t-box find-pwd">
			<div class="t-box-hd">密码找回</div>
			<div class="t-box-bd t-a-c f14">
				<div class="flowPath-mod m0a" style="width:736px;">
					<ul class="flowPath">
						<li class="flowPath-item">
							<div class="flowPath-item-img img1"></div>
							<div class="flowPath-item-text">输入账号</div>
						</li>
						<li class="flowPath-line"></li>
						<li  class="flowPath-item cur">
							<div class="flowPath-item-img img2"></div>
							<div class="flowPath-item-text">验证身份</div>
						</li>
						<li class="flowPath-line"></li>
						<li  class="flowPath-item">
							<div class="flowPath-item-img img3"></div>
							<div class="flowPath-item-text">重置密码</div>
						</li>
						<li class="flowPath-line"></li>
						<li  class="flowPath-item">
							<div class="flowPath-item-img img-suc"></div>
							<div class="flowPath-item-text">找回成功</div>
						</li>
					</ul>
				</div>
				<div class="w710 m0a relative">
				<!-- form -->
				<form id="reg-form" action="/31401033" method="post">
					<div class="form-item">
						<div class="form-item-left">选择验证方式：</div>
						<div class="form-item-right">
							<select class="select w120">
								<option></option>
							</select>
							<br/>
							<span class="gray f12 ml10">
								你的手机：158****2717，若当前号码已不用或丢失，或无法收到验证码？请联系<a src="javascript:;" class="blue hoverLine">在线客服</a>
							</span>
						</div>
					</div>
					<div class="form-item">
						<div class="form-item-left">验证码：</div>
						<div class="form-item-right">
							<input type="button" class="button gray-button" value="点此免费获取验证码(60)" />
							<span class="hide">
								<div class="input-text">
									<input class="text w120" name="eb5" id="eb5" type="text" />
								</div>
								<a class="ml10 blue hoverLine" href="javascript:;">没有收到短信验证码？</a>
								<br>
								<span class="msg-ico success vb"></span><span class="gray f12">验证码已发送到您的手机，30分钟内输入有效，请勿泄露。</span>
							</span>
						</div>
					</div>
					<div class="form-bottom">
						<input type="submit" value="下一步" class="button blue-buttom" />
					</div>
				</form>
				<div class="pop-mod hide" style="left: 282px;top: -24px;">
					<ul class="listdisc">
						<li>网络通讯异常可能照成短信丢失，请重新获取或稍后再试<br/>
							<input type="button" class="button gray-button" value="重新获取验证码" />
						</li>
						<li>请核实手机是否已欠费停机，或屏蔽的系统短信。</li>
						<li>如果手机156****0210已丢失或停用，请选择<a class="blue hoverLine">其他验证方式</a></li>
					</ul>
					<div class="arrow"></div>
				</div>
				<input type="hidden" name="puk" id="puk" value="${UserData.puk}"/>
				<input type="hidden" name="fb3" id="fb3" value="${UserData.fb3}"/>
				<!-- end form -->
				</div>
			</div>
		</div>
	</div>
</body>
</html>