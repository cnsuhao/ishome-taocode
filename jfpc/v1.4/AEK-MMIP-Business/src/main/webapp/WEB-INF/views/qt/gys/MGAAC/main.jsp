<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/taglib/pagetag.tld"  prefix="p" %>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
	<meta charset="utf-8">
	<title>注册证列表</title>
	<%@ include file="/resources/jsp/gys/inc_new.jsp" %>
<link rel="stylesheet" type="text/css" href="/resources/css/base.css">
<link rel="stylesheet" type="text/css" href="/resources/css/jquery.datetimepicker.css">
<link rel="stylesheet" type="text/css" href="/resources/css/supplier_cert.css">
<link rel="stylesheet" href="/resources/css/jquery.mCustomScrollbar.css">
<link rel="stylesheet" type="text/css" href="/resources/css/uploadify.css" />
<style type="text/css">
.lInput{text-align: left;}
.publicYUCList li{height: 245px;}
.pubicYUIList li{height: 145px;}
</style>
<script src="/resources/js/jquery.js"></script>
<script src="/resources/js/jq.ui.js"></script>
<script src="/resources/js/jquery.uploadify.js"></script>
<script src="/resources/js/override.uploadify.js"></script>
<script src="/resources/js/function.js"></script>
<script src="/resources/js/tableFixed.min.js"></script>
<script src="/resources/js/jquery.datetimepicker.js"></script>
<script src="/resources/js/jquery.mousewheel.js"></script>
<script src="/resources/js/jquery.mCustomScrollbar.js"></script>
<script src="/resources/js/validate.js"></script>
<%@ include file="/resources/jsp/ImagePath.jsp" %>
<script>
var index = getUrlParam("index") || 0;
$(function(){
    resetText();
    $(".tag").uiSwitch();
    $(".textTips").input();
    $(".Wdate").datetimepicker({timepicker:false,format:'Y-m-d',enabled:false});
    tagClick();
    $(".tag>span:eq("+index+")").click();
});

function tagClick(index){
	$(".tag>span").click(function(){
		var index = $(this).index();
		switch(index){
			case 0:
				getRegisterCert();
				pageGo = getRegisterCert;
			break;
			case 1:
				getFactoryList();
				pageGo = getFactoryList;
			break;
			case 2:
				getAuthorizationList();
				pageGo = getAuthorizationList;
			break;
			case 3:
				getEntrustList();
				pageGo = getEntrustList;
			break;
			case 4:
				getPromiseList();
				pageGo = getPromiseList;
			break;
			case 5:
				loadUpload({id:'#tag_1',success:uploadSuccess_1});
				loadUpload({id:'#tag_2',success:uploadSuccess_2});
				loadUpload({id:'#tag_3',success:uploadSuccess_3});
				removeInfo();
			break;
		}
	})
}
/**
 * 注册证
 */
function getRegisterCert(page){
	page = page || 1;
	var html = "", pageSize = 10, keywords = $("#f01_zczzwmc").val();
	$("#list").empty();
	removeInfo();
    ajax("/311100000?t="+Math.random(),{"pageCurrent":page, "pageLimit":pageSize,"f01_zczzwmc":keywords},function(data){
        if(data.result.list.length>0){
        	$.each(data.result.list,function(index, item){
            	var csState = '<td>厂商证书已维护 &nbsp;<a class="gray" href="javascript:showLayer(\'/33010001?pukid=' + item.k04_scqyid + '\', 800, true)">修改</a>';
            	var sqsState = '&nbsp;&nbsp;&nbsp;&nbsp;授权书已维护<a class="gray" href="javascript:post(\'/3111000?index=2\')">修改</a></td>';
            	if (item.eb3 == '1' || item.eb4 == '1') {
            		csState = '<td>厂商不全 &nbsp;<a class="gray" href="javascript:showLayer(\'/33010001?pukid=' + item.k04_scqyid + '\', 800, true)">去维护</a>';
            	}
            	if (item.eb5 == '1') {
            		sqsState = '&nbsp;&nbsp;&nbsp;&nbsp;授权书不全<a href="javascript:showLayer(\'/31104001\', 800, true)" class="gray"> &nbsp;去维护</a></td>';
            	}
                html +='<tr>'
                        +'<td>' + item.f01_zczzwmc + '</td>'
                        +'<td>' + item.f03_cpzwmc + '</td>'
                        +'<td>' + item.f09_yxksrq + '至' + item.f10_yxzzrq + '</td>'
                        +csState + sqsState
                        +'<td><a href="javascript:top.showWindow(\'\',\'/311100001/1?pukid=' + item.puk + '\', 900, 450, true);" class="btn minBtn">详情</a> \n\
                        <a href="/311100005/6?pukid=' + item.puk + '" class="btn minBtn" target="_blank">看图</a> \n\
                        <a href="javascript:showLayer(\'/311100001/0?pukid=' + item.puk + '\', 700, true);" class="btn minBtn">编辑</a> \n\
                        <a href="javascript:showLayer(\'/311100001/2?pukid=' + item.puk + '\', 600, true);" class="btn minBtn">换证</a> \n\
                        <a href="javascript:void(0);" onclick="del(0, \'/311100003/\', '+ item.puk +')" class="btn minBtn">删除</a></td>'
                        /* +'<td><img src="/resources/images/icon/i_show.png" align="absmiddle"/>&nbsp;<a href="javascript:showLayer(\'/311100001/1?pukid=' + item.puk + '\', 900, true);">详情</a>&nbsp;&nbsp;\n\
                        <img src="/resources/images/icon/t-view.png" align="absmiddle"/>&nbsp;<a href="javascript:post(\'/311100005/6?pukid=' + item.puk + '\')" target="_blank">看图</a>&nbsp;&nbsp;\n\
                        <img src="/resources/images/icon/i_edit.png" align="absmiddle"/>&nbsp;<a href="javascript:showLayer(\'/311100001/0?pukid=' + item.puk + '\', 700, true);">编辑</a>&nbsp;&nbsp;\n\
                        <img src="/resources/images/icon/t-view.png" align="absmiddle"/>&nbsp;<a href="javascript:showLayer(\'/311100001/2?pukid=' + item.puk + '\', 550, true);">换证</a>&nbsp;&nbsp;\n\
                        <img src="/resources/images/icon/i_del.png" align="absmiddle"/>&nbsp;<a href="javascript:void(0);" onclick="del(0, \'/311100003/\', '+ item.puk +')">删除&nbsp;&nbsp;</a></td>' */
                    +'</tr>';
            });
            $("#list").html(html);
        }else{
        	$("#list").info("暂无数据。");
        }
        var newpage = new createPage(data.result.pageCount, page, pageSize, 3);
		$("#registerCert-page").html(newpage.pageHtml);
		$("#ylqxCount").text(data.result.list.length );
         hoverTable(".hoverTable",true,true);
    });
}
/**
 * 厂家
 */
function getFactoryList(page){
	page = page || 1;
	var html = "", pageSize = 10;
	$("#list2").empty();
	removeInfo();
    ajax("/3301000?t="+Math.random(),{"pageCurrent":page,"pageLimit":pageSize},function(data){
    	if(data.result.list.length > 0){
    		console.log()
    		 $.each(data.result.list,function(index, item){
    	            html +='<li>'
    	                                +'<a class="mlogo mlogo-max" href="/311100005/7?pukid=' + item.puk + '" target="_blank">' + item.n03_logogjz + '</a>'
    	                                +'<div class="pubicYUIName" style="text-align:center;">' + item.f01_qyqc + '</div>'
    	                                +'<div class="dataMdofiy">'
    	                                    +'<a href="javascript:showLayer(\'/33010001?pukid=' + item.puk + '\', 600, true)" class="edit">编辑</a>&nbsp;&nbsp;<a class="del" href="javascript:void(0);" onclick="del(1, \'/33010003/\','+item.puk+');">删除</a>'
    	                                +'</div>'
    	                            +'</li>';
    	        });
    	        $("#list2").html(html);
    	}else{
    		 $("#list2").info("暂无信息。");
    	}
    	var newpage = new createPage(data.result.pageCount, data.result.pageCurrent, pageSize, 3)
		$("#factory-page").html(newpage.pageHtml);
    	$("#sccjCount").text(data.result.list.length );
    });
}

/**
 * 授权书
 */
function getAuthorizationList(page){
	page = page || 1;
	var html = "", pageSize = 10;
	$("#list3").empty();
	removeInfo();
    ajax("/3110400?t="+Math.random(),{"pageCurrent":page,"pageLimit":pageSize},function(data){
    	if(data.result.list.length > 0){
    		$.each(data.result.list,function(index, item){
                html +='<li>'
                                    +'<img src="<%=ImagePath %>/00003030/' + item.bbb + '/${loginer.token}">'
                                    +'<br><span >' + item.f04_yxksrq + '至' + item.f05_yxzzrq + '</span>'
                                    +'<div class="dataMdofiy">'
                                        +'<a href="/311100005/3?pukid=' + item.puk + '" target="_blank" class="showGlass"></a>'
                                        +'<a href="javascript:showLayer(\'/31104001?pukid=' + item.puk + '\',800, true)" class="edit">编辑</a> <a href="javascript:void(0);" onclick="del(2, \'/31104003/\','+item.puk+');" class="del">删除</a>'
                                    +'</div>'
                                +'</li>';
            });
            $("#list3").html(html);
    	}else{
    		$("#list3").info("暂无信息。");
    	}
    	var newpage = new createPage(data.result.pageCount, page, pageSize, 3);
		$("#authorization-page").html(newpage.pageHtml);
		$("#jxsqCount").text(data.result.list.length );
    });
}
/**
 * 委托书
 */
function getEntrustList(page){
	page = page || 1;
	var html = "", pageSize = 10;
	$("#list4").empty();
	removeInfo();
    ajax("/3110500?t="+Math.random(),{"pageCurrent":page,"pageLimit":pageSize},function(data){
    	if(data.result.list.length > 0){
    		$.each(data.result.list,function(index, item){      
                html +='<li>'
                    +'<a class="mlogo mlogo-max" href="/311100005/4?pukid=' + item.puk + '" target="_blank">' + item.f08 + '</a>'
                    +'<div class="pubicYUIName" style="text-align:center">' + item.f10 + '</div>'
                    +'<div class="dataMdofiy">'
                        +'<a href="javascript:showLayer(\'/31105001?pukid=' + item.puk + '\',600, true)" class="edit">编辑</a>&nbsp;&nbsp;<a href="javascript:void(0);" onclick="del(3, \'/31105003/\','+item.puk+');" class="del">删除</a>'
                    +'</div>'
                +'</li>';
            });
            $("#list4").html(html);
    	}else{
    		$("#list4").info("暂无信息.");
    	}
    	var newpage = new createPage(data.result.pageCount, page, pageSize, 3);
		$("#entrust-page").html(newpage.pageHtml);
		$("#frwtCount").text(data.result.list.length );
    });
}
/**
 * 承诺书
 */
function getPromiseList(page){
	page = page || 1;
	var html = "", pageSize = 10;
    ajax("/3110600?t="+Math.random(),{"pageCurrent":page,"pageLimit":pageSize},function(data){
    	if(data.result.list.length > 0){
	        $.each(data.result.list,function(index, item){
	            html +='<li >'
                          +'<img src="<%=ImagePath %>/00003030/' + item.bbb + '/${loginer.token}">'
                          +'<br><span >' + item.f04_yxksrq + '至' + item.f05_yxzzrq + '</span>'
                          +'<div class="dataMdofiy">'
                              +'<a href="/311100005/5?pukid=' + item.puk + '" class="showGlass" target="_blank"></a>'
                              +'<a href="javascript:showLayer(\'/31106001?pukid=' + item.puk + '\', 600, true)" class="edit">编辑</a> <a href="javascript:void(0);" onclick="del(4, \'/31106003/\','+item.puk+');" class="del">删除</a>'
                          +'</div>'
                      +'</li>';
	        });
	        $("#list5").html(html);
    	}else{
    		$("#list5").info("暂无信息。");
    	}
        var newpage = new createPage(data.result.pageCount, page, pageSize, 3);
		$("#promise-page").html(newpage.pageHtml);
		$("#cnsCount").text(data.result.list.length );
    });
}

//删除
function del(index, url, id){
	confirm("您确定要删除该信息吗？", function(){
		ajax({url:url+id+"?t="+Math.random(),data:{},callBack:function(data){
	    	 top.msgText(data.message,true);
	         post(document.URL.replace(/(\?)*index=\d+/g, '')+"?index="+index);
	    },load:false});
	});
}

function uploadSuccess_1(file, data, response) {
	var json = data.toJSON();
	var img = json.result;
	$("#zjPhoto1_url").val(img);
	$("#zjPhoto1").attr("src",cateImage(img, json.message));
	$("#zjPhoto1Link").attr("href",cateImage(img, json.message));
}

function uploadSuccess_2(file, data, response) {
	var json = data.toJSON();
	var img = json.result;
	$("#zjPhoto2_url").val(img);
	$("#zjPhoto2").attr("src",cateImage(img, json.message));
	$("#zjPhoto2Link").attr("href",cateImage(img, json.message));
}

function uploadSuccess_3(file, data, response) {
	var json = data.toJSON();
	var img = json.result;
	$("#zjPhoto3_url").val(img);
	$("#zjPhoto3").attr("src",cateImage(img,json.message));
	$("#zjPhoto3Link").attr("href",cateImage(img, json.message));
}
function onSubmit(){
    var url=$("#form").attr("action");
	var  parm=$("#form").serializeJSON();
	var vData = $("#form").validateForm({id : '#yui-error-msg'});
	if (!vData.allSuccess) {
		return false;
	}	
	if($("#startDate1").val()>$("#endDate1").val()){
		 top.msgText("营业执照开始时间应该小于等于结束时间！",true);
		 return false;
	}
	if($("#startDate2").val()>$("#endDate2").val()){
		 top.msgText("营业许可证开始时间应该小于等于结束时间！",true);
		 return false;
	}
	if($("#startDate3").val()>$("#endDate3").val()){
		 top.msgText("税务登记证开始时间应该小于等于结束时间！",true);
		 return false;
	}
	ajax({url:url,data:parm,callBack:function(result){
		 top.msgText(result.message,true);
	 },load:false});
	 return false;
}
</script>
<body>
<div class="contnet">
    <div class="boxContent">
        <div class="tagBox">
            <div class="tag {movetag:'span',showtag:'.tagBox .tagNav',addsclass:'tagnSelect',addtclass:'tagSelect',time:9999999}"> 
                <span class="tagSelect">医疗器械注册证<em id="ylqxCount"></em></span>
                <span>生产厂家/总经销商<em id="sccjCount"></em></span>
                <span>经销授权书<em id="jxsqCount"></em></span>
                <span>法人委托书<em id="frwtCount"></em></span>
                <span>承诺书<em id="cnsCount"></em></span>
                <span>经营证件</span>
            </div>
            
            <div class="tagBox">
                <div class="tagNav tagnSelect">
                    <div style="position:relative;" class="mTop10">
                        <a href="javascript:showLayer('/311100001/0', 700, true);" class="btn">添加</a>
                        <span class="font14 group" style="position:absolute;right:0px" id="group">
                            <input id="f01_zczzwmc" type="text" class="text textTips {text:'注册证号/产品名称/厂家名称'}" copy="search"  name="11" value="" size="30">
                            <input type="button" icon="search" onclick="javascript:getRegisterCert();" />
                        </span>
                    </div>
                    <div class="tablelist mTop10" style="height:400px;">
                        <table class="tableCert hoverTable " width="100%" cellspacing="0" cellpadding="0" border="0">
                            <thead>
                                <tr>
                                    <th width="25%">医疗器械注册证号</th>
                                    <th>注册产品名称</th>
                                    <th>有效期</th>
                                    <th width="20%">相关证书维护状态</th>
                                    <th width="20%">操作</th>
                                </tr>
                            </thead>
                            <tbody id="list">
                            </tbody>
                        </table>
                    </div>
                    <div class="pages" id="registerCert-page"></div>
                </div>
                <div class="tagNav">
                    <div class="operLeft mTop40 lFloat">
                        <a href="javascript:showLayer('/33010001', 600, true);" class="yuiAdd"></a>
						<p class="mTop10">添加厂家/总经销商</p>
                    </div>
                    <div class="operMain type1">
                        <ul class="pubicYUIList mTop10 clearfix" id="list2"></ul>
                    </div>
                    <div class="clear"></div>
                    <div class="pages" id="factory-page"></div>
                </div>
                <div class="tagNav">
                    <div class="operLeft mTop40 lFloat">
                        <a href="javascript:showLayer('/31104001', 800, true);" class="yuiAdd"></a>
						<div class="mTop10">添加经销授权书</div>
                    </div>
                    <div class="operMain type2">
                        <ul class="publicYUCList mTop10 clearfix" id="list3"></ul>
                    </div>
                    <div class="clear"></div>
                    <div class="pages" id="authorization-page"></div>
                </div>
                    <div class="tagNav">
                      <div class="operLeft mTop40 lFloat">
                      <a href="javascript:showLayer('/31105001', 600, true);" class="yuiAdd"></a>
						<div class="mTop10">
							添加法人委托书
							<p class="mTop10"><a class="gray" href="#">下载模板</a></p>
						</div>
                    </div>
                    <div class="operMain type3">
                        <ul class="pubicYUIList mTop10 clearfix" id="list4"></ul>
                    </div>
                   <div class="clear"></div>
                   <div class="pages"  id="entrust-page"></div>
                </div>
                <div class="tagNav">
                    <div class="operLeft mTop40 lFloat">
                        <a href="javascript:showLayer('/31106001', 600, true);" class="yuiAdd"></a>
						<div class="mTop10">
							添加承诺书
							<p class="mTop10"><a class="gray" href="#">下载模板</a></p>
						</div>
                    </div>
                    <div class="operMain type2">
                        <ul class="publicYUCList mTop10 clearfix" id="list5"></ul>
                    </div>
                    <div class="clear"></div>
                    <div class="pages" id="promise-page"></div>
                </div>
                <div class="tagNav">
                    <form id="form" action="/3110101" method="post" onsubmit="return onSubmit();">
                    <input type="hidden" name="puk" value="${yyzz.puk }"><!-- 营业执照ID -->
                    <input type="hidden" name="f01_fzdwmc" value="${jyxkz.puk }"><!-- 经营许可证ID -->
                    <input type="hidden" name="f05_yxzzrq" value="${swdjz.puk }"><!-- 税务登记证ID -->
                    <input type="hidden" name="k03_zjlb" value="${yyzz.bbb }" id="zjPhoto1_url"><!-- yyzzURL -->
                    <input type="hidden" name="f04_yxksrq" value="${jyxkz.bbb }" id="zjPhoto2_url"><!-- jyxkzURL -->
                    <input type="hidden" name="f08" value="${swdjz.bbb }" id="zjPhoto3_url"><!-- swdjzURL -->
					<ul class="ulFrm">
						<li>
							<span class="label labelW100 labelRight" style="vertical-align:top;"><span class="red">*</span>&nbsp;&nbsp;证件信息：</span>
							<div class="uploadBox uploadW200">
								<a href="/311100005/0?pukid=${yyzz.puk }" target="_blank" class="showGlass" style="display: none;"></a>
								<div class="uploadImg"><a id="zjPhoto1Link" href="<%=ImagePath %>/00003030/${yyzz.bbb }/${loginer.token}" target="_bank"><img src="<%=ImagePath %>/00003030/${yyzz.bbb }/${loginer.token}" id="zjPhoto1" /></a></div>
								<div class="uploadBoxText"><span id="tag_1"></span>营业执照</div>
							</div>
							<div class="uploadBox uploadW200" style="margin-left:20px;">
								<a href="/311100005/1?pukid=${jyxkz.puk }" target="_blank" class="showGlass" style="display: none;"></a>
								<div class="uploadImg"><a id="zjPhoto2Link" href="<%=ImagePath %>/00003030/${jyxkz.bbb }/${loginer.token}" target="_bank"><img src="<%=ImagePath %>/00003030/${jyxkz.bbb }/${loginer.token}" id="zjPhoto2" /></a></div>
								<div class="uploadBoxText"><span id="tag_2"></span>营业许可证</div>
							</div>
							<div class="uploadBox uploadW200" style="margin-left:20px;">
								<a href="/311100005/2?pukid=${swdjz.puk }" target="_blank" class="showGlass" style="display: none;"></a>
								<div class="uploadImg"><a id="zjPhoto3Link" href="<%=ImagePath %>/00003030/${swdjz.bbb }/${loginer.token}" target="_bank"><img src="<%=ImagePath %>/00003030/${swdjz.bbb }/${loginer.token}" id="zjPhoto3" /></a></div>
								<div class="uploadBoxText"><span id="tag_3"></span>税务登记证</div>
							</div>
						</li>
						<li class="p105 certInline">
							<div class="lFloat">
								<span class="label  labelRight"><span class="red">*</span> 开始日期：</span>
								<input type="text" vd-key="date" wdate="true" class="text startDate" name="k01_gysid" value="${yyzz.f04_yxksrq }" size="15" id="startDate1"><br>&nbsp;<br>
								<span class="label  labelRight"><span class="red">*</span> 结束日期：</span>
								<input type="text" vd-key="date" wdate="true" class="text endDate" name="k02_zjbh" value="${yyzz.f05_yxzzrq }" size="15" id="endDate1"> 
							</div>
							<div class="lFloat" style="margin-left:22px;">
								<span class="label  labelRight"><span class="red">*</span> 开始日期：</span>
								<input type="text" vd-key="date" wdate="true" class="text startDate" name="f02_fzrq" value="${jyxkz.f04_yxksrq }" size="15" id="startDate2"><br>&nbsp;<br>
								<span class="label  labelRight"><span class="red">*</span> 结束日期：</span>
								<input type="text" vd-key="date" wdate="true" class="text endDate" name="f03_yxnx" value="${jyxkz.f05_yxzzrq }" size="15" id="endDate2"> 
							</div>
							<div class="lFloat" style="margin-left:22px;" >
								<span class="label  labelRight"><span class="red">*</span> 开始日期：</span>
								<input type="text" vd-key="date" wdate="true" class="text startDate" name="f06_shzt" value="${swdjz.f04_yxksrq }" size="15" id="startDate3"><br>&nbsp;<br>
								<span class="label  labelRight"><span class="red">*</span> 结束日期：</span>
								<input type="text" vd-key="date" wdate="true" class="text endDate" name="f07" value="${swdjz.f05_yxzzrq }" size="15" id="endDate3"> 
							</div>
							<div class="clear" style="padding-top:10px;">
								<div class="errorMsg" id="yui-error-msg" style="width: 315px;"></div>
							</div>
						</li>
						<div class="mTop10" style="margin-left:100px"><input type="submit" class="btn" value="保存"></div>
					</ul>
					<div class="clear"></div>
					</form>
                </div>
            </div>
        </div>
        <!-- <ul class="subNav subNavLH clearfix2">
            <li><a href="javascript:void(0);" onclick="getPage(this, 'audit_supplier.html');" class="active">待审核供应商</a></li>
            <li><a href="javascript:void(0);" onclick="getPage(this, 'audit_cert.html');">待审核换证信息</a></li>
            <li><a href="javascript:void(0);" onclick="getPage(this, '');">已过期证件</a></li>
        </ul>
        <div class="boxPContent" id="contentBox"></div> -->
    </div>
</div>
<%@ include file="/resources/jsp/formJS.jsp" %>
</body>

</html>