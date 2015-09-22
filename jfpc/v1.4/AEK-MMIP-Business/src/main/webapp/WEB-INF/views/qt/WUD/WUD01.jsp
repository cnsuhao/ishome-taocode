<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html lang="zh-cn">
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<title></title>
<meta name="description" content="">
<meta name="keywords" content="">
<link rel="stylesheet" type="text/css" href="/resources/css/base.css">
<link rel="stylesheet" href="/resources/css/jquery.mCustomScrollbar.css">
<link rel="stylesheet" type="text/css"
	href="/resources/css/uploadify.css" />
<script src="/resources/js/jquery.js"></script>
<script src="/resources/js/jquery.mousewheel.js"></script>
<script src="/resources/js/jquery.mCustomScrollbar.js"></script>
<script src="/resources/js/jquery.uploadify.js"></script>
<script src="/resources/js/override.uploadify.js"></script>
<script src="/resources/js/jq.ui.js"></script>
<script src="/resources/js/function.js"></script>
<script src="/resources/js/jsAddress.js"></script>
<%@ include file="/resources/jsp/ImagePath.jsp"%>
<script src="/resources/js/validate.js"></script>
<script>
	$(function() {
		resetText();
		$(".tag").uiSwitch();
		$(".tag>span:eq(${index})").click();
		getMembers();
		new PCAS("f11_szs", "f12_szds", "f13_szqx", '${company.f11_szs}',
				'${company.f12_szds}', '${company.f13_szqx}');
		$(".select").uiSelect();
		loadUpload({
			id : '#tag_0',
			width : 120,
			height : 40,
			success : uploadSuccess_1
		});
	});

	function uploadSuccess_1(file, data, response) {
		var json = data.toJSON();
		var img = json.result;
		$("#gsyPhoto_url").val(img);
		$("#gsyPhoto_1").html("<img src='" + cateImage(img) + "' />");
		saveInfo(1);
	}

	function chanage(o) {
		if (o != undefined) {
			PCAS("f11_szs", "f12_szds", "f13_szqx", $("#f11_szs").val(), $(
					"#f12_szds").val());
			$(o).uiSelect();
		}
	}
	function getMembers(loading) {
		loading != undefined ? loading : true;
		var html = "";
		$("#member-list").empty();
		removeInfo();
		ajax({url:"/31402040?t=" + Math.random(),
				data:{},
				callBack:function(data) {
					if (data.code == 0) {
						if(data.result.length > 0){
							$.each(data.result,function(index, item) {
								html += "<tr id=\""+item.puk+"\" role=\""+item.roles+"\">\n\
									<td><span class=\"mlogo\">"+ item.f01_yhxm.substring(0, 1) + "</span>&nbsp;&nbsp;"+ item.f01_yhxm + "</td>\n\
										<td>"+ item.fb3 + "</td>\n\
										<td>"+ item.cc1 + "</td>\n\
										<td>"+ item.f08_zhdlrs + "</td>\n\
										<td><span><a href=\"javascript:void(0);\" onclick=\"setRole(this, "+ item.puk + ");\" class=\"btn\">权限</a></span> <a href=\"javascript:chongzhi("+ item.puk + ")\" class=\"btn\">重置密码</a> <a href=\"javascript:void(0);\" onclick=\"deleteUser(this, "+ item.puk + ")\" class=\"btn\">删除</a></td>\n\
									</tr>";
							});
							$("#member-list").html(html);
						}else
							$("#member-list").info("暂无信息。");
					}
				},load:loading});
	}
	
	function saveInfo(type) {
		var data = $("#hform1").serializeJSON(), vData = $("#hform1")
				.validateForm({
					id : '#yui-error-msg'
				});

		if (!vData.allSuccess) {
			return false;
		}
		ajax({url:"/31402050", data:data, callBack:function(json) {
			if(type == 1) json.message="图片上传成功。";
			top.msgText(json.message, true);
		},load:false});
		return false;
	}

	function deleteUser(obj, puk) {
		ajax({url:"/31402042", data:{
			"puk" : puk
		}, callBack:function(json) {
			if (json.code == 0) $(obj).parent().parent().remove();
			top.msgText(json.message, true);
		},load:false});
	}

	function chongzhi(puk) {
		ajax({url:"/31402044", data:{
			"puk" : puk
		}, callBack:function(json) {
			top.msgText(json.message, true);
		},load:false});
	}
</script>
</head>
<body>
	<div class="contnet">
		<div class="boxContent">
			<div class="tagBox">
				<div
					class="tag {movetag:'span',showtag:'.tagBox .tagNav',addsclass:'tagnSelect',addtclass:'tagSelect',time:9999999}">
					<span>帐户设置</span>
					<c:if test="${isadmin =='0' }">
						<span>成员管理</span>
						<span>企业资料</span>
					</c:if>
				</div>
				<div class="tagBox">
					<div class="tagNav tagnSelect">
						<ul class="ulFrm">
							<li><span class="label labelW100 labelRight">用户名：</span> <span
								class="gray">${UserData.k03_dlyhm }</span></li>
							<li><span class="label labelW100 labelRight">登录密码：</span> <span
								class="label labelW200">******</span> <a
								href="javascript:top.showWindow('修改密码','31402056?page=WUD02', 340, 260, true);"
								class="btn">修改密码</a></li>
							<li><span class="label labelW100 labelRight">验证手机：</span> <span
								class="label labelW200">${UserData.fb3 }</span> <a
								href="javascript:top.showWindow('修改手机号码','31402056?page=WUD03', 360, 230, true);"
								class="btn">修改手机</a></li>
							<li><span class="label labelW100 labelRight">验证邮箱：</span> <span class="label labelW200"> <c:choose>
										<c:when test="${UserData.fb1==''}">
										未设置
									</c:when>
										<c:otherwise>
										${UserData.fb1 }
									</c:otherwise>
									</c:choose>
							</span> <a
								href="javascript:top.showWindow('修改电子邮箱','31402056?page=WUD04', 360, 200, true);"
								class="btn">设置</a></li>
						</ul>
					</div>
					<c:if test="${isadmin =='0' }">
						<div class="tagNav">
							<div class="mTop10">
								<a
									href="javascript:showLayer('/31402046?page=WUE01', 500, true);"
									class="btn">添加成员</a>
							</div>
							<table class="table mTop10" width="100%" cellpadding="0"
								cellspacing="0" border="0">
								<thead>
									<tr>
										<th>成员</th>
										<th>手机</th>
										<th width="15%">加入日期</th>
										<th width="15%">最近操作时间</th>
										<th width="22%">操作</th>
									</tr>
								</thead>
								<tbody id="member-list"></tbody>
							</table>
						</div>

						<div class="tagNav">
							<form id="hform1" name="hform1" onsubmit="return saveInfo();">
								<input type="hidden" name="gsyPhoto_url" id="gsyPhoto_url" value="" />
								<div class="userInfoEdit clearfix2">
									<div class="lFloat" align="center">
										<div class="mlogo mlogo-max" id="gsyPhoto_1">
											<script>
											$(function(){
												$("#gsyPhoto_1").html(splitLogo('${company.f01_qyqc}','${company.f19_logo_url}'));
											});
											</script>
										</div>
										<br />
										<div class="btn relative mTop10">
											修改LOGO
											<div class="useEditBtn">
												<span id="tag_0"></span>
											</div>
										</div>
									</div>
									<div class="userInfoEditText">
										<h3>${company.f01_qyqc }</h3>
										<p class="gray">你可以选择png/jpg图片（180*180）作为医院LOGO</p>
									</div>
								</div>
								<ul class="ulFrm">
									<input type="hidden" name="puk" value="${company.puk }">
									<li><span class="label labelW100 labelRight">医院简称：</span>
										<input type="text" name="n01_qyjc" id="n01_qyjc" size="40"
										value="${company.n01_qyjc }" class="text" /></li>
									<li><span class="label labelW100 labelRight"><em>*</em>
											法人代表：</span> <input type="text" vd-key="nonempty"
										data-error="请输入法人代表！" id="f06_frxm" name="f06_frxm"
										value="${company.f06_frxm }" size="40" maxlength="20"
										class="text" /></li>
									<li><span class="label labelW100 labelRight"><em>*</em>
											注册地址：</span>
										<div
											class="select {height:200,onchange:function(){chanage('#f12_szds_s,#f13_szqx_s');}}">
											<select vd-key="pac" id="f11_szs" name="f11_szs"></select>
										</div>
										<div
											class="select {height:200,onchange:function(){chanage('#f13_szqx_s');}}"
											id="f12_szds_s">
											<select id="f12_szds" name="f12_szds"></select>
										</div>
										<div class="select {height:200}" id="f13_szqx_s">
											<select id="f13_szqx" name="f13_szqx"></select>
										</div> <input vd-key="nonempty" data-error="请输入详细地址！" type="text"
										id="f14_szxxdz" name="f14_szxxdz"
										value="${company.f14_szxxdz }" size="40" maxlength="80"
										class="text" /></li>
									<li><span class="label labelW100 labelRight"><em>*</em>
											固定电话：</span> <input vd-key="tel" type="text" name="f16_lxdh"
										id="f16_lxdh" value="${company.f16_lxdh }" size="40"
										maxlength="20" class="text" /></li>
									<li class="p105">
										<div class="errorMsg" id="yui-error-msg" style="width: 315px;">

										</div>
									</li>
									<li class="p105"><input type="submit" id="submit"
										name="submit" value="保存" class="btn" /></li>
								</ul>
							</form>
						</div>
					</c:if>
				</div>
			</div>
		</div>
	</div>
	<%@ include file="/resources/jsp/formJS.jsp"%>
</body>
</html>
