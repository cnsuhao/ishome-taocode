$(function() {
	// form表达提交
	$('#submitBtn').click(function(){
//		var hfForm = $("#hfForm").serializeJSON();
//		if (hfForm['bbb'].isEmpty()) {
//			$("#bbbErr").errMsgTip('回复内容不能为空！');
//			return false;
//		}
//		if (hfForm['bbb'].length > 2) {
//			$("#bbbErr").errMsgTip('回复内容过长！');
//			return false;
//		}
		$("#hfForm").submit();
	});
})