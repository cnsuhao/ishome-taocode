$(function() {
	$('.mTop20 a').click(function () {
		//$('.mTop20 a').removeClass('current');
		//$(this).attr('class', 'current');
		$('#fb1').val($(this).html().toUpperCase());
		$('#listSearchForm').submit();
	});
})
/**
 * 判断是否弹窗
 * @param zsmc
 * @param url
 * @param num
 */
function isCreateWindow(zsmc, url, num) {
	if (num == '0' || num == '') {
		alert('没有证书！');
	} else {
		certShow(zsmc, url);
	}
}

function getCheckVal(){
	var list = [];
	$("input[name='checkbox']:checked").each(function(){
		list.push($(this).val());
	});
	return list.join(";");
}
/**
 * 批量删除
 */
function deleteAll() {
	if($("input[name='checkbox']:checked").size()<1){
		alert("请选择一条以上信息记录。");
		return false;
	}
	var data = getCheckVal();
	$('#ids').val(data);
	$("#batchDeleteForm").submit();
}

/**
 * 批量发送短信
 */
function sendMsgAll() {
	if($("input[name='checkbox']:checked").size()<1){
		alert("请选择一条以上信息记录。");
		return false;
	}
	var data = getCheckVal();
	$.ajax({
		type: 'POST',
		url: '/314030734',
		data: {'gysids' : data},
		success: function(msg) {
			var isIn = msg.result;
			if (isIn == 0) {
				alert('发送成功！');
			} else {
				alert('发送失败！');
			}
		}
	});
}

function sendMsg(msgtype) {
	$.ajax({
		type: 'POST',
		url: '/314030736/' + msgtype,
		success: function(msg) {
			var isIn = msg.result;
			if (isIn == 0) {
				alert('发送成功！', 1);
			} else {
				alert('发送失败！');
			}
		}
	});
}
/**
 * 时间排序：1， 名字排序：2
 */
function sortList(sortType) {
	$('#fb4').val(sortType);
	$('#listSearchForm').submit();
}
