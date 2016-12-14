$(function() {
	var imgIndex = 0;
	$('#submitBtn').click(function(){
		$("#zczSearchForm").submit();
	});
	
	$('#normal').click(function (){
		$('#fb2').val('');
		$('#normal').attr('class', 'current');
		$('#overdue').attr('class', '');
		$("#zczSearchForm").submit();
	});
	
	$('#overdue').click(function (){
		$('#fb2').val('2');
		$('#overdue').attr('class', 'current');
		$('#normal').attr('class', '');
		$("#zczSearchForm").submit();
	});
	
});
function getCheckVal(){
	var list = [];
	$("input[name='checkbox']:checked").each(function(){
		list.push($(this).val());
	});
	return list.join(";");
}

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
				alert('发送成功！');
			} else {
				alert('发送失败！');
			}
		}
	});
}
