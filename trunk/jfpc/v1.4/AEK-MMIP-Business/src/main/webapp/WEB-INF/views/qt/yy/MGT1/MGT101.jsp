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
<link rel="stylesheet" type="text/css" href="/resources/css/jquery.mCustomScrollbar.css">
<link rel="stylesheet" type="text/css" href="/resources/css/hospital_cert.css">
<%@ include file="/resources/jsp/yy/inc.jsp" %>
<style type="text/css">
.lInput{text-align: left;}
.publicYUCList li{height: 255px;}
.pubicYUIList li{height: 150px;}
</style>
<script src="/resources/js/jquery.js"></script>
<script src="/resources/js/jquery.mousewheel.js"></script>
<script src="/resources/js/jquery.mCustomScrollbar.js"></script>
<script src="/resources/js/function.js"></script>
<script src="/resources/js/jq.ui.js"></script>
<script src="/resources/js/tableFixed.min.js"></script>
<script>
$(function(){
	resetText();
	$(".tag").uiSwitch();
	tagClick();
	$(".tag>span").eq(getUrlParam('index') || 0).click();
})

function tagClick(){
	$(".tag>span").click(function(){
		var index = $(this).index();
		switch(index){
			case 0:
				$(".businessTips").input();
				$("#listOrder>span").each(function(){
					$(this).click(function(){
						$(this).addClass("active").siblings().removeClass("active");
						getBusiness();
					});
				});
				getBusiness();
				pageGo = getBusiness;
			break;
			case 1:
				$(".licenseTips").input();
				$("#businessListOrder>span").each(function(){
					$(this).click(function(){
						$(this).addClass("active").siblings().removeClass("active");
						getBusinessLicense();
					});
				});
				getBusinessLicense();
				pageGo = getBusinessLicense;
			break;
			case 2:
				$(".taxTips").input();
				$("#taxOrder>span").each(function(){
					$(this).click(function(){
						$(this).addClass("active").siblings().removeClass("active");
						getBusinessTax();
					});
				});
				getBusinessTax();
				pageGo = getBusinessTax;
			break;
			case 3:
				$(".regTips").input();
				getRegCert();
				pageGo = getRegCert;
			break;
			case 4:
				$(".manufacturTips").input();
				$("#manufacturOrder>span").each(function(){
					$(this).click(function(){
						$(this).addClass("active").siblings().removeClass("active");
						getManufactur();
					});
				});
				getManufactur();
				pageGo = getManufactur;
			break;
			case 5:
				$(".manufacturTaxTips").input();
				$("#manufacturTaxOrder>span").each(function(){
					$(this).click(function(){
						$(this).addClass("active").siblings().removeClass("active");
						getManufacturTax();
					});
				});
				getManufacturTax();
				pageGo = getManufacturTax;
			break;
			case 6:
				$(".authorizaTips").input();
				$("#authorizaOrder>span").each(function(){
					$(this).click(function(){
						$(this).addClass("active").siblings().removeClass("active");
						getAuthoriza();
					});
				});
				getAuthoriza();
				pageGo = getAuthoriza;
			break;
			case 7:
				$(".attorneyTips").input();
				$("#attorneyOrder>span").each(function(){
					$(this).click(function(){
						$(this).addClass("active").siblings().removeClass("active");
						getAttorney();
					});
				});
				getAttorney();
				pageGo = getAttorney;
			break;
			case 8:
				$(".commitmentTips").input();
				$("#commitmentOrder>span").each(function(){
					$(this).click(function(){
						$(this).addClass("active").siblings().removeClass("active");
						getCommitment();
					});
				});
				getCommitment();
				pageGo = getCommitment;
			break;
		}
	})
}

function getBusiness(page){
	page = page || 1;
	var html = "", pageSize = 10, keywords = $("#keywords1").val(), index = $("#listOrder>span.active").index();
	$("#business-list").empty();
	removeInfo();
	ajax("/32101002?t="+Math.random(),{"act":"list","page":page, "pageSize":pageSize,"orderBy":index,"keywords":keywords},function(data){
		if(data.code == 0){
			if(data.result.list.length > 0){
				$.each(data.result.list,function(index, item){
					html +='<li>\n\
				    		<a href="/32113210103?zid='+item.puk+'&type=1" target="_blank"><img src="'+cateImage(item.bbb)+'"></a>\n\
				    		<p>'+item.fb1+'<br /><span class="gray">'+item.f04_yxksrq+'至'+item.f05_yxzzrq+'</span></p>\n\
				    		<div class="dataMdofiy"><a href="/32113210103?zid='+item.puk+'&cid='+item.k01_gysid+'&type=1" class="view" target="_blank">看图</a>&nbsp;&nbsp;<a href="javascript:removeCred(\'32101003\',\'0\','+item.puk+')" class="del">删除</a></div>\n\
				    	</li>';
				});
				$("#business-list").html(html);
			}else{
				$("#business-list").info("暂无信息.");
			}
			var newpage = new createPage(data.result.count, page, pageSize, 3);
			$("#business-pages").html(newpage.pageHtml);
			hoverTable(".hoverTable",true,true);
		}
	});
}

function getBusinessLicense(page){
	page = page || 1;
	var html = "", pageSize = 10, keywords = $("#keywords2").val(), index = $("#businessListOrder>span.active").index();
	$("#business-license-list").empty();
	removeInfo();
	ajax("32102002?t="+Math.random(),{"act":"list","page":page, "pageSize":pageSize,"orderBy":index,"keywords":keywords},function(data){
		if(data.code == 0){
			if(data.result.list.length > 0){
				$.each(data.result.list,function(index, item){
					html +='<li>\n\
						<a href="/32113210103?zid='+item.puk+'&type=2" target="_blank"><img src="'+cateImage(item.bbb)+'"></a>\n\
			    		<p>'+item.fb1+'<br /><span class="gray">'+item.f04_yxksrq+'至'+item.f05_yxzzrq+'</span></p>\n\
			    		<div class="dataMdofiy"><a href="/32113210103?zid='+item.puk+'&cid='+item.k01_gysid+'&type=2" class="view" target="_blank">看图</a>&nbsp;&nbsp;<a href="javascript:removeCred(\'32102003\',\'1\','+item.puk+')" class="del">删除</a></div>\n\
			    	</li>';
				});
				$("#business-license-list").html(html);
			}else{
				$("#business-license-list").info("暂无信息。");
			}
			var newpage = new createPage(data.result.count, page, pageSize, 3);
			$("#business-license-pages").html(newpage.pageHtml);
			hoverTable(".hoverTable",true,true);
		}
	});
}

function getBusinessTax(page){
	page = page || 1;
	var html = "", pageSize = 10, keywords = $("#keywords3").val(), index = $("#taxOrder>span.active").index();
	$("#business-tax-list").empty();
	removeInfo();
	ajax("/32103002?t="+Math.random(),{"act":"list","page":page, "pageSize":pageSize,"orderBy":index,"keywords":keywords},function(data){
		if(data.code == 0){
			if(data.result.list.length > 0){
				$.each(data.result.list,function(index, item){
					html +='<li>\n\
						<a href="/32113210103?zid='+item.puk+'&type=3" target="_blank"><img src="'+cateImage(item.bbb)+'"></a>\n\
			    		<p>'+item.fb1+'<br /><span class="gray">'+item.f04_yxksrq+'至'+item.f05_yxzzrq+'</span></p>\n\
			    		<div class="dataMdofiy"><a href="/32113210103?zid='+item.puk+'&cid='+item.k01_gysid+'&type=3" class="view" target="_blank">看图</a>&nbsp;&nbsp;<a href="javascript:removeCred(\'32103003\',\'2\','+item.puk+')" class="del">删除</a></div>\n\
			    	</li>';
				});
				$("#business-tax-list").html(html);
			}else{
				$("#business-tax-list").info("暂无信息。");
			}
			var newpage = new createPage(data.result.count, page, pageSize, 3);
			$("#business-tax-pages").html(newpage.pageHtml);
			hoverTable(".hoverTable",true,true);
		}
	});
}

function getRegCert(page){
	page = page || 1;
	var html = "", pageSize = 10, keywords = $("#keywords4").val();
	$("#regcert-list").empty();
	removeInfo();
	ajax("/32110002?t="+Math.random(),{"page":page, "pageSize":pageSize,"keywords":keywords},function(data){
		if(data.code == 0){
			if(data.result.list.length > 0){
				$.each(data.result.list,function(index, item){
					html +='<tr>\n\
						<td>'+item.f01_zczzwmc+'</td>\n\
						<td>'+item.f03_cpzwmc+'</td>\n\
						<td>'+item.f09_yxksrq+'至'+item.f10_yxzzrq+'</td>\n\
						<td>'+item.fb1+'</td>\n\
						<td>'+item.f32_scqyzwmc+'</td>\n\
						<td><a href="/32113210103?zid='+item.puk+'&cid='+item.p01_gysid+'&type=9" class="btn minBtn" target="_blank">看图</a>&nbsp;&nbsp;<a href="javascript:removeCred(\'32110003\',\'3\','+item.puk+')" class="btn minBtn">删除</a>&nbsp;&nbsp;<a href="javascript:top.showWindow(\'\',\'/32110004?cid='+item.puk+'\', 900, 500, true);" class="btn minBtn">回溯</a></td>\n\
					</tr>';
				});
				$("#regcert-list").html(html);
				hoverTable(".hoverTable",true,true);
			}else{
				$("#regcert-list").info("暂无信息。");
			}
			var newpage = new createPage(data.result.count, page, pageSize, 3);
			$("#regcert-pages").html(newpage.pageHtml);
		}
	});
}

function getManufactur(page){
	page = page || 1;
	var html = "", pageSize = 10, keywords = $("#keywords5").val(), index = $("#manufacturOrder>span.active").index();
	$("#manufactur-list").empty();
	removeInfo();
	ajax("/32108002?t="+Math.random(),{"act":"list","page":page, "pageSize":pageSize,"orderBy":index,"keywords":keywords},function(data){
		if(data.code == 0){
			if(data.result.list.length > 0){
				$.each(data.result.list,function(index, item){
					html +='<li>\n\
						<a href="/32113210103?zid='+item.puk+'&cid='+item.k01_gysid+'&type=7" target="_blank"><img src="'+cateImage(item.bbb)+'"></a>\n\
			    		<p>'+item.fb1+'<br /><span class="gray">'+item.f04_yxksrq+'至'+item.f05_yxzzrq+'</span></p>\n\
			    		<div class="dataMdofiy"><a href="/32113210103?zid='+item.puk+'&cid='+item.k01_gysid+'&type=7" class="view" target="_blank">看图</a>&nbsp;&nbsp;<a href="javascript:removeCred(\'32108003\',\'4\','+item.puk+')" class="del">删除</a></div>\n\
			    	</li>';
				});
				$("#manufactur-list").html(html);
			}else{
				$("#manufactur-list").info("暂无信息。");
			}
			var newpage = new createPage(data.result.count, page, pageSize, 3);
			$("#manufactur-pages").html(newpage.pageHtml);
			hoverTable(".hoverTable",true,true);
		}
	});
}


function getManufacturTax(page){
	page = page || 1;
	var html = "", pageSize = 10, keywords = $("#keywords6").val(), index = $("#manufacturTaxOrder>span.active").index();
	$("#manufactur-tax-list").empty();
	removeInfo();
	ajax("/32109002?t="+Math.random(),{"act":"list","page":page, "pageSize":pageSize,"orderBy":index,"keywords":keywords},function(data){
		if(data.code == 0){
			if(data.result.list.length > 0){
				$.each(data.result.list,function(index, item){
					html +='<li>\n\
						<a href="/32113210103?zid='+item.puk+'&cid='+item.k01_gysid+'&type=8" target="_blank"><img src="'+cateImage(item.bbb)+'"></a>\n\
			    		<p>'+item.fb1+'<br /><span class="gray">'+item.f04_yxksrq+'至'+item.f05_yxzzrq+'</span></p>\n\
			    		<div class="dataMdofiy"><a href="/32113210103?zid='+item.puk+'&cid='+item.k01_gysid+'&type=8" class="view" target="_blank">看图</a>&nbsp;&nbsp;<a href="javascript:removeCred(\'32109003\',\'5\','+item.puk+')" class="del">删除</a></div>\n\
			    	</li>';
				});
				$("#manufactur-tax-list").html(html);
			}else{
				$("#manufactur-tax-list").info("暂无信息。");
			}
			var newpage = new createPage(data.result.count, page, pageSize, 3);
			$("#manufactur-tax-pages").html(newpage.pageHtml);
			hoverTable(".hoverTable",true,true);
		}
	});
}

function getAuthoriza(page){
	page = page || 1;
	var html = "", pageSize = 10, keywords = $("#keywords7").val(), index = $("#authorizaOrder>span.active").index();
	$("#authoriza-list").empty();
	removeInfo();
	ajax("/32104002?t="+Math.random(),{"act":"list","page":page, "pageSize":pageSize,"orderBy":index,"keywords":keywords},function(data){
		if(data.code == 0){
			if(data.result.list.length > 0){
				$.each(data.result.list,function(index, item){
					html +='<tr>\n\
						<td>'+item.f08+'</td>\n\
						<td>'+item.fb1+'</td>\n\
						<td>'+item.f04_yxksrq+'至'+item.f05_yxzzrq+'</td>\n\
						<td><a href="/32113210103?zid='+item.puk+'&cid='+item.k01_gysid+'&type=4" class="view"> 看图</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:removeCred(\'32104003\',\'6\','+item.puk+')"><img src=\"/resources/images/icon/i_del.png\" /> 删除</a></td>\n\
					</tr>';
				});
				$("#authoriza-list").html(html);
			}else{
				$("#authoriza-list").info("暂无信息。");
			}
			var newpage = new createPage(data.result.count, page, pageSize, 3);
			$("#authoriza-pages").html(newpage.pageHtml);
			hoverTable(".hoverTable",true,true);
		}
	});
}

function getAttorney(page){
	page = page || 1;
	var html = "", pageSize = 10, keywords = $("#keywords8").val(), index = $("#attorneyOrder>span.active").index();
	$("#attorney-list").empty();
	removeInfo();
	ajax("/32105002?t="+Math.random(),{"act":"list","page":page, "pageSize":pageSize,"orderBy":index,"keywords":keywords},function(data){
		if(data.code == 0){
			if(data.result.list.length > 0){
				$.each(data.result.list,function(index, item){
					html +='<li>\n\
						<a href="/32113210103?zid='+item.puk+'&cid='+item.k01_gysid+'&type=5" class="btn minBtn" target="_blank"><sapn class="mlogo mlogo-max">'+splitlogo(item.f10, item.bbb)+'</sapn></a>\n\
			    		<p class="mTop10" style="text-align:center;">'+item.f10+'<br /><span class="gray">'+item.fb1+'</span></p>\n\
			    		<div class="dataMdofiy">\n\
			    		<a href="/32113210103?zid='+item.puk+'&cid='+item.k01_gysid+'&type=5" class="view" target="_blank">看图</a>&nbsp;&nbsp;<a href="javascript:removeCred(\'32105003\',\'7\','+item.puk+')" class="del">删除</a>\n\
			    		</div>\n\
			    	</li>';
				});
				$("#attorney-list").html(html);
			}else{
				$("#attorney-list").info("暂无信息。");
			}
			var newpage = new createPage(data.result.count, page, pageSize, 3);
			$("#attorney-pages").html(newpage.pageHtml);
			hoverTable(".hoverTable",true,true);
		}
	});
}

function getCommitment(page){
	page = page || 1;
	var html = "", pageSize = 10, keywords = $("#keywords9").val(), index = $("#commitmentOrder>span.active").index();
	$("#commitment-list").empty();
	removeInfo();
	ajax("/32106002?t="+Math.random(),{"act":"list","page":page, "pageSize":pageSize,"orderBy":index,"keywords":keywords},function(data){
		if(data.code == 0){
			if(data.result.list.length > 0){
				$.each(data.result.list,function(index, item){
					html +='<li>\n\
						<a href="/32113210103?zid='+item.puk+'&cid='+item.k01_gysid+'&type=6" target="_blank"><img src="'+cateImage(item.bbb)+'"></a>\n\
			    		<p>'+item.fb1+'<br /><span class="gray">'+item.f04_yxksrq+'至'+item.f05_yxzzrq+'</p>\n\
			    		<div class="dataMdofiy"><a href="/32113210103?zid='+item.puk+'&cid='+item.k01_gysid+'&type=6" class="view" target="_blank">看图</a>&nbsp;&nbsp;<a href="javascript:removeCred(\'32106003\',\'8\','+item.puk+')" class="del">删除</a></div>\n\
			    	</li>';
				});
				$("#commitment-list").html(html);
			}else{
				$("#commitment-list").info("暂无信息。");
			}
			var newpage = new createPage(data.result.count, page, pageSize, 3);
			$("#commitment-pages").html(newpage.pageHtml);
			hoverTable(".hoverTable",true,true);
		}
	});
}

function removeCred(bid,index,creid){
	ajax(bid,{'puk':creid},function(json){
		if(json.code == 0){
			alert(json.message);
			if(index=='0')
				getBusiness();
			if(index=='1')
				getBusinessLicense();
			if(index=='2')
				getBusinessTax();
			if(index=='3')
				getRegCert();
			if(index=='4')
				getManufactur();
			if(index=='5')
				getManufacturTax();
			if(index=='6')
				getAuthoriza();
			if(index=='7')
				getAttorney();
			if(index=='8')
				getCommitment();
		}else{
			alert(json.message);
		}
	})
}

function showIamge(cid,zid,type){
	showLayer("/32113210100?cid="+cid+"&zid="+zid+"&type="+type, 900, true);
}
</script>
</head>
<body>
<div class="contnet" id="yui-contentBox">
	<div class="boxContent">
		<div class="tagBox">
			<div class="tag {movetag:'span',showtag:'.tagBox .tagNav',addsclass:'tagnSelect',addtclass:'tagSelect',time:9999999}"> 
				<span class="tagSelect">营业执照</span>
				<span>经营许可证</span>
				<span>税务登记证</span>
				<span>医疗器械注册证</span>
				<span>厂家营业执照</span>
				<span>厂家生产许可证</span>
				<span>授权书</span>
				<span>委托书</span>
				<span>承诺书</span>
			</div>
			<div class="tagBox">
				<div class="tagNav tagnSelect">
					<div class="mTop10" align="right">
						<div class="listOrder" id="listOrder">
						<strong>排序：</strong>
						<span class="active">未过期</span>
						<span>已过期</span>
						</div>
						<input id="keywords1" type="text" copy="search" size="30" class="text businessTips {text:'请输入供应商名称'}"  name="keywords" value="">
						<input type="button" icon="search" onclick="getBusiness();" />
					</div>
					<ul class="publicYUCList mTop10 clearfix" id="business-list"></ul>
					<div class="pages" id="business-pages"></div>
				</div>
				<div class="tagNav">
					<div class="mTop10" align="right">
						<div class="listOrder" id="businessListOrder">
						<strong>排序：</strong>
						<span class="active">未过期</span>
						<span>已过期</span>
						</div>
						<input id="keywords2" type="text" copy="search" size="30" class="text licenseTips {text:'请输入供应商名称'}"  name="keywords" value="">
						<input type="button" icon="search" onclick="getBusinessLicense();" />
					</div>
					<ul class="publicYUCList mTop10 clearfix" id="business-license-list"></ul>
					<div class="pages" id="business-license-pages"></div>
				</div>
				<div class="tagNav">
					<div class="mTop10" align="right">
						<div class="listOrder" id="taxOrder">
						<strong>排序：</strong>
						<span class="active">未过期</span>
						<span>已过期</span>
						</div>
						<input id="keywords3" type="text" copy="search" size="30" class="text taxTips {text:'请输入供应商名称'}"  name="keywords" value="">
						<input type="button" icon="search" onclick="getBusinessTax();" />
					</div>
					<ul class="publicYUCList mTop10 clearfix" id="business-tax-list"></ul>
					<div class="pages" id="business-tax-pages"></div>
				</div>
				<div class="tagNav">
					<div class="mTop10" align="right">
						<input id="keywords4" type="text" copy="search" size="40" class="text regTips {text:'请输入注册证号、产品名称'}"  name="keywords" value="${name}">
						<input type="button" icon="search" onclick="getRegCert();" />
					</div>
					<div class="tablelist mTop10" style="height:450px;">
						<table class="table hoverTable" width="100%" cellspacing="0" cellpadding="0" border="0">
							<thead>
								<tr>
									<th width="22%">医疗器械注册证号</th>
									<th>注册证名称</th>
									<th>有效期</th>
									<th>供应商</th>
									<th>生产厂家</th>
									<th width="15%">操作</th>
								</tr>
							</thead>
							<tbody id="regcert-list">
							</tbody>
						</table>
					</div>
					<div class="pages" id="regcert-pages"></div>
				</div>
				<div class="tagNav">
					<div class="mTop10" align="right">
						<div class="listOrder" id="manufacturOrder">
						<strong>排序：</strong>
						<span class="active">未过期</span>
						<span>已过期</span>
						</div>
						<input id="keywords5" type="text" copy="search" size="30" class="text manufacturTips {text:'请输入生产厂家名称'}"  name="keywords" value="">
						<input type="button" icon="search" onclick="getManufactur();" />
					</div>
					<ul class="publicYUCList mTop10 clearfix" id="manufactur-list"></ul>
					<div class="pages" id="manufactur-pages"></div>
				</div>
				<div class="tagNav">
					<div class="mTop10" align="right">
						<div class="listOrder" id="manufacturTaxOrder">
						<strong>排序：</strong>
						<span class="active">未过期</span>
						<span>已过期</span>
						</div>
						<input id="keywords6" type="text" copy="search" size="30" class="text manufacturTaxTips {text:'请输入生产厂家名称'}"  name="keywords" value="">
						<input type="button" icon="search" onclick="getManufacturTax();" />
					</div>
					<ul class="publicYUCList mTop10 clearfix" id="manufactur-tax-list"></ul>
					<div class="pages" id="manufactur-tax-pages"></div>
				</div>
				<div class="tagNav">
					<div class="mTop10" align="right">
						<div class="listOrder" id="authorizaOrder">
						<strong>排序：</strong>
						<span class="active">未过期</span>
						<span>已过期</span>
						</div>
						<input id="keywords7" type="text" copy="search" size="30" class="text authorizaTips {text:'请输入单位名称'}"  name="keywords" value="">
						<input type="button" icon="search" onclick="getAuthoriza();" />
					</div>
					<div class="tablelist mTop10" style="height:450px;">
						<table class="table hoverTable" width="100%" cellspacing="0" cellpadding="0" border="0">
							<thead>
								<tr>
									<th>授权单位</th>
									<th>被授权单位</th>
									<th>有效期</th>
									<th width="10%">操作</th>
								</tr>
							</thead>
							<tbody id="authoriza-list">
							</tbody>
						</table>
					</div>
					<div class="pages" id="authoriza-pages"></div>
				</div>
				<div class="tagNav">
					<div class="mTop10" align="right">
						<div class="listOrder" id="attorneyOrder">
						<strong>排序：</strong>
						<span class="active">未过期</span>
						<span>已过期</span>
						</div>
						<input id="keywords8" type="text" copy="search" size="40" class="text attorneyTips {text:'请输入供应商名称、业务员姓名'}"  name="keywords" value="">
						<input type="button" icon="search" onclick="getAttorney();" />
					</div>
					<ul class="pubicYUIList clearfix mTop10" id="attorney-list"></ul>
					<div class="pages" id="attorney-pages"></div>
				</div>
				<div class="tagNav">
					<div class="mTop10" align="right">
						<div class="listOrder" id="commitmentOrder">
						<strong>排序：</strong>
						<span class="active">未过期</span>
						<span>已过期</span>
						</div>
						<input id="keywords9" type="text" copy="search" size="30" class="text commitmentTips {text:'请输入供应商名称'}"  name="keywords" value="">
						<input type="button" icon="search" onclick="getCommitment();" />
					</div>
					<ul class="publicYUCList mTop10 clearfix" id="commitment-list"></ul>
					<div class="pages" id="commitment-pages"></div>
				</div>
			</div>
		</div>
	</div>
</div>
</body>
<%@ include file="/resources/jsp/formJS.jsp" %>
</html>