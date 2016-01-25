<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/taglib/pagetag.tld"  prefix="p" %>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
	<meta charset="utf-8">
	<title>维护经营范围</title>
	<%@ include file="/resources/jsp/gys/inc_new.jsp" %>
<link rel="stylesheet" type="text/css" href="/resources/css/base.css">
<link rel="stylesheet" type="text/css" href="/resources/css/supplier_cert.css">
<link rel="stylesheet" type="text/css" href="/resources/css/zTreeStyle.css">
<link rel="stylesheet" href="/resources/css/jquery.mCustomScrollbar.css">
<style>
.switch{position:relative;}
.ztreeList{height:560px; overflow:hidden; overflow-y:auto;}
.posi{position:absolute; right:10px; top:48px;}
</style>
<script src="/resources/js/jquery.js"></script>
<script src="/resources/js/jq.ui.js"></script>
<script src="/resources/js/jquery.ztree.all-3.5.min.js"></script>
<script src="/resources/js/jquery.mousewheel.js"></script>
<script src="/resources/js/jquery.mCustomScrollbar.js"></script>
<script>
<!--
var zTree1, zTree2;
$(function(){
	$(".ztreeList").mCustomScrollbar({theme:"minimal"});
	zTree1=$.fn.zTree.init($("#list1"), setting);
	setting.async.url="/31011001/123?t="+Math.random()+"";
	zTree2=$.fn.zTree.init($("#list2"), setting);
	$(".tag").uiSwitch();
});

function arrayToObj(obj){
	var obj2={};
	for(var i=0;i<obj.length;i++){
		obj2[obj[i]]=true;
	}
	return obj2;
}


var setting= {
	async: {
		enable: true,
		url: "/31011001?t="+Math.random()+"",
		dataFilter:filter
	},
	check: {
		enable: true
	},
	data: {
		simpleData: {
			enable: true
		}
	}
};


function filter(treeId, parentNode, responseData){
	var ids=responseData.result.my;
	var checkObj=arrayToObj(ids.split(','));
	for (var i = 0; i < responseData.result.all.length; i++) {
		var id=responseData.result.all[i].id;
		if(checkObj[id])
			responseData.result.all[i].checked=true;
	};
	return responseData.result.all;
}

function saveTree(){
	var tree, type = $(".tag>span.tagSelect").index() || 0;
	tree = type == 0 ? zTree1:zTree2;
	var getNodes=tree.getCheckedNodes(true);
	var checkIds=[];
	var str="";
	for(var i=0;i<getNodes.length;i++){
		checkIds.push(getNodes[i].id);
	}
    str=checkIds.join(',');   
    var url=$("#saveForm").attr("action");
	var  parm=$("#saveForm").serializeJSON();
	parm.saveNodeInfos=str;
    ajax({url:url,data:parm,callBack:function(result){
		 top.msgText(result.message,true);
			 parent.closeLayer();
		
	 },load:false});
}

</script>
</head>
<body class="whiteBg">
    <div class="contnet">
      <div class="formTitle">选择经营范围</div>
      <a class="btn posi" href="javascript:saveTree();">保存</a>
      <div class="tagBox mTop10">
            <div class="tag {movetag:'span',showtag:'.tagBox .tagNav',addsclass:'tagnSelect',addtclass:'tagSelect',time:9999999}"> 
                <span class="tagSelect">按68码</span>
                <span>按分类</span>
            </div>
            <form id="saveForm" name="saveForm" action="/31011002" method="post">
            <input type="hidden" id="saveNodeInfos" name="saveNodeInfos" value="">
            <div class="tagBox">
                <div class="tagNav tagnSelect" >
                    <div class="ztreeList"><ul id="list1" class="ztree"></ul></div>
                </div>
                <div class="tagNav">
                    <div class="ztreeList"><ul id="list2" class="ztree"></ul></div>
                </div>
            </div>
            </form>
        </div>
    </div>
</body>
<%@ include file="/resources/jsp/formJS.jsp" %>
</html>