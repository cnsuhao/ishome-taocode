<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" import="org.jfpc.framework.helper.*" %>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<title>供应商查看医院</title>
<meta name="description" content="">
<meta name="keywords" content="">
<link rel="stylesheet" type="text/css" href="/resources/css/base.css">
<link rel="stylesheet" type="text/css" href="/resources/css/jquery.mCustomScrollbar.css">
<link rel="stylesheet" type="text/css" href="/resources/css/supplier_cert.css">
<%@ include file="/resources/jsp/yy/inc.jsp" %>
<script src="/resources/js/jquery.js"></script>
<script src="/resources/js/jquery.mousewheel.js"></script>
<script src="/resources/js/jquery.mCustomScrollbar.js"></script>
<script src="/resources/js/function.js"></script>
<script src="/resources/js/jq.ui.js"></script>
<script src="/resources/js/tableFixed.min.js"></script>
<script>
var index = 0;
$(function(){
	resetText();
	$(".textTips").input();
	$("#listOrder>span").each(function(){
		$(this).click(function(){
			index = $(this).index();
			$(this).addClass("active").siblings().removeClass("active");
			getCustomerList();
		});
	});
})
function getCustomerList(page){
	//page = page || 1;
	//var html = "", pageSize = 10, keywords = $("#keywords").val();
	page = page || 1;
    var html = "", pageSize = 10, keywords = $("#keywords").val();
    var data = {"page":page, "pageSize":pageSize,"orderBy":index,"keywords":keywords};
    $("#list").empty();
    removeInfo();
	ajax("/31022001/1?t="+Math.random(),data,function(data){
		if(data.code == 0){
			if(data.result.length > 0){
				$.each(data.result,function(index, item){
					html +='<tr>\n\
                        <td class="center"><sapn class="mlogo mlogo-min">'+splitLogo(item.f01_qyqc,item.f19_logo_url)+'</sapn></td>\n\
                        <td><h3>'+item.f01_qyqc+'</h3><img src="/resources/images/icon/tb_3.png" align="absmiddle" /> '+item.f30_lxrxm+' '+item.f32_lxrdh+' '+item.f16_lxdh+'</td>\n\
                        <td class="center">\n\
                            <ul class="mTop10 clearfix tableListImg">';
                            item.t01_gys_yyzz > 0 ? html +='<li><img src="/" /><br/><span>营业执照</span></li>':'';
                            item.t02_gys_jyxkz > 0 ? html +='<li><img src="/" /><br/><span>经营许可证</span></li>':'';
                            item.t03_gys_swdjz > 0 ? html +='<li><img src="/" /><br/><span>税务登记证</span></li>':'';
                            item.t09_cj_spzcz > 0 ? html +='<li><img src="/" /><br/><span>注册证('+item.t09_cj_spzcz+')</span></li>':'';
                            item.t07_cj_yyzz > 0 ? html +='<li><img src="/" /><br/><span>生产厂家营业执照('+item.t07_cj_yyzz+')</span></li>':'';
                            item.t08_cj_scxkz > 0 ? html +='<li><img src="/" /><br/><span>生产厂家许可证('+item.t08_cj_scxkz+')</span></li>':'';
                            item.t04_gys_jxsqs > 0 ? html +='<li><img src="/" /><br/><span>授权书('+item.t04_gys_jxsqs+')</span></li>':'';
                            item.t05_gys_xsrywts > 0 ? html +='<li><img src="/" /><br/><span>委托书('+item.t05_gys_xsrywts+')</span></li>':'';
                            item.t06_gys_shfwcns > 0 ? html +='<li><img src="/" /><br/><span>承诺书</span></li>':'';
                            item.n07_jksjbg > 0 ? html +='<li><img src="/" /><br/><span>商检报告('+item.n07_jksjbg+')</span></li>':'';
                            item.n11_cjhcsprz > 0 ? html +='<li><img src="/" /><br/><span>3C认证('+item.n11_cjhcsprz+')</span></li>':'';
                            item.n12_cjxdcpwsxkz > 0 ? html +='<li><img src="/" /><br/><span>卫生许可证('+item.n12_cjxdcpwsxkz+')</span></li>':'';
                            html +='</ul>\n\
                        </td>\n\
                        <td class="gray">\n\
                        <a href="javascript:showLayer(\'/3120090/' + item.puk + '\', 900, true);" class="btn minBtn"> 推送证书</a>&nbsp;&nbsp;<a href="" class="btn minBtn"  onclick="del(\'/311100003/\', '+ item.puk +')"> 解除关系</a></td>\n\
					</tr>';
				});
				$("#list").html(html);
			}else{
				$("#list").info("暂无信息.");
			}
			var newpage = new createPage(data.count, page, pageSize, 3);
			$("#pages").html(newpage.pageHtml);
			hoverTable(".hoverTable",true,true);
		}
	});
}

pageGo = getCustomerList;

function del(url, id){
	confirm("您确定要删除该信息吗？", function(){
		ajax({url:url+id+"?t="+Math.random(),data:{},callBack:function(data){
	    	 top.msgText(data.message,true);
	         post(document.URL);
	    },load:false});
	});
}
</script>
</head>
<body onload="getCustomerList()">
<input type="hidden" id="listId" />
<div class="contnet" id="yui-contentBox">
	<div class="boxContent">
		<div class="rFloat">
			<div class="listOrder" id="listOrder">
			<strong>排序：</strong>
			<span class="active">首字母</span>
			<span>添加时间</span>
			<!-- <span>已添加未注册(<em id="count">4</em>)</span> -->
			</div>
			<input id="keywords" type="text" copy="search" size="30" class="text textTips {text:'请输入医院名称'}"  name="keywords" value="">
			<input type="button" icon="search" onclick="getCustomerList();" />
		</div>
		<a href="javascript:showLayer('/31022002', 600, true);" class="btn">添加医院</a>
		<div class="tablelist mTop10" style="height:500px;">
			<table class="table hoverTable" width="100%" cellspacing="0" cellpadding="0" border="0">
				<thead>
					<tr>
						<th width="8%"></th>
						<th width="22%">名称</th>
						<th>相关证件</th>
						<th width="15%">操作</th>
					</tr>
				</thead>
				<tbody id="list">
				</tbody>
			</table>
		</div>
		<div class="pages" id="pages"></div>
	</div>
</div>
</body>
<%@ include file="/resources/jsp/formJS.jsp" %>
</html>