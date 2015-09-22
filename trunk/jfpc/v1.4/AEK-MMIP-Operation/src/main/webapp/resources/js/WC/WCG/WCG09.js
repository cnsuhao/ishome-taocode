
/**
 * 初始化右侧注册证证编号
 */
function initZjbh() {
	var html = '';
	for (var i = 0; i < dataList.length; i++) {
		var obj = dataList[i];
		var clazz = '';
		if (obj.eb1 == '1') {
			clazz = 'red';
		}
		html += '<li><a href="#" onclick="setZczValue(' + i + ')" class="' + clazz + '" id="zcz_zjbh_' + i + '">' + obj.fb2 + 
		'</a></li>';
	}
	$('#exp-step-nav').html(html);
}

/**
 * 根据下标，填充数据
 * @param index
 */
function setZczValue(index, sqsid) {
	$('#exp-step-nav a').removeClass('current');
	if (sqsid != 'null') {
		for (var i = 0; i < dataList.length; i++) {
			var tmp = dataList[i];
			if (sqsid == tmp.puk) {
				index = i;
				break;
			}
		}
		sqsid = 'null';
	}
	$('#zcz_zjbh_' + index).addClass('current');
	var obj = dataList[index];
	$('#fb2').html(obj.fb2);
	$('#k02_zjbh').html(obj.k02_zjbh);
	$('#f04_yxksrq').html(obj.f04_yxksrq + '至' + obj.f05_yxzzrq);
	$('#f02_fzrq').html(obj.f02_fzrq);
	var	imgHtml = '<a href="' + ftpImgPath + '/00003030/' + obj.bbb + sysToken + '" target="_blank">' + 
		'<img src="' + ftpImgPath + '/00003030/' + obj.bbb + sysToken + '" width="590" /></a><br /><br />';
	$('#show_images').html(imgHtml);
	
}