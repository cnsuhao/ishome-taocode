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
<script src="/resources/js/function.js"></script>
<script src="/resources/js/jq.ui.js"></script>
<script src="/resources/js/tableFixed.min.js"></script>
<%@ include file="/resources/jsp/ImagePath.jsp"%>
<script>
$(function(){
	$(".tag").uiSwitch();
	$(".checkbox").checkbox();
	tagClick();
	$(".tag>span:eq("+(${index} || 0)+")").click();
});

function tagClick(){
	$(".tag>span").click(function(){
		var index = $(this).index();
		if(index == 0){
			getAuditSup();
			pageGo = getAuditSup;
		}else if(index == 1){
			getTSAuditCert();
			pageGo = getTSAuditCert;
		}else if(index == 2){
			getAuditCert();
			pageGo = getAuditCert;
		}else if(index == 3){
			getOverdueCert();
			pageGo = getOverdueCert;
		}
	})
}

//审核供应商
function getAuditSup(page){
	page = page || 1;
	var pageSize = 10;
	$("#audit-list").empty();
	removeInfo();
	ajax("/320110001?t="+Math.random(),{"page":page,"pageSize":pageSize},function(data){
		var html = "";
		if(data.code == 0){
			if(data.result.list.length > 0){
				$.each(data.result.list,function(index, item){
					html +="<tr>\n\
						<td class=\"center\"><sapn class=\"mlogo\">"+splitLogo(item.f01_qyqc, item.f19_logo_url)+"</sapn></td>\n\
						<td><h3>"+item.f01_qyqc+"</h3></td>\n\
						<td class=\"gray\">\n\
							<img src=\"/resources/images/icon/tb_m_1.png\" align=\"absmiddle\" />"+item.f30_lxrxm+" "+item.f32_lxrdh+" "+item.f16_lxdh+"<br />\n\
							<img src=\"/resources/images/icon/tb_m_2.png\" align=\"absmiddle\" />"+item.f11_szs+" "+item.f12_szds+" "+item.f13_szqx+" "+item.f14_szxxdz+"<br />\n\
							<img src=\"/resources/images/icon/tb_m_3.png\" align=\"absmiddle\" /> <span class=\"inlineBlock w300\">"+item.bbb+"</span>\n\
						</td>\n\
						<td class=\"gray\">"+item.cc1+"</td>\n\
						<td class=\"gray\"><a href=\"javascript:ignore('/320110002','"+item.puk+"','0');\" class=\"btn\">通过</a> <a href=\"javascript:ignore('/320110002','"+item.puk+"','1');\" class=\"btn\">拒绝</a></td>\n\
					</tr>";
				});
				$("#audit-list").html(html);
			}else{
				$("#audit-list").info("暂无信息。");
			}
			var newpage = new createPage(data.result.total,page,pageSize,3);
			$("#audit-pages").html(newpage.pageHtml);
			hoverTable(".auditTable",true);
		}
	});
}

//审核推送证件
function getTSAuditCert(page){
	page = page || 1;
	var pageSize = 10;
	$("#tsaudit-list").empty();
	removeInfo();
	ajax("/321132101?t="+Math.random(),{"page":page,"pageSize":pageSize,"type":2},function(data){
		var html = "";
		if(data.code == 0){
			if(data.result.list.length > 0){
				$.each(data.result.list,function(index, item){
					html +='<tr>\n\
						<td class="center"><sapn class="mlogo">'+splitLogo(item.fb1, '')+'</sapn></td>\n\
						<td><h3>'+item.fb1+'</h3><img src="/resources/images/icon/tb_3.png" align="absmiddle" /> '+item.fb2+' '+item.fb3+'</td>\n\
						<td>\n\
							<ul class="mTop10 clearfix tableListImg">';
								item.n01_yyzz > 0 ? html +='<li><img src="/" /><br/><span>营业执照</span></li>':'';
								item.n02_jyxkz > 0 ? html +='<li><img src="/" /><br/><span>经营许可证</span></li>':'';
								item.n03_gsswdjz > 0 ? html +='<li><img src="/" /><br/><span>税务登记证</span></li>':'';
								item.n10_cjylqxzcz > 0 ? html +='<li><img src="/" /><br/><span>注册证('+item.n10_cjylqxzcz+')</span></li>':'';
								item.n08_cjyyzz > 0 ? html +='<li><img src="/" /><br/><span>厂家营业执照('+item.n08_cjyyzz+')</span></li>':'';
								item.n09_cjscxkz > 0 ? html +='<li><img src="/" /><br/><span>厂家许可证('+item.n09_cjscxkz+')</span></li>':'';
								item.n04_jxsqs > 0 ? html +='<li><img src="/" /><br/><span>授权书('+item.n04_jxsqs+')</span></li>':'';
								item.n05_xsrywts > 0 ? html +='<li><img src="/" /><br/><span>委托书('+item.n05_xsrywts+')</span></li>':'';
								item.n06_shfwcns > 0 ? html +='<li><img src="/" /><br/><span>承诺书('+item.n06_shfwcns+')</span></li>':'';
								item.n07_jksjbg > 0 ? html +='<li><img src="/" /><br/><span>商检报告('+item.n07_jksjbg+')</span></li>':'';
								item.n11_cjhcsprz > 0 ? html +='<li><img src="/" /><br/><span>3C认证('+item.n11_cjhcsprz+')</span></li>':'';
								item.n12_cjxdcpwsxkz > 0 ? html +='<li><img src="/" /><br/><span>卫生许可证('+item.n12_cjxdcpwsxkz+')</span></li>':'';							
								html +='</ul>\n\
						</td>\n\
						<td><a href=\"javascript:showLayer(\'/321132102?aid='+item.puk+'&type=\', 900, true);\" class=\"btn\">审核</a></td>\n\
					</tr>';
				});
				$("#tsaudit-list").html(html);
			}else{
				$("#tsaudit-list").info("暂无信息。");
			}
			var newpage = new createPage(data.result.total,page,pageSize,3);
			$("#tsaudit-pages").html(newpage.pageHtml);
			hoverTable(".tsauditCertTable",true);
		}
	});
}

//审核换证信息
function getAuditCert(page){
	page = page || 1;
	var pageSize = 10;
	$("#audit-cert-list").empty();
	removeInfo();
	ajax("/321132101?t="+Math.random(),{"page":page,"pageSize":pageSize,"type":"1"},function(data){
		var html = "";
		if(data.code == 0){
			if(data.result.list.length > 0){
				$.each(data.result.list,function(index, item){
					html +="<tr>\n\
						<td class=\"center\"><input type=\"checkbox\" class=\"checkbox\" name=\"puk\" gys=\""+item.k01_gysid+"\" value=\""+item.puk+"\"><label></label></td>\n\
						<td>"+(item.fb4 !='a'? getCname(item.fb4) : item.eb2 )+"</br>"+item.eb3+"至"+item.eb4+"</td>\n\
						<td><h3>"+item.fb1+"</h3><img src=\"/resources/images/icon/tb_3.png\" align=\"absmiddle\" /> "+item.fb2+" "+item.fb3+"</td>\n\
						<td class=\"gray\">"+item.cc1+"</td>\n\
						<td class=\"gray\"><a href=\"javascript:showLayer('/32113210100?aid="+item.puk+"&type="+item.fb4+"', 900, true);\" class=\"btn\">审核</a></td>\n\
					</tr>";
				});
				$("#audit-cert-list").html(html);
			}else{
				$("#audit-cert-list").info("暂无信息。");
			}
			var newpage = new createPage(data.result.total,page,pageSize,3);
			$("#cert-pages").html(newpage.pageHtml);
			hoverTable(".auditCertTable",true, true);
			$(".checkbox").checkbox();
		}
	});
}

//过期证件
function getOverdueCert(page){
	page = page || 1;
	var pageSize = 10;
	$("#over-cert-list").empty();
	removeInfo();
	ajax("/32200301?t="+Math.random(),{"page":page,"pageSize":pageSize},function(data){
		var html = "";
		if(data.code == 0){
			if(data.result.list.length > 0){
				$.each(data.result.list,function(index, item){
					html +="<tr>\n\
						<td class=\"center\"><input type=\"checkbox\" class=\"checkbox\" name=\"id\" gys=\""+item.k01_gysid+"\" value=\""+item.puk+"\"><label></label></td>\n\
						<td>"+(item.ppp != 'a'?item.k03_zjlb:item.k02_zjbh)+"<br />"+item.f04_yxksrq+"至"+item.f05_yxzzrq+"</td>\n\
						<td><h3>"+item.fb1+"</h3><img src=\"/resources/images/icon/tb_3.png\" align=\"absmiddle\" /> 张小花 15837599483</td>\n\
						<td class=\"gray\">已过期 <span class=\"font16\">"+item.ddd.replace("-","")+"</span> 天</td>\n\
						<td class=\"gray\"><a href=\"javascript:msgTips('#over-cert-list','"+item.puk+"','"+item.k01_gysid+"');\" class=\"btn\">短信提醒</a> <a href=\"javascript:ignore('32200302','"+item.puk+"','"+item.ppp+"');\" class=\"btn\">忽略</a></td>\n\
					</tr>";
				});
				$("#over-cert-list").html(html);
			}else{
				$("#over-cert-list").info("暂无信息。");
			}
			var newpage = new createPage(data.result.count,page,pageSize,3);
			$("#over-pages").html(newpage.pageHtml);
			hoverTable(".overCertTable",true,true);
			$(".checkbox").checkbox();
		}
	});
}

function moddiyCertAudit(type){
	var auditCert = [];
	$("#audit-cert-list>tr input[name='puk']").each(function(){
		if($(this).is(":checked")) auditCert.push($(this).val());
	})
	ajax("/32113210102?t="+Math.random(),{"puk":auditCert.join(","),"f13_shzt":type},function(data){
		top.msgText(data.message,true);
		if(data.code == 0) getAuditCert();
	})
}
</script>
</head>
<body>
<div class="contnet">
	<div class="boxContent">
		<div class="tagBox">
			<div class="tag {movetag:'span',showtag:'.tagBox .tagNav',addsclass:'tagnSelect',addtclass:'tagSelect',time:9999999}"> 
				<span class="tagSelect">审核供应商</span>
				<span>审核推送证件</span>
				<span>审核换证信息</span>
				<span>已过期证件</span>
			</div>
			<div class="tagBox">
				<div class="tagNav tagnSelect">
					<div class="tablelist" style="height:450px;">
						<table class="table hoverTable auditTable" width="100%" cellspacing="0" cellpadding="0" border="0">
							<thead>
								<tr>
									<th width="6%"></th>
									<th width="20%"></th>
									<th></th>
									<th width="13%"></th>
									<th width="13%"></th>
								</tr>
							</thead>
							<tbody id="audit-list">
							</tbody>
						</table>
					</div>
					<div id="audit-pages" class="pages"></div>
				</div>
				<div class="tagNav">
					<div class="tablelist" style="height:450px;">
						<table class="table hoverTable tsauditCertTable" width="100%" cellspacing="0" cellpadding="0" border="0">
							<thead>
								<tr>
									<th width="8%"></th>
									<th width="22%">供应商名称</th>
									<th>推送证件</th>
									<th width="10%">操作</th>
								</tr>
							</thead>
							<tbody id="tsaudit-list">
							</tbody>
						</table>
					</div>
					<div id="tsaudit-pages" class="pages"></div>
				</div>
				<div class="tagNav">
					<a href="javascript:moddiyCertAudit(0);" class="btn mTop10">全部审核通过</a>
					<div class="tablelist mTop10" style="height:450px;">
						<table class="table hoverTable auditCertTable" width="100%" cellspacing="0" cellpadding="0" border="0">
							<thead>
								<tr>
									<th width="6%"><input type="checkbox" class="checkbox {click:selectAll}" value="全选"></th>
									<th width="30%">证件名称</th>
									<th>供应商名称</th>
									<th width="15%">更换时间</th>
									<th width="8%">操作</th>
								</tr>
							</thead>
							<tbody id="audit-cert-list">
							</tbody>
						</table>
					</div>
					<div id="cert-pages" class="pages"></div>
				</div>
				<div class="tagNav">
					<a href="javascript:msgTips('#over-cert-list');" class="btn mTop10">短信提醒</a>
					<div class="tablelist mTop10" style="height:450px;">
						<table class="table hoverTable overCertTable" width="100%" cellspacing="0" cellpadding="0" border="0">
							<thead>
								<tr>
									<th width="6%"><input type="checkbox" class="checkbox" value=""><label>全选</label></th>
									<th>过期内容</th>
									<th>供应商</th>
									<th></th>
									<th width="15%">操作</th>
								</tr>
							</thead>
							<tbody id="over-cert-list">
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
</body>
<%@ include file="/resources/jsp/formJS.jsp"%>
</html>