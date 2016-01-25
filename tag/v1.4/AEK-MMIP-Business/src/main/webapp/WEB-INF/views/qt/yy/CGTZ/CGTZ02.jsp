<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" import="org.jfpc.framework.helper.*" %>
<!DOCTYPE html>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<title></title>
<meta name="description" content="">
<meta name="keywords" content="">
<link rel="stylesheet" type="text/css" href="/resources/css/base.css">
<link rel="stylesheet" type="text/css" href="/resources/css/hospital_cert.css">
<link rel="stylesheet" href="/resources/css/jquery.mCustomScrollbar.css">
<script src="/resources/js/jquery.js"></script>
<script src="/resources/js/jquery.mousewheel.js"></script>
<script src="/resources/js/jquery.mCustomScrollbar.js"></script>
<script src="/resources/js/jquery.jcarousellite.min.js"></script>
<script src="/resources/js/tableFixed.min.js"></script>
<script src="/resources/js/jq.ui.js"></script>
<script src="/resources/js/function.js"></script>
<%@ include file="/resources/jsp/yy/inc.jsp"%>
<script>
$(function(){
	$(".matchImg").mCustomScrollbar({theme:"minimal"});
})
function supplierValid(type){
	var ppp=[];
	ppp.push($("#seriveContent option:selected").text());
	ppp.push($("#bcontent").val());
	ajax({url:"/32113210101?t=" + Math.random(),data:{"puk":${aid},"f13_shzt":type,"ppp":ppp.join(",")},callBack:function(json){
		top.msgText(json.message,true);
		if(json.code == 0){
			window.top.iframe.getAuditCert();
			window.parent.closeLayer();
		}
	},load:false});
}

function supplierAudit(obj){
	var html = "<ul class=\"ulFrm\">\n\
		<li><div class=\"select {onchange:changeAudit}\">\n\
		<select name=\"seriveContent\" id=\"seriveContent\">\n\
			<option value=\"0\">证件信息没改</option>\n\
			<option value=\"1\">维护的证件信息不正确</option>\n\
			<option value=\"2\">其他</option>\n\
		</select>\n\
	</div></li>\n\
	<li><textarea class=\"textarea readonly\" disabled=\"disabled\" cols=\"30\" id=\"bcontent\" rows=\"4\"></textarea></li>\n\
	<li><input type='button' class='btn' value='拒绝接收' onclick=\"supplierValid(1);\" /></li></ul>";
	cLayer(obj,'拒绝邀请', html, null, 'right');
	resetText();
    $(".select").uiSelect();
}

</script>
</head>
<body class="whiteBg">
	<div class="formTitle">待审核换证信息</div>
	<div class="boxPContent clearfix2">
		<div class="supLookBox lFloat">
			<span class="mlogo mlogo-min lFloat" id="yui-supplier-logo">${UserData.cmp.f01_qyqc.substring(0,1) }</span>
			<div class="lookSuplierInfo2">
				<h3>${UserData.cmp.f01_qyqc }</h3>
				<p class="mTop10 gray">
					<img src="/resources/images/icon/tb_m_1.png" align="absmiddle" /> ${UserData.cmp.f30_lxrxm } ${UserData.cmp.f32_lxrdh } ${UserData.cmp.f16_lxdh }<br />
					<img src="/resources/images/icon/tb_m_2.png" align="absmiddle" /> ${UserData.cmp.f11_szs }${UserData.cmp.f12_szds }${UserData.cmp.f13_szqx }${UserData.cmp.f14_szxxdz }<br />
					<img src="/resources/images/icon/tb_m_3.png" align="absmiddle" /> <span class="inlineBlock w300">${UserData.cmp.bbb }</span>
				</p>
			</div>
		</div>
		<div class="rFloat supLookAudit">
			<a href="javascript:supplierValid('0');" class="btn maxBtn">审核通过</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:void(0);" onclick="supplierAudit(this);" gid="1" class="btn dislabel maxBtn">审核拒绝</a>
		</div>
	</div>
	<ul class="ulFrmTwo">
		<li>
			<div class="contnet">
				<div class="formTitle">
					<span class="font14">
						<b class="red">
							<c:choose>
								<c:when test="${type=='1' }">
									营业执照
								</c:when>
								<c:when test="${type=='a' }">
									${UserData.f10_cjylqxzcz[0].f01_zczzwmc}
								</c:when>
								<c:when test="${type=='2' }">
									供应商经营许可证
								</c:when>
								<c:when test="${type=='3' }">
									供应商工商税务登记证
								</c:when>
								<c:when test="${type=='4' }">
									供应商经销授权书
								</c:when>
								<c:when test="${type=='5' }">
									供应商销售人员委托书
								</c:when>
								<c:when test="${type=='6' }">
									供应商售后服务承诺书
								</c:when>
								<c:when test="${type=='7' }">
									供应商进口商检报告
								</c:when>
								<c:when test="${type=='8' }">
									厂家营业执照
								</c:when>
								<c:when test="${type=='9' }">
									厂家生产许可证
								</c:when>																																									
							</c:choose>
						</b>
					 更换前</span></div>
				<div class="mTop10"><span class="label">有效期：</span> ${UserData.f10_cjylqxzcz[0].f09_yxksrq}至${UserData.f10_cjylqxzcz[0].f10_yxzzrq}</div>
				<div class="mTop10 matchImg">
					<img src="/images/noImg.png" width="415" height="300" />
				</div>
			</div>
		</li>
		<li>
			<div class="contnet">
			<div class="formTitle"><span class="font14"><b class="green">
						<c:choose>
							<c:when test="${type=='1' }">
								营业执照
							</c:when>
							<c:when test="${type=='a' }">
								${UserData.f10_cjylqxzcz[1].f01_zczzwmc}
							</c:when>
							<c:when test="${type=='2' }">
								供应商经营许可证
							</c:when>
							<c:when test="${type=='3' }">
								供应商工商税务登记证
							</c:when>
							<c:when test="${type=='4' }">
								供应商经销授权书
							</c:when>
							<c:when test="${type=='5' }">
								供应商销售人员委托书
							</c:when>
							<c:when test="${type=='6' }">
								供应商售后服务承诺书
							</c:when>
							<c:when test="${type=='7' }">
								供应商进口商检报告
							</c:when>
							<c:when test="${type=='8' }">
								厂家营业执照
							</c:when>
							<c:when test="${type=='9' }">
								厂家生产许可证
							</c:when>																																									
						</c:choose>
				
				</b> 更换后</span></div>
			<div class="mTop10"><span class="label">有效期：</span>  ${UserData.f10_cjylqxzcz[1].f09_yxksrq}至${UserData.f10_cjylqxzcz[1].f10_yxzzrq}</div>
			<div class="mTop10 matchImg">
				<img src="/images/noImg.png" width="415" height="300" />
			</div>
			</div>
		</li>
	</ul>
</body>
</html>