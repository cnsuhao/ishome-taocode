<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/taglib/pagetag.tld"  prefix="p" %>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
	<meta charset="utf-8">
	<title>推送注册证</title>
	<%@ include file="/resources/jsp/gys/inc_new.jsp" %>
<link rel="stylesheet" type="text/css" href="/resources/css/base.css">
<link rel="stylesheet" type="text/css" href="/resources/css/supplier_cert.css">
<link rel="stylesheet" href="/resources/css/jquery.mCustomScrollbar.css">
<script src="/resources/js/jquery.js"></script>
<script src="/resources/js/jquery.mousewheel.js"></script>
<script src="/resources/js/jquery.mCustomScrollbar.js"></script>

<script src="/resources/js/function.js"></script>
<script src="/resources/js/tableFixed.min.js"></script>
<script src="/resources/js/jq.ui.js"></script>
<script>
//获取url中的参数
//console.log(getUrlParam("id"));
$(function(){
    resetText();
    $(".textTips").input();
    hoverTable(".hoverTable",true,true);
    $("body").mCustomScrollbar({theme:"minimal"});
    $(".select").uiSelect();
    $(".checkbox").checkbox();
    getHospitalInfo();
    getHospitalCert();
    
    var $allList = $("#allList").on('click', '.optBtn', function(){
        $(this).text("删除").closest('tr').prependTo($alreadyList);
    });
    var $alreadyList = $("#alreadyList").on('click', '.optBtn', function(){
        $(this).text("添加").closest('tr').prependTo($allList);
    });
    
});


//得到医院信息
function getHospitalInfo(){
	var yyid = $('#yyid').val();
        ajax("/31200903/" + yyid +"?t="+Math.random(),{},function(data){
            $("#yui-u-logo").text(data.result.f01_qyqc.substr(0,1));
            $("#yui-h-name").text(data.result.f01_qyqc)
            $("#userName").text(data.result.f06_frxm);
            $("#phone").text(data.result.f16_lxdh);
        });
    }


 //得到证件
 function getHospitalCert(page){
	$("#allList").empty();
	removeInfo();
	var html = "", pageSize = 10, keywords = $("#searchBox").val();
	var yyid = $('#yyid').val();
    ajax("/31200901/" + yyid + "?t="+Math.random(),{"certNum":keywords,"hospitalName":keywords,"pageCurrent":page, "pageLimit":pageSize},function(data){
   		if(data.result.list.length>0){
   		 $.each(data.result.list,function(index, item){
   			 var styleRed = "";
   			 if (item.eb1 == '0') {
   				styleRed = 'style="color:red"';
   			 }
             html +='<tr id='+item.puk+' ' + styleRed + '>'
                     +'<td>'+item.f01_zczzwmc+'</td>'
                     +'<td>'+ item.f03_cpzwmc +'</td>'
                     +'<td>' + item.f09_yxksrq + '至' + item.f10_yxzzrq + '</td>'
                     +'<td class="optTd"><a href="/311100005/6?pukid=' + item.puk + '" class="btn minBtn" target="_blank">查看</a>&nbsp;&nbsp; <a class="btn minBtn optBtn" href="javascript:void(0)">添加</a></td>'
                     +'</tr>';
         });
         
         $("#allList").html(html);
         var newpage = new createPage(data.result.pageCount, page, pageSize, 3);
		 $("#pages").html(newpage.pageHtml);
		 hoverTable(".hoverTable",true,false);
   		}else{
   			$("#allList").info("暂无数据。");
   		}
    });
 }


 
 function doSubmitAllZs() {
	var zczids=[];
	 var url=$("#form").attr("action");
	 console.log(url);
	 console.log(parm);
	 var  parm=$("#form").serializeJSON();
	 //得到zczids
	 $.each( $("#alreadyList").find("tr"),function(){
		zczids.push($(this).attr("id"));
	 });
	 parm.zczids=zczids.join(',');
	 ajax({url:url,data:parm,callBack:function(result){
		 top.msgText(result.message,true);
		 setTimeout(function(){
			 parent.closeLayer();
			 window.top.iframe.getCustomerList();
		 },1000);
	 },load:false});
     return false;
 }
 pageGo = getHospitalCert;
</script>
</head>
<body class="whiteBg">
<form id="form" action="/31200902/" method="post" onsubmit="return doSubmitAllZs()">
	<div class="contnet">
	<input type="hidden" name="yyid" id="yyid" value="${yyid }">
	    <div class="hospitalInfoBox lFloat">
	        <span class="mlogo mlogo-min lFloat" id="yui-u-logo"></span>
	        <div class="hospitalInfoText">
	                <span id="yui-h-name" class="font14" ></span><br />
	                <img src="/resources/images/icon/t-call.png" align="absmiddle"><span class="gray"><span id="userName"></span> <span id="phone"></span></span>
	        </div>
	    </div>
	    <div class="hospitalBox gray lFloat leftLine" align="center" >
	        <span class="font20" id="alreadyRegisterCount">${alreadyPushNum }</span><br />已成功推送注册证
	    </div>
	    <div class="hospitalBox gray lFloat " align="center">
	        <span class="font20" id="allRegisterCount">${allZczNum }</span><br /> 我的全部注册证
	    </div>
	    <div class="clear"></div>
	    
	    <div class="clear"></div>
	    <div class="mTop10">
	        <span class="font20">待选注册证</span>
	        <div class="rFloat" style="margin-right:10px;">
	            <input type="text" class="text textTips {text:'请输入注册证号或产品名'}" copy="search" name="11" value="" id="searchBox" size="30" />
	            <input type="button" icon="search" onclick="javascript:getHospitalCert();" />
	        </div>
	    </div>
	    <div class="tablelist mTop10" style="height:200px;">
	        <table class="table hoverTable" width="100%" cellspacing="0" cellpadding="0" border="0">
	            <thead>
	                <tr>
	                    <th width="40%">医疗器械注册证号</th>
	                    <th>注册证名称</th>
	                    <th>有效期</th>
	                    <th width="15%">&nbsp;</th>
	                </tr>
	            </thead>
	            <tbody id="allList">
	            </tbody>
	        </table>
	    </div>
	    <div class="pages" id="pages"></div>
	    <div class="font20 mTop10">已选注册证</div>
	    <div class="tablelist mTop10" style="height:200px;">
	        <table class="table hoverTable" width="100%" cellspacing="0" cellpadding="0" border="0">
	            <thead>
	                <tr>
	                    
	                    <th width="40%">医疗器械注册证号</th>
	                    <th>注册证名称</th>
	                    <th>有效期</th>
	                    <th width="15%">&nbsp;</th>
	                </tr>
	            </thead>
	            <tbody id="alreadyList">
	                
	            </tbody>
	        </table>
	    </div>
	    <div style="text-align:right;padding:20px;"><input type="submit" class="btn" value="推送"></div>
	</div>
</form>

</body>
<%@ include file="/resources/jsp/formJS.jsp" %>
</html>