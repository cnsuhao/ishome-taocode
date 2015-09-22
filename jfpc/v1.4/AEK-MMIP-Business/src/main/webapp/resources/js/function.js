function getUserInfo(){
	ajax("/3000003?t"+Math.random(), {}, function(data){
		if(data.code == 0){
			$("#yui-hosName").html(splitLogo(data.result.companyNameCN,data.result.companyLogo));
			$("#yui-name").html(data.result.userNameCN.substring(0, 1));
			$(".userName").html(data.result.userNameCN);
			$(".hospitalName").html(data.result.companyNameCN);
			$("#yui-msg").html(data.result.ddd);
		}
	});
}

function getHeight(){
	$("#yui-left-box").height($(window).height() - $(".header").height() - 4);
	$("#yui-iframe").height($("#yui-left-box").height() - $(".menuTitle").height());
}

function getMenu(){
	var html = "";
	ajax("/30000002?t="+Math.random(), {}, function(data){
		if(data.code == 0){
			$.each(data.result,function(index, item){  //alt=\""+item.f02_cdmc+"\"
				html +="<li><a href=\""+item.f04_cdurl+"\" isClick="+(item.isClick || false)+" onclick=\"clickMenu(this, "+item.puk+", '"+item.f07_xtburl+"');return false;\" title=\""+item.f02_cdmc+"\"><span><img src=\""+item.f07_xtburl+"\" ></span><em>"+item.f02_cdmc+"</em></a></li>";
			});
		}
		$("#yui-menu").html(html);
		$("#yui-menu a").get(0).click();
	});
}

/**
 * 获取二级菜单
 * @param subNavId
 */
function getNavMenu(subNavId){
	var gdSubNav="";
	ajax("/30000002/"+subNavId+"?t="+Math.random(),{},function(json){
		if(json.code == 0){
			$.each(json.result,function(index, item){
				gdSubNav +="<li><a href=\"javascript:void(0);\" onclick=\"clickSubMenu(this, '"+item.f04_cdurl+"');\">"+item.f02_cdmc+"</a></li>";
			})
			$("#yui-subNav").html(gdSubNav);
			if(json.result.length > 0 && isClick == "true"){
				setTimeout(function(){
					$("#yui-subNav a:eq(0)").click();
				},100);
			}/*else{
				getPage(this, '/main.html', '#yui-contentBox');
			}*/
		}
	});
}

function clickMenu(obj,id,src){
	isClick = $(obj).attr("isClick");
	$("#yui-menu a").removeClass("active");
	$(obj).addClass("active");
	$("#yui-iframeIcon").attr("src",src);
	//$("#yui-iframe").attr("src",$(obj).attr("href"));
	$("#yui-navName").html($("em",obj).html());
	post($(obj).attr("href"));
	getNavMenu(id);
}

function clickSubMenu(obj, url){
	$("#yui-subNav a").removeClass("active");
	$(obj).addClass("active");
	post(url);
	//window.iframe.location.href = url;
}

function clickZoom(obj){
	var left = $("#yui-left-box"), right = $("#yui-right-box");
	if(left.width() == "50"){
		$(obj).attr("class","zoomOn");
		left.width("150");
		right.css("margin-left","150px");
	}else{
		$(obj).attr("class","zoomOff");
		left.width("50");
		right.css("margin-left","50px");
	}
}

//设置权限列表页面
function setRole(obj, id){
	var html = "", $this = $(obj).parent().parent().parent(), role = $this.attr("role"), id = $this.attr("id");
	var roles = role.split(","), classStatus;
	getRole(function(data){
		html += "<div class=\"roleList\"><ul>";
		$.each(data,function(index, item){
				html +="<li";
				for(var i=0;i<=roles.length;i++){
					if(roles[i]==item.puk) html +=" class=\"active\"";
				}
				html +=" roleId=\""+item.puk+"\">"+item.f01_jsmc+"<br /><span class=\"gray\">"+item.bbb+"</span></li>\n";
		});
		html +="</ul></div>\n<p align=\"right\"><a href=\"javascript:saveRole("+id+");\" class=\"btn\">确定</a></p>";
		cLayer(obj,'权限设置', html, 240, 'right');
		bindRoleLi();
		$(".roleList").mCustomScrollbar({theme:"minimal"});		
	});
}

//获取权限列表
function getRole(callBacks){
	ajax({url:"/31402047?t="+Math.random(),data:{},callBack:function(data){
		if(data.code == 0) callBacks(data.result);
	},load:false});
}

//保存权限
function saveRole(id){
	var roleIds = getRoleId();
	ajax({url:"/31402045?t="+Math.random(),data:{"roles":roleIds, "puk":id},callBack:function(data){
		if(data.code == 0){
			getMembers(false);
			$(".minBox").remove();
		}
	},load:false});
}

//绑定权限列表事件
function bindRoleLi(){
	$(".roleList li").each(function(){
		$(this).click(function(){
			if($(this).attr("class") != "active")
				$(this).addClass("active");
			else
				$(this).removeClass("active");
		});
	})
}

//获取选择权限列表
function getRoleId(){
	var roleIds = [];
	$(".roleList li").each(function(){
		if($(this).attr("class") == "active") roleIds.push($(this).attr("roleId"));
	});
	return roleIds.join(",");
}

function custom(url, cid){
	ajax(url+"?t="+Math.random(),{'puk':cid},function(json){
		top.msgText(json.message, true);
	})
}

function ignore(url, id, type){
	ajax(url+"?t="+Math.random(),{'puk':id,"type":type},function(json){
		top.msgText(json.message, true);
	})
}

function getId(gid){
	var id = $(gid).find("input:checkbox:checked"), ids = [];
	id.each(function(){
		ids.push("'"+$(this).val()+"'");
	});
	return ids.join(",").trim();
}

function getgysId(gid){
	var id = $(gid).find("input:checkbox:checked"), gysids = [];
	id.each(function(){
		gysids.push("'"+$(this).attr('gys')+"'");
	});
	return gysids.join(",").trim();
}

function msgTips(gid, id,gysid){
	var ids = getId(gid);
	var gysids = getgysId(gid);
	if(id == undefined) id = ids;
	if(gysid == undefined)gysid = gysids;
	if(id.isEmpty()){
		top.msgText("请先选择要提醒的记录。", true);
	}
	ajax("/32200303?t="+Math.random(),{'puk':id,'gysid':gysid},function(json){
		top.msgText(json.message, true);
	})
}

function resetInput(type){
	var $this = $("input, textarea");
	$this.each(function(){
		if ($(this).attr("readonly") == "readonly" || $(this).attr("readonly") == true) {
			$(this).removeAttr("readonly");
			$(this).removeClass("readonly");
			if($(".Wdate").length > 0) $(".Wdate").datetimepicker({timepicker:false,format:'Y-m-d'});
			if($("input[name='isErrorCov']").length > 0) $("input[name='isErrorCov']").val(type == undefined ? 0 :type);
		}
	});
}

function msg(){
	msgText("提醒中");
	setTimeout(function(){
		msgText("已向 2 家供应商发出提醒消息。若供应商仍未更新过期证件，请电话联系！", function(obj){
			setTimeout(function(){
				obj.fadeOut("slow");
			},2000);
		});
	}, 2000);
}

function changeAudit(){
	if($("#seriveContent option:selected").val() == 2)
		$("#bcontent").removeAttr("readonly").removeClass("readonly");
	else
		$("#bcontent").attr("readonly",true).addClass("readonly");
}

function selectAll(obj){
    if($(obj).is(":checked")){
        $("input[name=puk]").check();
    }else{
        $("input[name=puk]").uncheck();
    }
    $(".checkbox").checkbox();
}

function getCname(type){
	if(type =='1')
		return '供应商营业执照';
	else if(type=='2')
		return '供应商经营许可证';
	else if(type=='3')
		return '供应商工商税务登记证';
	else if(type=='4')
		return '供应商经销授权书';
	else if(type=='5')
		return '供应商销售人员委托书';
	else if(type=='6')
		return '供应商售后服务承诺书';
	else if(type=='7')
		return '供应商进口商检报告';
	else if(type=='8')
		return '厂家营业执照';
	else if(type=='9')
		return '厂家生产许可证';	

}