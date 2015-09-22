$(function() {
	$(".text").input();
	//showJyfwInfos();
	onchangeK1();
	//changeK(1);
	getHospital();
});

/**
 * 搜索事件
 */
function doSearchClick() {
	$('#fb1').val('1');
	showJyfwInfos(1);
}

function allAdd(){
	var html = [], names = [], name = "", hiddenVal = "";
	var data = getSelectData();
	$("#oldList>tr").each(function(i){
		name = $(this).find("td:eq(0)").text().replace(/\[.*\]/igm,"");
		hiddenVal = $(this).find("td:eq(0) input").val();
		names.push(name);
		if(data.indexOf(hiddenVal) <= -1)
			html.push("<tr><td>"+name+"<input type='hidden' value='" + hiddenVal + "'></td>\n<td><a href=\"javascript:void(0);\" onclick=\"del(this);\">删除</a></td>\n</tr>");
		else
			alert("【"+names.join("|")+"】已存在，无需重复添加。");
	});
	$("#newList").append(html.join(""));
	resetTCss();
}

function resetTCss(){
	$("#newList>tr").removeAttr("class").each(function(i){
		if(i%2 == 1) $(this).attr("class","even");
	});
}

function getSelectData(){
	var list = [], hiddenVal = "";
	$("#newList>tr").each(function(){
		hiddenVal = $(this).find("td:eq(0) input").val();
		list.push(hiddenVal);
	});
	return list;
}

function clearList(){
	$("#newList").html("");
}

/**
 * 更多按钮事件
 */
function doMoreClick(v) {
	var num = parseInt($('#fb1').val())+parseInt(v);
	$('#fb1').val(num < 1 ? 1:num);
	showJyfwInfos();
}

function changeK(){
	showJyfwInfos(1);
}

function onchangeK1() {
	var dflId = $('#k01_dflid').val();
	showSecondSelect(dflId);
}

/**
 * 展示中分类名称
 * @param id
 */
function showSecondSelect(id) {
	var html = [];
	$.ajax({
		type: 'POST',
		url: '/31402096/' + id,
		success: function(data) {
			$.each(data.result,function(i, item){
				html.push('<option value="' + item.k02_zflid + '">['+ item.n03_xflmc+'类]' + item.n02_zflmc + '</option>');
			});
			$("#k02_zflid").html(html.join(""));
			changeK();
			$(".select").uiSelect();
		}
	});
}

/**
 * 展示全部经营项目列表
 */
function showJyfwInfos(page) {
	var html = [];
	if(page != undefined) $("#fb1").val(page);
	$.ajax({
		type: 'POST',
		url: '/31402099',
		data: $("#jyfwForm").serialize(),
		success: function(data) {
			$.each(data.result.list,function(i, item){
				html.push("<tr"+(i%2==1?" class=\"even\"":"")+">\n\
						<td>"+item.f01_zdmc+"<input type='hidden' value='" + item.puk + "'></td>\n\
						<td><a href=\"javascript:void(0);\" onclick=\"add(this);\">添加</a></td>\n\
					</tr>");
			});
			$("#oldList").html(html.join(""));
			if(data.result.pageCurrent <= 1)
				$('#prev').attr("href", "javascript:void(0);");
			else
				$('#prev').attr("href", "javascript:doMoreClick('-1');");
			
			if (data.result.pageCount <= data.result.pageCurrent)
				$('#next').attr("href", "javascript:void(0);");
			else
				$('#next').attr("href", "javascript:doMoreClick('+1');");
		}
	});
}

/**
 * 将已选择区域的记录添加到父页面
 */
function selectHospital(){
	var name = [];
	var jyfwId = [];
	$("#newList>tr").each(function(){
		name.push($(this).find("td:eq(0)").text().replace(/\[.*\]/igm,"").trim());
		//name.push($(this).find("td:eq(0)").text().trim());
		var hiddenVal = $(this).find("td:eq(0) input").val().trim();
		var valArray = hiddenVal.split(';');
		jyfwId.push(valArray[0]);
	});
	window.parent.$("#scopeMemo").val(name.join(";"));
	window.parent.$("#jyfwHidden").val(jyfwId.join(";"));
	window.parent.hideWindow();
}

/**
 * 复制父页面已经选择的记录到弹窗的已选择区域
 */
function getHospital(){
	var name = window.parent.$("#scopeMemo").val(),
		names = [], ids = [], 
		html = [];
	var id = window.parent.$("#jyfwHidden").val();
	if(id != ''){
		ids = id.split(";");
	}
	if(name != ''){
		names = name.split(";");
		$.each(names,function(i, item){
			html.push("<tr"+(i%2==1?" class=\"even\"":"")+">\n\
				<td>"+names[i]+"<input type='hidden' value='" + ids[i] + "'></td>\n\
				<td><a href=\"javascript:void(0);\" onclick=\"del(this);\">删除</a></td>\n\
			</tr>");
		});
		$("#newList").html(html.join(""));
	}
}

/**
 * 添加分类按钮
 * @param type 1：大分类，2：中分类
 */
function addFl(type) {
	var obj = null;
	if (type == 1) {
		obj = $('#k01_dflid');
	} else {
		obj = $('#k02_zflid');
	}
	var value = obj.val();
	var text = obj.find("option:selected").text().replace(/\[.*\]/igm,"").trim();
	var isSelect = false;
	$("#newList>tr").each(function(){
		if (text == $(this).find("td:eq(0)").text()) {
			isSelect = true;
		}
	});
	if (!isSelect) {
		$("#newList").append("<tr><td>"+text+"<input type='hidden' value='" + value + "'></td>\n<td><a href=\"javascript:void(0);\" onclick=\"del(this);\">删除</a></td>\n</tr>");
	} else {
		alert('已存在，无需重复添加。');
	}
}

function add(obj){
	var $this = $(obj).parent().parent();
	name = $this.find("td:eq(0)").text().replace(/\[.*\]/igm,"").trim();
	//name = $this.find("td:eq(0)").text().trim();
	var hiddenVal = $this.find("td:eq(0) input").val();
	var isSelect = false;
	$("#newList>tr").each(function(){
		if (name == $(this).find("td:eq(0)").text().trim()) {
			isSelect = true;
		}
	});
	if (!isSelect) {
		$("#newList").append("<tr><td>"+name+"<input type='hidden' value='" + hiddenVal + "'></td>\n<td><a href=\"javascript:void(0);\" onclick=\"del(this);\">删除</a></td>\n</tr>");
	} else {
		alert('已存在，无需重复添加。');
	}
}

function del(obj){
	$(obj).parent().parent().remove();
	resetTCss();
}