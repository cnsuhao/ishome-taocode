<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/taglib/pagetag.tld"  prefix="p" %>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<title></title>
<meta name="description" content="">
<meta name="keywords" content="">
<link rel="stylesheet" type="text/css" href="/resource/css/base.css">
<link rel="stylesheet" href="/resource/css/jquery.mCustomScrollbar.css">
<script src="/resource/js/jquery.js"></script>
<script src="/resource/js/jquery.mousewheel.js"></script>
<script src="/resource/js/jquery.mCustomScrollbar.js"></script>
<script src="/resource/js/jq.ui.js"></script>
<script src="/resource/js/function.js"></script>
<script>
$(function(){
	$(".tag").uiSwitch();
	tagClick();
	$(".tag>span:eq("+(getUrlParam("index") || 0)+")").click();
});

function tagClick(){
	$(".tag>span").click(function(){
		var index = $(this).index();
		if(index == 0){
			getMemberInfo();
		}else if(index == 1){
			getMembers();
			pageGo = getMembers;
		}
	})
}

function getMemberInfo(){

}

function getMembers(page){
	var html="";
	page = page || 1;
	var pageSize = 10;
	ajax("/ajax/json.php?t="+Math.random(),{"act":"memberList","page":page,"pageSize":pageSize},function(data){
		if(data.code == 0){
			$.each(data.list,function(index, item){
				html+= "<tr role=\""+item.role+"\">\n\
					<td>"+item.name+"</td>\n\
					<td>"+item.mobile+"</td>\n\
					<td>"+item.beginTime+"</td>\n\
					<td>"+item.optionTime+"</td>\n\
					<td>"+item.roleName+"</td>\n\
					<td>"+item.status+"</td>\n\
					<td><a href=\"javascript:setRole("+item.id+");\" class=\"btn\">设置</a></td>\n\
				</tr>";
			});
			$("#member-list").html(html);
			var newpage = new createPage(data.total,page,pageSize,3);
			$("#member-pages").html(newpage.pageHtml);
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
				<span class="tagSelect">帐户设置</span>
				<span>成员管理</span>
			</div>
			<div class="tagBox">
				<div class="tagNav tagnSelect">
					<ul class="ulFrm">
						<li><a href="" class="btn">修改手机</a></li>
						<li><a href="" class="btn">修改密码</a></li>
						<li><a href="" class="btn">设置</a></li>
					</ul>
				</div>
				<div class="tagNav">
					<div class="mTop10"><a href="javascript:void(0);" class="btn">添加成员</a></div>
					<table class="table mTop10" width="100%" cellpadding="0" cellspacing="0" border="0">
						<thead>
							<tr>
								<th>成员</th>
								<th>手机</th>
								<th>加入日期</th>
								<th>最近操作时间</th>
								<th>角色</th>
								<th>状态</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody id="member-list">
							<tr>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
							</tr>
						</tbody>
					</table>
					<div id="member-pages" class="pages"></div>
				</div>
			</div>
		</div>
	</div>
</div>
</body>
</html>