<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/taglib/pagetag.tld"  prefix="p" %>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
	<meta charset="utf-8">
	<title>厂商基本信息</title>
	<%@ include file="/resources/jsp/yw/inc.jsp" %>
<body>
<div class="container-fluid">
		<div class="panel panel-default mt20">
			<!-- 导航按钮 -->
			<div class="panel-heading tc">
				<a type="button" class="btn btn-default fl-l btn-xs" href="javascript:;" onclick="post('/392030001')">
					<span class="glyphicon glyphicon-arrow-left"></span>
				</a>
				厂商-比较
			</div>
			<!-- 主体 -->
			<div class="panel-body">
				<form class="form-horizontal form-edit form-read submit-form" role="form" action="/392030003" method="post" id="myform">
					<input type="hidden" name="puk" value="${newobj.puk }">

					
					<div class="form-group table-title">
						<label class="col-sm-2 control-label label-title">&nbsp;</label>
						<label class="col-sm-4 control-label label-content">修改前内容</label>
						<label class="col-sm-4 control-label label-content">修改后内容</label>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label label-title">企业全称：</label>
						<label class="col-sm-4 control-label label-content">${oldobj.f01_qyqc }</label>
						<label class="col-sm-4 control-label label-content">${newobj.f01_qyqc }</label>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label label-title">官网地址：</label>
						<label class="col-sm-4 control-label label-content">${oldobj.f03_gwdz }</label>
						<label class="col-sm-4 control-label label-content">${newobj.f03_gwdz }</label>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label label-title">成立日期：</label>
						<label class="col-sm-4 control-label label-content">${oldobj.f04_clrq }</label>
						<label class="col-sm-4 control-label label-content">${newobj.f04_clrq }</label>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label label-title">法人姓名：</label>
						<label class="col-sm-4 control-label label-content">${oldobj.f06_frxm }</label>
						<label class="col-sm-4 control-label label-content">${newobj.f06_frxm }</label>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label label-title">身份证号：</label>
						<label class="col-sm-4 control-label label-content">${oldobj.f07_frsfzid }</label>
						<label class="col-sm-4 control-label label-content">${newobj.f07_frsfzid }</label>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label label-title">付款方式：</label>
						<label class="col-sm-4 control-label label-content">${oldobj.f08_fkfs }</label>
						<%-- <p:dictValue class="col-sm-4 control-label label-content" value="${oldobj.f08_fkfs}"/> --%>
						<%-- <p:dictValue class="col-sm-4 control-label label-content" value="${newobj.f08_fkfs}"/> --%>
						<label class="col-sm-4 control-label label-content">${newobj.f08_fkfs }</label>
						
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label label-title">开户银行：</label>
						<label class="col-sm-4 control-label label-content">${oldobj.f09_khyx }</label>
						<label class="col-sm-4 control-label label-content">${newobj.f09_khyx }</label>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label label-title">开户银行账号：</label>
						<label class="col-sm-4 control-label label-content">${oldobj.f10_khyxzh }</label>
						<label class="col-sm-4 control-label label-content">${newobj.f10_khyxzh }</label>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label label-title">所在国家：</label>
						<label class="col-sm-4 control-label label-content">${oldobj.f27_qyszgj }</label>
						<label class="col-sm-4 control-label label-content">${newobj.f27_qyszgj }</label>
					</div>
<!-- 					<div class="form-group">
						<label class="col-sm-2 control-label label-title">单位全称：</label>
						<label class="col-sm-4 control-label label-content"></label>
						<label class="col-sm-4 control-label label-content"></label>
					</div> -->
					<div class="form-group">
						<label class="col-sm-2 control-label label-title">所在地：</label>
						<label class="col-sm-4 control-label label-content">${oldobj.f11_szs }${oldobj.f12_szds }${oldobj.f13_szqx }</label>
						<label class="col-sm-4 control-label label-content">${newobj.f11_szs }${newobj.f12_szds }${newobj.f13_szqx }</label>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label label-title">所在详细地址：</label>
						<label class="col-sm-4 control-label label-content">${oldobj.f14_szxxdz }</label>
						<label class="col-sm-4 control-label label-content">${newobj.f14_szxxdz }</label>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label label-title">办公详细地址：</label>
						<label class="col-sm-4 control-label label-content">${oldobj.f15_bgxxdz }</label>
						<label class="col-sm-4 control-label label-content">${newobj.f15_bgxxdz }</label>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label label-title">联系电话：</label>
						<label class="col-sm-4 control-label label-content">${oldobj.f16_lxdh }</label>
						<label class="col-sm-4 control-label label-content">${newobj.f16_lxdh }</label>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label label-title">传真号码：</label>
						<label class="col-sm-4 control-label label-content">${oldobj.f17_czhm }</label>
						<label class="col-sm-4 control-label label-content">${newobj.f17_czhm }</label>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label label-title">凭证编号：</label>
						<label class="col-sm-4 control-label label-content">${oldobj.f18_pzbm }</label>
						<label class="col-sm-4 control-label label-content">${newobj.f18_pzbm }</label>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label label-title">注册资本：</label>
						<label class="col-sm-4 control-label label-content">${oldobj.f05_zczbwy }</label>
						<label class="col-sm-4 control-label label-content">${newobj.f05_zczbwy }</label>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label label-title">联系人姓名：</label>
						<label class="col-sm-4 control-label label-content">${oldobj.f30_lxrxm }</label>
						<label class="col-sm-4 control-label label-content">${newobj.f30_lxrxm }</label>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label label-title">联系人电话：</label>
						<label class="col-sm-4 control-label label-content">${oldobj.f32_lxrdh }</label>
						<label class="col-sm-4 control-label label-content">${newobj.f32_lxrdh }</label>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label label-title">联系人邮箱：</label>
						<label class="col-sm-4 control-label label-content">${oldobj.f31_lxraqyx }</label>
						<label class="col-sm-4 control-label label-content">${newobj.f31_lxraqyx }</label>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label label-title">营业执照编号：</label>
						<label class="col-sm-4 control-label label-content">${oldobj.f29_qyyyzzbh }</label>
						<label class="col-sm-4 control-label label-content">${newobj.f29_qyyyzzbh }</label>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label label-title">营业执照有效期：</label>
						<label class="col-sm-4 control-label label-content">${oldobj.f25_qyyyzzksrq }至${oldobj.f26_qyyyzzzlrq }</label>
						<label class="col-sm-4 control-label label-content">${newobj.f25_qyyyzzksrq }至${newobj.f26_qyyyzzzlrq }</label>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label label-title">营业执照发证机关：</label>
						<label class="col-sm-4 control-label label-content">${oldobj.eb5 }</label>
						<label class="col-sm-4 control-label label-content">${newobj.eb5 }</label>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label label-title">许可证编号：</label>
						<label class="col-sm-4 control-label label-content">${oldobj.f20_qyjyxkzbh }</label>
						<label class="col-sm-4 control-label label-content">${newobj.f20_qyjyxkzbh }</label>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label label-title">许可证有效期：</label>
						<label class="col-sm-4 control-label label-content">${oldobj.f21_qyjyxkzksrq }至${oldobj.f22_qyjyxkzzlrq }</label>
						<label class="col-sm-4 control-label label-content">${newobj.f21_qyjyxkzksrq }至${newobj.f22_qyjyxkzzlrq }</label>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label label-title">许可证发证机关：</label>
						<label class="col-sm-4 control-label label-content">${oldobj.eb3 }</label>
						<label class="col-sm-4 control-label label-content">${newobj.eb3 }</label>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label label-title">税务登记证编号：</label>
						<label class="col-sm-4 control-label label-content">${oldobj.f28_qygsswdjzbh }</label>
						<label class="col-sm-4 control-label label-content">${newobj.f28_qygsswdjzbh }</label>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label label-title">税务登记证有效期：</label>
						<label class="col-sm-4 control-label label-content">${oldobj.f23_qygsswdjzksrq }至${oldobj.f24_qygsswdjzzlrq }</label>
						<label class="col-sm-4 control-label label-content">${newobj.f23_qygsswdjzksrq }至${newobj.f24_qygsswdjzzlrq }</label>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label label-title">税务发证机关：</label>
						<label class="col-sm-4 control-label label-content">${oldobj.eb4 }</label>
						<label class="col-sm-4 control-label label-content">${newobj.eb4 }</label>
					</div>
					<div class="form-result">
						<label class="col-sm-2 control-label label-title">理由</label>
						<div>
							<textarea cols="50" rows="3" name="n04_shly"></textarea>

						</div>
					</div>
					<div class="form-group btn-form-group">
						<label class="col-sm-2 control-label"></label>
						<div class="col-sm-4">
							<button type="button" class="btn btn-primary" onclick="msub({n03_shzt:0})">通过</button>
							<button type="button" class="btn btn-warning" onclick="msub({n03_shzt:1})">拒绝</button>
						</div>
					</div>
				</form>
			</div><!-- end-body -->
		</div>
	</div>
</body>
<script type="text/javascript">
	function setParentFrameHeight(){
		if(window.parent && window.parent.setIframeHeihgt){
			window.parent.setIframeHeihgt($("html").height());
		}
	}
	$(function(){
		setParentFrameHeight();
	});
	
	function msub(PARAMS){
		
		var temp = document.getElementById("myform");
		for ( var x in PARAMS) {
			var opt = document.createElement("input");
			opt.type="hidden";
			opt.name = x;
			opt.value = PARAMS[x];
			// alert(opt.name)        
			temp.appendChild(opt);
		}
		document.body.appendChild(temp);
		temp.submit();
		return false;
	}
</script>
</html>