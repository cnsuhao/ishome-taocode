<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
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
<link rel="stylesheet" href="/resources/css/jquery.mCustomScrollbar.css">
<link rel="stylesheet" type="text/css"
	href="/resources/css/uploadify.css" />
<script src="/resources/js/jquery.js"></script>
<script src="/resources/js/jquery.mousewheel.js"></script>
<script src="/resources/js/jquery.mCustomScrollbar.js"></script>
<script src="/resources/js/jquery.uploadify.js"></script>
<script src="/resources/js/override.uploadify.js"></script>
<script src="/resources/js/jq.ui.js"></script>
<script src="/resources/js/function.js"></script>
<script src="/resources/js/jsAddress.js"></script>
<%@ include file="/resources/jsp/ImagePath.jsp"%>
<script src="/resources/js/validate.js"></script>
<style type="text/css">
.lInput{text-align: left;}
</style>
<script>
$(function(){
	resetText();
	$(".tag").uiSwitch();
	$("#systemTime, #internalTime, #userTime").val(new Date().Format("yyyy-MM-dd"));
	$(".Wdate").datetimepicker({timepicker:false,format:"Y-m-d"});
	tagClick();
	$(".systemTips").input();
	$(".tag>span:eq(0)").click();
});

function tagClick(){
	$(".tag>span").click(function(){
		var index = $(this).index();
		if(index == 0){
			systemNote();
			pageGo=systemNote;
		}else if(index == 1){
			$(".internalTips").input();
			internalNote();
			pageGo=internalNote;
		}else if(index == 2){
			$(".userTips").input();
			userNote();
			pageGo=userNote;
		}
	})
}

function systemNote(page){
	var html="";
	page = page || 1;
	var pageSize = 10;
	ajax("/30002001?t="+Math.random(),{"act":"memberList","page":page,"pageSize":pageSize,"keywords":$("#system-keywords").val()},function(data){
		if(data.code == 0){
			$.each(data.list,function(index, item){
				if(index < 5){
				html+= "<tr>\n\
					<td>"+item.f02_kssh+"</td>\n\
					<td>"+item.f06_zyd+"</td>\n\
					<td>"+item.fb1+"</td>\n\
					<td>"+item.bbb+"</td>\n\
					<td><span><a href=\"javascript:void(0);\"><img src=\"/images/icon/i_show.png\" /> 查看</a></span>&nbsp;&nbsp;&nbsp;&nbsp;<a href=\"\"><img src=\"/images/icon/i_del.png\" /> 删除</a></td>\n\
				</tr>";
				}
			});

			$("#system-list").html(html);
			var newpage = new createPage(data.total,page,pageSize,3);
			$("#system-pages").html(newpage.pageHtml);
		}
	});
}

function internalNote(page){
	var html="";
	page = page || 1;
	var pageSize = 10;
	ajax("/30002002?t="+Math.random(),{"act":"memberList","page":page,"pageSize":pageSize,"keywords":$("#internal-keywords").val()},function(data){
		if(data.code == 0){
			$.each(data.list,function(index, item){
				if(index < 5){
				html+= "<tr>\n\
					<td>"+item.cc1+"</td>\n\
					<td>"+item.f05_zyd+"</td>\n\
					<td>"+item.f04_jjd+"</td>\n\
					<td>"+item.f01_fjrnc+"</td>\n\
					<td>"+item.f03_bt+"</td>\n\
					<td>"+item.fb4+"</td>\n\
					<td><span><a href=\"javascript:void(0);\"><img src=\"/images/icon/i_show.png\" /> 查看</a></span>&nbsp;&nbsp;&nbsp;&nbsp;<a href=\"\"><img src=\"/images/icon/i_del.png\" /> 删除</a></td>\n\
				</tr>";
				}
			});

			$("#internal-list").html(html);
			var newpage = new createPage(data.total,page,pageSize,3);
			$("#internal-pages").html(newpage.pageHtml);
		}
	});
}

function userNote(page){
	var html="";
	page = page || 1;
	var pageSize = 10;
	ajax("/30002003?t="+Math.random(),{"act":"memberList","pageCurrent":page,"pageLimit":pageSize,"keywords":$("#user-keywords").val()},function(data){
		if(data.code == 0){
			$.each(data.list,function(index, item){
				if(index < 5){
				html+= "<tr>\n\
					<td>"+item.cc1+"</td>\n\
					<td>"+item.fb1+"</td>\n\
					<td>"+item.fb4+"</td>\n\
					<td><span><a href=\"javascript:void(0);\"><img src=\"/images/icon/i_show.png\" /> 查看</a></span>&nbsp;&nbsp;&nbsp;&nbsp;<a href=\"\"><img src=\"/images/icon/i_del.png\" /> 删除</a></td>\n\
				</tr>";
				}
			});

			$("#user-list").html(html);
			var newpage = new createPage(data.total,page,pageSize,3);
			$("#user-pages").html(newpage.pageHtml);
		}
	});
}
</script>
</head>
<body>
<div class="contnet">
	<div class="boxContent">
		<div class="tagBox">
			<div class="tag {movetag:'span',showtag:'.tagBox .tagNav',addsclass:'tagnSelect',addtclass:'tagSelect',time:9999999}"> 
				<span class="tagSelect">系统公告</span>
				<span>内部消息</span>
				<span>用户建议</span>
			</div>
			<div class="tagBox">
				<div class="tagNav tagnSelect">
					<!-- <div class="mTop10" align="right">
						<form id="systemFrm" name="systemFrm">
							<input type="text" class="text" name="systemTime" id="systemTime" value="" wdate="true" >
							<input type="text" class="text systemTips {text:'请输入发起人'}"  copy="search" size="40" name="system-keywords" id="system-keywords" value="" />
							<input type="submit" icon="search" />
						</form>
					</div> -->
					<table class="table mTop10" cellpadding="0" cellspacing="0" width="100%">
			            <thead>
			                <tr>
			                    <th width="12%">公告时间</th>
			                    <th width="18%">重要度/紧急度</th>
			                    <th width="10%">发起人</th>
			                    <th>标题</th>
			                    <th width="10%">操作</th>
			                </tr>
			            </thead>
			            <tbody id="system-list">
						</tbody>
					</table>
				</div>
				<div class="tagNav">
					<!-- <div class="mTop10" align="right">
						<form id="systemFrm" name="systemFrm">
							<input type="text" class="text" name="internalTime" id="internalTime" value="" wdate="true" >
							<input type="text" class="text internalTips {text:'请输入发件人昵称'}"  copy="search" size="40" name="internal-keywords" id="internal-keywords" value="" />
							<input type="submit" icon="search" />
						</form>
					</div> -->
					<table class="table mTop10" cellpadding="0" cellspacing="0" width="100%">
			            <thead>
			                <tr>
			                    <th width="20%">发送时间</th>
			                    <th width="10%">重要度</th>
			                    <th width="10%">紧急度</th>
			                    <th width="10%">发件人</th>
			                    <th>标题</th>
			                    <th>阅读状态</th>
			                    <th width="10%">操作</th>
			                </tr>
			            </thead>
			            <tbody id="internal-list">
						</tbody>
					</table>
				</div>
				<div class="tagNav">
					<!-- <div class="mTop10" align="right">
						<form id="systemFrm" name="systemFrm">
							<input type="text" class="text" name="userTime" id="userTime" value="" wdate="true" >
							<input type="text" class="text userTips {text:'请输入关键词'}"  copy="search" size="40" name="user-keywords" id="user-keywords" value="" />
							<input type="submit" icon="search" />
						</form>
					</div> -->
					<table class="table mTop10" cellpadding="0" cellspacing="0" width="100%">
			            <thead>
			                <tr>
			                    <th>时间</th>
			                    <th>标题</th>
			                    <th width="10%">处理状态</th>
			                    <th width="10%">操作</th>
			                </tr>
			            </thead>
			            <tbody id="user-list">
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
</div>
</body>
</html>