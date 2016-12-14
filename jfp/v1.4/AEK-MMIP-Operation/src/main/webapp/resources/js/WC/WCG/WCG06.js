$(function() {
	initZjbh();
	setZczValue(0);
})

/**
 * 初始化右侧注册证证编号
 */
function initZjbh() {
	var html = '';
	for (var i = 0; i < dataList.length; i++) {
		var obj = dataList[i];
		var clazz = '';
		if (obj.eb1 == 1) {
			clazz = 'red';
		}
		html += '<li><a href="#" onclick="setZczValue(' + i + ')" class="' + clazz + '" id="zcz_zjbh_' + i + '">' + obj.f10 + 
		'</a></li>';
	}
	$('#exp-step-nav').html(html);
}

/**
 * 根据下标，填充数据
 * @param index
 */
function setZczValue(index) {
	$('#exp-step-nav a').attr('class', '');
	$('#zcz_zjbh_' + index).attr('class', 'current');
	var obj = dataList[index];
	$('#f10').html(obj.f10);
	$('#f12').html(obj.f12);
	$('#f04_yxksrq').html(obj.f04_yxksrq + '至' + obj.f05_yxzzrq);
	var	imgHtml = '<a href="' + ftpImgPath + '/00003030/' + obj.bbb + sysToken + '" target="_blank">' + 
		'<img src="' + ftpImgPath + '/00003030/' + obj.bbb + sysToken + '" width="590" /></a><br /><br />' + 
		'<a href="' + ftpImgPath + '/00003030/' + obj.f13 + sysToken + '" target="_blank">' + 
		'<img src="' + ftpImgPath + '/00003030/' + obj.f13 + sysToken + '" width="590" /></a>';
	$('#show_images').html(imgHtml);
	
}