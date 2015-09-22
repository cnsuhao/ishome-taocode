<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" import="org.jfpc.framework.helper.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>账号登录 - 爱医康医用耗材信息服务平台</title>
<meta http-equiv="keywords" content="爱医康,供应商登录,医院登录" />
<meta http-equiv="description" content="爱医康平台账户登录页面。" />
<meta name="copyright" content="© 爱医康医用耗材信息服务平台 v0.1.4.20141024-SNAPSHOT" />

	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=9; IE=8; IE=7; IE=EDGE"> 
	<title>登陆</title>
	<link rel="stylesheet" href="/resources/css/base.css"/>
	<link rel="stylesheet" href="/resources/css/reception.css"/>
	<style>
		body{
			background: transparent;
		}
	</style>



<script type="text/javascript">
function changCode(obj){
	$("img", obj).attr("src","/00003031/92/35?t="+Math.random());
} 
</script>
</head>

<body>
	<div class="content">
		<div class="container">
			<div class="wireBox">
				<div class="wireBoxHd">
				 	登陆
				</div>
				<div class="wireBoxBd">
					<form id="login-form"  action="/00000000" method="post">
						<ul class="ulFrm">
							<li>
								<div class="inputGroup">
									<div class="wireIcon"></div><input id="un" vd-key="username" type="text" class="text {text:'输入用户名'}" name="userName"/>
								</div>
							</li>
							<li>
								<div class="inputGroup">
									<div class="wireIcon"></div><input id="pw" vd-key="password" type="password" class="text {text:'输入密码'}" name="passWord"/>
								</div>
							</li>
							<li id='vCode-bg' style="display:none;">
								<span class="label labelW100 labelRight" style="width:60px">验证码：</span>
								<input type="text" class="text" id="vCodeInp" name="" style="width:100px;" />
								<span class="vCode">
									<img id="vCode-img"  class="vCode-r" src="" data-src=""/>
									<a href="javascript:;" class="vCode-r">
										看不清<br>换一张
									</a>
								</span>
							</li>
							<li>
								<div id="yui-error-msg" class="errorMsg" style="width:280px; display:none;">
									<span class="statusIcon err"></span> 出现错误
								</div>
							</li>
							<li><input type="submit" style="width:100%" class="btn" value="登陆" /></li>
							<li class="textRight"><a class="help" href="javascript:;">忘记密码？</a></li>
						</ul>
						
  <!-- 登录地址 -->
  <input type="hidden" name="loginUrl"  id="loginUrl" value="/3140000" />         
  <!-- 回调地址 -->
  <input type="hidden" name="callBackUrl"  id="callBackUrl" value="/3000000" />

  <!-- 产品ID -->
  <input type="hidden" name="productId" id="productId"/>
  <input type="hidden" name="companyId" id="companyId"/>
  <input type="hidden" name="userType" id="userType"/>
  <input type="hidden" name="token" id="token" />						
					</form>
				</div>
			</div>
		</div>
	</div>
	<script src="../js/jquery.js"></script>
	<script src="../js/jq.ui.js"></script>
	<script src="../js/validate.js"></script>
	<script>
	// ready
	$(function(){
		resetText();
		$('.text').input();
		var $pw = $('#pw');
		//login
		$("#login-form").on('submit', function(){
			var $this = $(this),
				vData = $this.validateForm({id:'#yui-error-msg'});

			if(vData.allSuccess){
				return false;
			}

			var url = $this.attr('action'),
				data = $this.serialize();
			ajax(url, data, function(res){
				if(res.code == 0){
					post();
				}if(res.code == 1){
					$vBg.show().find('#vCodeInp').attr('vd-key', 'code');
					$pw.val("").focus();
					$("#yui-error-msg").show().html(res.message);
				}else{
					$("#yui-error-msg").show().html(res.message);
				}
			});
			return false;
		}).blurVD({id:'#yui-error-msg'});
		// 验证码
		var $vImg = $("#vCode-img"),
			vSrc = $vImg.data('src'),
			$vBg =$("#vCode-bg");
		$vBg.on('click', '.vCode-r', function(){
			$vImg.attr('src' , vSrc + '?t=' + new Date().getTime());
		});
	});
	</script>
</body>
</html>