$(function() {
	// form表达提交
	$('#yyggSubmit').click(function(){
		
		var yyggForm = $("#yyggForm").serializeJSON();
		if (yyggForm['f01_bt'].isEmpty()) {
			$("#f01_bt").errMsg('公告标题不能为空！');
			return false;
		}
		if (yyggForm['f06_zyd'].isEmpty()) {
			$("#f06_zyd").errMsg('请选择公告重要度！');
			return false;
		}
		if (yyggForm['f05_jjd'].isEmpty()) {
			$("#f05_jjd").errMsg('请选择公告紧急度！');
			return false;
		}
		if (yyggForm['f03_zlsj'].isEmpty()) {
			$("#f03_zlsj").errMsg('终止日期不能为空！');
			return false;
		}
		if (yyggForm['bbb'].isEmpty()) {
			$("#bbbErr").errMsgTip('公告内容不能为空！');
			return false;
		}
		if (yyggForm['bbb'].length > 800) {
			$("#bbbErr").errMsgTip('公告内容过长！');
			return false;
		}
		$("#yyggForm").submit();
	});
})