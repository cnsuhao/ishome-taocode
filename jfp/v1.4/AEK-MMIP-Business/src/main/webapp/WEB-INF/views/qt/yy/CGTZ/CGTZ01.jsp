<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" import="org.jfpc.framework.helper.*" %>
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
<script src="/resources/js/tableFixed.min.js"></script>
<script src="/resources/js/jq.ui.js"></script>
<script src="/resources/js/function.js"></script>
<%@ include file="/resources/jsp/yy/inc.jsp"%>
<script>
var regDataList = ${f10_cjylqxzcz};
$(function(){
	resetText();
	$(".textTips").input();
	$(".carousel").jCarouselLite({
        btnNext: ".jcnext",
        btnPrev: ".jcprev",
        visible: 4,
        circular: false,
        scroll: 3
    });
	getRegList();
	$(".checkbox").checkbox();
	hoverTable(".hoverTable",true,true);
});

function supplierAudit(obj){
	var html = "<ul class=\"ulFrm\">\n\
		<li><div class=\"select {onchange:changeAudit}\">\n\
		<select name=\"seriveContent\" id=\"seriveContent\">\n\
			<option value=\"0\">不是我的服务供应商</option>\n\
			<option value=\"1\">维护的资料信息不完善</option>\n\
			<option value=\"2\">其他</option>\n\
		</select>\n\
	</div></li>\n\
	<li><textarea class=\"textarea readonly\" disabled=\"disabled\" cols=\"30\" id=\"bcontent\" rows=\"4\"></textarea></li>\n\
	<li><input type='button' class='btn' value='拒绝接收' onclick=\"supplierValid(1);\" /></li></ul>";
	cLayer(obj,'拒绝邀请', html, null, 'right');
	resetText();
    $(".select").uiSelect();
}

function supplierValid(type){
	var checkData = [], ppp=[];
	ppp.push($("#seriveContent option:selected").text());
	ppp.push($("#bcontent").val());
	
	$.each(regDataList, function(index, item){
		checkData.push(item.puk);
	});
	ajax({url:"/321132103?t=" + Math.random(),data:{"puk":${aid},"f13_shzt":type,"n10_cjylqxzcz":checkData.join(","),"ppp":ppp.join(",")},callBack:function(json){
		top.msgText(json.message,true);
		if(json.code == 0){
			window.top.iframe.getTSAuditCert();
			window.parent.closeLayer();
		}
	},load:false});
}

function getRegList(data){
	var html = "";
	data = data == undefined ? regDataList : data;
	$("#list").empty();
	if(data.length > 0){
		$.each(data, function(index, item){
			html +="<tr>\n\
				<td>"+item.f01_zczzwmc+"</td>\n\
				<td>"+item.f03_cpzwmc+"</td>\n\
				<td>"+item.f09_yxksrq+"至"+item.f10_yxzzrq+"</td>\n\
				<td><a href=\"javascript:top.showWindow('','/321132105?puk="+item.puk+"', 900, 420, true);\"><img src=\"/resources/images/icon/i_show.png\"> 详情</a>&nbsp;&nbsp;\n\
				<a href=\"/32113210103?flag=0&cid="+item.p01_gysid+"&type=9&tsbId=${aid}&zczId="+item.puk+"\" target=\"_bank\"><img src=\"/resources/images/icon/t-view.png\"> 看图</a>&nbsp;&nbsp;\n\
				<a href=\"javascript:top.showWindow('','/32110004/1?cid="+item.puk+"', 900, 500, true);\"><img src=\"/resources/images/icon/t-view.png\"> 回溯</a>&nbsp;&nbsp;\n\
				<a href=\"javascript:void(0);\" onclick=\"del(this, "+item.puk+");\"><img src=\"/resources/images/icon/i_del.png\"> 删除</a>\n\
				</td>\n\
			</tr>";
		});
		$("#list").html(html);
	}else{
		$("#list").info("暂无数据。");
	}
}

function del(obj, id){
	var newRegData = [];
	$.each(regDataList,function(index, item){
		if(item.puk != id) newRegData.push(item);
	});
	regDataList = newRegData;
	getRegList();
}

function searchReg(){
	var newRegData = [], keywords = $("#keywords").val();
	$.each(regDataList,function(index, item){
		if(item.f01_zczzwmc.indexOf(keywords) != -1 || item.f03_cpzwmc.indexOf(keywords) != -1) newRegData.push(item);
	});
	getRegList(newRegData);
}
</script>
</head>
<body class="whiteBg">
	<div class="formTitle">审核推送证件</div>
	<div class="boxPContent">
		<div class="supLookBox lFloat">
			<span class="mlogo lFloat" id="yui-supplier-logo">${UserData.cmp.f01_qyqc.substring(0,1) }</span>
			<div class="lookSuplierInfo">
				<h3>${UserData.cmp.f01_qyqc }</h3>
				<p class="mTop10 gray">
					<img src="/resources/images/icon/tb_m_1.png" align="absmiddle" /> ${UserData.cmp.f30_lxrxm } ${UserData.cmp.f32_lxrdh } ${UserData.cmp.f16_lxdh }<br />
					<img src="/resources/images/icon/tb_m_2.png" align="absmiddle" /> ${UserData.cmp.f11_szs }${UserData.cmp.f12_szds }${UserData.cmp.f13_szqx }${UserData.cmp.f14_szxxdz }<br />
					<img src="/resources/images/icon/tb_m_3.png" align="absmiddle" /> <span class="inlineBlock w300">${UserData.cmp.bbb }</span>
				</p>
			</div>
		</div>
		<div class="supLookCertBox">
			<div class="custom-container fraction" style="margin-right:20px;">
		        <a href="javascript:void(0);" class="jcprev">&lsaquo;</a>
		        <div class="carousel">
		            <ul>
		             	<c:forEach var="item" items="${UserData.f01_yyzz }">
			                <li>
			                	
			                	<div class="uploadBox uploadW80">
									<div class="uploadImg"><a href="/32113210103?flag=0&cid=${item.k01_gysid}&type=1&tsbId=${aid}" target="_blank"><img src="<%=ImagePath%>/00003030/${item.bbb}/${loginer.token}"></a></div>
									<div class="uploadBoxText">营业执照</div>
								</div>
								<%-- <p>${item.f04_yxksrq.substring(0,7) }至${item.f05_yxzzrq.substring(0,7) }</p> --%>
			                </li>
		                </c:forEach>
		             	<c:forEach var="item" items="${UserData.f02_jyxkz }">
			                <li>
			                	<div class="uploadBox uploadW80">
									<div class="uploadImg"><a href="/32113210103?flag=0&cid=${item.k01_gysid}&type=2&tsbId=${aid}" target="_blank"><img src="<%=ImagePath%>/00003030/${item.bbb}/${loginer.token}"></a></div>
									<div class="uploadBoxText">经营许可证</div>
								</div>
								<%-- <p>${item.f04_yxksrq.substring(0,7) }至${item.f05_yxzzrq.substring(0,7) }</p> --%>
			                </li>
		                </c:forEach>
		             	<c:forEach var="item" items="${UserData.f03_gsswdjz }">
			                <li>
			                	<div class="uploadBox uploadW80">
									<div class="uploadImg"><a href="/32113210103?flag=0&cid=${item.k01_gysid}&type=3&tsbId=${aid}" target="_blank"><img src="<%=ImagePath%>/00003030/${item.bbb}/${loginer.token}"></a></div>
									<div class="uploadBoxText">税务登记证</div>
								</div>
								<%-- <p>${item.f04_yxksrq.substring(0,7) }至${item.f05_yxzzrq.substring(0,7) }</p> --%>
			                </li>
		                </c:forEach>
		             	<c:forEach var="item" items="${UserData.f04_jxsqs }">
			                <li>
			                	<div class="uploadBox uploadW80">
									<div class="uploadImg"><a href="/32113210103?flag=0&cid=${item.k01_gysid}&type=4&tsbId=${aid}" target="_blank"><img src="<%=ImagePath%>/00003030/${item.bbb}/${loginer.token}"></a></div>
									<div class="uploadBoxText">经销授权书</div>
								</div>
								<%-- <p>${item.f04_yxksrq.substring(0,7) }至${item.f05_yxzzrq.substring(0,7) }</p> --%>
			                </li>
		                </c:forEach>
		             	<c:forEach var="item" items="${UserData.f05_xsrywts }">
			                <li>
			                	<div class="uploadBox uploadW80">
									<div class="uploadImg"><a href="/32113210103?flag=0&cid=${item.k01_gysid}&type=5&tsbId=${aid}" target="_blank"><img src="<%=ImagePath%>/00003030/${item.bbb}/${loginer.token}"></a></div>
									<div class="uploadBoxText">人员委托书</div>
								</div>
								<%-- <p>${item.f04_yxksrq.substring(0,7) }至${item.f05_yxzzrq.substring(0,7) }</p> --%>
			                </li>
		                </c:forEach>
		             	<c:forEach var="item" items="${UserData.f06_shfwcns }">
			                <li>
			                	<div class="uploadBox uploadW80">
									<div class="uploadImg"><a href="/32113210103?flag=0&cid=${item.k01_gysid}&type=6&tsbId=${aid}" target="_blank"><img src="<%=ImagePath%>/00003030/${item.bbb}/${loginer.token}"></a></div>
									<div class="uploadBoxText">售后服务承诺书</div>
								</div>
								<%-- <p>${item.f04_yxksrq.substring(0,7) }至${item.f05_yxzzrq.substring(0,7) }</p> --%>
			                </li>
		                </c:forEach>
		             	<c:forEach var="item" items="${UserData.f07_jksjbg }">
			                <li>
			                	<div class="uploadBox uploadW80">
									<div class="uploadImg"><a href="/32113210103?flag=0&cid=${item.k01_gysid}&type=1&tsbId=${aid}" target="_blank"><img src="<%=ImagePath%>/00003030/${item.bbb}/${loginer.token}"></a></div>
									<div class="uploadBoxText">进口商检报告</div>
								</div>
								<%-- <p>${item.f04_yxksrq.substring(0,7) }至${item.f05_yxzzrq.substring(0,7) }</p> --%>
			                </li>
		                </c:forEach>
		             	<c:forEach var="item" items="${UserData.f08_cjyyzz }">
			                <li>
			                	<div class="uploadBox uploadW80">
									<div class="uploadImg"><a href="/32113210103?flag=0&cid=${item.k01_gysid}&type=7&tsbId=${aid}" target="_blank"><img src="<%=ImagePath%>/00003030/${item.bbb}/${loginer.token}"></a></div>
									<div class="uploadBoxText">厂家营业执照</div>
								</div>
								<%-- <p>${item.f04_yxksrq.substring(0,7) }至${item.f05_yxzzrq.substring(0,7) }</p> --%>
			                </li>
		                </c:forEach>
		             	<c:forEach var="item" items="${UserData.f09_cjscxkz }">
			                <li>
			                	<div class="uploadBox uploadW80">
									<div class="uploadImg"><a href="/32113210103?flag=0&cid=${item.k01_gysid}&type=8&tsbId=${aid}" target="_blank"><img src="<%=ImagePath%>/00003030/${item.bbb}/${loginer.token}"></a></div>
									<div class="uploadBoxText">厂家生产许可证</div>
								</div>
								<%-- <p>${item.f04_yxksrq.substring(0,7) }至${item.f05_yxzzrq.substring(0,7) }</p> --%>
			                </li>
		                </c:forEach>
		             	<c:forEach var="item" items="${UserData.f11_cjhcsprz }">
			                <li>
			                	<div class="uploadBox uploadW80">
									<div class="uploadImg"><a href="/32113210103?flag=0&cid=${item.k01_gysid}&type=1&tsbId=${aid}" target="_blank"><img src="<%=ImagePath%>/00003030/${item.bbb}/${loginer.token}"></a></div>
									<div class="uploadBoxText">3c认证</div>
								</div>
								<%-- <p>${item.f04_yxksrq.substring(0,7) }至${item.f05_yxzzrq.substring(0,7) }</p> --%>
			                </li>
		                </c:forEach>		                		                		                		                		                		                
		             	<c:forEach var="item" items="${UserData.f12_cjxdcpwsxkz }">
			                <li>
			                	<div class="uploadBox uploadW80">
									<div class="uploadImg"><a href="/32113210103?flag=0&cid=${item.k01_gysid}&type=1&tsbId=${aid}" target="_blank"><img src="<%=ImagePath%>/00003030/${item.bbb}/${loginer.token}"></a></div>
									<div class="uploadBoxText">卫生许可证</div>
								</div>
								<%-- <p>${item.f04_yxksrq.substring(0,7) }至${item.f05_yxzzrq.substring(0,7) }</p> --%>
			                </li>
		                </c:forEach>		                		                		                		             
		            </ul>
		        </div>
		        <a href="javascript:void(0);" class="jcnext">&rsaquo;</a>
		        <div class="clear"></div>
		    </div>
		</div>
		<div class="rFloat">
			<input type="text" name="keywords" id="keywords" size="30" class="text textTips {text:'请输入注册证号或产品名称'}" copy="search" />
			<input type="button" icon="search" onclick="searchReg();" />
			<a href="javascript:supplierValid(0);" class="btn">通过</a>&nbsp;&nbsp;<a href="javascript:void(0);" onclick="supplierAudit(this);" gid="1" class="btn dislabel">拒绝</a>
		</div>
		<div class="formTitle mTop10">医疗器械注册证</div>
		<div class="tableList" style="height:400px;">
		<table class="table hoverTable" width="100%" cellspacing="0" cellpadding="0" border="0">
				<thead>
					<tr>
						<th width="35%">医疗器械注册证</th>
						<th>注册证产品名称</th>
						<th width="20%">有效期</th>
						<th width="25%">操作</th>
					</tr>
				</thead>
				<tbody id="list"></tbody>
			</table>
		</div>
	</div>
</div>
</body>
</html>