<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- 宝付借贷分离页面 -->

<title>充值页面</title>
<link href="/resources/css/wm/qt/bootstrap.min.css" rel="stylesheet"
	media="screen">
<link href="/resources/css/wm/qt/global.css" rel="stylesheet"
	media="screen">

<script type="text/javascript" src="/resources/js/wm/qt/jquery.js"></script>

<script src="/resources/js/wm/qt/bootstrap.js"></script>
<script type="text/javascript">
	function valForm() {
		var a = document.getElementById('channel').value;
		if (a == '0 0') {
			$("#channel_tips").css('visibility', 'visible');
			return false;
		}
		var f07 = document.getElementById('f07').value;
		if (f07 == '') {
			$("#f07_tips").css('visibility', 'visible');
			return false;
		}
		return true;
	}

	function bao() {
		var rs = document.getElementById("f07").value;
		var rs1 = document.getElementById("f02").value;
		if (rs1.indexOf("%") > 0) {
			rs1 = rs1.replace("%", "") * 0.01;
		}

		var total = 0;
		try {
			if (rs !== "" && rs1 != "")
				total = rs * rs1 * 1;
		} catch (e) {
		}
		document.getElementById("f07m").innerHTML = "充值手续费用: " + total + " 元";
	}

	function tdChanged(obj) {
		var array = new Array();
		array.push('bftd');
		array.push('rbtd');
		array.push('zftd');
		var key = (obj.options[obj.selectedIndex]).getAttribute('key');

		//根据通道显示银行
		if (key != 'bftd') {
			$("#card_type").css('visibility', 'hidden');
		} else {
			$("#card_type").css('visibility', 'visible');
		}
		for ( var i = 0; i < array.length; i++) {
			if (array[i] != key) {
				$("#" + array[i] + "_s").css('display', 'none');
			} else {
				$("#" + key + "_s").css('display', 'block');
			}

		}
		
		var value = obj.value;
		
		if(value =='0 0')return;
		var strs = value.split("|");
		document.getElementById("f01").value = strs[0];
		document.getElementById("f02").value = strs[1];

		document.getElementById("f02m").innerHTML = "手续费率: 【" + strs[1] + "】　"
				+ strs[2];
		if (document.getElementById("f07").value != "")
			bao();
	}

	function yhkChanged(value) {
		var strs = value.split("|");
		document.getElementById("f03").value = strs[0];
		document.getElementById("f04").value = strs[1];
		document.getElementById("f05").value = strs[2];
		document.getElementById("f05m").innerHTML = strs[2];
	}

	//选择卡来改变支付渠道
	function selectCard(value) {
		var arr = value.split('\|');
		var show = arr[0] + "_list";
		var hidd = arr[1] + "_list";
		$("#" + hidd).css("display", "none");
		$("#" + show).css("display", "block");
		document.getElementById('f04').value = document.getElementById(show).value;
	}

	//选择卡来改变支付渠道
	function resetCardValue(obj) {
		document.getElementById('f04').value = obj.value

	}
</script>
</head>
<body>
	<form id="form01" name="form01" method="post" action="/WMBM06/C.go"
		onsubmit="return valForm()">
		<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" style="margin: 0; padding: 0; border: 1px solid #DBDBDB;">

			<tr height="40"
				style="">
				<td style=" background: #f8f8f8; border-bottom:1px solid #dbdbdb;"><h4 style="margin-left: 10px; ">充值</h4></td>
			</tr>
			<tr
				style="border-left: 1px solid #DBDBDB; border-right: 1px solid #DBDBDB;">
				<td height="60"><table width="100%" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td width="28"></td>
							<td width="701" style="color: red; font-size: 12px;">${message}</td>
							<td width="838" align="right"><input name="Submit" type="submit"
								id="btnsave" value="确定提交" /></td>
							<td width="100"></td>
						</tr>
					</table></td>
			</tr>
			<tr
				style="border-bottom: 1px solid #DBDBDB; border-left: 1px solid #DBDBDB; border-right: 1px solid #DBDBDB;">
				<td valign="top">
					<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0"
						style="margin: 0; padding: 0;">

						<tr height="36">
							<td width="574" height="40" align="right"><b>支付通道</b></td>
							<td width="10"></td>
							<td width="828" align="left"><select onchange="tdChanged(this)"
								id="channel">
									<option value="0 0"></option>
									<c:forEach var="item" items="${listGrtd}">
										<option key="${item.k03}"
											value="${item.puk}|${item.f15}${item.f05}|${item.bbb}">${item.fb1}</option>
									</c:forEach>
							</select><span id='channel_tips'
								style="background-color: red; visibility: hidden">请选择支付通道</span>
							</td>
							<td width="103"></td>
							<td width="112"></td>
					  </tr>
						<tr height="36">
							<td height="40" align="right"></td>
							<td width="10"></td>
							<td align="left"><label name="f02m" id="f02m"></label></td>
							<td></td>
							<td></td>
					  </tr>
						<input type="hidden" name="f01" id="f01" value="通道ID">
						<input type="hidden" name="f02" id="f02" value="通道手续费率">
						<tr id="card_type" style="visibility:hidden" height="36">
							<td height="40" align="right"><b>卡类型</b></td>
							<td width="10"></td>
							<td align="left">信用卡<input type="radio" checked name="card"
								onclick="selectCard('CreditCard|DebitCard')" /> 借记卡<input
								type="radio" name="card"
								onclick="selectCard('DebitCard|CreditCard')" />


							</td>
							<td width="103"></td>
							<td></td>
					  </tr>


						<tr height="36">
							<td height="40" align="right"><b>银行名称</b></td>
							<td width="10"></td>
							<td align="left">


								<div id="bftd_s" style="display: none">
									<!-- 宝付信用卡 -->
									<select id="CreditCard_list" onchange="resetCardValue(this)"
										style="display: block">
										<option value="4001">招商银行</option>
										<option value="4002">工商银行</option>
										<option value="4003">建设银行</option>
										<option value="4004">浦发银行</option>
										<option value="4005">农业银行</option>
										<option value="4006">中国民生银行</option>
										<option value="4009">兴业银行</option>
										<option value="4020">中国交通银行</option>
										<option value="4022">中国光大银行</option>
										<option value="4026">中国银行</option>
										<option value="4032">北京银行</option>
										<option value="4033">BEA东亚银行</option>
										<option value="4034">渤海银行</option>
										<option value="4035">平安银行</option>
										<option value="4036">广发银行|CGB</option>
										<option value="4037">上海农商银行</option>
										<option value="4038">中国邮政储蓄银行(信用卡)</option>
		                                <option value="4039">中信银行(信用卡)</option>
		                                <option value="4059">上海银行(信用卡)</option>
									</select>

									<!-- 宝付借记卡 -->
									<select id="DebitCard_list" onchange="resetCardValue(this)"
										style="display: none">
										<option value="3001">招商银行(借记卡)</option>
										<option value="3002">工商银行(借记卡)</option>
										<option value="3003">建设银行(借记卡)</option>
										<option value="3004">浦发银行(借记卡)</option>
										<option value="3005">农业银行(借记卡)</option>
										<option value="3006">民生银行(借记卡)</option>
										<option value="3009">兴业银行(借记卡)</option>
										<option value="3020">交通银行(借记卡)</option>
										<option value="3022">光大银行(借记卡)</option>
										<option value="3026">中国银行(借记卡)</option>
										<option value="3032">北京银行(借记卡)</option>
										<option value="3035">平安银行(借记卡)</option>
										<option value="3036">广发银行|CGB(借记卡)</option>
										<option value="3038">中国邮政储蓄银行()</option>
										<option value="3039">中信银行(借记卡)</option>
										<option value="3059">上海银行(借记卡)</option>
									</select>
								</div>


								<div id="rbtd_s" style="display: none">
									<!-- 融宝支持银行卡 -->
									<select onchange="resetCardValue(this)">
										<option value="CMB">招商银行</option>
										<option value="ICBC">中国工商银行</option>
										<option value="CCB">中国建设银行</option>
										<option value="BOC">中国银行</option>
										<option value="ABC">中国农业银行</option>
										<option value="BOCM">交通银行</option>
										<option value="SPDB">浦发银行</option>
										<option value="GDB">广东发展银行</option>
										<option value="CITIC">中信银行</option>
										<option value="CEB">中国光大银行</option>
										<option value="CIB">兴业银行</option>
										<option value="SDB">平安银行</option>
										<option value="CMBC">中国民生银行</option>
										<option value="HXB">华夏银行</option>
										<option value="PSBC">中国邮政储蓄银行</option>
										<option value="BCCB">北京银行</option>
										<option value="SHBANK">上海银行</option>
									</select>

								</div>


								<div id='zftd_s' style="display: none">
									<!-- 智付支付银行卡  -->
									<select onchange="resetCardValue(this)">
										<option value="ABC">农业银行</option>
										<option value="ICBC">中国工商银行</option>
										<option value="CCB">中国建设银行</option>
										<option value="BOCM">交通银行</option>
										<option value="BOC">中国银行</option>
										<option value="CMB">招商银行</option>
										<option value="CMBC">中国民生银行</option>
										<option value="CEBB">中国光大银行</option>
										<option value="CIB">兴业银行</option>
										<option value="PSBC">中国邮政储蓄银行</option>
										<option value="SPABANK">平安银行</option>
										<option value="ECITIC">中信银行</option>
										<option value="GDB">广东发展银行</option>
										<option value="SDB">深圳发展银行</option>
										<option value="HXB">华夏银行</option>
										<option value="SPDB">浦发银行</option>
										<option value="BEA">东亚银行</option>
																				<option value="CMPAY">中国移动手机支付</option>
										
									</select>
								</div>





							</td>
							<td width="103"></td>
							<td></td>
					  </tr>
						<input type="hidden" name="f03" id="f03" value="银行卡识别ID" />
						<input type="hidden" name="f04" id="f04" value="银行卡分类ID" />
						<input type="hidden" name="f05" id="f05" value="银行卡片ID" />
						<!-- 支付渠道 -->
						<input type="hidden" name="fb2" id="fb2" value="4001" />
						<!-- 支付渠道 -->



						<tr height="36">
							<td height="40" align="right"><b>充值金额</b></td>
							<td width="10"></td>
							<td align="left"><input onBlur="bao()" style="margin-bottom: 0;"
								type="text" maxlength="20" placeholder="" id="f07" name="f07"
								value=""> 元<span id="f07_tips"
								style="background-color: red; visibility: hidden">充值金额不能为空</span></td>
							<td></td>
							<td></td>
					  </tr>
					  
				<tr height="36">
					<td align="right"></td>
					<td></td>
					<td><label id="f07m"></label></td>
					<td></td>
					<td></td>
				</tr>

						<!-- 				<tr height="36"> -->
						<!-- 					<td align="right" valign="center"><b>验证码</b></td> -->
						<!-- 					<td></td> -->
						<!-- 					<td> -->
						<!-- 					  <input style="margin-bottom:0;" type="text"  placeholder="" id="f10" name="f10" value=""> -->
						<!-- 				    </td> -->
						<!-- 					<td></td> -->
						<!-- 					<td></td> -->
						<!-- 				</tr>	 -->
						<!-- 				<tr height="36"> -->
						<!-- 					<td align="right"></td> -->
						<!-- 					<td></td> -->
						<!-- 					<td></td> -->
						<!-- 					<td></td> -->
						<!-- 					<td></td> -->
						<!-- 				</tr> -->
						<tr height="36">
							<td height="40" align="right"><b>备注说明</b></td>
							<td width="10"></td>
							<td align="left"><input style="margin-bottom: 0;" type="text"
								placeholder="" id="bbb" name="bbb" value=""></td>
							<td></td>
							<td></td>
					  </tr>
						<tr height="36">
							<td align="right"></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
						</tr>

					</table>
			  </td>

			</tr>

		</table>
	</form>
</body>
</html>
