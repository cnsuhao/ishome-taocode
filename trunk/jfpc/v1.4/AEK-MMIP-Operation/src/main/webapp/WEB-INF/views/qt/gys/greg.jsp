<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" import="org.jfpc.framework.helper.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>注册 - 爱医康医用耗材信息服务平台</title>
<meta http-equiv="keywords" content="爱医康,供应商登录,医院登录" />
<meta http-equiv="description" content="爱医康平台账户登录页面。" />
<%@ include file="/resources/jsp/main/inc.jsp" %>
<link rel="stylesheet" type="text/css" href="/resources/skin/main/css/reg.css">
<script type="text/javascript" src="/resources/skin/main/js/reg.js"></script>
<script type="text/javascript" src="/resources/skin/main/js/function.js"></script>
<script type="text/javascript">
$(function(){
	resetText();
	$(".text").input();
	new inputTips("#eb1","/2001030/11",2,"#k01_ssqyid",330);
})
function checkStrength(val){
	var sClass = "s0";
	if (!val.isEmpty()){
		var S_level=checkStrong(val);
		switch(S_level){
			case 1:
				sClass="s1";
			break;
			case 2:
				sClass="s2";
			break;
			case 3:
				sClass="s3";
			break;
			default:
				sClass="s1";
		}
	}
	$(".strength").find("span").removeClass().addClass(sClass);
	return;
}

function fSubmit(){
	var myForm = $("#reg-form").serializeJSON();
	if (myForm['eb1'].isEmpty()) {
		$("#eb1").errMsg('医院名称不能为空！');
		return false;
	}
	if (myForm['k03_dlyhm'].isEmpty()) {
		$("#k03_dlyhm").errMsg('登录帐号不能为空！');
		return false;
	}
	if (myForm['k03_dlyhm'].length < 6) {
		$("#k03_dlyhm").errMsg('请输入6位以上的帐号！');
		return false;
	}
	if (myForm['k04_dlmm'].isEmpty()) {
		$("#k04_dlmm").errMsg('登录密码不能为空！');
		return false;
	}
	if (myForm['k04_dlmm'].length < 6) {
		$("#k04_dlmm").errMsg('请输入6位以上的登录密码！');
		return false;
	}
	if($("#k04_dlmm").val() != $("#rek04_dlmm").val()){
		alert("请保证两次输入相同！");
		return false;
	}
	if (myForm['f01_yhxm'].isEmpty()) {
		$("#f01_yhxm").errMsg('真实姓名不能为空！');
		return false;
	}
	if (myForm['fb3'].isEmpty()) {
		$("#fb3").errMsg('手机号码不能为空！');
		return false;
	}
	if (!myForm['fb3'].isTelephone()) {
		$("#fb3").errMsg('请输入正确的手机号码！');
		return false;
	}

	if (myForm['verCode'].isEmpty()) {
		$("#verCode").errMsg('请输入验证码！');
		return false;
	}
	return true;
}

function changCode(src){
	$("#vcode").attr("src",src+"?t="+Math.random());
} 
</script>
</head>
<body>
	<!-- content -->
	<div class="content-bd">
		<div class="t-box">
			<div class="t-box-hd">爱医康注册</div>
			<div class="t-box-bd t-a-c f14">
				<div class="tabs-mod tabs-max-2 w710 m0a">
					<div class="tabs-hd">
						<ul>
							<li><a href="/3002000">医院注册<div class="arrow"></div></a></li>
							<li class="selected">供应商注册<div class="arrow"></div></li>
						</ul>
					</div>
					<div class="tabs-bd">
						<ul>
							<li>
								<form id="reg-form" action="/3001001" method="post" onsubmit="return fSubmit();">								
									<div class="form-item">
										<div class="form-item-left"><em>*</em>供应商名称：</div>
										<div class="form-item-right">
											<input name="eb1" id="eb1" arrow="true" class="text {text:'请输入供应商名称'}" size="60" type="text" />
										</div>
									</div>
									<div class="form-item">
										<div class="form-item-left"><em>*</em>登录账号：</div>
										<div class="form-item-right">
											<div class="input-text">
												<input type="text" name="k03_dlyhm" id="k03_dlyhm" class="text w230" value="${UserData.k03_dlyhm}"/>
											</div>
										</div>
										<div class="inline-block fl-l ml10">
										${page_message}
										</div>
									</div>
									<div class="form-item">
										<div class="form-item-left"><em>*</em>登录密码：</div>
										<div class="form-item-right">
											<div class="input-text">
												<input type="password" name="k04_dlmm" id="k04_dlmm" class="text w230" onkeyup="checkStrength(this.value);" onBlur="checkStrength(this.value);" />
											</div>
											<div class="strength" style="width:230px;"><span class="s0"></span></div>
										</div>
									</div>
									<div class="form-item">
										<div class="form-item-left"><em>*</em>再次确认：</div>
										<div class="form-item-right">
											<div class="input-text">
												<input type="password" name="rek04_dlmm" id="rek04_dlmm" class="text w230" />
											</div>
										</div>
									</div>
									<div class="form-item">
										<div class="form-item-left"><em>*</em>真实姓名：</div>
										<div class="form-item-right">
											<div class="input-text">
												<input type="text" name="f01_yhxm" id="f01_yhxm" class="text w230" value="${UserData.f01_yhxm}"/>
											</div>
										</div>
									</div>
									<div class="form-item">
										<div class="form-item-left"><em>*</em>您的手机号码：</div>
										<div class="form-item-right">
											<div class="input-text fl-l">
												<input type="text" name="fb3" id="fb3" class="text w230" value="${UserData.fb3}"/>
											</div>
											<div class="inline-block fl-l ml10">
											<!-- <input type="button" style="width:135px;" class="button gray-button vm" value="免费获取验证码(60)" />
											<span class="f12">您还可以：<a href="javascript:;" class="blurHoverLine">使用邮箱验证</a></span> -->
											</div>
										</div>
									</div>
									<div class="form-item">
										<div class="form-item-left"><em>*</em>固定电话：</div>
										<div class="form-item-right">
											<div class="input-text">
												<input type="text" name="fb2" id="fb2" class="text w230" value="${UserData.fb2}"/>
											</div>
										</div>
									</div>
									<div class="form-item">
										<div class="form-item-left"><em>*</em>验证码：</div>
										<div class="form-item-right">
											<div class="input-text">
												<input type="text" name="verCode" id="verCode" class="text w120" placeholder="输入验证码" tips="请输入4位验证码。"/>
											</div>
										</div>
										<img src="/00003030?t=<%=DateHelper.currentTime1()%>" onclick="changCode(this.src);" id="vcode" align="middle" />
									</div>
									<div class="form-bottom">
										<input type="submit" value="完成注册" class="button blue-buttom" />
									</div>
									<input type="hidden" name="f02_yhlb" id="f02_yhlb" value="1/>
									<input type="hidden" name="k01_ssqyid" id="k01_ssqyid" value="${UserData.puk}"/>
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