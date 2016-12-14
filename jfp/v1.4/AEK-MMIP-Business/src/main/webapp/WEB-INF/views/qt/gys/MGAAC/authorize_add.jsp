<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/taglib/pagetag.tld"  prefix="p" %>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
	<meta charset="utf-8">
	<title>添加经销授权书</title>
<link rel="stylesheet" type="text/css" href="/resources/css/base.css">
<link rel="stylesheet" type="text/css" href="/resources/css/supplier_cert.css">
<link rel="stylesheet" type="text/css" href="/resources/css/jquery.datetimepicker.css">
<link rel="stylesheet" href="/resources/css/jquery.mCustomScrollbar.css">
<link rel="stylesheet" type="text/css" href="/resources/css/uploadify.css" />
<script src="/resources/js/jquery.js"></script>
<script src="/resources/js/jquery.uploadify.js"></script>
<script src="/resources/js/override.uploadify.js"></script>
<script src="/resources/js/jquery.mousewheel.js"></script>
<script src="/resources/js/function.js"></script>
<script src="/resources/js/jquery.mCustomScrollbar.js"></script>
<script src="/resources/js/jq.ui.js"></script>
<script src="/resources/js/jquery.datetimepicker.js"></script>
<script src="/resources/js/validate.js"></script>
<%@ include file="/resources/jsp/gys/inc.jsp" %>
<script>
var ids = ${page};
$(function(){
    resetText();
    $(".textTips").input();
    <c:if test="${UserData.puk != null && UserData.puk != ''}">pushCheckRegCert();</c:if>
    $(".Wdate").datetimepicker({timepicker:false,format:'Y-m-d'});
  //  $(".uploadBox").hoverUpload();
    loadUpload({
        id:'#tag_1',
        success:uploadSuccess_1
    });
    new inputTips("#zczName","/31104004",2,"#zczId",425,function(data){
		var str = "";
		if(data.id != undefined){
			$(this).val(data.zczh);
			if(ids.length > 0){
				if(isDataStata(data.id) != true){
					alert("该注册证号已经存在关联注册证中！");
					return false;
				}
			}
			str ='<tr>\n\
		        <td></td>\n\
		        <td>'+data.zczh+'</td>\n\
		        <td>'+data.cpmc+'</td>\n\
		        <td>'+data.cjmc+'</td>\n\
		        <td><a href="javascript:void(0);" onclick="del(this, '+ids.length+');" class="btn minBtn">删除</a></td>\n\
		    </tr>';
			$("#glzcz").append(str);
			ids.push(data);
			resetIndex();
		}
    }, true,false);
    new inputTips("#sjjxsName","/2001030/13",2,"#sjjxsId",425,null, true,false);
});

function isDataStata(id){
	var status = true;
	$.each(ids,function(index, item){
		if(item.id == id){
			status = false;
		}
	});
	return status;
}
function resetIndex(){
	$("#glzcz>tr").each(function(index){
		$(this).find("td:first-child").text(parseInt(index) + 1);
		$(this).find("td:last-child>a").attr("onclick", "del(this, "+index+")");
	});
}
function pushCheckRegCert(){
	var str = "", id = [];
	$.each(ids,function(index, item){
		str +='<tr>\n\
	        <td>'+parseInt(index + 1)+'</td>\n\
	        <td>'+item.zczh+'</td>\n\
	        <td>'+item.cpmc+'</td>\n\
	        <td>'+item.cjmc+'</td>\n\
	        <td><a href="javascript:void(0);" onclick="del(this, '+index+');" class="btn minBtn">删除</a></td>\n\
	    </tr>';
		id.push(item.puk);
	});
	$("#glzcz").append(str);
	$("#zczIds").val(id.join(","));
}

function del(obj, index){
	$(obj).parent().parent().remove();
	ids.splice(index, 1);
	resetIndex();
}


function onSubmit(){
    var url=$("#form").attr("action");
	var  parm=$("#form").serializeJSON();
	var zczIds=[];
	$.each(ids,function(index, item){
		zczIds.push(item.id);
	});
	parm.zczIds=zczIds.join(',');
	$("#zczIds").val(parm.zczIds);
	var vData = $("#form").validateForm({id : '#yui-error-msg'});

	if (!vData.allSuccess) {
		return false;
	}
	if($("#startDate").val()>$("#endDate").val()){
		 top.msgText("开始时间应该小于等于结束时间！",true);
		 return false;
	}
	ajax({url:url,data:parm,callBack:function(result){
		 top.msgText(result.message,true);
		 setTimeout(function(){
			 parent.closeLayer();
			 window.top.iframe.getAuthorizationList();
		 },1000);
	 },load:false});
	 return false;
}

function uploadSuccess_1(file, data, response){
	var json = data.toJSON();
	var img = json.result;
	$("#zjPhoto_url").val(img);
	$("#zjPhoto").attr("src",cateImage(img,json.message));
	$("#zjPhoto1Link").attr("href",cateImage(img,json.message));
}
</script>
</head>
<body class="whiteBg">
<form id="form" action="/31104002" method="post" onsubmit ="return onSubmit()">
    
    <!-- 上级单位ID -->
    <input type="hidden" name="f11" id="sjjxsId" value="${UserData.f11 }">
    <input type="hidden" name="puk" value="${UserData.puk }">
    <input type="hidden" name="zczIds"  vd-key="nonempty" data-error="请关联注册证!" data-for="#zczName" value="" id="zczIds">
    <div class="contnet">
      <div class="formTitle">
           <c:if test="${UserData.puk == null || UserData.puk == ''}">添加经销授权书</c:if>
           <c:if test="${UserData.puk != null && UserData.puk != ''}">编辑经销授权书</c:if>
           </div>
        <ul class="ulFrm">
        <li>
            <span class="label labelW100 labelRight"><span class="red">*</span> 上级经销商：</span>
            <input type="text" vd-key="nonempty" data-error="输入对方公司全称!" id="sjjxsName" class="text textTips {text:'输入对方公司全称'}" name="f12" value="${UserData.f12 }" size="65" arrow="true">
        </li>
        <li class="hidePart">
            <span class="label labelW100 labelRight"><span class="red">*</span> 证件信息：</span>
            <div class="uploadBox uploadW200">
                <a href="#" class="showGlass" style="display: none;"></a>
				<div class="uploadImg"><a id="zjPhoto1Link" href="<%=ImagePath %>/00003030/${UserData.bbb }/${loginer.token}333" target="_bank"><img src="<%=ImagePath %>/00003030/${UserData.bbb }/${loginer.token}333" id="zjPhoto" ></a></div>
                <div class="uploadBoxText"><span id="tag_1"></span>经销授权书</div>
                <input type="hidden" name="bbb" value="${UserData.bbb }" id="zjPhoto_url" vd-key="nonempty" data-error="请上传图片信息">
            </div>
        </li>
        <li class="p105 hidePart">
            <div class="lFloat">
                <span class="label  labelRight"><span class="red">*</span> 开始日期：</span>
                <input type="text" wdate="true" vd-key="date" class="text startDate" name="f04_yxksrq" value="${UserData.f04_yxksrq }" size="15" id="startDate"><br>&nbsp;<br>
                <span class="label  labelRight"><span class="red">*</span> 结束日期：</span>
                <input type="text" wdate="true" vd-key="date" class="text endDate" name="f05_yxzzrq" value="${UserData.f05_yxzzrq }" size="15" id="endDate"> 
            </div>
            <div class="clear"></div>
        </li>
        <li>
            <span class="label labelW100 labelRight"><span class="red">*</span> 关联注册证：</span>
            <input type="text" class="text textTips {text:'请输入注册证'}"   value="" size="65"  id="zczName" name="zczName"  arrow="true">
            <input type="hidden" name="zczId" id="zczId" value="">
        </li>
        <li>
            <div class="font20 mTop10 lFloat">已关联注册证：</div><div class="errorMsg lFloat" id="yui-error-msg" style="width: 315px;margin-bottom:10px;"></div>
            <div class="tablelist mTop10" style="height:200px;">
                <table class="table hoverTable" width="100%" cellspacing="0" cellpadding="0" border="0">
                    <thead>
                        <tr>
                            
                            <th width="8%">序号 </th>
                            <th>注册证号 </th>
                            <th>注册证产品名称</th>
                            <th >生产厂家</th>
                            <th width="10%" >操作</th>
                        </tr>
                    </thead>
                    <tbody id="glzcz">
                    </tbody>
                </table>
                <div class="pages"></div>
            </div>
        </li>
        <li><input type="submit" class="btn" value="保存"></li>
    </ul>
    </div>
</form>
</body>
<%@ include file="/resources/jsp/formJS.jsp" %>
</html>