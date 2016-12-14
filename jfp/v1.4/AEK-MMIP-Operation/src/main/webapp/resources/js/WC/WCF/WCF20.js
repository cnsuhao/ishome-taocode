/**
 * 点击下一步按钮
 */
function goNext(type) {
	// 如果供应商ID为空，则跳转到预注册页面
	if ($('#gysId').val() == '') {
		$('#f02_qyqc').val($('#gysName').val());
		if ($('#f02_qyqc').val() == '') {
			alert('请输入厂家名称！');
			return false;
		}
	} else {// 否则跳转到证件推送页面
		
		if (type == 2) {
			$('#yzcForm').attr('action', '/314030732/' + $('#gysId').val());
		} else {
			$('#yzcForm').attr('action', '/314030615/' + $('#gysId').val());
		}
	}
	$('#yzcForm').submit();
}

function queryDetail() {
	// 如果供应商ID为空，则跳转到预注册页面
	if ($('#gysId').val() == '') {
		$('#f02_qyqc').val($('#gysName').val());
		if ($('#f02_qyqc').val() == '') {
			alert('请输入厂家名称！');
			return false;
		}
	} else {// 否则跳转到证件推送页面
		$('#yzcForm').attr('action', '/314030741/' + $('#gysId').val());
	}
	$('#yzcForm').submit();
}